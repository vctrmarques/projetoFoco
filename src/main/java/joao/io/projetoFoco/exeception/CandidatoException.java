package joao.io.projetoFoco.exeception;

public class CandidatoException extends Exception{

	private static final long serialVersionUID = 9037000044457029468L;

	private String mensagem;
	private Exception exception;
	
	public CandidatoException(String mensagem, Exception exception) {
		this.mensagem = mensagem;
		if (exception == null) {
			this.exception = new Exception();
		}else {
			this.exception = exception;	
		}		
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
	
}
