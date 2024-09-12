package org.prgrms.coffee_order_be.user.service;

import lombok.RequiredArgsConstructor;
import org.prgrms.coffee_order_be.user.JwtProvider;
import org.prgrms.coffee_order_be.user.dto.TokenDto;
import org.prgrms.coffee_order_be.user.dto.UserCreateRequestDto;
import org.prgrms.coffee_order_be.user.entity.User;
import org.prgrms.coffee_order_be.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Value("${admin.password}")
    private String adminPassword;

    public User createUser(UserCreateRequestDto userCreateRequestDto) {
        if (userRepository.existsByEmail(userCreateRequestDto.getEmail())) {
            return null;
        }

        User user = User.builder()
                .email(userCreateRequestDto.getEmail())
                .password(passwordEncoder.encode(userCreateRequestDto.getPassword()))
                .build();
        return userRepository.save(user);
    }

    public TokenDto authenticateUser(UserCreateRequestDto userCreateRequestDto) {
        User findUser = userRepository.findByEmail(userCreateRequestDto.getEmail());

        if (passwordEncoder.matches(userCreateRequestDto.getPassword(), findUser.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("email", userCreateRequestDto.getEmail());
            claims.put("role", "user");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, 1);

            Date tomorrow = calendar.getTime();
            String token = jwtProvider.createToken(claims, tomorrow);

            return new TokenDto(token);
        }
        return null;
    }

    public TokenDto authenticateAdmin(UserCreateRequestDto userCreateRequestDto) {
        if (Objects.equals(userCreateRequestDto.getEmail(), "admin") && Objects.equals(userCreateRequestDto.getPassword(), adminPassword)) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("role", "admin");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, 1);

            Date tomorrow = calendar.getTime();
            String token = jwtProvider.createToken(claims, tomorrow);

            return new TokenDto(token);
        }
        return null;
    }
}
