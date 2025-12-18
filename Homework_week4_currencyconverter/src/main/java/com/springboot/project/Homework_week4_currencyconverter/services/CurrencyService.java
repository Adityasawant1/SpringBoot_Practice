package com.springboot.project.Homework_week4_currencyconverter.services;

import com.springboot.project.Homework_week4_currencyconverter.dto.CurrencyResponseDTO;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
@Service
public class CurrencyService {

    private static final String api_key = "3d6260d32a05e3af9e5f0bb8";

    private static final String API ="https://v6.exchangerate-api.com/v6";

    private final RestClient restClient;

    public CurrencyService()
    {
        restClient = RestClient.builder()
                .baseUrl("https://v6.exchangerate-api.com/v6")
                .build();
    }
    public @Nullable CurrencyResponseDTO getCurrency(String from, String to, double amount){
        String finalAPI = "/"+api_key+"/pair/"+from+"/"+to+"/"+amount;
        return restClient.get()
                .uri(finalAPI)
                .retrieve()
                .body(CurrencyResponseDTO.class);

    }
}
