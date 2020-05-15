package joao.io.projetoFoco.service;

import java.util.List;

import org.springframework.stereotype.Service;

import joao.io.projetoFoco.exeception.CandidatoException;
import joao.io.projetoFoco.model.Candidato;

@Service
public interface CandidatoService {

	public Candidato salvarCandidato(Candidato candidato) throws CandidatoException;
	
	public void removerCandidato(String id) throws CandidatoException;
	
	public Candidato buscarCandidatoPorId(String id) throws CandidatoException;
	
	public List<Candidato> buscarTodos() throws CandidatoException;
}
