package org.prgrms.coffee_order_be.coupon.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.prgrms.coffee_order_be.user.entity.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "coupon_mapping")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponMapping {
    @Id
    @UuidGenerator
    @Column(name = "mapping_id", columnDefinition = "BINARY(16)", nullable = false)
    private UUID mappingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    private boolean used;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;

    @Builder
    public CouponMapping(User user, Coupon coupon, LocalDateTime expirationDate) {
        this.user = user;
        this.coupon = coupon;
        this.used = false;
        this.expirationDate = expirationDate;
    }
}