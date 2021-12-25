package br.com.cobranca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cobranca.model.Titulo;

public interface TituloRepository extends JpaRepository<Titulo, Long>{

}
