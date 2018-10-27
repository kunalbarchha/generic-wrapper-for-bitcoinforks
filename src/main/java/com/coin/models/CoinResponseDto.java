package com.coin.models;

import com.coin.models.respose.CoinError;
import lombok.Data;

@Data
public class CoinResponseDto<T> {

    private T result;
    private CoinError error;
    private String id;
}
