package com.linoer.app.test.controller;

import com.linoer.app.test.error.EntityNotFoundException;
import com.linoer.app.test.model.User;
import com.linoer.app.test.model.UserRepository;
import com.linoer.app.test.model.UserVO;
import com.linoer.app.test.utils.CommonReturnType;
import com.linoer.app.test.utils.NormalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class UserApiController {
    private UserRepository userRepository;

    @Autowired
    public UserApiController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @RequestMapping(value = "/user/{email}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable String email, @RequestBody User user){
        return userRepository.save(email, user);
    }

    @RequestMapping(value = "/user/{email}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable String email){
        userRepository.delete(email);
    }

    @RequestMapping(value = "/user_v2/{email}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUserNew(@PathVariable String email, @RequestBody User user) throws EntityNotFoundException {
        User saved = userRepository.update(email, user);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user_v2/{email}", method = RequestMethod.GET)
    public CommonReturnType getUser(@PathVariable String email){
        User user = userRepository.findOne(email);
        UserVO userVO = (UserVO) NormalUtil.convertFromModel(user);
        return CommonReturnType.create(userVO);
    }
}
