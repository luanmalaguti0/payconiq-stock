package com.luan.payconiq.stock.model;

import static com.luan.payconiq.stock.constants.StockConstants.STOCK_MIN_PRICE_VALUE;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class StockDto {

    private Long id;

    @NotEmpty(message = "Stock name is required")
    private String name;

    @NotNull(message = "Stock price is required")
    @Min(value = STOCK_MIN_PRICE_VALUE, message = "Stock price requires a positive number")
    private BigDecimal currentPrice;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime lastUpdated;
}
