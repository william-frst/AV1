package com.example.empresa.controller.mvc;

import com.example.empresa.dto.DepartamentoDTO;
import com.example.empresa.model.Departamento;
import com.example.empresa.service.DepartamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mvc/departamentos")
@RequiredArgsConstructor
public class DepartamentoMvcController {

    private final DepartamentoService service;

    @GetMapping
    public String list(Model model){
        model.addAttribute("departamentos", service.findAll());
        return "departamentos/list";
    }

    @GetMapping("/novo")
    public String novoForm(Model model){
        model.addAttribute("departamento", new DepartamentoDTO());
        return "departamentos/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute DepartamentoDTO departamento){
        service.create(new Departamento(null, departamento.getNome(), departamento.getLocalizacao(), null));
        return "redirect:/mvc/departamentos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model){
        Departamento d = service.findById(id);
        DepartamentoDTO dto = new DepartamentoDTO(d.getId(), d.getNome(), d.getLocalizacao());
        model.addAttribute("departamento", dto);
        return "departamentos/form";
    }

    @GetMapping("/{id}")
    public String detalhe(@PathVariable Long id, Model model){
        Departamento d = service.findById(id);
        model.addAttribute("departamento", d);
        return "departamentos/detail";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id){
        service.delete(id);
        return "redirect:/mvc/departamentos";
    }
}
