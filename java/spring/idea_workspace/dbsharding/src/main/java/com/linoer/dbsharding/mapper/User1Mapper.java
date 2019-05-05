package com.linoer.dbsharding.mapper;

import com.linoer.dbsharding.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kane on 2018/1/17.
 */
@Service
public interface User1Mapper {

    List<UserEntity> getAll();

    void update(UserEntity user);
    void insert(UserEntity user);
}