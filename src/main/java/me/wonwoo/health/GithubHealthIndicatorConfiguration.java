package me.wonwoo.health;

import org.springframework.boot.actuate.autoconfigure.health.CompositeHealthIndicatorConfiguration;
import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.actuate.autoconfigure.health.HealthIndicatorAutoConfiguration;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Configuration
@ConditionalOnClass(name = "org.springframework.web.client.RestTemplate")
@ConditionalOnBean(RestTemplate.class)
@ConditionalOnEnabledHealthIndicator("github")
@AutoConfigureBefore(HealthIndicatorAutoConfiguration.class)
public class GithubHealthIndicatorConfiguration extends CompositeHealthIndicatorConfiguration<GithubHealthIndicator, RestTemplate> {

  private final Map<String, RestTemplate> restTemplate;

  public GithubHealthIndicatorConfiguration(
      Map<String, RestTemplate> restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Bean
  @ConditionalOnMissingBean(name = "githubHealthHealthIndicator")
  public HealthIndicator githubHealthHealthIndicator() {
    return createHealthIndicator(this.restTemplate);
  }
}
