package br.com.devjoaopedro.expresscontabilidade.entities.cliente;

import br.com.devjoaopedro.expresscontabilidade.entities.empresa.Empresa;
import br.com.devjoaopedro.expresscontabilidade.entities.funcionario.Funcionario;
import br.com.devjoaopedro.expresscontabilidade.repositories.FuncionarioRepository;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
    private String telefone;

    @Column(nullable = false)
    private Boolean status = true;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Empresa> empresas = new ArrayList<>();

    public Cliente(DadosCadastroCliente dados, FuncionarioRepository funcionarioRepository) {
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.dataNascimento = dados.dataNascimento();
        this.email = dados.email();
        this.telefone = dados.telefone();
        if (dados.empresas() != null) {
            dados.empresas().forEach(dto -> {
                Funcionario funcionario = funcionarioRepository.findById(dto.funcionarioId())
                        .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));
                this.empresas.add(new Empresa(dto, this, funcionario));
            });
        }
    }

    public void atualizarInformacoes(DadosAtualizarCliente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.status() != null) {
            this.status = dados.status();
            // Se o cliente for desativado, todas as empresas também são desativadas
            if (!this.status) {
                this.empresas.forEach(empresa -> empresa.setStatus(false));
            }
        }
    }

    public void desativar() {
        this.status = false;
        //Desativa todas as empresas associadas
        this.empresas.forEach(empresa -> empresa.setStatus(false));
    }

    public void reativar() {
        this.status = true;
    }

}
