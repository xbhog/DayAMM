package com.xbhog.ThreadLocal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xbhog
 * @describe:
 * @date 2022/8/10
 */

@RestController
@RequestMapping("threadlocal")
public class ThreadLocalDemo {
    private static final ThreadLocal<Integer> CURRENT_USER = new ThreadLocal<Integer>();
    @GetMapping("wrong")
    public Map Wrong(@RequestParam("userId") Integer userId){
        //设置用户信息之前先查询一次ThreadLocal中的用户信息
        String before = Thread.currentThread().getName() + ":" + CURRENT_USER.get();
        //设置ThreadLocal中的用户数据
        CURRENT_USER.set(userId);
        //设置用户信息之后再查询一次ThreadLocal中的用户信息
        String after = Thread.currentThread().getName() + ":" + CURRENT_USER.get();
        //汇总两次的执行结果输出
        Map result = new HashMap();
        result.put("before",before);
        result.put("after",after);
        return result;
    }
    @GetMapping("right")
    public Map Rigth(@RequestParam("userId") Integer userId){
        //设置用户信息之前先查询一次ThreadLocal中的用户信息
        String before = Thread.currentThread().getName() + ":" + CURRENT_USER.get();
        //设置ThreadLocal中的用户数据
        CURRENT_USER.set(userId);
        try{
            //设置用户信息之后再查询一次ThreadLocal中的用户信息
            String after = Thread.currentThread().getName() + ":" + CURRENT_USER.get();
            //汇总两次的执行结果输出
            Map result = new HashMap();
            result.put("before",before);
            result.put("after",after);
            return result;
        }finally {
            //删除ThreadLocal数据，既避免了内存溢出的风险也解决了数据重复的问题
            CURRENT_USER.remove();
        }

    }
}
