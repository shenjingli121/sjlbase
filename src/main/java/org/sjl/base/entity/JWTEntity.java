package org.sjl.base.entity;

import lombok.Data;

@Data
public class JWTEntity {

    private JWTHeader jwtHeader;
    private Payload payload;
    private String signature;
}
