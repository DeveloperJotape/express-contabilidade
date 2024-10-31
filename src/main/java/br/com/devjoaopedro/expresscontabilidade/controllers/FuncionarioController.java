package br.com.devjoaopedro.expresscontabilidade.controllers;

import br.com.devjoaopedro.expresscontabilidade.entities.funcionario.DadosAtualizarFuncionario;
import br.com.devjoaopedro.expresscontabilidade.entities.funcionario.DadosCadastroFuncionario;
import br.com.devjoaopedro.expresscontabilidade.entities.funcionario.DadosListagemFuncionario;
import br.com.devjoaopedro.expresscontabilidade.entities.funcionario.Funcionario;
import br.com.devjoaopedro.expresscontabilidade.repositories.FuncionarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    /*@PostMapping
    @Transactional
    public ResponseEntity<> cadastrar(@RequestBody @Valid DadosCadastroFuncionario dados) {
        funcionarioRepository.save(new Funcionario(dados));
    }*/

    @GetMapping
    public ResponseEntity<List<DadosListagemFuncionario>> listar() {
        var listaFuncionarios = funcionarioRepository.findAllBySituacaoTrue()
                .stream()
                .map(DadosListagemFuncionario::new)
                .toList();

        return ResponseEntity.ok(listaFuncionarios);
    }

    /*@PutMapping
    @Transactional
    public ResponseEntity<?> atualizar(@RequestBody @Valid DadosAtualizarFuncionario dados) {
        //Busca por id o dado
        var funcionario = funcionarioRepository.getReferenceById(dados.id());
        funcionario.atualizarInformacoes(dados);
    }*/

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

    /*@PutMapping("ativar/{id}")
    @Transactional
    public ResponseEntity<> ativar(@PathVariable Long id) {
        //Busca o dado pelo id
        var funcionario = funcionarioRepository.getReferenceById(id);
        funcionario.ativar();
    }*/

}
