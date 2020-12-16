package org.mephi.timofeev.lab3.config;

import lombok.RequiredArgsConstructor;
import org.mephi.timofeev.lab3.properties.TwitterAuthProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@Configuration
@EnableConfigurationProperties(value = TwitterAuthProperties.class)
@RequiredArgsConstructor
public class TwitterAppConfig {
    private final TwitterAuthProperties twitterAuthProperties;

    @Bean
    TwitterTemplate twitterTemplate(){
        return new TwitterTemplate(twitterAuthProperties.getConsumerKey(), twitterAuthProperties.getConsumerSecret(),
                twitterAuthProperties.getAccessToken(), twitterAuthProperties.getAccessTokenSecret());
    }
}
