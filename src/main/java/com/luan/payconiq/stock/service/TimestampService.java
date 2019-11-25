package com.luan.payconiq.stock.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class TimestampService {

    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
