package br.sp.gov.etesp.tasks.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.sp.gov.etesp.tasks.model.Tarefa;
import br.sp.gov.etesp.tasks.repository.TarefaRepository;
import br.sp.gov.etesp.tasks.utils.StatusTarefa;

@Controller
public class HomeController {

	@Autowired
	TarefaRepository repository;
	
	@GetMapping("/")
	public String abrirHome() {
		return "home";
		
	}
	
	@PostMapping("/adicionar")
	
	
	public ModelAndView adicionarTarefa(Tarefa tarefa) {
		
		List<Tarefa> tarefas =  new ArrayList<Tarefa>();
		
		tarefa.setStatus(StatusTarefa.ABERTO.name());
		Date dataAtualSistema = new Date();
		//LocalDate dataSistema = LocalDateTime.now();
		tarefa.setDataInicio(dataAtualSistema);
		
		repository.save(tarefa);
		
		//tarefas.add(tarefa);
		
		tarefas = repository.findAll();
		ModelAndView view = new ModelAndView("home");
		view.addObject("tarefas", tarefas);
		
		return view;
	}
	
	
}
