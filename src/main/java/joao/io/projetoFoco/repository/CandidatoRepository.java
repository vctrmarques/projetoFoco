package joao.io.projetoFoco.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import joao.io.projetoFoco.model.Candidato;

public interface CandidatoRepository extends MongoRepository<Candidato, String>{
	
	public Optional<Candidato> findById(@Param("id") Long id);
	
	public List<Candidato> buscarTodosCandidatos();

}