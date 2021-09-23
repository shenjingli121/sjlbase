package org.sjl.base.config;

import org.sjl.base.entity.RsaKey;
import org.sjl.base.filter.JwtVerifyFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.annotation.Resource;

/**
 * @author Robod
 * @date 2020/8/9 15:47
 */
@Configuration
@EnableWebSecurity      //加了这个注解才能写SpringSecurity相关的配置
@EnableGlobalMethodSecurity(securedEnabled = true)  //开启权限控制的注解支持,securedEnabled表示SpringSecurity内部的权限控制注解开关
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private RsaKey rsaKey;

//    public WebSecurityConfig(RsaKeyProperties rsaKeyProperties) {
//        this.rsaKeyProperties = rsaKeyProperties;
//    }

    /**
     * 配置SpringSecurity相关信息
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
//                .antMatchers("/login", "/logout").permitAll()
                .antMatchers("/api/**").authenticated()
                .anyRequest()
                .authenticated();
//                .and()
//                .addFilter(new JwtVerifyFilter(super.authenticationManager(),rsaKey))
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
