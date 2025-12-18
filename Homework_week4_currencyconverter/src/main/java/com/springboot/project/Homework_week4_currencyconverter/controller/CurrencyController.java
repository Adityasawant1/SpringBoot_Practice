package com.springboot.project.Homework_week4_currencyconverter.controller;



import com.springboot.project.Homework_week4_currencyconverter.dto.CurrencyResponseDTO;
import com.springboot.project.Homework_week4_currencyconverter.services.CurrencyService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {


    private final CurrencyService currencyService;

    @GetMapping("/convert")
    public @Nullable CurrencyResponseDTO getCurrency(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam double amount){

        return currencyService.getCurrency(from,to,amount);

    }
}
