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


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public List<SeatEntity> getAllSeats(){
        return seatMapper.selectList(null);
    }

    public SeatEntity getSeatById(Integer id){
        return seatMapper.selectById(id);
    }




    /**
     *  查询某车次下的可售座位数量
     * @param trainId
     * @return
     *
     * //    key&value
     * //	seatcount_trainid_{xxx} & 该trainid下的可售座位数量
     * //	例
     * //	key="seatcount_trainid_1"
     * //  value=4
     */
    public int getSeatCountByTrainid(Integer trainId){
        String key = "seatcount_trainid_" + trainId;
        Integer count = (Integer) redisTemplate.opsForValue().get(key);
        if(count != null){
            return count;
        }
        redisTemplate.opsForValue().set(key, seatMapper.getSeatCountByTrainid(trainId));
        return (Integer) redisTemplate.opsForValue().get(key);
    }

    /**
     * 尝试某个列车的票
     */
    public boolean sellSeat(Integer trainid){
        //todo 并发锁
        Integer count = seatMapper.sellSeat(trainid);
        if(count==1){
            //删除缓存
            redisTemplate.delete("seatcount_trainid_" + trainid);
        }
        return count == 1;
    }

    /**
     * 回滚座位状态
     */
    public boolean unsell(Integer id){
        //todo 并发锁
        Integer count = seatMapper.unsellSeat(id);
        if(count==1){
            // 删除缓存
            int trainid=seatMapper.selectById(id).getTrainid();
            redisTemplate.delete("seatcount_trainid_" + trainid);
            return true;
        }
        return false;
    }


    public void testRedis(){
        redisTemplate.opsForValue().set("canal_test_redis", "hello world");
    }
}
