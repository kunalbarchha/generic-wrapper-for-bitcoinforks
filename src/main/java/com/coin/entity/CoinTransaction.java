package com.coin.entity;


import com.coin.models.respose.Detail;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "transaction")
public class CoinTransaction extends BaseEntity {
    private Long amount;

    private String blockhash;

    private Long blockindex;

    private Long blocktime;

    private Long confirmations;

    private List<Detail> details;

    private Double fee;

    private String normtxid;

    private Long time;

    private Long timereceived;

    @Indexed
    private String txid;
}
