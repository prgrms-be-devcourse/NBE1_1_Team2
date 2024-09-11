package org.prgrms.coffee_order_be.coupon.dto.request;

import lombok.Getter;
import org.prgrms.coffee_order_be.coupon.entity.Coupon;

import java.time.LocalDateTime;

@Getter
public class CouponCreateDto {

    private String couponName;

    private String couponCode;

    private long discount;

    private LocalDateTime expirationDate;

    public Coupon toEntity(){
        return Coupon.builder()
                .couponCode(this.getCouponCode())
                .couponName(this.getCouponName())
                .discount(this.getDiscount())
                .expirationDate(this.getExpirationDate())
                .build();
    }
}
