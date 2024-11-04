package br.com.devjoaopedro.expresscontabilidade.entities.funcionario;

import br.com.devjoaopedro.expresscontabilidade.entities.funcionario.enums.Cargo;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroFuncionario(
        @NotBlank
        String nome,
        @NotBlank
        String cpf,
        @NotNull
        LocalDate dataNascimento,
        @NotBlank
        String email,
        @NotBlank
        String senha,
        @NotBlank
        String telefone,
        @Enumerated
        Cargo cargo,
        @NotNull
        LocalDate dataEntrada,
        LocalDate dataSaida
) {

}
