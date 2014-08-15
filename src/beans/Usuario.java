package beans;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Comparable<Usuario>{

	private int id;
	private String nome;
	private String apelido;
	private Date dataDeNascimento;
	private String cidade;
	private String email;
	private String fotoPerfil;
	private String senha;
	private boolean statusPerfil;
	private List<Usuario> listaDeAmigo;
	private List<Foto> listaDeFoto;
	private List<String> listaDeLocaisDeEstudo;
	private List<String> listaDeLocaisDeTrabalho;
	private List<Grupo> listaDeGrupo;
	private List<Mensagem> listadeMensagem;
	
	public Usuario(String senha, String email){
		this.senha = senha;
		this.email = email;
		initList();
		gerarId();
	}
	
	public Usuario(){
		initList();
	}
	
	private void initList(){
		this.listaDeAmigo = new ArrayList<Usuario>();
		this.listaDeFoto = new ArrayList<Foto>();
		this.listaDeGrupo = new ArrayList<Grupo>();
		this.listaDeLocaisDeEstudo = new ArrayList<String>();
		this.listaDeLocaisDeTrabalho = new ArrayList<String>();
		this.listadeMensagem = new ArrayList<Mensagem>();
		this.statusPerfil = true;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void gerarId(){
		this.id = hashCode(); 
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getApelido() {
		return apelido;
	}
	
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	
	public Date getDataDeNascimento() {
		return this.dataDeNascimento;
	}
	
	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFotoPerfil() {
		return fotoPerfil;
	}
	
	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public boolean getStatusPerfil() {
		return statusPerfil;
	}
	
	public void setStatusPerfil(boolean statusPerfil) {
		this.statusPerfil = statusPerfil;
	}
	
	public List<Usuario> getlistaDeAmigo() {
		return listaDeAmigo;
	}
	
	public void setlistaDeAmigo(List<Usuario> listaDeAmigo) {
		this.listaDeAmigo = listaDeAmigo;
	}

	public List<Foto> getListaDeFoto() {
		return listaDeFoto;
	}

	public void setListaDeFoto(List<Foto> listaDeFoto) {
		this.listaDeFoto = listaDeFoto;
	}

	public List<String> getListaDeLocaisDeEstudo() {
		return listaDeLocaisDeEstudo;
	}

	public void setListaDeLocaisDeEstudo(List<String> listaDeLocaisDeEstudo) {
		this.listaDeLocaisDeEstudo = listaDeLocaisDeEstudo;
	}

	public List<String> getListaDeLocaisDeTrabalho() {
		return listaDeLocaisDeTrabalho;
	}

	public void setListaDeLocaisDeTrabalho(List<String> listaDeLocaisDeTrabalho) {
		this.listaDeLocaisDeTrabalho = listaDeLocaisDeTrabalho;
	}

	public List<Grupo> getListaDeGrupo() {
		return listaDeGrupo;
	}

	public void setListaDeGrupo(List<Grupo> listaDeGrupo) {
		this.listaDeGrupo = listaDeGrupo;
	}

	public List<Mensagem> getListadeMensagem() {
		return listadeMensagem;
	}

	public void setListadeMensagem(List<Mensagem> listadeMensagem) {
		this.listadeMensagem = listadeMensagem;
	}

	@Override
	public int compareTo(Usuario user) {
		return this.nome.compareTo( user.getNome() );
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		
		if(result < 0){
			return result * (-1);
		}
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}
	
}
