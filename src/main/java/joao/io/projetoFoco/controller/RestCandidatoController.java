package joao.io.projetoFoco.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import joao.io.projetoFoco.exeception.CandidatoException;
import joao.io.projetoFoco.model.Candidato;
import joao.io.projetoFoco.rest.Resposta;
import joao.io.projetoFoco.service.CandidatoService;
import joao.io.projetoFoco.util.Constantes;

@RestController
public class RestCandidatoController {
	@Autowired
	private CandidatoService candidatoService;
	
	Logger logger = LoggerFactory.getLogger(RestCandidatoController.class);
	
	@RequestMapping(path=Constantes.Url.URL_CANDIDATO, method = RequestMethod.GET)
	public @ResponseBody Resposta consultarTodos() {
		Resposta resposta = new Resposta();
		try {
			resposta.setResposta(candidatoService.buscarTodos());
			logger.info("Consultando todos os candidato");
		}catch(CandidatoException e) {
			resposta.setCodigo(Constantes.Status.CODIGO_ERRO);
			resposta.setMensagem(e.getMensagem());
		}
		return resposta;
	}
	
	@RequestMapping(path=Constantes.Url.URL_CANDIDATO + "/{id}", method = RequestMethod.GET)
	public @ResponseBody Resposta consultarcandidatoPorId(@PathVariable String id) {
		Resposta resposta = new Resposta();
		try {
			Candidato candidato = candidatoService.buscarCandidatoPorId(id);
			resposta.setResposta(candidato); 
			logger.info(String.format("Consultando candidato %s", id));
		}catch(CandidatoException e) {
			resposta.setCodigo(Constantes.Status.CODIGO_ERRO);
			resposta.setMensagem(e.getMensagem());
		}
		return resposta;
	}
	
	@RequestMapping(path=Constantes.Url.URL_CANDIDATO, method = RequestMethod.POST)
	public @ResponseBody Resposta salvarcandidato(@RequestBody Candidato candidato) {
		Resposta resposta = new Resposta();
		try {
			candidato.setId(null);
			candidatoService.salvarCandidato(candidato);
			resposta.setResposta(candidato); 
			logger.info(String.format("Salvando novo candidato %s", candidato.getNome()));
		}catch(CandidatoException e) {
			resposta.setCodigo(Constantes.Status.CODIGO_ERRO);
			resposta.setMensagem(e.getMensagem());
		}
		return resposta;
	}
	
	@RequestMapping(path=Constantes.Url.URL_CANDIDATO + "/{id}", method = RequestMethod.PUT)
	public @ResponseBody Resposta atualizarcandidato(@RequestBody Candidato candidatoAtualizado, @PathVariable String id) {
		Resposta resposta = new Resposta();
		try {
		    Candidato candidatoSalvo = candidatoService.buscarCandidatoPorId(id);
		    candidatoSalvo.setId(candidatoSalvo.getId());
		    Candidato candidato = candidatoService.salvarCandidato(candidatoSalvo);
			resposta.setResposta(candidato);
			logger.info(String.format("Atualizando candidato %s", id));
		}catch(CandidatoException e) {
			resposta.setCodigo(Constantes.Status.CODIGO_ERRO);
			resposta.setMensagem(e.getMensagem());
		}
		return resposta;
	}
	
	@RequestMapping(path=Constantes.Url.URL_CANDIDATO + "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Resposta removercandidato(@PathVariable String id) {
		Resposta resposta = new Resposta();
		try {
			candidatoService.removerCandidato(id);
			logger.info(String.format("Removendo candidato %s", id));
		}catch(CandidatoException e) {
			resposta.setCodigo(Constantes.Status.CODIGO_ERRO);
			resposta.setMensagem(e.getMensagem());
		}
		return resposta;
	}
	
}
