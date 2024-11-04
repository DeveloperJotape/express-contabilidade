package br.com.devjoaopedro.expresscontabilidade.controllers;

import br.com.devjoaopedro.expresscontabilidade.entities.cliente.*;
import br.com.devjoaopedro.expresscontabilidade.repositories.ClienteRepository;
import br.com.devjoaopedro.expresscontabilidade.service.ClienteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCliente> cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder) {
        var cliente = clienteService.cadastrar(dados);
        var uri = uriBuilder.path("/clientes/{id}")
                .buildAndExpand(cliente.id()).toUri();
        return ResponseEntity.created(uri).body(cliente);

    }

    @GetMapping
    public ResponseEntity<List<DadosListagemCliente>> listar() {
        return ResponseEntity.ok(clienteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoCliente> buscarPorId(@PathVariable Long id) {
        var cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCliente> atualizar(@RequestBody @Valid DadosAtualizarCliente dados) {
        return ResponseEntity.ok(clienteService.atualizar(dados));
    }

    @PutMapping("/desativar/{id}")
    @Transactional
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        clienteService.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/reativar/{id}")
    @Transactional
    public ResponseEntity<Void> reativarCliente(@PathVariable Long id) {
        clienteService.reativar(id);
        return ResponseEntity.ok().build();
    }


}
