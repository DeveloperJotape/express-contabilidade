package br.com.devjoaopedro.expresscontabilidade.controllers;

import br.com.devjoaopedro.expresscontabilidade.entities.empresa.DadosCadastroEmpresa;
import br.com.devjoaopedro.expresscontabilidade.entities.empresa.DadosListagemEmpresa;
import br.com.devjoaopedro.expresscontabilidade.service.EmpresaService;
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
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Operation(
            summary = "Cadastra uma empresa",
            description = "Cadastra uma nova empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os dados cadastrados")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemEmpresa> cadastrar(@RequestBody @Valid DadosCadastroEmpresa dados, @RequestParam Long clienteId, @RequestParam Long funcionarioId, UriComponentsBuilder uriBuilder) {
        var empresa = empresaService.cadastrar(dados, clienteId, funcionarioId);
        var uri = uriBuilder.path("/empresas/{id}").buildAndExpand(empresa.id()).toUri();
        return ResponseEntity.created(uri).body(empresa);
    }

    @Operation(
            summary = "Lista todas as empresas",
            description = "Lista as empresas, também mostra seu dono(cliente) e o funcionário responsável por ela")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna dados da empresa, dono(cliente) e funcionário responsável")
    })
    @GetMapping
    public ResponseEntity<List<DadosListagemEmpresa>> listar() {
        return ResponseEntity.ok(empresaService.listar());
    }

    @Operation(
            summary = "Lista uma empresa",
            description = "Lista uma empresa pelo ID, também mostra seu dono(cliente) e o funcionário responsável por ela")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna dados da empresa, dono(cliente) e funcionário responsável")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemEmpresa> buscarPorId(@PathVariable Long id) {
        var empresa = empresaService.buscarPorId(id);
        return ResponseEntity.ok(new DadosListagemEmpresa(empresa));
    }

    @Operation(
            summary = "Desativa uma empresa",
            description = "Desativa uma empresa através do ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna vazio")
    })
    @PutMapping("/desativar/{id}")
    @Transactional
    public ResponseEntity<Void> desativarEmpresa(@PathVariable Long id) {
        empresaService.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Ativa uma empresa",
            description = "Ativa uma empresa através do ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna vazio")
    })
    @PutMapping("/ativar/{id}")
    @Transactional
    public ResponseEntity<Void> ativarEmpresa(@PathVariable Long id) {
        empresaService.ativar(id);
        return ResponseEntity.noContent().build();
    }

}
