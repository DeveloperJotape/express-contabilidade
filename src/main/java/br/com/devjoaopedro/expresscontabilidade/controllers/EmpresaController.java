package br.com.devjoaopedro.expresscontabilidade.controllers;

import br.com.devjoaopedro.expresscontabilidade.entities.empresa.DadosCadastroEmpresa;
import br.com.devjoaopedro.expresscontabilidade.entities.empresa.DadosListagemEmpresa;
import br.com.devjoaopedro.expresscontabilidade.service.EmpresaService;
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

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemEmpresa> cadastrar(@RequestBody @Valid DadosCadastroEmpresa dados, @RequestParam Long clienteId, @RequestParam Long funcionarioId, UriComponentsBuilder uriBuilder) {
        var empresa = empresaService.cadastrar(dados, clienteId, funcionarioId);
        var uri = uriBuilder.path("/empresas/{id}").buildAndExpand(empresa.id()).toUri();
        return ResponseEntity.created(uri).body(empresa);
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemEmpresa>> listar() {
        return ResponseEntity.ok(empresaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemEmpresa> buscarPorId(@PathVariable Long id) {
        var empresa = empresaService.buscarPorId(id);
        return ResponseEntity.ok(new DadosListagemEmpresa(empresa));
    }

    @PutMapping("/desativar/{id}")
    @Transactional
    public ResponseEntity<Void> desativarEmpresa(@PathVariable Long id) {
        empresaService.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/ativar/{id}")
    @Transactional
    public ResponseEntity<Void> ativarEmpresa(@PathVariable Long id) {
        empresaService.ativar(id);
        return ResponseEntity.noContent().build();
    }

}
