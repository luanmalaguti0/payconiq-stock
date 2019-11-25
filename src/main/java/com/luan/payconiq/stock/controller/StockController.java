package com.luan.payconiq.stock.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luan.payconiq.stock.model.StockDto;
import com.luan.payconiq.stock.service.StockService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/api/stocks")
@RestController
public class StockController {

    private StockService stockService;

    @GetMapping
    @ResponseBody
    public List<StockDto> getStocks() {
        return stockService.getStocks();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public StockDto getStock(@PathVariable Long id) {
        return stockService.getStock(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(OK)
    public StockDto updateStock(@PathVariable Long id, @RequestBody StockDto stockDto) {
        return stockService.updateStock(id, stockDto);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ResponseBody
    public StockDto createStock(@Validated @RequestBody StockDto stockDto) {
        return stockService.createStock(stockDto);
    }
}
