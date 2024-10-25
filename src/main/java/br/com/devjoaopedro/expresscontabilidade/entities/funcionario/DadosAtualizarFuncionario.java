package br.com.devjoaopedro.expresscontabilidade.entities.funcionario;

import br.com.devjoaopedro.expresscontabilidade.entities.funcionario.enums.Cargo;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;

public record DadosAtualizarFuncionario(
        @NotNull
        Long id,
        String nome,
        String email,
        String senha,
        String telefone,
        Cargo cargo,
        LocalDate dataSaida,
        Boolean situacao
) {
}
