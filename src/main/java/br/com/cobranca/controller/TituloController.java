package br.com.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import br.com.cobranca.service.TituloService;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	private static final String CADASTRO_VIEW = "CadastroTitulo";
	
	@Autowired
	private TituloService service;
	
	@Autowired
	private TituloRepository repository;
	
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("titulo", new Titulo());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String novo(@Validated Titulo titulo, Errors erros, RedirectAttributes redirect){
		if(erros.hasErrors()){
			return CADASTRO_VIEW;
		}
		try {
			service.salvar(titulo);
			redirect.addFlashAttribute("mensagem", "Título salvo com sucesso!");
			return "redirect:/titulos/novo";
		} catch (IllegalArgumentException e) {
			erros.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_VIEW;
		}
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
		Titulo titulo = repository.getById(id);
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(titulo);
		return mv;
	}
	
	@RequestMapping(value = "{idtitulo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long idtitulo, RedirectAttributes atributos){
		service.excluir(idtitulo);
		
		atributos.addFlashAttribute("mensagem", "Título excluido com sucesso!");
		return "redirect:/titulos";
	}
	
	@ModelAttribute("listaStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}
	
}
