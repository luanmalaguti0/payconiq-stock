package com.luan.payconiq.stock.entity;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class StockEntity {

    private Long id;

    private String name;

    private BigDecimal currentPrice;

    private LocalDateTime lastUpdated;
}
