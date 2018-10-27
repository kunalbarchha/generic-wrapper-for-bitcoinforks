package com.coin.controller;

import com.coin.models.AppResponse;
import com.coin.services.CoinBalanceUpdateService;
import com.coin.services.WalletAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoinController {

    @Autowired
    WalletAddressService walletAddressService;



    @Autowired
    CoinBalanceUpdateService coinBalanceUpdateService;

    @RequestMapping(value = "/getWalletAddress", method = RequestMethod.POST)
    public AppResponse<String> getWalletAddress(String userId) {
        String address = walletAddressService.getAddress(userId);
        return new AppResponse<>(address);
    }

    @RequestMapping(value = "/balanceUpdate/{txid}", method = RequestMethod.GET)
    public void updateBalanceByTxId(@PathVariable("txid") String txId) {
        coinBalanceUpdateService.update(txId);
    }
}
