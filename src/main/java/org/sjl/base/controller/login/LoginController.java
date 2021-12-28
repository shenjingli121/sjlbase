package org.sjl.base.controller.login;

import org.sjl.base.entity.HttpEntity;


import org.sjl.base.entity.SysUserEntity;
import org.sjl.base.service.RoleService;
import org.sjl.base.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RoleService roleService;
    @PostMapping
    public HttpEntity<SysUserEntity> login(@RequestBody SysUserEntity sysUser, HttpServletResponse response) {

        HttpEntity<SysUserEntity> httpEntity = new HttpEntity<>();
//        sysUserService.
        return httpEntity;
    }
}
