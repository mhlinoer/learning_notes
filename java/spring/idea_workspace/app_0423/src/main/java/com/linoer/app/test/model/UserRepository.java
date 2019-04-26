package com.linoer.app.test.model;

import com.linoer.app.test.error.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private static Map<String, User> userMap = new ConcurrentHashMap<>();

    static{
        for (int i = 0; i < 10; i++) {
            User u = new User();
            u.setName("robot0" + i);
            userMap.put("user" + i, u);
        }
    }

    public User save(String email, User user){
        user.setName(email);
        return userMap.put(email, user);
    }
    public User save(User user){
        return save(user.getEmail(), user);
    }

    public User findOne(String email){
        return userMap.get(email);
    }

    public User findOneByName(String name){
        return userMap.get(name);
    }

    public List<User> findAll(){
        return new ArrayList<>(userMap.values());
    }

    public void delete(String email){
        userMap.remove(email);
    }
    public boolean exists(String email){
        return userMap.containsKey(email);
    }

    public User update(String email, User user) throws EntityNotFoundException{
        if(!exists(email)){
            throw new EntityNotFoundException("user cannot be found:" + email);
        }
        user.setEmail(email);
        return userMap.get(email);
    }
}
