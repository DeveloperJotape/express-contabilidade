package br.com.devjoaopedro.expresscontabilidade.controllers;

import br.com.devjoaopedro.expresscontabilidade.funcionario.DadosAtualizarFuncionario;
import br.com.devjoaopedro.expresscontabilidade.funcionario.DadosCadastroFuncionario;
import br.com.devjoaopedro.expresscontabilidade.funcionario.DadosListagemFuncionario;
import br.com.devjoaopedro.expresscontabilidade.funcionario.Funcionario;
import br.com.devjoaopedro.expresscontabilidade.repositories.FuncionarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroFuncionario dados) {
        funcionarioRepository.save(new Funcionario(dados));
    }

    @GetMapping
    public List<DadosListagemFuncionario> listar() {
        return funcionarioRepository.findAllBySituacaoTrue()
                .stream()
                .map(DadosListagemFuncionario::new)
                .toList();
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizarFuncionario dados) {
        //Busca por id o dado
        var funcionario = funcionarioRepository.getReferenceById(dados.id());
        funcionario.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        funcionarioRepository.deleteById(id);
    }

    @DeleteMapping("inativar/{id}")
    @Transactional
    public void inativar(@PathVariable Long id) {
        var funcionario = funcionarioRepository.getReferenceById(id);
        funcionario.inativar();
    }

    @PutMapping("ativar/{id}")
    @Transactional
    public void ativar(@PathVariable Long id) {
        //Busca por id o dado
        var funcionario = funcionarioRepository.getReferenceById(id);
        funcionario.ativar();
    }

}
