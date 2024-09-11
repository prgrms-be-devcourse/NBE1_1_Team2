package org.prgrms.coffee_order_be.coupon.service;

import lombok.RequiredArgsConstructor;
import org.prgrms.coffee_order_be.coupon.dto.request.CouponCreateDto;
import org.prgrms.coffee_order_be.coupon.entity.Coupon;
import org.prgrms.coffee_order_be.coupon.repository.CouponRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;

    @Transactional
    public Coupon createCoupon(CouponCreateDto couponCreateDto){
        Coupon coupon = couponCreateDto.toEntity();
        couponRepository.save(coupon);

        return coupon;
    }
}
