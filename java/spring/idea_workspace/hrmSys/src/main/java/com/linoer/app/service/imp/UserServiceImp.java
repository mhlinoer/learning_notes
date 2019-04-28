package com.linoer.app.service.imp;

import com.linoer.app.dao.UserDao;
import com.linoer.app.pojo.UserNew;
import com.linoer.app.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImp implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public UserNew selectUser(long userId) {
        return this.userDao.selectUser(userId);
    }

}