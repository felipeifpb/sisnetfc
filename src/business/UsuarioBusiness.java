package business;

import interfaces.InterfaceUsuarioDAO;
import interfaces.UsuarioBusinessInterface;

import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import util.CaminhoDasImagens;
import excecoes.CampoTextoVazioException;
import excecoes.EmailJaExisteException;
import fabricaDAO.CreateFactoryDAO;
import fabricaDAO.DAOFactoryInterface;
import beans.Mensagem;
import beans.Usuario;

public class UsuarioBusiness implements UsuarioBusinessInterface{
	
	private DAOFactoryInterface fabrica;
	private InterfaceUsuarioDAO user;
	private File file;
		
	public UsuarioBusiness(){
		this.fabrica = CreateFactoryDAO.createFactoryDAO();
		this.user = fabrica.createUsuarioDAO();
	}
	
	public void cadatrarUsuario(Usuario usuario) throws ClassNotFoundException, SQLException, ParseException , CampoTextoVazioException, EmailJaExisteException{
		
		if( usuario.getNome().equals("") || usuario.getDataDeNascimento().equals("") || usuario.getEmail().equals("") || usuario.getSenha().equals("") ){
			throw new CampoTextoVazioException("Campo obrigatório.");
		}
		
		if( user.existeEmail( usuario.getEmail() ) ){
			throw new EmailJaExisteException("O email digitado já pertence a uma conta. Tente outro.");
		}
		
		String caminho = CaminhoDasImagens.URL_IMAGENS_USUARIO_ABSOLUTE + CaminhoDasImagens.URL_IMAGENS_USUARIO + usuario.getId();
		file = new File(caminho);
		
		if( !file.exists() ){
			file.mkdir();
		}
		
		user.salvar(usuario);
		
	}
	
	public void atualizarUsuario( Usuario usuario ) throws ClassNotFoundException, SQLException, CampoTextoVazioException{
		
		if( usuario.getNome().equals("") || usuario.getDataDeNascimento().equals("") || usuario.getEmail().equals("") || usuario.getSenha().equals("") ){
			throw new CampoTextoVazioException("Campo obrigatório.");
		}
		
		user.atualizar(usuario);
		
	}
	
	public Usuario pesquisarUsuarioPorEmail( String email ) throws CampoTextoVazioException, ClassNotFoundException, SQLException{
		
		if( email.equals("") ){
			throw new CampoTextoVazioException("Campo obrigatório.");  
		}
		
		Usuario usuario = user.pesquisarPorEmail(email);
		
		return usuario;
		
	}
	
	public void redefinirSenha( Usuario usuario ) throws CampoTextoVazioException, ClassNotFoundException, SQLException{
		
		if( usuario.getSenha().equals("") ){
			throw new CampoTextoVazioException("Campo obrigatório.");
		}
		
		user.atualizar(usuario);
		
	}

	public Usuario verificaLoginUsuario(String email, String senha) throws ClassNotFoundException, SQLException, ParseException, CampoTextoVazioException {
		
		if( email.equals("") || senha.equals("") || email == null || senha == null) {
			throw new CampoTextoVazioException("Campo obrigatório");			
		}
		
		return user.verificaLogin(email, senha);
		
	}
	
	public void ativarConta( Usuario usuario ) throws ClassNotFoundException, SQLException{
		
		user.ativaConta(usuario);
		
	}
		
	public void desativarConta( Usuario usuario ) throws ClassNotFoundException, SQLException{
		
		user.desativaConta(usuario);
		
	}

	@Override
	public List<Usuario> getListaDeAmigos(Integer idUser) throws ClassNotFoundException, SQLException {
		return user.listarAmigos(idUser);
	}

	@Override
	public List<Usuario> getListaDeSolicitacoesDeAmizade(Integer idUser) throws ClassNotFoundException, SQLException {
		return user.getListaSolicitacoesDeAmizade(idUser);
	}

	@Override
	public void aceitarSolicitacaoDeAmizade(Integer idAmigo, Integer idProprietario) throws ClassNotFoundException, SQLException {
		user.aceitarSolicitacao(idAmigo, idProprietario);
	}

	@Override
	public void rejeitarSolicitacaoDeAmizade(Integer idAmigo,Integer idProprietario) throws ClassNotFoundException, SQLException {
		user.rejeitarSolicitacao(idAmigo, idProprietario);		
	}

	@Override
	public void excluirAmizade(Integer idProprietario, Integer idAmigo) throws ClassNotFoundException, SQLException {
		user.desfazerAmizade(idProprietario, idAmigo);
	}

	@Override
	public Usuario pesquisarUsuario(Integer idUser)throws ClassNotFoundException, SQLException {
		return user.pesquisar(idUser);
	}
	
	@Override
	public void solicitaAmizade(int idUsuario, int idUsuarioSolicitado) throws ClassNotFoundException, SQLException{
		user.solicitaAmizade(idUsuario, idUsuarioSolicitado);
	}
	
	@Override
	public void cancelarSolicitacao(int idUsuario,int idUsuarioSolicitado) throws ClassNotFoundException, SQLException{
		user.cancelarSolicitacao(idUsuario, idUsuarioSolicitado);
	}
	
	@Override
	public String verificaAmizade(int idUsuario,int idUsuarioSolicitado) throws ClassNotFoundException, SQLException{
		return user.verificaAmizade(idUsuario, idUsuarioSolicitado);
	}

	@Override
	public List<Usuario> buscarUsuarios(String nome)throws ClassNotFoundException, SQLException {
		return user.buscarUsuarios(nome);
	}

	@Override
	public void addMensagem(Mensagem mensagem, int idRemetente,	int idDestinatario) throws ClassNotFoundException, SQLException {
		user.addMensagem(mensagem, idRemetente, idDestinatario);
	}

	@Override
	public List<Usuario> getListaDeUsuariosParticipantesDeUmGrupo(Integer idGrupo) throws ClassNotFoundException, SQLException {
		return user.getListaDeUsuariosParticipantesDeUmGrupo(idGrupo);
	}

}

