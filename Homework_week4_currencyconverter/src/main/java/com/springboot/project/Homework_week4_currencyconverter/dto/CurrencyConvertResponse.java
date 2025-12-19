package com.springboot.project.Homework_week4_currencyconverter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrencyConvertResponse {
    private String from;
    private String to;
    private double amount;
    private double convertedAmount;
    private double rate;
}
