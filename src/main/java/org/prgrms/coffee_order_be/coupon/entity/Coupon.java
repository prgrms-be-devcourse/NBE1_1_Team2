package org.prgrms.coffee_order_be.coupon.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.prgrms.coffee_order_be.common.audit.Timestamped;
import org.prgrms.coffee_order_be.user.entity.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "coupons")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon extends Timestamped{

    @Id
    @UuidGenerator
    @Column(name = "coupon_id", columnDefinition = "BINARY(16)", nullable = false)
    private UUID couponId;

    @Column(name = "coupon_name", nullable = false)
    private String couponName;

    @Column(name = "coupon_code", nullable = false, unique = true)
    private String couponCode;

    private long discount;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Builder
    public Coupon(String couponName, String couponCode, long discount, LocalDateTime expirationDate) {
        this.couponName = couponName;
        this.couponCode = couponCode;
        this.discount = discount;
        this.expirationDate = expirationDate;
    }
}
