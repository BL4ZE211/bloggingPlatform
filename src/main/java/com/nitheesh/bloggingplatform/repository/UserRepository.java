package com.nitheesh.bloggingplatform.repository;

import com.nitheesh.bloggingplatform.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<Users> findByIdAndRole(long id,String role);
}
