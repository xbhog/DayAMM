package com.xbhog;

import com.xbhog.common.Utils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xbhog
 * @describe:
 * @date 2022/8/10
 */
@SpringBootApplication
public class ConCurrentToolApplication {
    public static void main(String[] args){
        //加载配置文件
        Utils.loadPropertySource(ConCurrentToolApplication.class,"tomcat.properties");
        SpringApplication.run(ConCurrentToolApplication.class,args);
    }
}
