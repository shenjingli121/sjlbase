package org.sjl.base.entity;

import lombok.Data;

@Data
public class JWTHeader {
    private String alg;
    private String typ;
}
