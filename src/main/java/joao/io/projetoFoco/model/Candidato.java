package joao.io.projetoFoco.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document
@Table(name = "\"Candidato\"")
public class Candidato {

	@Id
	private String id;
	
	private String nome;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	
	private Date dataNascimento;
	private String cpf;
	private String email;
	private String telefone;
	private boolean concorda;
	private Acao acao;

	public Candidato() {
	}
	
	public Candidato(String nome, Date dataNascimento, String cpf, String email, String telefone, boolean concorda) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.concorda = concorda;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean isConcorda() {
		return concorda;
	}

	public void setConcorda(boolean concorda) {
		this.concorda = concorda;
	}
	
	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	@Override
	public String toString() {
		return "Candidato [id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", cpf=" + cpf
				+ ", email=" + email + ", telefone=" + telefone + ", concorda=" + concorda + "]";
	}
	
	
}
