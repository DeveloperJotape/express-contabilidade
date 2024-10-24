package br.com.devjoaopedro.expresscontabilidade.controllers;

import br.com.devjoaopedro.expresscontabilidade.funcionario.DadosCadastroFuncionario;
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



}
