package br.com.devjoaopedro.expresscontabilidade.repositories;

import br.com.devjoaopedro.expresscontabilidade.funcionario.DadosListagemFuncionario;
import br.com.devjoaopedro.expresscontabilidade.funcionario.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    List<Funcionario> findAllBySituacaoTrue();
}
