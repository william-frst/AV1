package com.example.empresa.controller.mvc;

import com.example.empresa.dto.FuncionarioDTO;
import com.example.empresa.model.Funcionario;
import com.example.empresa.model.Departamento;
import com.example.empresa.service.FuncionarioService;
import com.example.empresa.service.DepartamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mvc/funcionarios")
@RequiredArgsConstructor
public class FuncionarioMvcController {

    private final FuncionarioService funcService;
    private final DepartamentoService depService;

    @GetMapping
    public String list(Model model){
        model.addAttribute("funcionarios", funcService.findAll());
        return "funcionarios/list";
    }

    @GetMapping("/novo")
    public String novoForm(Model model){
        model.addAttribute("funcionario", new FuncionarioDTO());
        model.addAttribute("departamentos", depService.findAll());
        return "funcionarios/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute FuncionarioDTO funcionario){
        funcService.create(new Funcionario(null, funcionario.getNome(), funcionario.getEmail(), funcionario.getDataAdmissao(), null), funcionario.getDepartamentoId());
        return "redirect:/mvc/funcionarios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model){
        Funcionario f = funcService.findById(id);
        FuncionarioDTO dto = new FuncionarioDTO(f.getId(), f.getNome(), f.getEmail(), f.getDataAdmissao(), f.getDepartamento()!=null?f.getDepartamento().getId():null);
        model.addAttribute("funcionario", dto);
        model.addAttribute("departamentos", depService.findAll());
        return "funcionarios/form";
    }

    @GetMapping("/{id}")
    public String detalhe(@PathVariable Long id, Model model){
        Funcionario f = funcService.findById(id);
        model.addAttribute("funcionario", f);
        return "funcionarios/detail";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id){
        funcService.delete(id);
        return "redirect:/mvc/funcionarios";
    }
}
