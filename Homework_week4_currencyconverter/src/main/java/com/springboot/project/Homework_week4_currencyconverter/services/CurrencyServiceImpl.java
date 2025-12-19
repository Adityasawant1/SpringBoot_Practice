package com.springboot.project.Homework_week4_currencyconverter.services;

import com.springboot.project.Homework_week4_currencyconverter.client.CurrencyApiClient;
import com.springboot.project.Homework_week4_currencyconverter.dto.CurrencyConvertResponse;
import com.springboot.project.Homework_week4_currencyconverter.dto.CurrencyApiResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {


    private final CurrencyApiClient apiClient;

    @Override
    public CurrencyConvertResponse convert(
            String from, String to, double amount) {

        CurrencyApiResponseDTO apiResponse =
                apiClient.fetchConversion(from, to, amount);

        return new CurrencyConvertResponse(
                from,
                to,
                amount,
                apiResponse.getConversion_result(),
                apiResponse.getConversion_rate()
        );
    }
}
