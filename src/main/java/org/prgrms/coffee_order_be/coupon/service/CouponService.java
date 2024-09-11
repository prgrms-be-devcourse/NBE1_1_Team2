package org.prgrms.coffee_order_be.coupon.service;

import lombok.RequiredArgsConstructor;
import org.prgrms.coffee_order_be.common.exception.BusinessLogicException;
import org.prgrms.coffee_order_be.coupon.dto.request.CouponCreateDto;
import org.prgrms.coffee_order_be.coupon.dto.response.CouponMappingResponseDto;
import org.prgrms.coffee_order_be.coupon.entity.Coupon;
import org.prgrms.coffee_order_be.coupon.entity.CouponMapping;
import org.prgrms.coffee_order_be.coupon.repository.CouponMappingRepository;
import org.prgrms.coffee_order_be.coupon.repository.CouponRepository;
import org.prgrms.coffee_order_be.user.entity.User;
import org.prgrms.coffee_order_be.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.prgrms.coffee_order_be.common.exception.ExceptionCode.NOT_FOUND_COUPON;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final UserRepository userRepository;
    private final CouponMappingRepository couponMappingRepository;

    @Transactional
    public Coupon createCoupon(CouponCreateDto couponCreateDto){
        Coupon coupon = couponCreateDto.toEntity();
        couponRepository.save(coupon);

        return coupon;
    }

    @Transactional
    public CouponMappingResponseDto issueCoupon(String couponCode, String email){
        Coupon coupon = couponRepository.findByCouponCode(couponCode)
                .orElseThrow(() -> new BusinessLogicException(NOT_FOUND_COUPON));

        User user = userRepository.findByEmail(email)
                .orElseThrow(); // 추후 채울 예정

        CouponMapping couponMapping = new CouponMapping(user, coupon);

        couponMappingRepository.save(couponMapping);

        return CouponMappingResponseDto.from(couponMapping);
    }

    public List<Coupon> getCoupons(String email){
        return couponRepository.findValidCouponsByUserEmail(email);
    }
}
