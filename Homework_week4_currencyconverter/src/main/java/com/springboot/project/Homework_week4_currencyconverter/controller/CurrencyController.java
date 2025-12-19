package com.springboot.project.Homework_week4_currencyconverter.controller;



import com.springboot.project.Homework_week4_currencyconverter.dto.CurrencyConvertResponse;
import com.springboot.project.Homework_week4_currencyconverter.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/convert")
    public ResponseEntity<CurrencyConvertResponse> convert(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam double amount) {

        return ResponseEntity.ok(
                currencyService.convert(from, to, amount)
        );
    }
}
