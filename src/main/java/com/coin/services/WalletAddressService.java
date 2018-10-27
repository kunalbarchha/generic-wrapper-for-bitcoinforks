package com.coin.services;


import com.coin.coin.CoinWalletAddress;
import com.coin.models.CoinRequestDto;
import com.coin.models.CoinResponseDto;
import com.coin.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WalletAddressService implements CoinWalletAddress {


    @Autowired
    CoinNetworkService<String> coinNetworkService;

    @Override
    public String getAddress(String userId) {
        CoinRequestDto coinRequestDto = new CoinRequestDto();
        coinRequestDto.setMethod(Constant.WALLET_ADDRESS);

        List<Object> objectList = new ArrayList<>();

        objectList.add(userId);

        coinRequestDto.setParams(objectList);

        CoinResponseDto<String> responseDto = coinNetworkService.postRequest(coinRequestDto);

        return responseDto.getResult();
    }
}
