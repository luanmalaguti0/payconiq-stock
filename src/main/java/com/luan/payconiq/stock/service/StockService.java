package com.luan.payconiq.stock.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.luan.payconiq.stock.entity.StockEntity;
import com.luan.payconiq.stock.exception.StockNotFoundException;
import com.luan.payconiq.stock.mapper.StockMapper;
import com.luan.payconiq.stock.model.StockDto;
import com.luan.payconiq.stock.repository.StockRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StockService {

    private StockRepository stockRepository;
    private StockMapper stockMapper;
    private TimestampService timestampService;

    public List<StockDto> getStocks() {
        return stockRepository.getStocks()
                .stream()
                .map(stockEntity -> stockMapper.toStockDto(stockEntity))
                .collect(Collectors.toList());
    }

    public StockDto getStock(Long id) {
        StockEntity stockEntity = stockRepository.getStock(id)
                .orElseThrow(() -> new StockNotFoundException(id));
        return stockMapper.toStockDto(stockEntity);
    }

    public StockDto updateStock(Long id, StockDto stockDto) {
        StockEntity stockEntity = stockMapper.toStockEntity(stockDto, timestampService.now());
        stockEntity.setId(id);
        Optional<StockEntity> stockEntityUpdated = stockRepository.updateStock(id, stockEntity);
        return stockMapper.toStockDto(stockEntityUpdated
                .orElseThrow(() -> new StockNotFoundException(id)));
    }

    public StockDto createStock(StockDto stockDto) {
        StockEntity stockEntity = stockMapper.toStockEntity(stockDto, timestampService.now());
        return stockMapper.toStockDto(stockRepository.createStock(stockEntity));
    }
}
