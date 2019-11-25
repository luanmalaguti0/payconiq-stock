package com.luan.payconiq.stock.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.luan.payconiq.stock.entity.StockEntity;
import com.luan.payconiq.stock.model.StockDto;

@ExtendWith(SpringExtension.class)
class StockMapperTest {

    @InjectMocks
    private StockMapper subject;

    @Test
    void toStockDtoTest() {
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

        assertThat(expected).isEqualToComparingFieldByField(result);
    }

    @Test
    void toStockEntityTest() {
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

        assertThat(expected).isEqualToComparingFieldByField(result);
    }

}