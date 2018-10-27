package com.coin.models;

import lombok.Data;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Data
public class CoinRequestDto {

    private String jsonrpc;
    private String id;
    private String method;
    private List<Object> params;

    private static final AtomicLong counter = new AtomicLong(0);

    public CoinRequestDto() {

        this.id = String.valueOf(counter.incrementAndGet() + System.currentTimeMillis());
        this.jsonrpc = "1.0";

    }
}
