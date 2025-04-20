package com.SDP.project.config;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class PaypalConfig {

    @Value("${paypal.clientId}")
    private String clientId;

    @Value("${paypal.clientSecret}")
    private String clientSecret;

    @Value("${paypal.mode}")
    private String mode;

    @Value("${paypal.baseUrl}")
    private String baseUrl;

    @Bean
    public PayPalEnvironment.Sandbox payPalEnvironment() {
        return new PayPalEnvironment.Sandbox(clientId, clientSecret);
    }

    @Bean
    public PayPalHttpClient payPalHttpClient(PayPalEnvironment environment) {
        return new PayPalHttpClient(environment);
    }
}
