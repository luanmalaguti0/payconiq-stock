package com.luan.payconiq.stock.mapper;

import com.luan.payconiq.stock.entity.StockEntity;
import com.luan.payconiq.stock.model.StockDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
class StockMapperTest {

    @InjectMocks
    private StockMapper subject;

    @Test
    void toStockDtoTest(){
        Long id = 1L;
        String name = "name";
        BigDecimal currentPrice = new BigDecimal(450.99);
        LocalDateTime lastUpdated = LocalDateTime.now();

        StockEntity stockEntity = StockEntity.builder()
                .id(id)
                .name(name)
                .currentPrice(currentPrice)
                .lastUpdated(lastUpdated)
                .build();

        StockDto expected = StockDto.builder()
                .id(id)
                .name(name)
                .currentPrice(currentPrice)
                .lastUpdated(lastUpdated)
                .build();

        StockDto result = subject.toStockDto(stockEntity);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void toStockEntityTest(){
        Long id = 1L;
        String name = "name";
        BigDecimal currentPrice = new BigDecimal(450.99);
        LocalDateTime lastUpdated = LocalDateTime.now();

        StockDto stockDto = StockDto.builder()
                .id(id)
                .name(name)
                .currentPrice(currentPrice)
                .lastUpdated(lastUpdated)
                .build();

        StockEntity expected = StockEntity.builder()
                .name(name)
                .currentPrice(currentPrice)
                .lastUpdated(lastUpdated)
                .build();

        StockEntity result = subject.toStockEntity(stockDto, lastUpdated);

        Assertions.assertEquals(expected, result);
    }

}