package com.alessandro.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by alessandro.santos on 1/8/17.
 */


@SpringBootApplication
@EnableRedisRepositories
@EnableWebMvc
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }


    @Bean
    public RedisConnectionFactory redisConnFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setUsePool(true);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory redisConnFactory) {
        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<byte[], byte[]>();
        redisTemplate.setConnectionFactory(redisConnFactory);

        return redisTemplate;
    }


}
