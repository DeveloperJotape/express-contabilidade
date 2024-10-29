package br.com.devjoaopedro.expresscontabilidade.controllers;

import br.com.devjoaopedro.expresscontabilidade.entities.cliente.Cliente;
import br.com.devjoaopedro.expresscontabilidade.entities.cliente.DadosAtualizarCliente;
import br.com.devjoaopedro.expresscontabilidade.entities.cliente.DadosCadastroCliente;
import br.com.devjoaopedro.expresscontabilidade.entities.cliente.DadosListagemCliente;
import br.com.devjoaopedro.expresscontabilidade.entities.empresa.Empresa;
import br.com.devjoaopedro.expresscontabilidade.repositories.ClienteRepository;
import br.com.devjoaopedro.expresscontabilidade.repositories.EmpresaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    @Transactional
    public Cliente cadastrar(@RequestBody @Valid DadosCadastroCliente dados) {
       return clienteRepository.save(new Cliente(dados));
    }

    @GetMapping
    public List<DadosListagemCliente> listar() {
        return clienteRepository.findAll()
                .stream()
                .map(DadosListagemCliente::new)
                .toList();
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizarCliente dados) {
        var cliente = clienteRepository.getReferenceById(dados.id());
        cliente.atualizarInformacoes(dados);
    }


}
