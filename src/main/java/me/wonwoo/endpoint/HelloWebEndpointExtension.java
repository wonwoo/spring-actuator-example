package me.wonwoo.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpointExtension;
import org.springframework.lang.Nullable;

@WebEndpointExtension(endpoint = HelloEndpoint.class)
public class HelloWebEndpointExtension {

  private final HelloEndpoint delegate;

  public HelloWebEndpointExtension(HelloEndpoint delegate) {
    this.delegate = delegate;
  }

  @ReadOperation
  public WebEndpointResponse<String> hello(@Nullable String name) {
    return new WebEndpointResponse<>(delegate.hello(name));
  }

  @ReadOperation
  public WebEndpointResponse<String> selector(@Selector String name) {
    return new WebEndpointResponse<>(delegate.hello(name));
  }

  @WriteOperation
  public WebEndpointResponse<Person> person(String name) {
    return new WebEndpointResponse<>(new Person(delegate.hello(name)));
  }


  static class Person {
    private String name;

    public Person(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}
