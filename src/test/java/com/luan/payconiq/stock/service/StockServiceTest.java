package com.luan.payconiq.stock.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.luan.payconiq.stock.entity.StockEntity;
import com.luan.payconiq.stock.exception.StockNotFoundException;
import com.luan.payconiq.stock.mapper.StockMapper;
import com.luan.payconiq.stock.model.StockDto;
import com.luan.payconiq.stock.repository.StockRepository;

@ExtendWith(SpringExtension.class)
class StockServiceTest {

    @InjectMocks
    private StockService subject;

    @Mock
    private StockRepository stockRepository;
    @Mock
    private StockMapper stockMapper;
    @Mock
    private TimestampService timestampService;

    @Test
    void getStocksTest() {
        StockDto stockDto = mock(StockDto.class);
        StockEntity stockEntity = mock(StockEntity.class);
        List<StockEntity> stockEntities = Arrays.asList(stockEntity);

        when(stockRepository.getStocks()).thenReturn(stockEntities);
        when(stockMapper.toStockDto(stockEntity)).thenReturn(stockDto);

        subject.getStocks();

        verify(stockRepository).getStocks();
        verify(stockMapper).toStockDto(stockEntity);
    }

    @Test
    void getStockTest() {
        Long id = 1L;
        StockDto stockDto = mock(StockDto.class);
        StockEntity stockEntity = mock(StockEntity.class);

        when(stockRepository.getStock(id)).thenReturn(Optional.ofNullable(stockEntity));
        when(stockMapper.toStockDto(stockEntity)).thenReturn(stockDto);

        subject.getStock(id);

        verify(stockRepository).getStock(id);
        verify(stockMapper).toStockDto(stockEntity);
    }

    @Test
    void getStock_GivenStockNotFound_SubjectShouldThrowStockNotFoundException() {
        Long id = 1L;
        StockEntity stockEntity = null;

        when(stockRepository.getStock(id)).thenReturn(Optional.ofNullable(stockEntity));

        Assertions.assertThrows(StockNotFoundException.class, () -> {
            subject.getStock(id);
        });
        verify(stockRepository).getStock(id);
    }

    @Test
    void updateStockTest() {
        Long id = 1L;
        LocalDateTime timestamp = LocalDateTime.now();
        StockDto stockDto = mock(StockDto.class);
        StockEntity stockEntity = mock(StockEntity.class);

        when(timestampService.now()).thenReturn(timestamp);
        when(stockMapper.toStockEntity(stockDto, timestamp)).thenReturn(stockEntity);
        when(stockRepository.updateStock(id, stockEntity)).thenReturn(Optional.ofNullable(stockEntity));
        when(stockMapper.toStockDto(stockEntity)).thenReturn(stockDto);

        subject.updateStock(id, stockDto);

        verify(stockMapper).toStockEntity(stockDto, timestamp);
        verify(timestampService).now();
        verify(stockRepository).updateStock(id, stockEntity);
        verify(stockMapper).toStockDto(stockEntity);

    }

    @Test
    void createStockTest() {
        LocalDateTime timestamp = LocalDateTime.now();
        StockDto stockDto = mock(StockDto.class);
        StockEntity stockEntity = mock(StockEntity.class);

        when(timestampService.now()).thenReturn(timestamp);
        when(stockMapper.toStockEntity(stockDto, timestamp)).thenReturn(stockEntity);
        when(stockMapper.toStockDto(stockEntity)).thenReturn(stockDto);
        when(stockRepository.createStock(stockEntity)).thenReturn(stockEntity);

        subject.createStock(stockDto);

        verify(timestampService).now();
        verify(stockMapper).toStockDto(stockEntity);
        verify(stockMapper).toStockEntity(stockDto, timestamp);
        verify(stockRepository).createStock(stockEntity);
    }
}