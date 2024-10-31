package br.com.devjoaopedro.expresscontabilidade.entities.funcionario;

import br.com.devjoaopedro.expresscontabilidade.entities.funcionario.enums.Cargo;

import java.time.LocalDate;

public record DadosDetalhamentoFuncionario(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String email,
        String telefone,
        Cargo cargo,
        LocalDate dataEntrada,
        LocalDate dataSaida,
        Boolean situacao
) {
    public DadosDetalhamentoFuncionario (Funcionario funcionario){
        this(funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getDataNascimento(),
                funcionario.getEmail(),
                funcionario.getTelefone(),
                funcionario.getCargo(),
                funcionario.getDataEntrada(),
                funcionario.getDataSaida(),
                funcionario.getSituacao());
    }
}
