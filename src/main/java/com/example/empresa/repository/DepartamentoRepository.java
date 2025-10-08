package com.example.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.empresa.model.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}
