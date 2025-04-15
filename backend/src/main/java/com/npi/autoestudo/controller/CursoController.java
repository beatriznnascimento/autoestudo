package com.npi.autoestudo.controller;

import com.npi.autoestudo.dto.CursoDTO;
import com.npi.autoestudo.model.Curso;
import com.npi.autoestudo.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping
    public List<CursoDTO> listar() {
        return service.listar().stream()
                .map(c -> new CursoDTO(c.getId(), c.getNome(), c.getTurno()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public Curso criar(@RequestBody @Valid Curso curso) {
        return service.salvar(curso);
    }

    @PutMapping("/{id}")
    public Curso atualizar(@PathVariable Long id, @RequestBody @Valid Curso curso) {
        return service.atualizar(id, curso);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.excluir(id);
    }
}
