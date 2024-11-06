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
