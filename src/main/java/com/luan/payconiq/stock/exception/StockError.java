package com.luan.payconiq.stock.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StockError implements Serializable {

    private static final long serialVersionUID = 8716347936651958223L;

    private int code;
    private String message;
    private String cause;
    private LocalDateTime timestamp;
}
