package com.example.demo.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@Service
public class IEXService {
    RestTemplate restTemplate = new RestTemplate();

    private static final String IEX_API_BASE_URL = "https://sandbox.iexapis.com/stable/stock";
    @Value("${spring.iex.token.public}")
    private String token;

    public String getStocks(String tickerList) {
        String[] tickers = tickerList.split(",");
        int n = tickers.length;
        StringBuilder stocks = new StringBuilder();

        for (int i = 0; i < n; i++) {
            ResponseEntity responseEntity = restTemplate.getForEntity(
                    IEX_API_BASE_URL + "/" + tickers[i] + "/quote/latestPrice?token=" + token,String.class);
            if (responseEntity.hasBody()) stocks.append(tickers[i] + ":" + responseEntity.getBody() + ",");
            else {
                System.out.println("no body");
            }
        }
        stocks.deleteCharAt(stocks.length() - 1);
        return stocks.toString();
    }
}

