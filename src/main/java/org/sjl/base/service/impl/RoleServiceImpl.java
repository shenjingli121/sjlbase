package org.sjl.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.sjl.base.entity.RoleEntity;
import org.sjl.base.mapper.RoleMapper;
import org.sjl.base.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {
}
