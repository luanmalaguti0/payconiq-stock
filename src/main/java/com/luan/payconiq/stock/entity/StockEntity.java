package com.luan.payconiq.stock.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class StockEntity {

    @Setter
    private Long id;

    @Setter
    private String name;

    private BigDecimal currentPrice;

    private LocalDateTime lastUpdated;
}
