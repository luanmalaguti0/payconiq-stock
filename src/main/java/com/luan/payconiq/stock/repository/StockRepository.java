package com.luan.payconiq.stock.repository;
import com.luan.payconiq.stock.entity.StockEntity;

import java.util.List;
import java.util.Optional;

public interface StockRepository {

    List<StockEntity> getStocks();

    Optional<StockEntity> getStock(Long id);

    Optional<StockEntity> updateStock(Long id, StockEntity stockEntity);

    StockEntity createStock(StockEntity stockEntity);
}
