package br.com.devjoaopedro.expresscontabilidade.entities.empresa;

import br.com.devjoaopedro.expresscontabilidade.entities.funcionario.Funcionario;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarEmpresa(
        @NotNull
        Long id,
        String nome,
        String regTributacao,
        String qualificacao,
        String inscricaoEstadual,
        String telefone,
        String email,
        Funcionario funcionarioResponsavel
) {
}
