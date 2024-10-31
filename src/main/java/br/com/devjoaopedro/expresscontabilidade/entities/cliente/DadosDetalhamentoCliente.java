package br.com.devjoaopedro.expresscontabilidade.entities.cliente;

import br.com.devjoaopedro.expresscontabilidade.entities.empresa.Empresa;

import java.time.LocalDate;
import java.util.List;

public record DadosDetalhamentoCliente(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String email,
        String telefone,
        Boolean status,
        List<Empresa> empresas
) {
    public DadosDetalhamentoCliente(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getDataNascimento(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getStatus(),
                cliente.getEmpresas()
        );
    }
}
