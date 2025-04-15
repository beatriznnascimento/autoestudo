package com.npi.autoestudo.controller;

import com.npi.autoestudo.dto.UsuarioDTO;
import com.npi.autoestudo.dto.UsuarioInputDTO;
import com.npi.autoestudo.model.Curso;
import com.npi.autoestudo.model.Usuario;
import com.npi.autoestudo.service.CursoService;
import com.npi.autoestudo.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final CursoService cursoService;

    public UsuarioController(UsuarioService usuarioService, CursoService cursoService) {
        this.usuarioService = usuarioService;
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<UsuarioDTO> listar() {
        return usuarioService.listarTodos().stream()
                .map(usuario -> new UsuarioDTO(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.isAtivo(),
                        usuario.getCurso() != null ? usuario.getCurso().getId() : null,
                        usuario.getCurso() != null ? usuario.getCurso().getNome() : null
                ))
                .collect(Collectors.toList());
    }

    @PostMapping
    public UsuarioDTO criar(@RequestBody @Valid UsuarioInputDTO dto) {
        Curso curso = cursoService.buscarPorId(dto.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setAtivo(dto.ativo());
        usuario.setCurso(curso);

        Usuario salvo = usuarioService.salvar(usuario);

        return new UsuarioDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getEmail(),
                salvo.isAtivo(),
                curso.getId(),
                curso.getNome()
        );
    }

    @PutMapping("/{id}")
    public UsuarioDTO atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioInputDTO dto) {
        Curso curso = cursoService.buscarPorId(dto.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setAtivo(dto.ativo());
        usuario.setCurso(curso);

        Usuario atualizado = usuarioService.atualizar(id, usuario);

        return new UsuarioDTO(
                atualizado.getId(),
                atualizado.getNome(),
                atualizado.getEmail(),
                atualizado.isAtivo(),
                curso.getId(),
                curso.getNome()
        );
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        usuarioService.excluir(id);
    }
}
