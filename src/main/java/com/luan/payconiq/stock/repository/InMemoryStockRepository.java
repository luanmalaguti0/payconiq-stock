package com.luan.payconiq.stock.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luan.payconiq.stock.entity.StockEntity;
import com.luan.payconiq.stock.handler.Loggable;

@Loggable
@Repository
public class InMemoryStockRepository implements StockRepository {
    private static final int STOCK_INITIAL_ID_VALUE = 1;

    private Map<Long, StockEntity> stocks;
    private AtomicLong atomicLong;

    @Autowired
    public InMemoryStockRepository() {
        this.stocks = new HashMap<>();
        this.atomicLong = new AtomicLong(STOCK_INITIAL_ID_VALUE);
    }

    @Override
    public List<StockEntity> getStocks() {
        return stocks.values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StockEntity> getStock(Long id) {
        return Optional.ofNullable(stocks.get(id));
    }

    @Override
    public Optional<StockEntity> updateStock(Long id, StockEntity stockEntity) {
        if (getStock(id).isPresent()) {
            stocks.put(id, stockEntity);
            return Optional.of(stocks.get(id));
        }
        return Optional.empty();
    }

    @Override
    public StockEntity createStock(StockEntity stockEntity) {
        Long id = atomicLong.getAndIncrement();
        stockEntity.setId(id);
        stocks.put(id, stockEntity);
        return stocks.get(id);
    }
}
