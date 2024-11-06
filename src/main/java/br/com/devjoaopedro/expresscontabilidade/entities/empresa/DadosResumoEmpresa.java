package br.com.devjoaopedro.expresscontabilidade.entities.empresa;

public record DadosResumoEmpresa(
        Long id,
        String nome,
        String cnpj
) {
    public DadosResumoEmpresa(Empresa empresa) {
        this(
                empresa.getId(),
                empresa.getNome(),
                empresa.getCnpj()
        );
    }
}
