package com.github.wkw.share;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@SpringBootApplication
@MapperScan("com.github.wkw.share.dao")
public class ShareServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShareServerApplication.class, args);
    }


    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "false");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }

}
