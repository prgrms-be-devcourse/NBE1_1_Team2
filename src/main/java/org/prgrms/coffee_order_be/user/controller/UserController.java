package org.prgrms.coffee_order_be.user.controller;

import lombok.RequiredArgsConstructor;
import org.prgrms.coffee_order_be.user.dto.TokenDto;
import org.prgrms.coffee_order_be.user.dto.UserCreateRequestDto;
import org.prgrms.coffee_order_be.user.dto.UserResponseDto;
import org.prgrms.coffee_order_be.user.entity.User;
import org.prgrms.coffee_order_be.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserCreateRequestDto userCreateRequestDto) {
        User user = userService.createUser(userCreateRequestDto);

        if (user == null) {
            return ResponseEntity
                    .status(409)
                    .body("중복 이메일입니다.");
        }

        return ResponseEntity
                .status(201)
                .body(new UserResponseDto(user));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@RequestBody UserCreateRequestDto userCreateRequestDto) {
        TokenDto tokenDto = userService.authenticateUser(userCreateRequestDto);

        if (tokenDto == null) {
            return ResponseEntity
                    .status(404)
                    .body("이메일과 비밀번호를 확인하세요.");
        }

        return ResponseEntity
                .status(200)
                .body(tokenDto);
    }

    @PostMapping("/authenticate/admin")
    public ResponseEntity<?> adminLogin(@RequestBody UserCreateRequestDto userCreateRequestDto) {
        TokenDto tokenDto = userService.authenticateAdmin(userCreateRequestDto);

        if (tokenDto == null) {
            return ResponseEntity
                    .status(404)
                    .body("관리자 인증 정보가 틀립니다.");
        }

        return ResponseEntity
                .status(200)
                .body(tokenDto);
    }
}
