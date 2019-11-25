package com.luan.payconiq.stock.mapper;

import com.luan.payconiq.stock.entity.StockEntity;
import com.luan.payconiq.stock.model.StockDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class StockMapper {

    public StockEntity toStockEntity(StockDto stockDto, LocalDateTime lastUpdated) {
        return StockEntity.builder()
                .name(stockDto.getName())
                .currentPrice(stockDto.getCurrentPrice())
                .lastUpdated(lastUpdated)
                .build();
    }

    public StockDto toStockDto(StockEntity stockEntity) {
        return StockDto.builder()
                .id(stockEntity.getId())
                .name(stockEntity.getName())
                .currentPrice(stockEntity.getCurrentPrice())
                .lastUpdated(stockEntity.getLastUpdated())
                .build();
    }
}
