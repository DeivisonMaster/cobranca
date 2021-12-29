package br.com.cobranca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.cobranca.model.Titulo;
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
	
}
