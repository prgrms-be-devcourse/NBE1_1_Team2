package org.prgrms.coffee_order_be.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCreateRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
