package com.npi.autoestudo.service;

import com.npi.autoestudo.model.Usuario;
import com.npi.autoestudo.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }
    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id);
    }    

    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario atualizar(Long id, Usuario usuario) {
        usuario.setId(id);
        return repository.save(usuario);
    }

    public void excluir(Long id) {
        Usuario usuario = repository.findById(id).orElseThrow();
        if (!usuario.isAtivo()) {
            repository.deleteById(id);
        } else {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário ainda está ativo e não pode ser excluído.");

        }
    }
}
 