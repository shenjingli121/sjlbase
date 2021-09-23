package org.sjl.base.config;


import lombok.Data;
import org.sjl.base.entity.RsaKey;
import org.sjl.base.utils.RsaUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RsaKeyConfig {

    @Value("${rsa.sjl.key}")
    private String key;
    @Value("${rsa.sjl.size}")
    private String size;

    @Bean
    public RsaKey getRsaKey() throws Exception {
        return RsaUtils.generateKey(key, Integer.parseInt(size));
    }
}
