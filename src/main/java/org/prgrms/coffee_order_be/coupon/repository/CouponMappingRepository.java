package org.prgrms.coffee_order_be.coupon.repository;

import org.prgrms.coffee_order_be.coupon.entity.CouponMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CouponMappingRepository extends JpaRepository<CouponMapping, UUID> {
}
