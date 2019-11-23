package com.luan.payconiq.stock.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.luan.payconiq.stock.service.TimestampService;

@ExtendWith(SpringExtension.class)
class StockExceptionHandlerTest {

    @InjectMocks
    private StockExceptionHandler subject;

    @Mock
    private TimestampService timestampService;

    @Test
    void handleAllTest() {
        String exception_message = "exception message";
        String localized_message = "localized message";
        LocalDateTime timestamp = LocalDateTime.now();
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        Exception exception = mock(Exception.class);

        when(timestampService.now()).thenReturn(timestamp);
        when(exception.getMessage()).thenReturn(exception_message);
        when(exception.getLocalizedMessage()).thenReturn(localized_message);

        StockError expected = StockError.builder()
                .code(httpStatus.value())
                .message(exception_message)
                .cause(localized_message)
                .timestamp(timestamp)
                .build();

        ResponseEntity<Object> result = subject.handleAll(exception);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(result.getBody()).isEqualToComparingFieldByField(expected);

        verify(timestampService).now();
        verify(exception, times(2)).getMessage();
        verify(exception).getLocalizedMessage();
    }

    @Test
    void handleStockNotFoundExceptionTest() {
        String exception_message = "exception message";
        String localized_message = "localized message";
        LocalDateTime timestamp = LocalDateTime.now();
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        StockNotFoundException exception = mock(StockNotFoundException.class);

        when(timestampService.now()).thenReturn(timestamp);
        when(exception.getMessage()).thenReturn(exception_message);
        when(exception.getLocalizedMessage()).thenReturn(localized_message);

        StockError expected = StockError.builder()
                .code(httpStatus.value())
                .message(exception_message)
                .cause(localized_message)
                .timestamp(timestamp)
                .build();

        ResponseEntity<Object> result = subject.handleStockNotFoundException(exception);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getBody()).isEqualToComparingFieldByField(expected);

        verify(timestampService).now();
        verify(exception, times(2)).getMessage();
        verify(exception).getLocalizedMessage();
    }
}