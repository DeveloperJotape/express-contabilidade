package br.com.devjoaopedro.expresscontabilidade.funcionario;

import br.com.devjoaopedro.expresscontabilidade.funcionario.enums.Cargo;
import br.com.devjoaopedro.expresscontabilidade.funcionario.enums.Situacao;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    private LocalDate dataNascimento;
    private String email;
    private String senha;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    private LocalDate dataEntrada;
    private LocalDate dataSaida;

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

    public void atualizarInformacoes(DadosAtualizarFuncionario dados) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }
        if(dados.email() != null) {
            this.email = dados.email();
        }
        if(dados.senha() != null) {
            this.senha = dados.senha();
        }
        if(dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if(dados.cargo() != null) {
            this.cargo = dados.cargo();
        }
        if(dados.situacao() != null) {
            this.situacao = dados.situacao();
        }
        if(dados.dataSaida() != null) {
            this.dataSaida = dados.dataSaida();
        }
    }
}
