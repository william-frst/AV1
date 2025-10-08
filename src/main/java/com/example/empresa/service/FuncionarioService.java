package com.example.empresa.service;

import com.example.empresa.model.Funcionario;
import com.example.empresa.model.Departamento;
import com.example.empresa.repository.FuncionarioRepository;
import com.example.empresa.repository.DepartamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcRepo;
    private final DepartamentoRepository depRepo;

    public Funcionario create(Funcionario funcionario, Long departamentoId){
        Departamento dep = depRepo.findById(departamentoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Departamento não encontrado"));
        funcionario.setDepartamento(dep);
        return funcRepo.save(funcionario);
    }

    public List<Funcionario> findAll(){
        return funcRepo.findAll();
    }

    public Funcionario findById(Long id){
        return funcRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado"));
    }

    public Funcionario update(Long id, Funcionario body, Long departamentoId){
        Funcionario f = findById(id);
        f.setNome(body.getNome());
        f.setEmail(body.getEmail());
        f.setDataAdmissao(body.getDataAdmissao());
        if(departamentoId != null){
            Departamento dep = depRepo.findById(departamentoId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Departamento não encontrado"));
            f.setDepartamento(dep);
        }
        return funcRepo.save(f);
    }

    public void delete(Long id){
        funcRepo.deleteById(id);
    }
}
