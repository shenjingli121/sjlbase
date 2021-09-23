package org.sjl.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Robod
 * @date 2020/8/9 17:29
 */
@Data
public class SysTokenUser   {

    private Integer id;
    private String username;
    private String password;
    private Integer status;




}
