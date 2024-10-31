package br.com.devjoaopedro.expresscontabilidade.controllers;

import br.com.devjoaopedro.expresscontabilidade.entities.empresa.DadosListagemEmpresa;
import br.com.devjoaopedro.expresscontabilidade.entities.empresa.Empresa;
import br.com.devjoaopedro.expresscontabilidade.repositories.EmpresaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping
    public ResponseEntity<List<DadosListagemEmpresa>> listar() {
        var listaEmpresas = empresaRepository.findAll()
                .stream()
                .map(DadosListagemEmpresa::new)
                .toList();

        return ResponseEntity.ok(listaEmpresas);
    }

    @GetMapping("/{id}")
    public Empresa buscarPorId(@PathVariable Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
    }

    @PutMapping("/ativar/{id}")
    @Transactional
    public void ativarEmpresa(@PathVariable Long id) {
        var empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        empresa.ativar();
        empresaRepository.save(empresa);
    }

    @PutMapping("/desativar/{id}")
    @Transactional
    public ResponseEntity<Void> desativarEmpresa(@PathVariable Long id) {
        var empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        empresa.desativar();
        empresaRepository.save(empresa);
        return ResponseEntity.noContent().build();
    }

}
