package com.spring.project.prod_ready_features.ProductionReady.auto;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Aditya Sawant");
    }
}
