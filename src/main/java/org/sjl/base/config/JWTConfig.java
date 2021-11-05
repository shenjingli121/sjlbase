package org.sjl.base.config;

import org.apache.commons.lang3.StringUtils;
import org.sjl.base.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class JWTConfig implements ApplicationRunner {

    @Value("${token.timeout}")
    private String timeout;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (StringUtils.isNoneBlank(timeout)) {
            JwtUtils.TIMEOUT = Long.valueOf(timeout);
        }
    }
}
