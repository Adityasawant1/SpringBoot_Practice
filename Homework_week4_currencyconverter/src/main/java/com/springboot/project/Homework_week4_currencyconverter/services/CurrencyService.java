package com.springboot.project.Homework_week4_currencyconverter.services;

import com.springboot.project.Homework_week4_currencyconverter.dto.CurrencyConvertResponse;
import org.springframework.stereotype.Service;

@Service
public interface CurrencyService {


        CurrencyConvertResponse convert(String from, String to, double amount);


}
