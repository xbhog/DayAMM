package com.xbhog.common;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class Utils {

    public static void loadPropertySource(Class clazz,String fileName){
        try {
            Properties p=new Properties();
            InputStream stream = clazz.getClassLoader().getResourceAsStream(fileName);
            p.load(stream);
            p.forEach((k,v)->{
                log.info("{}={}",k,v);
                System.setProperty(k.toString(),v.toString());
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}