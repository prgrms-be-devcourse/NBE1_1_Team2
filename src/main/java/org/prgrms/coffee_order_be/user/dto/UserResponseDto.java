package org.prgrms.coffee_order_be.user.dto;

import lombok.Data;
import org.prgrms.coffee_order_be.user.entity.User;

@Data
public class UserResponseDto {
    private String email;

    public UserResponseDto(User user) {
        this.email = user.getEmail();
    }
}
