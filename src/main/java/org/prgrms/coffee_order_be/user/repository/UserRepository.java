package org.prgrms.coffee_order_be.user.repository;

import java.util.Optional;
import org.prgrms.coffee_order_be.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
