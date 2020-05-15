package joao.io.projetoFoco.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import joao.io.projetoFoco.exeception.CandidatoException;
import joao.io.projetoFoco.model.Candidato;
import joao.io.projetoFoco.repository.CandidatoRepository;
import joao.io.projetoFoco.service.CandidatoService;

@Service
public class CandidatoServiceImpl implements CandidatoService{
	
	@Autowired
	private CandidatoRepository cancidatoRepository;
	
	@Autowired
    private MessageSource messageSource;
	
	public Candidato salvarCandidato(Candidato candidato) throws CandidatoException{
		try {
			return cancidatoRepository.save(candidato);
		}catch(Exception e) {
			e.printStackTrace();
			throw new CandidatoException(String.format(messageSource.getMessage("Candidato.erro.salvamento", null, Locale.getDefault()), candidato.getNome()),e);
		}
	}
	
	public void removerCandidato(String id) throws CandidatoException{
		try {
			Candidato Candidato = buscarCandidatoPorId(id);
			cancidatoRepository.delete(Candidato);
		}catch(CandidatoException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new CandidatoException(String.format(messageSource.getMessage("Candidato.erro.remocao", null, Locale.getDefault()), id),e);
		}
	}
	
	public Candidato buscarCandidatoPorId(String id) throws CandidatoException{
		try {
			Optional<Candidato> Candidato = cancidatoRepository.findById(id);
			if (Candidato.isPresent()) {
				return Candidato.get();
			}else {
				throw new CandidatoException(String.format(messageSource.getMessage("Candidato.erro.Candidato.naoencontrado", null, Locale.getDefault()), id), null);
			}
		}catch(CandidatoException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new CandidatoException(String.format(messageSource.getMessage("Candidato.erro.consulta", null, Locale.getDefault()), id),e);
		}
	}
	
	public List<Candidato> buscarTodos() throws CandidatoException{
		try {
			List<Candidato> lstCandidato = new ArrayList<Candidato>();
			Iterable<Candidato> it = cancidatoRepository.buscarTodosCandidatos();
			for (Candidato Candidato: it) {
				lstCandidato.add(Candidato);
			}
			if (lstCandidato.isEmpty()) {
				throw new CandidatoException(messageSource.getMessage("Candidato.erro.nenhum.encontrado", null, Locale.getDefault()),null);
			}
			return lstCandidato;
		}catch(CandidatoException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new CandidatoException(messageSource.getMessage("Candidato.erro.nenhum.encontrado", null, Locale.getDefault()),e);
		}
	}

}
