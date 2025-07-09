package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.entity.SeatEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SeatMapper extends BaseMapper<SeatEntity> {

    @Select("select count(*) from tb_seat where trainid = #{trainid} and status = 0")
    int getSeatCountByTrainid(Integer trainId);

    @Select("update tb_seat set status = 1 where trainid = #{trainid} limit 1")
    int sellSeat(Integer trainid);

}
