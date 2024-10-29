package br.com.devjoaopedro.expresscontabilidade.entities.cliente;

import br.com.devjoaopedro.expresscontabilidade.entities.empresa.Empresa;

import java.time.LocalDate;
import java.util.List;

public record DadosListagemCliente (
        Long id,
        String nome,
        String cpf,
        String email,
        String telefone,
        List<Empresa> empresas
){
    public DadosListagemCliente(Cliente cliente){
        this(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getEmpresas()
        );
    }
}
