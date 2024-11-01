package br.com.devjoaopedro.expresscontabilidade.controllers;

import br.com.devjoaopedro.expresscontabilidade.entities.funcionario.*;
import br.com.devjoaopedro.expresscontabilidade.repositories.FuncionarioRepository;
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

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoFuncionario> cadastrar(@RequestBody @Valid DadosCadastroFuncionario dados, UriComponentsBuilder uriBuilder) {
        var funcionario = new Funcionario(dados);
        funcionarioRepository.save(funcionario);

        var uri = uriBuilder.path("/funcionarios/{id}")
                .buildAndExpand(funcionario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoFuncionario(funcionario));
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemFuncionario>> listar() {
        var listaFuncionarios = funcionarioRepository.findAllBySituacaoTrue()
                .stream()
                .map(DadosListagemFuncionario::new)
                .toList();

        return ResponseEntity.ok(listaFuncionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoFuncionario> buscarPorId(@PathVariable Long id) {
        var funcionario = funcionarioRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoFuncionario(funcionario));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoFuncionario> atualizar(@RequestBody @Valid DadosAtualizarFuncionario dados) {
        //Busca por id o dado
        var funcionario = funcionarioRepository.getReferenceById(dados.id());
        funcionario.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoFuncionario(funcionario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        funcionarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("desativar/{id}")
    @Transactional
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        var funcionario = funcionarioRepository.getReferenceById(id);
        funcionario.desativar();

        return ResponseEntity.noContent().build();
    }

    @PutMapping("ativar/{id}")
    @Transactional
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        //Busca o dado pelo id
        var funcionario = funcionarioRepository.getReferenceById(id);
        funcionario.ativar();

        return ResponseEntity.noContent().build();
    }

}
