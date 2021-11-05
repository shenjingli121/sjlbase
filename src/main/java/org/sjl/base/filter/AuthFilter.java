package org.sjl.base.filter;

import org.apache.commons.lang3.StringUtils;
import org.sjl.base.entity.HttpEntity;
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

        String requestURI = request.getRequestURI();
        // 为获取到权限
        if (!tokenManager.verifyToken(request.getHeader(AUTHORIZATION))) {
            HttpEntityUtil.setResponse(response, "身份认证失败，请重新登录", HttpEntityUtil.NO_PERMISSION);
        }
        UsernamePasswordAuthenticationToken authentication = null;
        try {
            authentication = getAuthentication(request);
        } catch (Exception e) {
            HttpEntityUtil.setResponse(response, "身份认证失败，请重新登录", HttpEntityUtil.NO_PERMISSION);
        }
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            HttpEntityUtil.setResponse(response, "身份认证失败，请重新登录", HttpEntityUtil.NO_PERMISSION);
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        // 获取Token字符串，token 置于 header 里
        String token = request.getHeader("Authorization");
        if (StringUtils.isBlank(token)) {
            return null;
        }
        Payload payload = JwtUtils.getPayload(token);
        List<Long> role = payload.getRole();

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Long s : role) {
            authorities.add(new SimpleGrantedAuthority(s.toString()));
        }
        return new UsernamePasswordAuthenticationToken(payload.getUsername(), token, authorities);

    }

}
