package com.npi.autoestudo.service;

import com.npi.autoestudo.model.Curso;
import com.npi.autoestudo.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public List<Curso> listar() {
        return repository.findAll();
    }

    public Curso salvar(Curso curso) {
        return repository.save(curso);
    }

    public Curso atualizar(Long id, Curso novo) {
        novo.setId(id);
        return repository.save(novo);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public Optional<Curso> buscarPorId(Long id) {
        return repository.findById(id);
    }
}
