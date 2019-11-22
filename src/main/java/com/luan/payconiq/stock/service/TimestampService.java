package com.luan.payconiq.stock.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimestampService {

    public LocalDateTime now(){
        return LocalDateTime.now();
    }
}
