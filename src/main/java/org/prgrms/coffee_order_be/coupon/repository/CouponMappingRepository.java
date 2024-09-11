package org.prgrms.coffee_order_be.coupon.repository;

import org.prgrms.coffee_order_be.coupon.entity.Coupon;
import org.prgrms.coffee_order_be.coupon.entity.CouponMapping;
import org.prgrms.coffee_order_be.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CouponMappingRepository extends JpaRepository<CouponMapping, UUID> {

    boolean existsByUserAndCoupon(User user, Coupon coupon);
}
