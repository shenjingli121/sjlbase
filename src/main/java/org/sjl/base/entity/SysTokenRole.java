package org.sjl.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Robod
 * @date 2020/8/9 17:30
 */
@Data
public class SysTokenRole   {

    private Integer id;
    private String roleName;
    private String roleDesc;


}
