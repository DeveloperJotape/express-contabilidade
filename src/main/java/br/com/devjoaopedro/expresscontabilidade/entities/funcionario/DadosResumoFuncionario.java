package br.com.devjoaopedro.expresscontabilidade.entities.funcionario;

import br.com.devjoaopedro.expresscontabilidade.entities.funcionario.enums.Cargo;

public record DadosResumoFuncionario(
        Long id,
        String nome,
        Cargo cargo
) {

    public DadosResumoFuncionario(Funcionario funcionario) {
        this(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCargo()
        );
    }

}
