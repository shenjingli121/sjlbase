package org.sjl.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.sjl.base.entity.SysUserEntity;

import java.util.Map;

public interface SysUserService extends IService<SysUserEntity> {


    SysUserEntity getUserByUsernameToken(String username);
}
