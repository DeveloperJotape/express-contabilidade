package br.com.devjoaopedro.expresscontabilidade.controllers;

import br.com.devjoaopedro.expresscontabilidade.entities.empresa.DadosListagemEmpresa;
import br.com.devjoaopedro.expresscontabilidade.service.EmpresaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

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
