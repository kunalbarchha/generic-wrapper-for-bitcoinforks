package com.coin.services;


import com.coin.models.CoinRequestDto;
import com.coin.models.CoinResponseDto;
import lombok.extern.log4j.Log4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Log4j
@Service
public class CoinNetworkService<T> {

    @Value("${bitcoin.base_url}")
    private String BASE_URL;

    @Value("${bitcoin.rpc_user}")
    private String rpc_user;

    @Value("${bitcoin.rpc_password}")
    private String rpc_password;


    public CoinResponseDto<T> postRequest(CoinRequestDto listBaseRequest) {

        log.debug("Coin Post Request " + listBaseRequest.toString());
        RestTemplate restTemplate = new RestTemplate();

        String plainCreds = rpc_user + ":" + rpc_password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);


        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);

        headers.setContentType(MediaType.APPLICATION_JSON);

        ParameterizedTypeReference<CoinResponseDto<T>> responseType =
                new ParameterizedTypeReference<CoinResponseDto<T>>() {
                };

        HttpEntity<CoinRequestDto> request = new HttpEntity<>(listBaseRequest, headers);


        ResponseEntity<CoinResponseDto<T>> response =
                restTemplate.exchange(BASE_URL, HttpMethod.POST, request, responseType);

        log.debug("Coin Post Request " + listBaseRequest.toString() + " :-> Response " + response.getBody());

        return response.getBody();
    }
}
