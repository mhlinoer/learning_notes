package com.linoer.dbsharding.controller;

import com.linoer.dbsharding.entity.UserEntity;
import com.linoer.dbsharding.enums.UserSexEnum;
import com.linoer.dbsharding.service.User1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
@RestController
public class UserController {

    @Autowired
    private User1Service user1Service;

    @RequestMapping("/getUsers")
    public List<UserEntity> getUsers(){
        List<UserEntity> users = user1Service.getUsers();
        return users;
    }
    //测试
    @RequestMapping(value="/insert")
    public String insertUser(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "user_id") Long user_id,
            @RequestParam(value = "order_id") Long order_id,
            @RequestParam(value = "nickName") String nickName,
            @RequestParam(value = "passWord") String passWord,
            @RequestParam(value = "userName") String userName
    ){
        UserEntity user = new UserEntity();
        user.setId(id);
        user.setOrder_id(order_id);
        user.setPassWord(passWord);
        user.setNickName(nickName);
        user.setUser_id(user_id);
        user.setUserName(userName);
        user.setUserSex("man");
        user1Service.insert(user);
        return "test";
    }

}
