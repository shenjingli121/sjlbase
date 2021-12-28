package org.sjl.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.sjl.base.entity.SysUserEntity;
import org.sjl.base.mapper.SysUserMapper;
import org.sjl.base.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {

    @Override
    public SysUserMapper getBaseMapper() {
        return super.getBaseMapper();
    }

    /**
     * 生成token时获取用户信息
     *
     * @param username
     * @return
     */
    @Override
    public SysUserEntity getUserByUsernameToken(String username) {
        return getBaseMapper().getUserByUsernameToken(username);
    }
}
