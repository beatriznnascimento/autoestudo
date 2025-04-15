package com.npi.autoestudo.dto;

public record UsuarioDTO(
    Long id,
    String nome,
    String email,
    boolean ativo,
    Long cursoId,
    String cursoDTO
) {}
