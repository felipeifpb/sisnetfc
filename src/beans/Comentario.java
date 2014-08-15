package beans;

import java.sql.Date;
import java.util.Calendar;

public abstract class Comentario {

	private int id;
	private Usuario usuario;
	private String comentario;
	private Date dataComentario;
		
	public Comentario(){
		this.usuario = new Usuario();
		this.dataComentario = new Date( Calendar.getInstance().getTime().getTime() );
	}
	
	public Comentario(Usuario user){
		this.usuario = user;
		this.dataComentario = new Date( Calendar.getInstance().getTime().getTime() );
	}
	
	public Date getDataComentario() {
		return dataComentario;
	}
	
	public void setDataComentario( Date dataComentario) {
		this.dataComentario = dataComentario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
