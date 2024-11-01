package br.com.devjoaopedro.expresscontabilidade.controllers;

import br.com.devjoaopedro.expresscontabilidade.entities.cliente.*;
import br.com.devjoaopedro.expresscontabilidade.repositories.ClienteRepository;
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

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCliente> cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder) {
        var cliente = new Cliente(dados);
        clienteRepository.save(cliente);

        var uri = uriBuilder.path("/clientes/{id}")
                .buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoCliente(cliente));

    }

    @GetMapping
    public ResponseEntity<List<DadosListagemCliente>> listar() {
        var listaClientes = clienteRepository.findAll()
                .stream()
                .map(DadosListagemCliente::new)
                .toList();

        return ResponseEntity.ok(listaClientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoCliente> buscarPorId(@PathVariable Long id) {
        var cliente = clienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCliente> atualizar(@RequestBody @Valid DadosAtualizarCliente dados) {
        var cliente = clienteRepository.getReferenceById(dados.id());
        cliente.atualizarInformacoes(dados);
        clienteRepository.save(cliente);

        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }

    @PutMapping("/desativar/{id}")
    @Transactional
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        var cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.desativar();
        clienteRepository.save(cliente);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/reativar/{id}")
    @Transactional
    public ResponseEntity<Void> reativarCliente(@PathVariable Long id) {
        var clienteOptional = clienteRepository.findById(id);

        if(clienteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var cliente = clienteOptional.get();
        cliente.reativar();
        clienteRepository.save(cliente);

        return ResponseEntity.ok().build();
    }


}
