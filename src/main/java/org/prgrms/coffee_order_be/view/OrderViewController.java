package org.prgrms.coffee_order_be.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderViewController {

  @GetMapping
  public String showOrderPage() {
    return "orders/order";
  }
}
