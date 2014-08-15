package beans;

import interfaces.Observado;
import interfaces.Observador;

import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

public class Topico implements Observado{
	
	private int id;
	private Usuario usuario;
	private Grupo grupo;
	private String nomeTopico;
	private Date dataCriacao;
	private List<ComentarioTopico> listaDeComentarioTopico;
	private List<Observador> listaObservadores;
	
	public Topico(){
		this.usuario = new Usuario();
		this.grupo = new Grupo();
		this.listaDeComentarioTopico = new ArrayList<ComentarioTopico>();
		this.dataCriacao = new Date( Calendar.getInstance().getTime().getTime() );
		this.listaObservadores = new ArrayList<Observador>();
	}
	
	public Topico(Grupo grupo, Usuario user){
		this.usuario = user;
		this.grupo = grupo;
		this.listaDeComentarioTopico = new ArrayList<ComentarioTopico>();
		this.dataCriacao = new Date( Calendar.getInstance().getTime().getTime() );
		this.listaObservadores = new ArrayList<Observador>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public String getNomeTopico() {
		return nomeTopico;
	}

	public void setNomeTopico(String nomeTopico) {
		this.nomeTopico = nomeTopico;
	}

	public List<ComentarioTopico> getListaDeComentarioTopico() {
		return listaDeComentarioTopico;
	}

	public void setListaDeComentarioTopico(List<ComentarioTopico> listaDeComentarioTopico) {
		this.listaDeComentarioTopico = listaDeComentarioTopico;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Date getDataCriacao(){
		return dataCriacao;
	}
	
	public void setDataCriacao(Date data){
		this.dataCriacao = data;
	}

	public void add( ComentarioTopico comentario ){
		this.listaDeComentarioTopico.add(comentario);
		notificar();
	}
	
	@Override
	public void registrar(Observador o) {
		this.listaObservadores.add(o);		
	}

	@Override
	public void remover(Observador o) {
		this.listaObservadores.remove(o);		
	}

	@Override
	public void notificar() {
		for( Observador o : this.listaObservadores ){
			o.update();
		}
	}
	
}
