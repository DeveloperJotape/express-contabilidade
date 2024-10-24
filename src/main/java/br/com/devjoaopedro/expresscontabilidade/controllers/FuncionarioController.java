package br.com.devjoaopedro.expresscontabilidade.controllers;

import br.com.devjoaopedro.expresscontabilidade.funcionario.DadosCadastroFuncionario;
import br.com.devjoaopedro.expresscontabilidade.funcionario.Funcionario;
import br.com.devjoaopedro.expresscontabilidade.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroFuncionario dados) {
        funcionarioRepository.save(new Funcionario(dados));
    }

}
