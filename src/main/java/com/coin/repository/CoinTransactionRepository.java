package com.coin.repository;

import com.coin.entity.CoinTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoinTransactionRepository extends MongoRepository<CoinTransaction, String> {

    Optional<CoinTransaction> findByTxid(String txid);
}
