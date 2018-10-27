package com.coin.coin;


import java.math.BigDecimal;

public interface CoinWithDraw {

     String send(String to, String userId, BigDecimal amount);
}
