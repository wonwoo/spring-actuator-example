package me.wonwoo;

import me.wonwoo.endpoint.HelloEndpoint;
import me.wonwoo.endpoint.HelloWebEndpointExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringActuatorExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringActuatorExampleApplication.class, args);
  }

  @Bean
  @ConditionalOnEnabledEndpoint
  public HelloEndpoint helloEndpoint() {
    return new HelloEndpoint();
  }

  @Bean
  @ConditionalOnEnabledEndpoint
  public HelloWebEndpointExtension helloWebEndpointExtension() {
    return new HelloWebEndpointExtension(helloEndpoint());
  }
}
