package org.prgrms.coffee_order_be.coupon.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.prgrms.coffee_order_be.coupon.dto.request.CouponCreateDto;
import org.prgrms.coffee_order_be.coupon.dto.response.CouponMappingResponseDto;
import org.prgrms.coffee_order_be.coupon.entity.Coupon;
import org.prgrms.coffee_order_be.coupon.service.CouponService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/coupon")
public class CouponController {

    private final CouponService couponService;

    @PostMapping
    public ResponseEntity<Coupon> createCoupon(@RequestBody @Valid CouponCreateDto couponCreateDto){
        Coupon response = couponService.createCoupon(couponCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/issue")
    public ResponseEntity<CouponMappingResponseDto> issueCoupon(@RequestParam("couponCode") String couponCode,
                                                                @RequestParam("email") String email){ // 추후 JWT로 수정 예정
        CouponMappingResponseDto responseDto = couponService.issueCoupon(couponCode, email);
        return ResponseEntity.ok().body(responseDto);

    }
}
