package com.linoer.app.dao;

import com.linoer.app.pojo.MyTest;

/**
 *持久层的访问接口，mapper
 */
public interface TestMapper {
    MyTest selectByPrimaryKey(Integer id);
}
