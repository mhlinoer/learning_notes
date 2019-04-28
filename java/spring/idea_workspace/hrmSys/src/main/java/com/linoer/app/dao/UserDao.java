package com.linoer.app.dao;


import com.linoer.app.pojo.UserNew;

public interface UserDao {

    UserNew selectUser(long id);

}