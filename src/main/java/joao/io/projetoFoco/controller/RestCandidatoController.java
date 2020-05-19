package joao.io.projetoFoco.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
	
	@Autowired
	private JavaMailSender mailSender;

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
			//name, dataNascimento, cpf, email, telefone, concorda
			Candidato candidatoSalvo = candidatoService.buscarCandidatoPorId(id);
			candidatoSalvo.setNome(candidatoAtualizado.getNome());
			candidatoSalvo.setEmail(candidatoAtualizado.getEmail());
			candidatoSalvo.setDataNascimento(candidatoAtualizado.getDataNascimento());
			candidatoSalvo.setTelefone(candidatoAtualizado.getTelefone());
			candidatoSalvo.setConcorda(candidatoAtualizado.isConcorda());
			candidatoSalvo.setAcao(candidatoAtualizado.getAcao());
			Candidato novoCandidato = candidatoService.salvarCandidato(candidatoSalvo);
			resposta.setResposta(novoCandidato);
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

	@RequestMapping(path=Constantes.Url.EMAIL_SEND + "/{id}", method = RequestMethod.GET)
	public @ResponseBody Resposta sendMail(@PathVariable String id) {
		Resposta resposta = new Resposta();
		try {
			Candidato candidato = candidatoService.buscarCandidatoPorId(id);
			MimeMessage mail = mailSender.createMimeMessage();

			String corpoEmail = "Eu,"	 + candidato.getNome() +", nascido em " + candidato.getDataNascimento() + ", inscrito no CPF No." + candidato.getCpf() + ", desejo \n me inscrever como voluntário nessa organização e me considero apto a executar \n as atividades indicadas no formulário de convocação.\n\n";
            corpoEmail = corpoEmail + "Abaixo seguem os meus contatos:\n\n";
            corpoEmail = corpoEmail + candidato.getTelefone() +"\n" ;
            if(candidato.isConcorda()) {
            	corpoEmail = corpoEmail + candidato.getEmail() +"\n\n\n" ;
            }
            corpoEmail = corpoEmail + getDataTime() +"\n" ;

			
			MimeMessageHelper helper = new MimeMessageHelper( mail );
			helper.setTo( candidato.getEmail());
			helper.setSubject("Documento de Submissão");
			helper.setText(corpoEmail, true);
			mailSender.send(mail);
			logger.info(String.format("Email enviado com sucesso para %s", candidato.getEmail()));
		} catch (Exception e) {
			resposta.setCodigo(Constantes.Status.CODIGO_ERRO);
			resposta.setMensagem("Erro ao enviar e-mail");
		}
		return resposta;
	}
	
	private String getDataTime() {
		Date date = new Date();
        DateFormat df2 = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("pt", "BR"));
		return df2.format(date); 
	}

}
