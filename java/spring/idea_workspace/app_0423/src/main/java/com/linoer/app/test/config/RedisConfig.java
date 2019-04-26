package com.linoer.app.test.config;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.time.Duration;

@Configuration
@Profile("redis")
@EnableRedisHttpSession
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

//    @Value("${spring.redis.password}")
//    private String password;

    /**
     * RedisTemplate配置
     * @param redisConnectionFactory
     * @return
     */
    @Bean(name = "objectRedisTemplate")
    public RedisTemplate<?, ?> objectRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // 使用jackson序列化和反序列化redis的value
        Jackson2JsonRedisSerializer<JSON> serializer = new Jackson2JsonRedisSerializer<JSON>(JSON.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);

        //使用StringRedisSerializer序列化和反序列化redis的key
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory){
        // 生成一个默认配置，通过config对象对缓存进行自定义配置
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();

        Jackson2JsonRedisSerializer<JSON> serializer = new Jackson2JsonRedisSerializer<JSON>(JSON.class);

        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer));
        configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer));

        configuration.entryTtl(Duration.ofSeconds(60));
        // 不缓存空值
        configuration.disableCachingNullValues();
        return RedisCacheManager.builder(factory).cacheDefaults(configuration).build();
    }
}
