package org.sjl.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.sjl.base.entity.SysUser;

import java.util.Map;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {


    @Select("SELECT id,name,user_name ,phone FROM sys_user WHERE user_name = #{user_name} and delete_status = 0")
    SysUser getUserByUsernameToken(String username);
}
