package br.com.devjoaopedro.expresscontabilidade.controllers;

import br.com.devjoaopedro.expresscontabilidade.entities.funcionario.*;
import br.com.devjoaopedro.expresscontabilidade.repositories.FuncionarioRepository;
import br.com.devjoaopedro.expresscontabilidade.service.FuncionarioService;
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

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoFuncionario> cadastrar(@RequestBody @Valid DadosCadastroFuncionario dados, UriComponentsBuilder uriBuilder) {
        var detalhamentoFuncionario = funcionarioService.cadastrar(dados);
        var uri = uriBuilder.path("/funcionarios/{id}").buildAndExpand(detalhamentoFuncionario.id()).toUri();
        return ResponseEntity.created(uri).body(detalhamentoFuncionario);
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemFuncionario>> listar() {
        return ResponseEntity.ok(funcionarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoFuncionario> buscarPorId(@PathVariable Long id) {
        var funcionario = funcionarioService.buscarPorId(id);
        return ResponseEntity.ok(new DadosDetalhamentoFuncionario(funcionario));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoFuncionario> atualizar(@RequestBody @Valid DadosAtualizarFuncionario dados) {
        return ResponseEntity.ok(funcionarioService.atualizar(dados));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        funcionarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("desativar/{id}")
    @Transactional
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        funcionarioService.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("ativar/{id}")
    @Transactional
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        funcionarioService.ativar(id);
        return ResponseEntity.noContent().build();
    }

}
