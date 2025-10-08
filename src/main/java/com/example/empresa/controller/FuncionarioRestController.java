package com.example.empresa.controller;

import com.example.empresa.dto.FuncionarioDTO;
import com.example.empresa.model.Funcionario;
import com.example.empresa.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/funcionarios")
@RequiredArgsConstructor
public class FuncionarioRestController {

    private final FuncionarioService service;

    private Funcionario toEntity(FuncionarioDTO dto){
        return new Funcionario(dto.getId(), dto.getNome(), dto.getEmail(), dto.getDataAdmissao(), null);
    }

    private FuncionarioDTO toDto(Funcionario f){
        return new FuncionarioDTO(f.getId(), f.getNome(), f.getEmail(), f.getDataAdmissao(), f.getDepartamento()!=null?f.getDepartamento().getId():null);
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> create(@Valid @RequestBody FuncionarioDTO dto){
        Funcionario saved = service.create(toEntity(dto), dto.getDepartamentoId());
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    @GetMapping
    public List<FuncionarioDTO> all() { return service.findAll().stream().map(this::toDto).collect(Collectors.toList()); }

    @GetMapping("/{id}")
    public FuncionarioDTO get(@PathVariable Long id) {
        return toDto(service.findById(id));
    }

    @PutMapping("/{id}")
    public FuncionarioDTO update(@PathVariable Long id, @Valid @RequestBody FuncionarioDTO body) {
        Funcionario updated = service.update(id, toEntity(body), body.getDepartamentoId());
        return toDto(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
