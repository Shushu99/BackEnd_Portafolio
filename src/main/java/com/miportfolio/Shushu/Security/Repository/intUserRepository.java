package com.miportfolio.Shushu.Security.Repository;

import com.miportfolio.Shushu.Security.Entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface intUserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByNombreUser(String nombreUser);
    
    boolean existsByNombreUser(String nombreUser);
    boolean existsByEmail(String email);
}
