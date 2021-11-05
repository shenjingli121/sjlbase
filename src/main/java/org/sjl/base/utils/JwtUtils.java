package org.sjl.base.utils;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.sjl.base.entity.JWTEntity;
import org.sjl.base.entity.JWTHeader;
import org.sjl.base.entity.Payload;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;

/**
 *
 */
@Slf4j
public class JwtUtils {
    private static final String ALG = "alg";
    private static final String TYP = "typ";
    private static final String CLAIM_TYPE_ONE = "user";
    public static Long TIMEOUT = 10l;
    /**
     * 生成token
     *
     * @param o
     * @return
     */
    public static String createToken(Object o) {
        if (ObjectUtils.isEmpty(o)) {
            log.error("生成Token时，传过来的载体为空。");
            return "";
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put(ALG, SignatureAlgorithm.RS256);
        map.put(TYP, Header.JWT_TYPE);
        return Jwts.builder()
                .setHeader(map)
                .claim(CLAIM_TYPE_ONE, JSONObject.toJSONString(o))
                .signWith(RsaUtils.PRIVATE_KEY, SignatureAlgorithm.RS256)
                .compact();
    }

    /**
     * 获取token 的header
     */
    public static JWTHeader getTokenHeader(String token) {
        Jws<Claims> jwsClaims = getJwsClaims(token);
        JwsHeader header = jwsClaims.getHeader();
        JWTHeader jwtHeader = new JWTHeader();
        jwtHeader.setAlg(header.getAlgorithm());
        jwtHeader.setTyp(header.getType());
        return jwtHeader;
    }

    /**
     * 获取token 载体 payload
     */
    public static Payload getPayload(String token) {
        Jws<Claims> jwsClaims = getJwsClaims(token);
        Claims body = jwsClaims.getBody();
        return JSONObject.parseObject(body.get(CLAIM_TYPE_ONE).toString(), Payload.class);
    }


    /**
     * 获取token种的签名
     */
    public static String getSignature(String token) {
        Jws<Claims> jwsClaims = getJwsClaims(token);
        return jwsClaims.getSignature();
    }

    /**
     * 验证token签名
     */
    public static JWTEntity parseToken(String token) {
        JWTEntity jwtEntity = new JWTEntity();
        jwtEntity.setJwtHeader(getTokenHeader(token));
        jwtEntity.setPayload(getPayload(token));
        jwtEntity.setSignature(getSignature(token));
        return jwtEntity;
    }


    public static HashMap<String, Object> verifyToken(String token) {
        return null;
    }

    /**
     * 解析token
     */
    private static Jws<Claims> getJwsClaims(String token) {
        return Jwts.parser().setSigningKey(RsaUtils.PUBLIC_KEY).parseClaimsJws(token);
    }
}
