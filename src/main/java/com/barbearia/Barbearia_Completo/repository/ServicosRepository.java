package com.barbearia.Barbearia_Completo.repository;
import com.barbearia.Barbearia_Completo.model.ServicosModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicosRepository extends JpaRepository<ServicosModel, Long> {
    boolean existsByNameIgnoreCase(String name);

    List<ServicosModel> findByActiveTrue();
}
