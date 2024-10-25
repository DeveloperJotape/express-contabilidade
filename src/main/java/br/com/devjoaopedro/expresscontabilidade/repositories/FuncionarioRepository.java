package br.com.devjoaopedro.expresscontabilidade.repositories;

import br.com.devjoaopedro.expresscontabilidade.entities.funcionario.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    List<Funcionario> findAllBySituacaoTrue();
}
