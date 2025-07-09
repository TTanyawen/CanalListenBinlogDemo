package com.example.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_seat")
public class SeatEntity {
    private Integer id;
    private Integer trainid;
    private Integer status;
}
