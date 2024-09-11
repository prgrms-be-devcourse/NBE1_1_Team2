package org.prgrms.coffee_order_be.coupon.repository;

import org.prgrms.coffee_order_be.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CouponRepository extends JpaRepository<Coupon, UUID> {
}
