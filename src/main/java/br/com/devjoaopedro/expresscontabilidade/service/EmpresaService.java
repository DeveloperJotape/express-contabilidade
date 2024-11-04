package br.com.devjoaopedro.expresscontabilidade.service;

import br.com.devjoaopedro.expresscontabilidade.entities.empresa.DadosListagemEmpresa;
import br.com.devjoaopedro.expresscontabilidade.entities.empresa.Empresa;
import br.com.devjoaopedro.expresscontabilidade.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<DadosListagemEmpresa> listar() {
        return empresaRepository.findAll()
                .stream()
                .map(DadosListagemEmpresa::new)
                .toList();
    }

    public Empresa buscarPorId(Long id) {
        return empresaRepository.getReferenceById(id);
    }

    public void desativar(Long id) {
        var empresa = empresaRepository.getReferenceById(id);
        empresa.desativar();
    }

    public void ativar(Long id) {
        var empresa = empresaRepository.getReferenceById(id);
        empresa.ativar();
    }
}
