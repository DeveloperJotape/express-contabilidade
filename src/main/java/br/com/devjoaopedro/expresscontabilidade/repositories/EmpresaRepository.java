package br.com.devjoaopedro.expresscontabilidade.repositories;

import br.com.devjoaopedro.expresscontabilidade.entities.empresa.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
