package com.linoer.app.test.config;


import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;

/**
 * 使用缓存
 */

//@Configuration
//@EnableCaching
public class CacheConfiguration {

    /**
     *
      * @return
     */
//    @Bean
    public CacheManager cacheManager(){
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("search")));
        return simpleCacheManager;
    }
}
