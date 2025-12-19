package com.springboot.project.Homework_week4_currencyconverter.client;

import com.springboot.project.Homework_week4_currencyconverter.dto.CurrencyApiResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
@Component
public class CurrencyApiClient {

    private final RestClient restClient;
    private final String apiKey;

    public CurrencyApiClient(
            RestClient restClient,
            @Value("${currency.api.key}") String apiKey) {
        this.restClient = restClient;
        this.apiKey = apiKey;
    }

    public CurrencyApiResponseDTO fetchConversion(
            String from, String to, double amount) {

        String uri = "/" + apiKey + "/pair/" + from + "/" + to + "/" + amount;

        return restClient.get()
                .uri(uri)
                .retrieve()
                .body(CurrencyApiResponseDTO.class);
    }
}
