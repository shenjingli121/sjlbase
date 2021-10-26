package org.sjl.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@TableName("sys_role")
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class RoleEntity extends BaseEntity {

    private String roleName;
}
