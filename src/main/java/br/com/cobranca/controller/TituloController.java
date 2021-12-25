package br.com.cobranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.cobranca.model.Titulo;
import br.com.cobranca.repository.TituloRepository;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired
	private TituloRepository repository;
	
	@RequestMapping("/novo")
	public String novo(){
		return "CadastroTitulo";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String novo(Titulo titulo){
		System.out.println(titulo.getDescricao());
		
		repository.save(titulo);
		return "CadastroTitulo";
	}
	
}
