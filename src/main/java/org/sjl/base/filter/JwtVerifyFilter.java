package org.sjl.base.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.sjl.base.entity.RsaKey;
import org.sjl.base.entity.SysTokenUser;
import org.sjl.base.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Robod
 * @date 2020/8/10 8:42
 * 检验token过滤器
 */
public class JwtVerifyFilter extends BasicAuthenticationFilter {
    private static final String login = "/login";
    private RsaKey rsaKey;

    public JwtVerifyFilter(AuthenticationManager authenticationManager, RsaKey rsaKey) {
        super(authenticationManager);
        this.rsaKey = rsaKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        if (requestURI.contains(login)) {
//            chain.doFilter(request, response);
            return;
        }
        String token = request.getHeader("Authorization");
        //没有登录
        if (token == null) {
            chain.doFilter(request, response);
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            PrintWriter out = response.getWriter();
            Map<String, Object> map = new HashMap<String, Object>(4);
            map.put("code", HttpServletResponse.SC_FORBIDDEN);
            map.put("message", "请登录！");
            out.write(new ObjectMapper().writeValueAsString(map));
            out.flush();
            out.close();
            return;
        }
        //登录之后从token中获取用户信息
//        SysTokenUser sysUser = JwtUtils.getInfoFromToken(token, rsaKey.getPrivateKey(), SysTokenUser.class).getUserInfo();
//        if (sysUser != null) {
//            Authentication authResult = new UsernamePasswordAuthenticationToken
//                    (sysUser.getUsername(),null,sysUser.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authResult);
//            chain.doFilter(request, response);
//        }
    }
}
