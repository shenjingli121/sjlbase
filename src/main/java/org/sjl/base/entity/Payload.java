package org.sjl.base.entity;

import lombok.Data;

import java.util.List;

@Data
public class Payload {

    private Long userId;
    private String username;
    private String phone;
    private List<Long> role;
}
