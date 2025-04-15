package com.npi.autoestudo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioInputDTO(

    @NotBlank(message = "O nome é obrigatório")
    String nome,

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    String email,

    @NotBlank(message = "A senha é obrigatória")
    String senha,

    @NotNull(message = "Informe se o usuário está ativo ou não")
    Boolean ativo,

    @NotNull(message = "Informe o ID do curso")
    Long cursoId

) {}
