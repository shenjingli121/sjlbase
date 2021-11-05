package org.sjl.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.sjl.base.entity.SysUser;

import java.util.Map;

public interface SysUserService extends IService<SysUser> {


    SysUser getUserByUsernameToken(String username);
}
