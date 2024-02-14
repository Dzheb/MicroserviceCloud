package org.example.gb;

import org.example.gb.timing.TimingAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TimingProperties.class)
@ConditionalOnProperty(value = "timing.enabled", havingValue = "true")
public class TimingAutoConfiguration {

  @Bean
  TimingAspect timingAspect(TimingProperties properties) {
    return new TimingAspect(properties);
  }

}
