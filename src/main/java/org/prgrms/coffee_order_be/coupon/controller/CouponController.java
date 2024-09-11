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

import java.util.List;
import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<List<Coupon>> getCoupons(){
        List<Coupon> response = couponService.getCoupons();
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteCoupon(@PathVariable("uuid") UUID uuid){
        couponService.deleteCoupon(uuid);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/issue")
    public ResponseEntity<CouponMappingResponseDto> issueCoupon(@RequestParam("couponCode") String couponCode,
                                                                @RequestParam("email") String email){ // 추후 JWT로 수정 예정
        CouponMappingResponseDto responseDto = couponService.issueCoupon(couponCode, email);
        return ResponseEntity.ok().body(responseDto);

    }

    @GetMapping("/issued")
    public ResponseEntity<List<Coupon>> getIssuedCoupons(@RequestParam("email") String email){ // 추후 JWT 로 수정 예정
        List<Coupon> response = couponService.getIssuedCoupons(email);
        return ResponseEntity.ok().body(response);
    }
}
