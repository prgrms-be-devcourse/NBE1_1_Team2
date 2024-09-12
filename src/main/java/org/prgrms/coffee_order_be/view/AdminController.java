package org.prgrms.coffee_order_be.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/products")
public class AdminController {

  @GetMapping
  public String showProductList() {
    return "admin/products/product-list";
  }

  @GetMapping("/new-product")
  public String showNewProductForm() {
    return "admin/products/new-product";
  }

}
