package com.example.empresa.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotNull(message = "Data de admissão é obrigatória")
    private LocalDate dataAdmissao;

    // apenas o id do departamento é esperado do cliente
    @NotNull(message = "Departamento é obrigatório")
    private Long departamentoId;
}
