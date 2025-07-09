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
}
