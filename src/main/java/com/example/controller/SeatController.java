package com.example.controller;

import com.example.domain.entity.SeatEntity;
import com.example.service.SeatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatController {

    @Autowired
    private SeatServiceImpl seatService;

    @RequestMapping("/list")
    public List<SeatEntity> list() {
        return seatService.getAllSeats();
    }

    @RequestMapping("/get")
    public SeatEntity get(Integer id) {
        return seatService.getSeatById(id);
    }
    @RequestMapping("/count")
    public int count(Integer trainid) {
        return seatService.getSeatCountByTrainid(trainid);
    }

    /**
     * 购买某个列车的车票
     * @param trainid
     * @return
     */
    @RequestMapping("/sell")
    public boolean sell(Integer trainid) {
        return seatService.sellSeat(trainid);
    }

    /**
     * 退票
     * 入参：座位id
     * @param id
     * @return
     */
    @RequestMapping("/unsell")
    public boolean unsell(Integer id) {
        return seatService.unsell(id);
    }

    /**
     * 模拟意外事务执行失败导致sql回滚了
     * 入参：seat的id
     */
    @RequestMapping("/opps")
    public boolean opps(Integer id) {
        return seatService.opps(id);
    }
}
