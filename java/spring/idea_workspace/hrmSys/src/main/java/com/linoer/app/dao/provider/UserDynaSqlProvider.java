package com.linoer.app.dao.provider;

import com.linoer.app.common.Constants;
import com.linoer.app.pojo.User;
import com.linoer.app.utils.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class UserDynaSqlProvider {

    /**
     * 分页查询
     * @param param
     * @return
     */
    public String selectWhitParam(Map<String, Object> param){
        String sql = new SQL(){
            {
                SELECT("*");
                FROM(Constants.USERTABLE);
                if(param.get("user") != null){
                    User user = (User) param.get("user");
                    if(StringUtils.isNotNullAndNotEmpty(user.getUsername())){
                        WHERE(" username like concat ('%', #{user.username}), '%')");
                    }
                    if(StringUtils.isNotNullAndNotEmpty(user.getStatus())){
                        WHERE(" status like concat ('%', #{user.status}), '%')");
                    }
                }
            }
        }.toString();
        if(param.get("pageModel") != null){
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }

    /**
     * 查询总数
     * @param param
     * @return
     */
    public String count(Map<String, Object> param){
        return new SQL(){
            {
                SELECT("count(*)");
                FROM(Constants.USERTABLE);
                if(param.get("user") != null){
                    User user = (User) param.get("user");
                    if(StringUtils.isNotNullAndNotEmpty(user.getUsername())){
                        WHERE(" username like concat ('%', #{user.username}), '%')");
                    }
                    if(StringUtils.isNotNullAndNotEmpty(user.getStatus())){
                        WHERE(" status like concat ('%', #{user.status}), '%')");
                    }
                }
            }
        }.toString();
    }

    /**
     * 插入用户信息
     * @param user
     * @return
     */
    public String insertUser(User user){
        if (user == null){
            return "";
        }
        return new SQL(){
            {
                INSERT_INTO(Constants.USERTABLE);
                if(StringUtils.isNotNullAndNotEmpty(user.getUsername())){
                    VALUES("username", "#{username}");
                }
                if(StringUtils.isNotNullAndNotEmpty(user.getStatus())){
                    VALUES("status", "#{status}");
                }
                if(StringUtils.isNotNullAndNotEmpty(user.getLoginname())){
                    VALUES("loginname", "#{loginname}");
                }
                if(StringUtils.isNotNullAndNotEmpty(user.getPassword())){
                    VALUES("password", "#{password}");
                }
            }
        }.toString();
    }

    public String updateUser(User user){
        return new SQL(){
            {
                UPDATE(Constants.USERTABLE);
                if(user.getUsername()!=null){
                    SET(" username = #{username} ");
                }
                if(user.getUsername()!=null){
                    SET(" loginname = #{loginname} ");
                }
                if(user.getPassword()!=null){
                    SET(" password = #{password} ");
                }
                if(user.getStatus()!=null){
                    SET(" status = #{status} ");
                }
                if(user.getCreateDate()!=null){
                    SET(" create_date = #{createDate} ");
                }
                WHERE(" id = #{id}");
            }
        }.toString();
    }
}
