package org.sjl.base.manager;

import org.sjl.base.entity.*;
import org.sjl.base.service.RoleService;
import org.sjl.base.service.SysUserService;
import org.sjl.base.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class TokenManager {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    /**
     * 生成token
     * @param username
     * @return
     */
    public String createToken(String username) {
        // 获取用户信息
        SysUserEntity user = sysUserService.getUserByUsernameToken(username);
        Long id = user.getId();
        // 获取用户角色
        List<RoleEntity> roles = roleService.getRoleListByUserId(id);
        List<String> roleList = roles.stream().map(o -> o.getRoleValue()).collect(Collectors.toList());
        Payload payload = getNewPayload(user, roleList);
        return JwtUtils.createToken(payload);
    }
    /**
     * 将token放入Redis
     */
    public String setTokenToRedis(String token) {
        String tokenId = UUID.randomUUID().toString();
        if (redisTemplate.opsForValue().setIfAbsent(tokenId, token, JwtUtils.TIMEOUT, TimeUnit.MINUTES)) {
            return tokenId;
        }
        return null;
    }

    /**
     * 生成token并存入Redis
     */
    public String createTokenAndToRedis(String username) {
        String token = createToken(username);
        return setTokenToRedis(token);
    }


    /**
     * 验证token
     * @param token
     * @return
     */
    public String verifyToken(String token) {
        JWTEntity jwtEntity = JwtUtils.parseToken(token);
        String username = jwtEntity.getPayload().getUsername();
        String token1 = createToken(username);
        String signature = JwtUtils.getSignature(token1);
        if (signature.equals(jwtEntity.getSignature())) {
            return token1;
        }
        return null;
    }

    /**
     * 生成载体
     */
    private Payload getNewPayload(SysUserEntity user, List<String> roleList) {
        Payload payload = new Payload();
        payload.setPhone(user.getPhone());
        payload.setRole(roleList);
        payload.setUserId(user.getId());
        payload.setUsername(user.getUserName());
        return payload;
    }


    public boolean verifyAuthByRole(String token, String uri) {
        Payload payload = JwtUtils.getPayload(token);

        return false;
    }
}
