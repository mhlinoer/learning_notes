package com.linoer.app.dao;


import com.linoer.app.common.Constants;
import com.linoer.app.dao.provider.UserDynaSqlProvider;
import com.linoer.app.pojo.User;
import com.linoer.app.pojo.UserNew;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface UserDao {

    UserNew selectUser(long id);

    /**
     * 根据用户名和密码查询用户
     * @param loginname
     * @param password
     * @return
     */
    @Select("select * from " + Constants.USERTABLE + " where loginname = #{loginname} and password = #{password}")
    User selectByLoginNameAndPassword(@Param("loginname") String loginname, @Param("password") String password);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Select("select * from " + Constants.USERTABLE + " where id = #{id}")
    User selectById(@Param("id") Integer id);

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @Delete("delete from " + Constants.USERTABLE + " where id = #{id}")
    boolean deleteById(@Param("id") Integer id);

    /**
     * 动态修改用户
     * @param user
     */
    @SelectProvider(type = UserDynaSqlProvider.class, method = "updateUser")
    void update(User user);

    @SelectProvider(type = UserDynaSqlProvider.class, method = "selectWhitParam")
    List<User> selectByPage(Map<String, Object> params);

    /**
     * 根据参数查询用户数
     * @param params
     * @return
     */
    @SelectProvider(type = UserDynaSqlProvider.class, method = "count")
    Integer cout(Map<String, Object> params);

    @SelectProvider(type = UserDynaSqlProvider.class, method = "insertUser")
    void save(User user);
}