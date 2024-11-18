package br.com.devjoaopedro.expresscontabilidade.entities.empresa;

import br.com.devjoaopedro.expresscontabilidade.entities.cliente.Cliente;
import br.com.devjoaopedro.expresscontabilidade.entities.funcionario.Funcionario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(nullable = false, unique = true)
    private String cnpj;

    private String uf;
    private String regTributacao;
    private String qualificacao;
    private String inscricaoEstadual;
    private String telefone;
    private String email;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false)
    private LocalDate dataUltimaAtualizacao;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonBackReference
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    @JsonBackReference
    private Funcionario funcionarioResponsavel;

    public Empresa(DadosCadastroEmpresa dados, Cliente cliente, Funcionario funcionario) {
        this.nome = dados.nome();
        this.cnpj = dados.cnpj();
        this.uf = dados.uf();
        this.regTributacao = dados.regTributacao();
        this.qualificacao = dados.qualificacao();
        this.inscricaoEstadual = dados.inscricaoEstadual();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.status = dados.status();
        this.dataUltimaAtualizacao = LocalDate.now();
        this.cliente = cliente;
        this.funcionarioResponsavel = funcionario;
    }

    /*public void atualizarInformacoes(DadosAtualizarEmpresa dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.cnpj() != null) {
            this.cnpj = dados.cnpj();
        }
        if (dados.uf() != null) {
            this.uf = dados.uf();
        }
        if (dados.regTributacao() != null) {
            this.regTributacao = dados.regTributacao();
        }
        if (dados.qualificacao() != null) {
            this.qualificacao = dados.qualificacao();
        }
        if (dados.inscricaoEstadual() != null) {
            this.inscricaoEstadual = dados.inscricaoEstadual();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.funcionarioResponsavel() != null) {
            this.funcionarioResponsavel = dados.funcionarioResponsavel();
        }
    }*/

    @PreUpdate
    private void atualizarDataUltimaAtualizacao() {
        this.dataUltimaAtualizacao = LocalDate.now();
    }

    public void ativar() {
        this.status = true;
    }

    public void desativar() {
        this.status = false;
    }

}
