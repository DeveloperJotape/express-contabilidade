package br.com.devjoaopedro.expresscontabilidade.entities.funcionario;

import br.com.devjoaopedro.expresscontabilidade.entities.empresa.Empresa;
import br.com.devjoaopedro.expresscontabilidade.entities.funcionario.enums.Cargo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    private LocalDate dataEntrada;
    private LocalDate dataSaida;

    private Boolean situacao;

    @OneToMany(mappedBy = "funcionarioResponsavel")
    private List<Empresa> empresas = new ArrayList<>();

    public Funcionario(DadosCadastroFuncionario dados) {
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.dataNascimento = dados.dataNascimento();
        this.email = dados.email();
        this.senha = dados.senha();
        this.telefone = dados.telefone();
        this.cargo = dados.cargo();
        this.dataEntrada = dados.dataEntrada();
        this.dataSaida = dados.dataSaida();
        this.situacao = true;
    }

    public void atualizarInformacoes(DadosAtualizarFuncionario dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.senha() != null) {
            this.senha = dados.senha();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.cargo() != null) {
            this.cargo = dados.cargo();
        }
        if (dados.dataSaida() != null) {
            this.dataSaida = dados.dataSaida();
        }
    }

    public void desativar() {
        this.situacao = false;
    }

    public void ativar() {
        this.situacao = true;
    }

}
