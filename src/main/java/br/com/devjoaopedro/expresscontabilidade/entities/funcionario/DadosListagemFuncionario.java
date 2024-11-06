package br.com.devjoaopedro.expresscontabilidade.entities.funcionario;

import br.com.devjoaopedro.expresscontabilidade.entities.empresa.DadosResumoEmpresa;
import br.com.devjoaopedro.expresscontabilidade.entities.funcionario.enums.Cargo;

import java.util.List;
import java.util.stream.Collectors;

public record DadosListagemFuncionario(
        Long id,
        String nome,
        String email,
        String telefone,
        Cargo cargo,
        Boolean situacao,
        List<DadosResumoEmpresa> empresas
) {
    public DadosListagemFuncionario(Funcionario funcionario) {
        this(funcionario.getId(),
                funcionario.getNome(),
                funcionario.getEmail(),
                funcionario.getTelefone(),
                funcionario.getCargo(),
                funcionario.getSituacao(),
                funcionario.getEmpresas().stream().map(DadosResumoEmpresa::new).collect(Collectors.toList())
        );
    }
}
