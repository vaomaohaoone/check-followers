package org.mephi.timofeev.lab3.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Настройки twitter аккаунта разработчика
 */
@ConfigurationProperties(prefix = "twitter.auth")
@Data
public class TwitterAuthProperties {
    /**
     * Ключ
     */
    private String consumerKey;
    /**
     * Секрет
     */
    private String consumerSecret;
    /**
     * access токен
     */
    private String accessToken;
    /**
     * Секрет access токена
     */
    private String accessTokenSecret;
}
