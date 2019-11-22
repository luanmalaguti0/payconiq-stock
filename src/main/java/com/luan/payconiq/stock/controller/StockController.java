package com.luan.payconiq.stock.controller;

import com.luan.payconiq.stock.model.StockDto;
import com.luan.payconiq.stock.service.StockService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/api/stocks")
@RestController
public class StockController {

    private StockService stockService;

    @GetMapping
    @ResponseBody
    public List<StockDto> getStocks(){
        return stockService.getStocks();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public StockDto getStock(@PathVariable Long id){
        return stockService.getStock(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(OK)
    public StockDto updateStock(@PathVariable Long id, @RequestBody StockDto stockDto){
        return stockService.updateStock(id, stockDto);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ResponseBody
    public StockDto createStock(@Validated @RequestBody StockDto stockDto){
        return stockService.createStock(stockDto);
    }
}
