package com.linoer.app.test.utils;

import com.linoer.app.test.model.User;
import com.linoer.app.test.model.UserVO;

public class NormalUtil {
    public static Object convertFromModel(User user){
        UserVO userVO = new UserVO();
        userVO.setEmail(user.getEmail());
        return userVO;
    }
}
