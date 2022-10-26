package com.miportfolio.Shushu.Repository;

import com.miportfolio.Shushu.Entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntPersonaRepository extends JpaRepository<Persona,Integer> {
    public Optional<Persona> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
