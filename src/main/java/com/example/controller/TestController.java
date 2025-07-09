package com.example.controller;

import com.example.service.SeatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private SeatServiceImpl seatService;
    @RequestMapping("/test")
    public String test() {
        return "hello world";
    }
    @RequestMapping("/test/redis")
    public String testredis() {
        seatService.testRedis();
        return "success";
    }

}
