package com.coin.models.respose;


import lombok.Data;

@Data
public class CoinError {

    private int code;
    private String message;
}
