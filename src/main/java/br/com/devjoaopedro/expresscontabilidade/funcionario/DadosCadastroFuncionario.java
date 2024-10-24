package br.com.devjoaopedro.expresscontabilidade.funcionario;

import br.com.devjoaopedro.expresscontabilidade.funcionario.enums.Cargo;
import br.com.devjoaopedro.expresscontabilidade.funcionario.enums.Situacao;

import java.util.Date;

public record DadosCadastroFuncionario(
        String nome,
        String cpf,
        Date dataNascimento,
        String email,
        String senha,
        String telefone,
        Cargo cargo,
        Situacao situacao,
        Date dataEntrada,
        Date dataSaida) {
}
