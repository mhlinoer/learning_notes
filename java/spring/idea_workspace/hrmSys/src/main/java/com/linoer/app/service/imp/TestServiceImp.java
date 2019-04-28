package com.linoer.app.service.imp;

import com.linoer.app.dao.TestMapper;
import com.linoer.app.pojo.MyTest;
import com.linoer.app.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 服务接口的实现
 */
@Service("testService")
public class TestServiceImp implements TestService {

    @Resource
    private TestMapper mapper = null;

    @Override
    public MyTest getById(int id) {
        return mapper.selectByPrimaryKey(id);
    }
}
