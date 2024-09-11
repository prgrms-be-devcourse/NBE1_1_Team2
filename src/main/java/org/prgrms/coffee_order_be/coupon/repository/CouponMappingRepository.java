package org.prgrms.coffee_order_be.coupon.repository;

import org.prgrms.coffee_order_be.coupon.entity.Coupon;
import org.prgrms.coffee_order_be.coupon.entity.CouponMapping;
import org.prgrms.coffee_order_be.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CouponMappingRepository extends JpaRepository<CouponMapping, UUID> {

    boolean existsByUserAndCoupon(User user, Coupon coupon);

    @Query("SELECT cm FROM CouponMapping cm " +
            "LEFT JOIN FETCH cm.coupon " +
            "where cm.user.email = :email AND " +
            "cm.coupon.couponId = :couponId")
    Optional<CouponMapping> findByUserEmailAndCoupon_CouponId(String email, UUID couponId);
}
