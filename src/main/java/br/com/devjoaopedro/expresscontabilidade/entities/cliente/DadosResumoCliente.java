package br.com.devjoaopedro.expresscontabilidade.entities.cliente;

public record DadosResumoCliente(
        Long id,
        String nome,
        String cpf,
        String email,
        String telefone
) {

    public DadosResumoCliente(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getTelefone()
        );
    }

}
