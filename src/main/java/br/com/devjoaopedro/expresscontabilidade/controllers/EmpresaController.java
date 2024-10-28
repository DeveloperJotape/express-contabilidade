package br.com.devjoaopedro.expresscontabilidade.controllers;

import br.com.devjoaopedro.expresscontabilidade.entities.empresa.Empresa;
import br.com.devjoaopedro.expresscontabilidade.repositories.EmpresaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping
    public List<Empresa> listar() {
        return empresaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Empresa buscarPorId(@PathVariable Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
    }

}
