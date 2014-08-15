package beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

public class Grupo {

	private int id;
	private Usuario usuario;
	private String descricao;
	private String nomeFundador;
	private Date dataDeCriacao;
	private String nomeDoGrupo;
	private List<Usuario> listaUsuarios;
	private List<Topico> listaDeTopico;
	
	public Grupo(){
		this.listaDeTopico = new ArrayList<Topico>();
		this.usuario = new Usuario();
		this.dataDeCriacao = new Date( Calendar.getInstance().getTime().getTime() );
		this.listaUsuarios = new ArrayList<Usuario>();
	}

	public Grupo(Usuario user){
		this.usuario = user;
		this.listaDeTopico = new ArrayList<Topico>();
		this.dataDeCriacao = new Date( Calendar.getInstance().getTime().getTime() );
		this.listaUsuarios = new ArrayList<Usuario>();
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

	public String getNomeFundador() {
		return nomeFundador;
	}

	public void setNomeFundador(String nomeFundador) {
		this.nomeFundador = nomeFundador;
	}

	public Date getDataDeCriacao() {
		return dataDeCriacao;
	}

	public void setDataDeCriacao(Date dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}

	public String getNomeDoGrupo() {
		return nomeDoGrupo;
	}

	public void setNomeDoGrupo(String nomeDoGrupo) {
		this.nomeDoGrupo = nomeDoGrupo;
	}

	public List<Topico> getListaDeTopico() {
		return listaDeTopico;
	}

	public void setListaDeTopico(List<Topico> listaDeTopico) {
		this.listaDeTopico = listaDeTopico;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Usuario> getListaUsuarios(){
		return this.listaUsuarios;
	}
		
	public void setListaUsuarios(List<Usuario> lista){
		this.listaUsuarios = lista;
	}
	
}
