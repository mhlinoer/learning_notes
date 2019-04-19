package com.linoer.springtest;

import com.linoer.springtest.config.MyWebConfiguration;
import com.linoer.springtest.config.PictureUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({PictureUploadProperties.class})
public class SpringtestApplication extends MyWebConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(SpringtestApplication.class, args);
    }

}
