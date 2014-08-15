package beans;

import java.sql.Date;
import java.util.Calendar;

public class Foto {

	private int id;
	private Usuario usuario;
	private String descricao;
	private Date data;
	private String caminhoFoto;
	
	public Foto(){
		this.data = new Date( Calendar.getInstance().getTime().getTime() );
		this.usuario = new Usuario();
	}

	public Foto(Usuario user){
		this.data = new Date( Calendar.getInstance().getTime().getTime() );
		this.usuario = user;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getCaminhoFoto(){
		return caminhoFoto;
	}
	
	public void setCaminhoFoto(String foto){
		this.caminhoFoto = foto;
	}
	
}
