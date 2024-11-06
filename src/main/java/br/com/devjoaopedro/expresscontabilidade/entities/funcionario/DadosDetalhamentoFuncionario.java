package br.com.devjoaopedro.expresscontabilidade.entities.funcionario;

import br.com.devjoaopedro.expresscontabilidade.entities.empresa.DadosResumoEmpresa;
import br.com.devjoaopedro.expresscontabilidade.entities.funcionario.enums.Cargo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
        Boolean situacao,
        List<DadosResumoEmpresa> empresas
) {
    public DadosDetalhamentoFuncionario(Funcionario funcionario) {
        this(funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getDataNascimento(),
                funcionario.getEmail(),
                funcionario.getTelefone(),
                funcionario.getCargo(),
                funcionario.getDataEntrada(),
                funcionario.getDataSaida(),
                funcionario.getSituacao(),
                funcionario.getEmpresas().stream().map(DadosResumoEmpresa::new).collect(Collectors.toList())
        );
    }
}
