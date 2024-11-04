package br.com.devjoaopedro.expresscontabilidade.service;

import br.com.devjoaopedro.expresscontabilidade.entities.funcionario.*;
import br.com.devjoaopedro.expresscontabilidade.repositories.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public DadosDetalhamentoFuncionario cadastrar(DadosCadastroFuncionario dados) {
        var funcionario = new Funcionario(dados);
        funcionarioRepository.save(funcionario);
        return new DadosDetalhamentoFuncionario(funcionario);
    }

    public List<DadosListagemFuncionario> listar() {
        return funcionarioRepository.findAllBySituacaoTrue()
                .stream()
                .map(DadosListagemFuncionario::new)
                .toList();
    }

    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.getReferenceById(id);
    }

    @Transactional
    public DadosDetalhamentoFuncionario atualizar(DadosAtualizarFuncionario dados) {
        var funcionario = funcionarioRepository.getReferenceById(dados.id());
        funcionario.atualizarInformacoes(dados);
        return new DadosDetalhamentoFuncionario(funcionario);
    }

    @Transactional
    public void excluir(Long id) {
        var funcionario = funcionarioRepository.getReferenceById(id);
        funcionarioRepository.delete(funcionario);
    }

    @Transactional
    public void desativar(Long id) {
        var funcionario = funcionarioRepository.getReferenceById(id);
        funcionario.desativar();
    }

    @Transactional
    public void ativar(Long id) {
        var funcionario = funcionarioRepository.getReferenceById(id);
        funcionario.ativar();
    }

}
