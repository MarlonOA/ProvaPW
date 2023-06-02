package br.com.sobre.sobremesa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sobre.sobremesa.model.Sobremesa;

public interface SobremesaRepository extends JpaRepository<Sobremesa,Long> {
    List<Sobremesa> findByDeletedIsNull();
}
