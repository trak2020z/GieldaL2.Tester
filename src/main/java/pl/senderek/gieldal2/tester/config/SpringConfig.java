package pl.senderek.gieldal2.tester.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "pl.senderek.gieldal2.tester.model")
@ComponentScan(basePackages = {"pl.senderek.gieldal2.tester.service.internal.impl",
        "pl.senderek.gieldal2.tester.service.external.impl"})
@EnableJpaRepositories("pl.senderek.gieldal2.tester.repository")
public class SpringConfig {
}
