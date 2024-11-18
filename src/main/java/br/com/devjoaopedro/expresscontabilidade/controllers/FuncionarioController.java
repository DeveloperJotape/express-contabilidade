package br.com.devjoaopedro.expresscontabilidade.controllers;

import br.com.devjoaopedro.expresscontabilidade.entities.funcionario.*;
import br.com.devjoaopedro.expresscontabilidade.repositories.FuncionarioRepository;
import br.com.devjoaopedro.expresscontabilidade.service.FuncionarioService;
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
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private FuncionarioService funcionarioService;

    @Operation(
            summary = "Cadastra de funcionário",
            description = "Cadastra um novo funcionário no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os dados do funcionário cadastrado")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoFuncionario> cadastrar(@RequestBody @Valid DadosCadastroFuncionario dados, UriComponentsBuilder uriBuilder) {
        var detalhamentoFuncionario = funcionarioService.cadastrar(dados);
        var uri = uriBuilder.path("/funcionarios/{id}").buildAndExpand(detalhamentoFuncionario.id()).toUri();
        return ResponseEntity.created(uri).body(detalhamentoFuncionario);
    }

    @Operation(
            summary = "Lista de funcionários",
            description = "Lista todos os funcionários, ativos e inativos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna todos os funcionários")
    })
    @GetMapping
    public ResponseEntity<List<DadosListagemFuncionario>> listar() {
        return ResponseEntity.ok(funcionarioService.listar());
    }

    @Operation(
            summary = "Busca um funcionário",
            description = "Busca um funcionário ativo pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os dados do funcionário e empresas que ele é responsável")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoFuncionario> buscarPorId(@PathVariable Long id) {
        var funcionario = funcionarioService.buscarPorId(id);
        return ResponseEntity.ok(new DadosDetalhamentoFuncionario(funcionario));
    }

    @Operation(
            summary = "Atualiza um funcionário",
            description = "Atualiza um funcionário através do ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os dados do funcionário atualizado")
    })
    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoFuncionario> atualizar(@RequestBody @Valid DadosAtualizarFuncionario dados) {
        return ResponseEntity.ok(funcionarioService.atualizar(dados));
    }

    @Operation(
            summary = "Exclui um funcionário",
            description = "Exclui um funcionário através do ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna vazio")
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        funcionarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Desativa um funcionário",
            description = "Desativa um funcionário através do ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna vazio")
    })
    @DeleteMapping("desativar/{id}")
    @Transactional
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        funcionarioService.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Ativa um funcionário",
            description = "Ativa um funcionário através do ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna vazio")
    })
    @PutMapping("ativar/{id}")
    @Transactional
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        funcionarioService.ativar(id);
        return ResponseEntity.noContent().build();
    }

}
