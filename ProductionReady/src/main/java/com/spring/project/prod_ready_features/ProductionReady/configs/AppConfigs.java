package com.spring.project.prod_ready_features.ProductionReady.configs;

import com.spring.project.prod_ready_features.ProductionReady.auto.AuditorAwareImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef ="getAuditor")
public class AppConfigs {

    @Bean
    ModelMapper getMapper()
    {
        return new ModelMapper();
    }

    @Bean
    AuditorAware<String> getAuditor()
    {
        return new AuditorAwareImpl();
    }
}
