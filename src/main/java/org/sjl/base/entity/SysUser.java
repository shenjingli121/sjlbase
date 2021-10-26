package org.sjl.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@TableName("sys_user")
public class SysUser extends BaseEntity {

  private String name;
  private String userName;
  private String password;
  private String phone;
}
