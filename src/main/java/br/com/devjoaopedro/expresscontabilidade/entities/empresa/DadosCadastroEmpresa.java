package br.com.devjoaopedro.expresscontabilidade.entities.empresa;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroEmpresa(
        @NotBlank
        String nome,
        @NotBlank
        String cnpj,
        @NotBlank
        String uf,
        @NotBlank
        String regTributacao,
        String qualificacao,
        @NotBlank
        String inscricaoEstadual,
        String telefone,
        @Email @NotBlank
        String email,
        Boolean status,
        LocalDate dataUltimaAtualizacao,
        Long funcionarioId
) {
}
