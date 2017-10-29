package me.wonwoo.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;

@Endpoint(id = "hello")
public class HelloEndpoint {

  @ReadOperation
  public String hello(String name)  {
    return "hello " + name;
  }

  @WriteOperation
  public String foo(String name) {
    return name;
  }
}
