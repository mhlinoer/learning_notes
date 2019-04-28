package com.linoer.app.service;

import com.linoer.app.pojo.UserNew;

public interface UserService {
    UserNew selectUser(long userId);
}