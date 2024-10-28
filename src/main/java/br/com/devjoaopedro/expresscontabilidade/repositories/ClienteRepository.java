package br.com.devjoaopedro.expresscontabilidade.repositories;


import br.com.devjoaopedro.expresscontabilidade.entities.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
