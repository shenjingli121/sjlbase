package org.sjl.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.sjl.base.entity.RoleEntity;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {


    @Select("select * from sys_role left join sys_user_role on sys_role.id = sys_user_role.role_id where sys_user_role.id = #{id}")
    List<RoleEntity> getRoleByUserId(Long id);
}
