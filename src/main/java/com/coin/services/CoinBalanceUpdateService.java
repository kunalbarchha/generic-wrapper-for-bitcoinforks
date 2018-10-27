package com.coin.services;


import com.coin.utils.Constant;
import com.coin.coin.CoinBalanceUpdate;
import com.coin.entity.CoinTransaction;
import com.coin.models.CoinRequestDto;
import com.coin.models.CoinResponseDto;
import com.coin.repository.CoinTransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j
@Service
public class CoinBalanceUpdateService implements CoinBalanceUpdate {

    @Autowired
    CoinNetworkService<String> coinNetworkService;


    @Autowired
    CoinTransactionRepository coinTransactionRepository;

    @Override
    public void update(String txid) {
        CoinRequestDto coinRequestDto = new CoinRequestDto();
        coinRequestDto.setMethod(Constant.WALLET_TRANSACTION);

        List<Object> objectList = new ArrayList<>();

        objectList.add(txid);


        coinRequestDto.setParams(objectList);

        CoinResponseDto<String> responseDto = coinNetworkService.postRequest(coinRequestDto);
        ObjectMapper objectMapper = new ObjectMapper();
        CoinTransaction coinTransaction = objectMapper.convertValue(responseDto.getResult(), CoinTransaction.class);


        Optional<CoinTransaction> optionalCoinTransaction = coinTransactionRepository.findByTxid(txid);
        if (optionalCoinTransaction.isPresent()) {
            CoinTransaction transaction = optionalCoinTransaction.get();
            transaction.setConfirmations(coinTransaction.getConfirmations());
            if (coinTransaction.getConfirmations() > 3) {
                //TODO: move balance unconfirmed to confirmed
                //TODO: delete transaction
            } else {

                //TODO: Update block Confirmation
            }
        } else {
            //TODO: set unconfirmed balance
            coinTransaction = coinTransactionRepository.save(coinTransaction);

        }

    }
}
