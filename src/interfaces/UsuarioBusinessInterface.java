package interfaces;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import beans.Mensagem;
import beans.Usuario;
import excecoes.CampoTextoVazioException;
import excecoes.EmailJaExisteException;

public interface UsuarioBusinessInterface {
	
	public void cadatrarUsuario(Usuario usuario) throws ClassNotFoundException, SQLException, ParseException , CampoTextoVazioException, EmailJaExisteException;
	public void atualizarUsuario( Usuario usuario ) throws ClassNotFoundException, SQLException, CampoTextoVazioException;
	public Usuario pesquisarUsuarioPorEmail( String email ) throws CampoTextoVazioException, ClassNotFoundException, SQLException;
	public void redefinirSenha( Usuario usuario ) throws CampoTextoVazioException, ClassNotFoundException, SQLException;
	public Usuario verificaLoginUsuario(String email, String senha) throws ClassNotFoundException, SQLException,ParseException, CampoTextoVazioException;
	public void ativarConta( Usuario usuario ) throws ClassNotFoundException, SQLException;
	public void desativarConta( Usuario usuario ) throws ClassNotFoundException, SQLException;
	public List<Usuario> getListaDeAmigos( Integer idUser ) throws ClassNotFoundException, SQLException;
	public List<Usuario> getListaDeSolicitacoesDeAmizade( Integer idUser ) throws ClassNotFoundException, SQLException;
	public void aceitarSolicitacaoDeAmizade( Integer idAmigo, Integer idProprietario ) throws ClassNotFoundException, SQLException;
	public void rejeitarSolicitacaoDeAmizade( Integer idAmigo, Integer idProprietario ) throws ClassNotFoundException, SQLException;
	public void excluirAmizade( Integer idProprietario, Integer idAmigo ) throws ClassNotFoundException, SQLException;
	public Usuario pesquisarUsuario( Integer idUser )throws ClassNotFoundException, SQLException;
	public String verificaAmizade(int idUsuario,int idUsuarioSolicitado) throws ClassNotFoundException, SQLException;
	public void solicitaAmizade(int idUsuario, int idUsuarioSolicitado) throws ClassNotFoundException, SQLException;
	public void cancelarSolicitacao(int idUsuario,int idUsuarioSolicitado) throws ClassNotFoundException, SQLException;
	public List<Usuario> buscarUsuarios( String nome ) throws ClassNotFoundException, SQLException;
	public void addMensagem(Mensagem mensagem,int idRemetente, int idDestinatario) throws ClassNotFoundException, SQLException;
	public List<Usuario> getListaDeUsuariosParticipantesDeUmGrupo( Integer idGrupo ) throws ClassNotFoundException, SQLException;
	
}
