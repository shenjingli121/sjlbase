package org.sjl.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.sjl.base.entity.SysUser;
import org.sjl.base.mapper.SysUserMapper;
import org.sjl.base.service.SysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
}
