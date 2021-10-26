package org.sjl.base.controller.login;

import org.sjl.base.entity.HttpEntity;

import org.sjl.base.entity.SysUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("login")
public class LoginController {


    @PostMapping
    public HttpEntity<SysUser> login(@RequestBody SysUser sysUser, HttpServletResponse response) {

        HttpEntity<SysUser> httpEntity = new HttpEntity<>();
        return httpEntity;
    }
}
