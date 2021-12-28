package br.com.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.cobranca.model.StatusTitulo;
import br.com.cobranca.model.Titulo;
import br.com.cobranca.repository.TituloRepository;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	private static final String CADASTRO_VIEW = "CadastroTitulo";
	@Autowired
	private TituloRepository repository;
	
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("titulo", new Titulo());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView novo(@Validated Titulo titulo, Errors erros, RedirectAttributes redirect){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		if(erros.hasErrors()){
			return mv;
		}
		repository.save(titulo);
		redirect.addFlashAttribute("mensagem", "Título salvo com sucesso!");
		
		return new ModelAndView("redirect:/titulos/novo");
	}
	
	@RequestMapping
	public ModelAndView consultar(){
		List<Titulo> listaTitulos = repository.findAll();
		ModelAndView mv = new ModelAndView("ConsultaTitulo");
		mv.addObject("listaTitulos", listaTitulos);	
		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable Long id){
		Titulo titulo = repository.getOne(id);
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(titulo);
		return mv;
	}
	
	@ModelAttribute("listaStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}
	
}
