package br.com.cobranca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.cobranca.model.StatusTitulo;
import br.com.cobranca.model.Titulo;
import br.com.cobranca.model.TituloFiltro;
import br.com.cobranca.repository.TituloRepository;

@Service
public class TituloService {
	
	@Autowired
	private TituloRepository repository;
	
	public void salvar(Titulo titulo){
		try {
			repository.save(titulo);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido!");
		}
	}
	
	public void excluir(Long idtitulo){
		repository.deleteById(idtitulo);
	}

	public String atualizaStatus(Long id) {
		Titulo titulo = repository.getById(id);
		titulo.setStatus(StatusTitulo.RECEBIDO);
		salvar(titulo);
		return titulo.getStatus().getDescricao();
	}

	public List<Titulo> consultaFiltro(TituloFiltro filtro) {
		String descricaoPesquisa = filtro.getDescricao() == null ? "" : filtro.getDescricao();
		return repository.findByDescricaoContainingIgnoreCase(descricaoPesquisa);
	}
	
}
