package br.com.devjoaopedro.expresscontabilidade.funcionario;

import br.com.devjoaopedro.expresscontabilidade.funcionario.enums.Cargo;
import br.com.devjoaopedro.expresscontabilidade.funcionario.enums.Situacao;

public record DadosListagemFuncionario(
        String nome,
        String email,
        String telefone,
        Cargo cargo,
        Situacao situacao
) {
    public DadosListagemFuncionario (Funcionario funcionario){
        this(funcionario.getNome(),
                funcionario.getEmail(),
                funcionario.getTelefone(),
                funcionario.getCargo(),
                funcionario.getSituacao());
    }
}
