package com.ericrhinebolt.abg.app;

import com.ericrhinebolt.abg.app.data.UserRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@TestConfiguration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.ericrhinebolt.abg", repositoryFactoryBeanClass = UserRepository.class)
public class ApplicationConfigurationTest {
}
