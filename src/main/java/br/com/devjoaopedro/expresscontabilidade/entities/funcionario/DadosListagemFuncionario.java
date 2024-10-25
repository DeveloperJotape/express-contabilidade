package br.com.devjoaopedro.expresscontabilidade.entities.funcionario;

import br.com.devjoaopedro.expresscontabilidade.entities.funcionario.enums.Cargo;

public record DadosListagemFuncionario(
        Long id,
        String nome,
        String email,
        String telefone,
        Cargo cargo,
        Boolean situacao
) {
    public DadosListagemFuncionario (Funcionario funcionario){
        this(funcionario.getId(),
                funcionario.getNome(),
                funcionario.getEmail(),
                funcionario.getTelefone(),
                funcionario.getCargo(),
                funcionario.getSituacao());
    }
}
