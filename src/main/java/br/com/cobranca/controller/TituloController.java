package br.com.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cobranca.model.StatusTitulo;
import br.com.cobranca.model.Titulo;
import br.com.cobranca.repository.TituloRepository;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired
	private TituloRepository repository;
	
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject("titulo", new Titulo());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView novo(@Validated Titulo titulo, Errors erros){
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		if(erros.hasErrors()){
			return mv;
		}
		repository.save(titulo);
		
		mv.addObject("mensagem", "Título salvo com sucesso!");
		return mv;
	}
	
	@RequestMapping
	public ModelAndView consultar(){
		List<Titulo> listaTitulos = repository.findAll();
		ModelAndView mv = new ModelAndView("ConsultaTitulo");
		mv.addObject("listaTitulos", listaTitulos);	
		return mv;
	}
	
	@ModelAttribute("listaStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}
	
}
