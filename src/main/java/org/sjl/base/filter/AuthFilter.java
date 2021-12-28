package org.sjl.base.filter;

import org.apache.commons.lang3.StringUtils;
import org.sjl.base.entity.Payload;
import org.sjl.base.manager.TokenManager;
import org.sjl.base.utils.HttpEntityUtil;
import org.sjl.base.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 认证+授权
 */
public class AuthFilter extends BasicAuthenticationFilter {
    private static final String AUTHORIZATION = "Authorization";
    private TokenManager tokenManager;

    public AuthFilter(AuthenticationManager authManager, TokenManager tokenManager) {
        super(authManager);
        this.tokenManager = tokenManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException, IOException, ServletException {
        logger.info("=================" + request.getRequestURI());
        // 校验token
        String token = tokenManager.verifyToken(request.getHeader(AUTHORIZATION));
        // 如果获取到得token为空表示校验失败
        if (StringUtils.isBlank(token)) {
            HttpEntityUtil.setResponse(response, "身份认证失败，请重新登录", HttpEntityUtil.NO_PERMISSION);
        }
        // 设置token到返回头 更新前端的token
        response.setHeader(AUTHORIZATION, token);
        // 根据url判断该用户所属的角色权限是否可以访问该接口
        String requestURI = request.getRequestURI();
        // 校验角色是否有权限访问该接口
        boolean result = tokenManager.verifyAuthByRole(token, requestURI);
        if (result) {
            try {
                // 从新生成的token中获取到角色和用户名
                UsernamePasswordAuthenticationToken authentication = getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                HttpEntityUtil.setResponse(response, "身份认证失败，请重新登录", HttpEntityUtil.NO_PERMISSION);
            }
        } else {
            HttpEntityUtil.setResponse(response, "您无权限访问，请联系管理员", HttpEntityUtil.NO_PERMISSION);

        }

        chain.doFilter(request, response);
    }

    /**
     * 生成校验权限的对象
     *
     * @param token
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {

        Payload payload = JwtUtils.getPayload(token);
        List<String> role = payload.getRole();

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String s : role) {
            authorities.add(new SimpleGrantedAuthority(s));
        }
        return new UsernamePasswordAuthenticationToken(payload.getUsername(), token, authorities);

    }

}
