package org.prgrms.coffee_order_be.coupon.dto.response;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import org.prgrms.coffee_order_be.coupon.entity.Coupon;
import org.prgrms.coffee_order_be.coupon.entity.CouponMapping;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class CouponResponseDto {
    private UUID couponId;

    private String couponName;

    private String couponCode;

    private long discount;

    private LocalDateTime expirationDate;

    private boolean used;

    @Builder
    public CouponResponseDto(UUID couponId, String couponName, String couponCode, long discount, LocalDateTime expirationDate, boolean used) {
        this.couponId = couponId;
        this.couponName = couponName;
        this.couponCode = couponCode;
        this.discount = discount;
        this.expirationDate = expirationDate;
        this.used = used;
    }

    public static CouponResponseDto from(CouponMapping couponMapping){
        Coupon coupon = couponMapping.getCoupon();

        return CouponResponseDto.builder()
                .couponId(coupon.getCouponId())
                .couponCode(coupon.getCouponCode())
                .couponName(coupon.getCouponName())
                .discount(coupon.getDiscount())
                .expirationDate(coupon.getExpirationDate())
                .used(couponMapping.isUsed())
                .build();
    }
}
