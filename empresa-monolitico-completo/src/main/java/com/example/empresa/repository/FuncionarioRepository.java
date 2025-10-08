package com.example.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.empresa.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
