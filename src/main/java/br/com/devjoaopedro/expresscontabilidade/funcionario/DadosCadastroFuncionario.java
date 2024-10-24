package br.com.devjoaopedro.expresscontabilidade.funcionario;

import br.com.devjoaopedro.expresscontabilidade.funcionario.enums.Cargo;
import br.com.devjoaopedro.expresscontabilidade.funcionario.enums.Situacao;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

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
        @Enumerated
        Situacao situacao,
        @NotNull
        LocalDate dataEntrada,
        LocalDate dataSaida) {
}
