package br.com.devjoaopedro.expresscontabilidade.entities.empresa;

import br.com.devjoaopedro.expresscontabilidade.entities.cliente.Cliente;
import br.com.devjoaopedro.expresscontabilidade.entities.cliente.DadosResumoCliente;
import br.com.devjoaopedro.expresscontabilidade.entities.funcionario.Funcionario;
import br.com.devjoaopedro.expresscontabilidade.entities.funcionario.enums.Cargo;

import java.time.LocalDate;

public record DadosListagemEmpresa(
        Long id,
        String nome,
        String cnpj,
        String regTributacao,
        String qualificacao,
        String telefone,
        String email,
        Boolean status,
        LocalDate dataUltimaAtualizacao,
        DadosResumoCliente responsavel
) {
    public DadosListagemEmpresa(Empresa empresa) {
        this(empresa.getId(),
                empresa.getNome(),
                empresa.getCnpj(),
                empresa.getRegTributacao(),
                empresa.getQualificacao(),
                empresa.getTelefone(),
                empresa.getEmail(),
                empresa.getStatus(),
                empresa.getDataUltimaAtualizacao(),
                new DadosResumoCliente(empresa.getCliente()));
    }
}
