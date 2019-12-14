package pl.senderek.gieldal2.tester.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
@EntityScan(basePackages = "pl.senderek.gieldal2.tester.model")
@ComponentScan(basePackages = {"pl.senderek.gieldal2.tester.service.internal.impl",
        "pl.senderek.gieldal2.tester.service.external.impl",
        "pl.senderek.gieldal2.tester.service.external.mapper"})
@EnableJpaRepositories("pl.senderek.gieldal2.tester.repository")
public class SpringConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> mc = restTemplate.getMessageConverters();
        mc.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(mc);
        return restTemplate;
    }
}
