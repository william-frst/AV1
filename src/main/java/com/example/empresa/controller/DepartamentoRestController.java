package com.example.empresa.controller;

import com.example.empresa.dto.DepartamentoDTO;
import com.example.empresa.model.Departamento;
import com.example.empresa.service.DepartamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/departamentos")
@RequiredArgsConstructor
public class DepartamentoRestController {

    private final DepartamentoService service;

    private DepartamentoDTO toDto(Departamento d){
        return new DepartamentoDTO(d.getId(), d.getNome(), d.getLocalizacao());
    }

    private Departamento toEntity(DepartamentoDTO dto){
        return new Departamento(dto.getId(), dto.getNome(), dto.getLocalizacao(), null);
    }

    @PostMapping
    public ResponseEntity<DepartamentoDTO> create(@Valid @RequestBody DepartamentoDTO dto){
        Departamento saved = service.create(toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    @GetMapping
    public List<DepartamentoDTO> all() { return service.findAll().stream().map(this::toDto).collect(Collectors.toList()); }

    @GetMapping("/{id}")
    public DepartamentoDTO get(@PathVariable Long id) {
        return toDto(service.findById(id));
    }

    @PutMapping("/{id}")
    public DepartamentoDTO update(@PathVariable Long id, @Valid @RequestBody DepartamentoDTO body) {
        Departamento updated = service.update(id, toEntity(body));
        return toDto(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
