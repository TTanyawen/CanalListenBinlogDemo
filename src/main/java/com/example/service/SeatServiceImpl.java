package com.example.service;

import com.example.domain.entity.SeatEntity;
import com.example.mapper.SeatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl {
    @Autowired
    private SeatMapper seatMapper;
    public List<SeatEntity> getAllSeats(){
        return seatMapper.selectList(null);
    }

    public SeatEntity getSeatById(Integer id){
        return seatMapper.selectById(id);
    }

    public int getSeatCountByTrainid(Integer trainId){
        return seatMapper.getSeatCountByTrainid(trainId);
    }




    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    public void testRedis(){
        redisTemplate.opsForValue().set("canal_test_redis", "hello world");
    }
}
