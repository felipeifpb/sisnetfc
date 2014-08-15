package beans;

import java.sql.Date;
import java.util.Calendar;

public class Mensagem {
	
	private int id;
	private String mensagem;
	private Date dataDaMensagem;
	
	public Mensagem(){
		this.dataDaMensagem = new Date( Calendar.getInstance().getTime().getTime() );
	}
	
	public Mensagem(Usuario remetente, Usuario destinatario){
		this.dataDaMensagem = new Date( Calendar.getInstance().getTime().getTime() );
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getDataDaMensagem() {
		return dataDaMensagem;
	}

	public void setDataDaMensagem(Date dataDaMensagem) {
		this.dataDaMensagem = dataDaMensagem;
	}

}
