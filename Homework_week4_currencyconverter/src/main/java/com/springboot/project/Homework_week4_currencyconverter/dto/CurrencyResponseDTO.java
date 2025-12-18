package com.springboot.project.Homework_week4_currencyconverter.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CurrencyResponseDTO {
    String result;
    String base_code;
    String target_code;
    double conversion_rate;
    double conversion_result;
}
