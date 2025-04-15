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
                .map(c -> new CursoDTO(c.getId(), c.getNome(), c.getSigla(), c.getTurno()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CursoDTO buscarPorId(@PathVariable Long id) {
        Curso curso = service.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        return new CursoDTO(curso.getId(), curso.getNome(), curso.getSigla(), curso.getTurno());
    }

    @PostMapping
    public CursoDTO criar(@RequestBody @Valid CursoDTO dto) {
        Curso curso = new Curso();
        curso.setNome(dto.nome());
        curso.setSigla(dto.sigla());
        curso.setTurno(dto.turno());

        Curso salvo = service.salvar(curso);
        return new CursoDTO(salvo.getId(), salvo.getNome(), salvo.getSigla(), salvo.getTurno());
    }

    @PutMapping("/{id}")
    public CursoDTO atualizar(@PathVariable Long id, @RequestBody @Valid CursoDTO dto) {
        Curso curso = new Curso();
        curso.setId(id);
        curso.setNome(dto.nome());
        curso.setSigla(dto.sigla());
        curso.setTurno(dto.turno());

        Curso atualizado = service.atualizar(id, curso);
        return new CursoDTO(atualizado.getId(), atualizado.getNome(), atualizado.getSigla(), atualizado.getTurno());
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.excluir(id);
    }
}
