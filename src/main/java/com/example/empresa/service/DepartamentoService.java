package com.example.empresa.service;

import com.example.empresa.model.Departamento;
import com.example.empresa.repository.DepartamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartamentoService {

    private final DepartamentoRepository repo;

    public Departamento create(Departamento departamento){
        return repo.save(departamento);
    }

    public List<Departamento> findAll(){
        return repo.findAll();
    }

    public Departamento findById(Long id){
        return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento não encontrado"));
    }

    public Departamento update(Long id, Departamento body){
        Departamento d = findById(id);
        d.setNome(body.getNome());
        d.setLocalizacao(body.getLocalizacao());
        return repo.save(d);
    }

    public void delete(Long id){
        repo.deleteById(id);
    }
}
