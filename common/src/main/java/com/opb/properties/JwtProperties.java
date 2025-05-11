package com.opb.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "opb.jwt")
@Data
public class JwtProperties {
    //用户jwt相关配置
    private String userSecretKey;
    private String userTokenName;
    private long userTtl;

    //管理员jwt相关配置
    private String manageSecretKey;
    private String manageTokenName;
    private long manageTtl;
}
