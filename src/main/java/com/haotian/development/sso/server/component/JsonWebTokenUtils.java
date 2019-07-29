package com.haotian.development.sso.server.component;


import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * @Description: jwt生成校验工具
 * @Author: ZhangPeng
 * @Email zhangpeng@hiynn.com
 * @Date: 2019/4/23 10:00
 */
@Component
@PropertySource("classpath:conf/jwt.properties")
@ConfigurationProperties(prefix = "jwt.config")
public class JsonWebTokenUtils {

    private String tokenVersion;
    private String issuer;
    private String subject;
    private long tokenExpiration;
    private String privateKey;
    private Key signingKey;

    public String getTokenVersion() {
        return tokenVersion;
    }

    public void setTokenVersion(String tokenVersion) {
        this.tokenVersion = tokenVersion;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public long getTokenExpiration() {
        return tokenExpiration;
    }

    public void setTokenExpiration(long tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    private Key getSigningKey() {
        if (this.signingKey != null) {
            return this.signingKey;
        }
        this.signingKey = new SecretKeySpec(Base64.decodeBase64(privateKey), SignatureAlgorithm.HS512.getJcaName());
        return this.signingKey;
    }


    public String getJWTString(String userId, Map<String, Object> claims) {

        long nowMillis = System.currentTimeMillis();
        claims.put(Claims.ID, this.tokenVersion);
        claims.put(Claims.ISSUER, this.issuer);
        claims.put(Claims.SUBJECT, this.subject);
        if (userId != null) {
            claims.put(Claims.AUDIENCE, userId);
        }
        claims.put(Claims.EXPIRATION, new Date(nowMillis + tokenExpiration));
        claims.put(Claims.ISSUED_AT, new Date(nowMillis));
        JwtBuilder jwtBuilder = Jwts.builder().setClaims(claims);
        jwtBuilder.signWith(SignatureAlgorithm.HS512, getSigningKey());
        return jwtBuilder.compact();
    }

    public boolean isValid(String token) {
        try {
            Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token.trim());
            Long exp = (Long) jwsClaims.getBody().get(Claims.EXPIRATION);
            return exp - System.currentTimeMillis() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public Map<String, Object> parseJWTtoMap(String token) {
        return Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token.trim()).getBody();
    }

    public String getHS512Key() {
        Key key = MacProvider.generateKey(SignatureAlgorithm.HS512);
        return Base64.encodeBase64String(key.getEncoded());
    }


}
