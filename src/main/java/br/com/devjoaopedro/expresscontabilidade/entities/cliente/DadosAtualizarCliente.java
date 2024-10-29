package br.com.devjoaopedro.expresscontabilidade.entities.cliente;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosAtualizarCliente(
        @NotNull
        Long id,
        String nome,
        String email,
        String telefone
) {
}
