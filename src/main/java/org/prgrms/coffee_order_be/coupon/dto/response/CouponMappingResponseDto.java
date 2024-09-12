package org.prgrms.coffee_order_be.coupon.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.prgrms.coffee_order_be.coupon.entity.CouponMapping;

import java.util.UUID;

@Getter
public class CouponMappingResponseDto {

    private UUID couponMappingId;

    private String email;

    private String couponName;

    @Builder
    public CouponMappingResponseDto(UUID couponMappingId, String email, String couponName) {
        this.couponMappingId = couponMappingId;
        this.email = email;
        this.couponName = couponName;
    }

    public static CouponMappingResponseDto from(CouponMapping couponMapping){
        return CouponMappingResponseDto.builder()
                    .couponMappingId(couponMapping.getMappingId())
                    .couponName(couponMapping.getCoupon().getCouponName())
                    .email(couponMapping.getUser().getEmail())
                    .build();
    }
}
