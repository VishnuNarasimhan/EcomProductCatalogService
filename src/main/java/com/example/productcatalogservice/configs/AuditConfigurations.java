package com.example.productcatalogservice.configs;

import com.example.productcatalogservice.services.AuditAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditConfigurations {

    @Bean
    public AuditorAware<String> getAuditor() {
        return new AuditAwareImpl();
    }

}
