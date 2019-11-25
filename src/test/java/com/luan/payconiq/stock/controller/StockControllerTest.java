package com.luan.payconiq.stock.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.math.BigDecimal;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luan.payconiq.stock.model.StockDto;
import com.luan.payconiq.stock.service.StockService;
import com.luan.payconiq.stock.service.TimestampService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = StockController.class)
class StockControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    @MockBean
    private TimestampService timestampService;

    @BeforeEach
    void setMockMvc() {
        this.mockMvc = standaloneSetup(new StockController(stockService)).build();
    }

    @Test
    void getStocksTest() throws Exception {
        when(stockService.getStocks()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/stocks")
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(stockService).getStocks();
    }

    @Test
    void getStockTest() throws Exception {
        Long id = 1L;
        StockDto stockDto = new StockDto();

        when(stockService.getStock(id)).thenReturn(stockDto);

        mockMvc.perform(get("/api/stocks/" + id)
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(stockService).getStock(id);
    }

    @Test
    void updateStockTest() throws Exception {
        Long id = 1L;
        BigDecimal currentPrice = new BigDecimal(350.99);
        String name = "Guitar";

        StockDto stockDto = new StockDto();
        stockDto.setName(name);
        stockDto.setCurrentPrice(currentPrice);

        when(stockService.getStock(id)).thenReturn(stockDto);

        mockMvc.perform(put("/api/stocks/" + id)
                .content(OBJECT_MAPPER.writeValueAsString(stockDto))
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(stockService).updateStock(id, stockDto);
    }

    @Test
    void createStockTest() throws Exception {
        Long id = 1L;
        BigDecimal currentPrice = new BigDecimal(350.99);
        String name = "Guitar";

        StockDto stockDto = new StockDto();
        stockDto.setName(name);
        stockDto.setCurrentPrice(currentPrice);

        when(stockService.getStock(id)).thenReturn(stockDto);

        mockMvc.perform(post("/api/stocks")
                .content(OBJECT_MAPPER.writeValueAsString(stockDto))
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());

        verify(stockService).createStock(stockDto);
    }
}