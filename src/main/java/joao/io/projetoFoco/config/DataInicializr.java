package joao.io.projetoFoco.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import joao.io.projetoFoco.model.Candidato;
import joao.io.projetoFoco.repository.CandidatoRepository;

@Component
public class DataInicializr implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	CandidatoRepository candidatoRepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		List<Candidato> candidatos = candidatoRepository.findAll();
		
		try {
			if(candidatos.isEmpty()) {
				creatCandidatos("André Marques", formato.parse("05/05/2018"), "644.992.980-40", "testando@gmailcom", "9999-9999", true);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	private void creatCandidatos(String name, Date dataNascimento, String cpf, String email, String telefone, boolean concorda) {
		Candidato candidato = new Candidato(name, dataNascimento, cpf, email, telefone, concorda);
		candidatoRepository.save(candidato);
	}
	
}
