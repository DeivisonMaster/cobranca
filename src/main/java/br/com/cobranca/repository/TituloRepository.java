package br.com.cobranca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cobranca.model.Titulo;

public interface TituloRepository extends JpaRepository<Titulo, Long>{
	
	public List<Titulo> findByDescricaoContainingIgnoreCase(String descricao);
}
