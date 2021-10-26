package org.sjl.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.sjl.base.entity.RoleEntity;
import org.sjl.base.entity.SysUser;
import org.sjl.base.mapper.RoleMapper;
import org.sjl.base.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RoleMapper roleMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username);
        SysUser sysUser = sysUserMapper.selectOne(wrapper);
        List<RoleEntity> roleList = roleMapper.getRoleByUserId(sysUser.getId());
        String collect = roleList.stream().map(o -> o.getId().toString()).collect(Collectors.joining(","));
        List<GrantedAuthority> role = AuthorityUtils.commaSeparatedStringToAuthorityList(collect);

        return new User(sysUser.getUserName(), passwordEncoder.encode(sysUser.getPassword()), role);
    }
}

