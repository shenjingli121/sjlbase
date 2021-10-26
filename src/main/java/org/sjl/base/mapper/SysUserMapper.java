package org.sjl.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.sjl.base.entity.SysUser;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
