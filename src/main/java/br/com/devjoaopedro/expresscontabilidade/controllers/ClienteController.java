package br.com.devjoaopedro.expresscontabilidade.controllers;

import br.com.devjoaopedro.expresscontabilidade.entities.cliente.*;
import br.com.devjoaopedro.expresscontabilidade.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    private ClienteService clienteService;

    @Operation(
            summary = "Cadastra um cliente",
            description = "Cadastra um cliente e suas respectivas empresas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os dados do cliente e suas respectivas empresas")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCliente> cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder) {
        var cliente = clienteService.cadastrar(dados);
        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.id()).toUri();
        return ResponseEntity.created(uri).body(cliente);

    }

    @Operation(
            summary = "Lista os clientes",
            description = "Lista todos os clientes cadastrados no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os dados do cliente e suas respectivas empresas")
    })
    @GetMapping
    public ResponseEntity<List<DadosListagemCliente>> listar() {
        return ResponseEntity.ok(clienteService.listar());
    }

    @Operation(
            summary = "Busca um cliente",
            description = "Busca um cliente pelo ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os dados do cliente e suas respectivas empresas")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoCliente> buscarPorId(@PathVariable Long id) {
        var cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }

    @Operation(
            summary = "Atualiza um cliente",
            description = "Atualiza os dados do cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os dados do cliente")
    })
    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCliente> atualizar(@RequestBody @Valid DadosAtualizarCliente dados) {
        return ResponseEntity.ok(clienteService.atualizar(dados));
    }

    @Operation(
            summary = "Desativa um cliente",
            description = "Desativa um cliente através do ID fornecido, além disso todas as suas respectivas empresas são desativadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna vazio")
    })
    @PutMapping("/desativar/{id}")
    @Transactional
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        clienteService.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Reativa um cliente",
            description = "Reativa um cliente através do ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna vazio")
    })
    @PutMapping("/reativar/{id}")
    @Transactional
    public ResponseEntity<Void> reativarCliente(@PathVariable Long id) {
        clienteService.reativar(id);
        return ResponseEntity.ok().build();
    }


}
