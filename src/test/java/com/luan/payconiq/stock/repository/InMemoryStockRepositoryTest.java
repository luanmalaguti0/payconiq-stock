package com.luan.payconiq.stock.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.luan.payconiq.stock.entity.StockEntity;

@ExtendWith(SpringExtension.class)
class InMemoryStockRepositoryTest {

    @InjectMocks
    private InMemoryStockRepository subject;

    @Test
    @DisplayName("Given no stocks in memory subject should return empty list")
    void getStocksTest_GivenNoStocksInMemory() {
        List<StockEntity> result = subject.getStocks();
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Given stocks in memory subject should return the list of existent stocks")
    void getStocksTest_GivenStocksInMemory() {
        subject.createStock(StockEntity.builder().build());
        subject.createStock(StockEntity.builder().build());
        subject.createStock(StockEntity.builder().build());

        List<StockEntity> result = subject.getStocks();
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(3, result.size());
    }

    @Test
    @DisplayName("Given null id as param subject should return Optional.isPresent() = false")
    void getStockTest_GivenNullId() {
        Optional<StockEntity> result = subject.getStock(null);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Given valid id as param subject subject should return Optional.isPresent = true")
    void getStockTest_GivenExistentId() {
        Long id = 1L;
        subject.createStock(StockEntity.builder().build());

        Optional<StockEntity> result = subject.getStock(id);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("Given valid id as param subject subject should return Optional.isPresent = true")
    void updateStockTest_GivenInvalidEntry() {
        Long id = 1L;

        Optional<StockEntity> result = subject.updateStock(id, null);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    void updateStockTest_GivenValidEntry() {
        String originalName = "originalName";
        String updatedName = "updatedName";

        StockEntity stockEntity = subject.createStock(StockEntity.builder()
                .name(originalName)
                .build());

        Assertions.assertEquals(originalName, stockEntity.getName());

        stockEntity.setName(updatedName);

        Optional<StockEntity> result = subject.updateStock(stockEntity.getId(), stockEntity);

        Assertions.assertEquals(updatedName, result.get().getName());
    }

    @Test
    void createStockTest() {
        Long id = 1L;
        String originalName = "originalName";
        BigDecimal currentPrice = new BigDecimal(22.44);
        LocalDateTime lastUpdated = LocalDateTime.now();

        StockEntity expected = StockEntity.builder()
                .id(id)
                .name(originalName)
                .lastUpdated(lastUpdated)
                .currentPrice(currentPrice)
                .build();

        StockEntity result = subject.createStock(StockEntity.builder()
                .name(originalName)
                .lastUpdated(lastUpdated)
                .currentPrice(currentPrice)
                .build());

        assertThat(expected).isEqualToComparingFieldByField(result);
    }
}