package com.linoer.app.test.service;

import com.alibaba.fastjson.JSONObject;
import com.linoer.app.test.model.User;
import com.linoer.app.test.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * @Cacheable 应用到获取数据的方法表示先从缓存读取，没有就写进DB
     * unless 表示条件成立的话不放入缓存
     * @param name
     * @return
     */
    @Cacheable(value = "user", key = "#root.targetClass + #name", unless = "#result eq null")
    public User getUserByName(String name){
        return userRepository.findOneByName(name);
    }


}
