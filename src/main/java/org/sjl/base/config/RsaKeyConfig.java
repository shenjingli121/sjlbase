package org.sjl.base.config;


import lombok.Data;
import org.sjl.base.entity.RsaKey;
import org.sjl.base.utils.RsaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class RsaKeyConfig implements ApplicationRunner {

    @Value("${rsa.sjl.key}")
    private String key;
    @Value("${rsa.sjl.size}")
    private String size;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        RsaKey rsaKey = RsaUtils.generateKey(key, Integer.parseInt(size));
        RsaUtils.PRIVATE_KEY = rsaKey.getPrivateKey();
        RsaUtils.PUBLIC_KEY = rsaKey.getPublicKey();
    }
}
