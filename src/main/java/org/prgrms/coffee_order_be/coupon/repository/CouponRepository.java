package org.prgrms.coffee_order_be.coupon.repository;

import org.prgrms.coffee_order_be.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CouponRepository extends JpaRepository<Coupon, UUID> {
    Optional<Coupon> findByCouponCode(String CouponCode);


    // 유효기간이 지나지 않은 유저가 가지고 있는 사용하지 않은 쿠폰들
    @Query("SELECT cm.coupon FROM CouponMapping cm " +
            "JOIN cm.user u " +
            "WHERE u.email = :email " +
            "AND cm.used = false " +
            "AND cm.coupon.expirationDate > CURRENT_TIMESTAMP")
    List<Coupon> findValidCouponsByUserEmail(@Param("email") String email);

}
