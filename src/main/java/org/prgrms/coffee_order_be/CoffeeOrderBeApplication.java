package org.prgrms.coffee_order_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoffeeOrderBeApplication {

  public static void main(String[] args) {
    SpringApplication.run(CoffeeOrderBeApplication.class, args);
  }

}
