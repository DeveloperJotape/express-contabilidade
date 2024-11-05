package br.com.devjoaopedro.expresscontabilidade.service;

import br.com.devjoaopedro.expresscontabilidade.entities.cliente.*;
import br.com.devjoaopedro.expresscontabilidade.repositories.ClienteRepository;
import br.com.devjoaopedro.expresscontabilidade.repositories.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional
    public DadosDetalhamentoCliente cadastrar(DadosCadastroCliente dados) {
        var cliente = new Cliente(dados, funcionarioRepository);
        clienteRepository.save(cliente);
        return new DadosDetalhamentoCliente(cliente);
    }

    public List<DadosListagemCliente> listar() {
        return clienteRepository.findAll()
                .stream()
                .map(DadosListagemCliente::new)
                .toList();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.getReferenceById(id);
    }

    @Transactional
    public DadosDetalhamentoCliente atualizar(DadosAtualizarCliente dados) {
        var cliente = clienteRepository.getReferenceById(dados.id());
        cliente.atualizarInformacoes(dados);
        return new DadosDetalhamentoCliente(cliente);
    }

    @Transactional
    public void desativar(Long id) {
        var cliente = clienteRepository.getReferenceById(id);
        cliente.desativar();
    }

    @Transactional
    public void reativar(Long id) {
        var cliente = clienteRepository.getReferenceById(id);
        cliente.reativar();
    }
}
