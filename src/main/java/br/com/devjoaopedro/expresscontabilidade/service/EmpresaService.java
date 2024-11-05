package br.com.devjoaopedro.expresscontabilidade.service;

import br.com.devjoaopedro.expresscontabilidade.entities.empresa.DadosCadastroEmpresa;
import br.com.devjoaopedro.expresscontabilidade.entities.empresa.DadosListagemEmpresa;
import br.com.devjoaopedro.expresscontabilidade.entities.empresa.Empresa;
import br.com.devjoaopedro.expresscontabilidade.repositories.ClienteRepository;
import br.com.devjoaopedro.expresscontabilidade.repositories.EmpresaRepository;
import br.com.devjoaopedro.expresscontabilidade.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public DadosListagemEmpresa cadastrar(DadosCadastroEmpresa dados, Long clienteId, Long funcionarioId) {
        var cliente = clienteRepository.getReferenceById(clienteId);
        var funcionario = funcionarioRepository.getReferenceById(funcionarioId);
        var empresa = new Empresa(dados, cliente, funcionario);
        empresaRepository.save(empresa);
        return new DadosListagemEmpresa(empresa);
    }

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
