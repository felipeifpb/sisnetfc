package fachada;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import excecoes.CampoTextoVazioException;
import excecoes.EmailJaExisteException;
import fabricaDAO.BusinessFactory;
import interfaces.FotoBusinessInterface;
import interfaces.GrupoBusinessInterface;
import interfaces.TopicoBusinessInterface;
import interfaces.UsuarioBusinessInterface;
import beans.ComentarioTopico;
import beans.Foto;
import beans.Grupo;
import beans.Mensagem;
import beans.Topico;
import beans.Usuario;

public class Fachada {
	
	private UsuarioBusinessInterface usuarioBusiness;
	private FotoBusinessInterface fotoBusiness;
	private GrupoBusinessInterface grupoBusiness;
	private TopicoBusinessInterface topicoBusiness;
	
	private static Fachada instance = null;
	
	private Fachada(){
		this.usuarioBusiness = BusinessFactory.createUsuerBusiness();
		this.grupoBusiness = BusinessFactory.createGrupoBusiness();
		this.fotoBusiness = BusinessFactory.createFotoBusiness();
		this.topicoBusiness = BusinessFactory.createTopicoBusiness();	
	}
	
	public static Fachada getInstance(){
		if( instance == null){
			return new Fachada();
		}
		return instance;
	}
	
	public void cadatrarUsuario(Usuario usuario) throws ClassNotFoundException, SQLException, ParseException, CampoTextoVazioException, EmailJaExisteException {
		usuarioBusiness.cadatrarUsuario(usuario);
	}
	
	public void atualizarUsuario( Usuario usuario ) throws ClassNotFoundException, SQLException, CampoTextoVazioException{
		usuarioBusiness.atualizarUsuario(usuario);
	}
	
	public Usuario pesquisarUsuarioPorEmail( String email ) throws CampoTextoVazioException, ClassNotFoundException, SQLException{
		return usuarioBusiness.pesquisarUsuarioPorEmail(email);
	}
	
	public Usuario pesquisarUsuario( Integer idUser ) throws CampoTextoVazioException, ClassNotFoundException, SQLException{
		return usuarioBusiness.pesquisarUsuario(idUser);
	}
	
	public void redefinirSenha( Usuario usuario ) throws CampoTextoVazioException, ClassNotFoundException, SQLException{
		usuarioBusiness.redefinirSenha(usuario);
	}
	
	public Usuario verificaLoginUsuario(String email, String senha) throws ClassNotFoundException, SQLException,ParseException, CampoTextoVazioException{
		return usuarioBusiness.verificaLoginUsuario(email, senha);
	}
	
	public void ativarConta( Usuario usuario ) throws ClassNotFoundException, SQLException{
		usuarioBusiness.ativarConta(usuario);
	}
	
	public void desativarConta( Usuario usuario ) throws ClassNotFoundException, SQLException{
		usuarioBusiness.desativarConta(usuario);
	}
	
	public List<Usuario> buscarAmigos( Integer idUser )throws ClassNotFoundException, SQLException{
		return usuarioBusiness.getListaDeAmigos(idUser);
	}
	
	public List<Usuario> buscarSolicitacoesDeAmizade( Integer idUser )throws ClassNotFoundException, SQLException{
		return usuarioBusiness.getListaDeSolicitacoesDeAmizade(idUser);
	}
	
	public void aceitarAmizade(Integer idAmigo, Integer idProprietario) throws ClassNotFoundException, SQLException{
		usuarioBusiness.aceitarSolicitacaoDeAmizade(idAmigo, idProprietario);
	}
	
	public void rejeitarAmizade(Integer idAmigo, Integer idProprietario) throws ClassNotFoundException, SQLException{
		usuarioBusiness.rejeitarSolicitacaoDeAmizade(idAmigo, idProprietario);
	}
	
	public void desfazerAmizade( Integer idProprietario, Integer idAmigo ) throws ClassNotFoundException, SQLException{
		usuarioBusiness.excluirAmizade(idProprietario, idAmigo);
	}
	
	public void addMensagem(Mensagem mensagem,int idRemetente, int idDestinatario) throws ClassNotFoundException, SQLException{
		usuarioBusiness.addMensagem(mensagem, idRemetente, idDestinatario);
	}
	
	public List<Usuario> getListaDeUsuariosParticipantesDeUmGrupo( Integer idGrupo ) throws ClassNotFoundException, SQLException{
		return usuarioBusiness.getListaDeUsuariosParticipantesDeUmGrupo(idGrupo);
	}
	
	// metodos para grupo - atualizados
	public int cadatrarGrupo(Grupo grupo) throws ClassNotFoundException, SQLException, ParseException , CampoTextoVazioException, EmailJaExisteException{
		return grupoBusiness.cadastrarGrupo(grupo);
	}
	
	public void participarDoGrupo(Grupo grupo, Usuario usuario) throws ClassNotFoundException, SQLException{
		grupoBusiness.participarDoGrupo(grupo, usuario);
	}
	
	public List<Grupo> getListaGrupoDoUsuario(Integer idUser) throws ClassNotFoundException, SQLException{
		return grupoBusiness.getListaDeGruposUsuario(idUser);
	}
	
	public List<Grupo> getListaComOsGruposDoUsuario( Integer idUser ) throws ClassNotFoundException, SQLException{
		return grupoBusiness.getListaDeGruposParticipantes(idUser);
	}
	
	public void excluirGrupo(int idGrupo )throws ClassNotFoundException, SQLException{
		grupoBusiness.excluirGrupo(idGrupo);
	}
	
	public void desvincularDoGrupo(Integer idGrupo, Integer idUser) throws ClassNotFoundException, SQLException{
		grupoBusiness.sairDoGrupo(idGrupo, idUser);
	}
	
	public Grupo buscarGrupo(Integer idGrupo ) throws ClassNotFoundException, SQLException, CampoTextoVazioException{
		return grupoBusiness.buscar(idGrupo);
	}
	
	public List<Grupo> buscarTodosOsGrupos( String nome ) throws ClassNotFoundException, SQLException, CampoTextoVazioException{
		return grupoBusiness.listarGrupos(nome);
	}
	
	public boolean verificaSeUsuarioParticipaDoGrupo( Integer idGrupo, Integer idUser ) throws ClassNotFoundException, SQLException{
		return grupoBusiness.verificaSeUsuarioParticipaDoGrupo(idGrupo, idUser);
	}
	
	//metodos para o topico
	public void cadastrarTopico( Topico topico ) throws ClassNotFoundException, SQLException, CampoTextoVazioException{
		topicoBusiness.gravar(topico);
	}
	
	public void excluirTopico( Integer idT )throws ClassNotFoundException, SQLException{
		topicoBusiness.excluir( idT );
	}
	
	public Topico buscarTopicoUser( Integer idTopico, Integer codUser )throws ClassNotFoundException, SQLException, CampoTextoVazioException{
		return topicoBusiness.buscar(idTopico, codUser);
	}
	
	public List<Topico> getListaDeTopicosDeUmGrupo( Integer idTopico )throws ClassNotFoundException, SQLException, CampoTextoVazioException{
		return topicoBusiness.getLista(idTopico);
	}
		
	public void comentarTopico( ComentarioTopico coment ) throws ClassNotFoundException, SQLException{
		topicoBusiness.gravarComentario( coment );
	}
	
	public List<ComentarioTopico> buscarComentariosTopico( Integer idTopico )throws ClassNotFoundException, SQLException{
		return topicoBusiness.listarComentarios( idTopico );
	}
	
	public void excluirComentario( Integer idComentario )throws ClassNotFoundException, SQLException{
		topicoBusiness.excluirComentario( idComentario );
	}
	
	//criado por felipe 04.08.14
	public void salvarFoto( Foto foto )throws ClassNotFoundException, SQLException{
		fotoBusiness.salvarFoto(foto);
	}
	
	public List<Foto> listarFotos( Integer idUser )throws ClassNotFoundException, SQLException{
		return fotoBusiness.getListaFoto(idUser);
	}
	
	public void excluirFoto( Integer foto ) throws ClassNotFoundException, SQLException{
		fotoBusiness.excluir(foto);
	}
		
	public void solicitaAmizade(int idUsuario, int idUsuarioSolicitado) throws ClassNotFoundException, SQLException{
		usuarioBusiness.solicitaAmizade(idUsuario, idUsuarioSolicitado);
	}
	public void cancelarSolicitacao(int idUsuario,int idUsuarioSolicitado) throws ClassNotFoundException, SQLException{
		usuarioBusiness.cancelarSolicitacao(idUsuario, idUsuarioSolicitado);
	}
	
	public String verificaAmizade(int idUsuario,int idUsuarioSolicitado) throws ClassNotFoundException, SQLException{
		return usuarioBusiness.verificaAmizade(idUsuario, idUsuarioSolicitado);
	}

	public List<Usuario> buscarUsuarioPorNome( String nome ) throws ClassNotFoundException, SQLException{
		return usuarioBusiness.buscarUsuarios(nome);
	}
	
}
