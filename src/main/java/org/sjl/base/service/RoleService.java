package org.sjl.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.sjl.base.entity.RoleEntity;

import javax.management.relation.Role;
import java.util.List;

public interface RoleService extends IService<RoleEntity> {

    List<RoleEntity> getRoleListByUserId(Long id);
}
