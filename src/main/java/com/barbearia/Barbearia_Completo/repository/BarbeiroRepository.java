package com.barbearia.Barbearia_Completo.repository;

import com.barbearia.Barbearia_Completo.model.BarbeiroModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BarbeiroRepository extends JpaRepository<BarbeiroModel, Long> {
    boolean existsByNameIgnoreCase(String name);

    List<BarbeiroModel> findByActiveTrue();
}
