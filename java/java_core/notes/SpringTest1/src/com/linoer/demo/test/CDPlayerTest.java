package com.linoer.demo.test;

import com.linoer.demo.ScanTest;
import com.linoer.demo.config.CDPlayerConfig;
import com.linoer.demo.interfaces.CompactDisc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= ScanTest.class)
public class CDPlayerTest {
    /*
    SpringJUnit4ClassRunner: 自动创建Spring的应用上下文
    CDPlayerConfig: 需要在这里加载配置,这里的ScanTest类只有ComponentScan配置

    * */
    @Autowired
    private CompactDisc cd;

    @Test
    public void cdShouldNotBeNull(){
        assertNotNull(cd);
        cd.play();
    }
}
