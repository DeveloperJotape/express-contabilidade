package br.com.devjoaopedro.expresscontabilidade.entities.cliente;

import br.com.devjoaopedro.expresscontabilidade.entities.empresa.DadosCadastroEmpresa;
import br.com.devjoaopedro.expresscontabilidade.entities.funcionario.Funcionario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record DadosCadastroCliente(

        @NotBlank
        String nome,
        @NotBlank
        String cpf,
        @NotNull
        LocalDate dataNascimento,
        @NotBlank @Email
        String email,
        @NotBlank
        String telefone,
        List<DadosCadastroEmpresa> empresas
) {
}
