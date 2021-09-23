package org.sjl.base.entity;

import lombok.Data;

import java.security.PrivateKey;
import java.security.PublicKey;

@Data
public class RsaKey {
    private PublicKey publicKey;
    private PrivateKey privateKey;

}
