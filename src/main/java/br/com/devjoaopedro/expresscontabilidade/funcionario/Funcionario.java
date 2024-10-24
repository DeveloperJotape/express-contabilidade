package br.com.devjoaopedro.expresscontabilidade.funcionario;

import br.com.devjoaopedro.expresscontabilidade.funcionario.enums.Cargo;
import br.com.devjoaopedro.expresscontabilidade.funcionario.enums.Situacao;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "funcionarios")
@Table(name = "Funcionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private Date dataNascimento;
    private String email;
    private String senha;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    private Date dataEntrada;
    private Date dataSaida;

    public Funcionario(DadosCadastroFuncionario dados) {
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.dataNascimento = dados.dataNascimento();
        this.email = dados.email();
        this.senha = dados.senha();
        this.telefone = dados.telefone();
        this.cargo = dados.cargo();
        this.situacao = dados.situacao();
        this.dataEntrada = dados.dataEntrada();
        this.dataSaida = dados.dataSaida();
    }

}
