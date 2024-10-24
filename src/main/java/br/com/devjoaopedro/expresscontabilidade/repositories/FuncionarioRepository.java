package br.com.devjoaopedro.expresscontabilidade.repositories;

import br.com.devjoaopedro.expresscontabilidade.funcionario.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
