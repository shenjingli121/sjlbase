package org.sjl.base.filter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.sjl.base.entity.SysUser;
import org.sjl.base.manager.TokenManager;
import org.sjl.base.utils.HttpEntityUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
 * @date 2020/8/10 7:54
 * 认证过滤器
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private TokenManager tokenManager;

    public LoginFilter(AuthenticationManager authenticationManager, TokenManager tokenManager) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
    }

    /**
     * 这个方法是用来去尝试验证用户的，父类中是从POST请求的form表单中获取，但是这里不是，所以需要重写
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            SysUser sysUser = JSONObject.parseObject(request.getInputStream(), SysUser.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            sysUser.getUserName(),
                            sysUser.getPassword())
            );
        } catch (Exception e) {

            try {
                HttpEntityUtil.setResponse(response, "账号或密码错误！", HttpEntityUtil.ERROR_CODE);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            throw new RuntimeException(e);
        }
    }

    /**
     * 成功之后执行的方法，
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = tokenManager.createToken(authResult.getName());
        if (StringUtils.isBlank(token)) {
            HttpEntityUtil.setResponse(response, "登录失败请稍后再试！", HttpEntityUtil.NO_PERMISSION);
        }
        response.addHeader("Authorization", token);
        //登录成功时，返回json格式进行提示
        HttpEntityUtil.setResponse(response, "登陆成功！", HttpEntityUtil.SUCCESS_CODE);


    }


}
