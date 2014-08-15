package interfaces;

import java.sql.SQLException;
import java.util.List;

import beans.Mensagem;
import beans.Usuario;

public interface InterfaceUsuarioDAO {

	/* MÉTODOS PARA A PERSISTENCIA DA ENTIDADE USUARIO */
	public void salvar( Usuario user) throws ClassNotFoundException, SQLException;
	public void atualizar( Usuario user ) throws ClassNotFoundException, SQLException;
	public void excluir( Usuario user ) throws ClassNotFoundException, SQLException;
	public List<Usuario> getLista() throws ClassNotFoundException, SQLException;
	public Usuario pesquisar(Integer idUser) throws ClassNotFoundException, SQLException;
	public Usuario pesquisarPorEmail(String email) throws ClassNotFoundException, SQLException;
	public boolean existeEmail( String email )throws ClassNotFoundException, SQLException;
	public Usuario verificaLogin( String email, String senha ) throws ClassNotFoundException, SQLException;
	public void desativaConta( Usuario user ) throws ClassNotFoundException, SQLException;
	public void ativaConta( Usuario user ) throws ClassNotFoundException, SQLException;
	public List<Usuario> listarAmigos( Integer user ) throws ClassNotFoundException, SQLException;
	public void aceitarSolicitacao( Integer idAmigo, Integer idProprietario ) throws ClassNotFoundException, SQLException;
	public void rejeitarSolicitacao( Integer idAmigo, Integer idProprietario ) throws ClassNotFoundException, SQLException;
	public void solicitaAmizade(int idUsuario,int idUsuarioSolicitado) throws ClassNotFoundException, SQLException;
	public void cancelarSolicitacao(int idUsuario,int idUsuarioSolicitado) throws ClassNotFoundException, SQLException;
	public String verificaAmizade(int idUsuario,int idUsuarioSolicitado) throws ClassNotFoundException, SQLException;
	public List<Usuario> buscarUsuarios( String nome ) throws ClassNotFoundException, SQLException;	
	public List<Usuario> getListaDeUsuariosParticipantesDeUmGrupo( Integer idGrupo ) throws ClassNotFoundException, SQLException;
	//metodo de login
	
	/* MÉTODOS PARA A PERSISTENCIA DA ENTIDADE MENSAGENS */
	public void addMensagem(Mensagem mensagem,int idRemetente, int idDestinatario) throws ClassNotFoundException, SQLException;
	
	/* MÉTODOS PARA A PERSISTENCIA DA ENTIDADE RELACIONAMENTO */
	public List<Usuario> getListaSolicitacoesDeAmizade( Integer idUser ) throws ClassNotFoundException, SQLException;
	public void desfazerAmizade( Integer idProprietario, Integer idAmigo ) throws ClassNotFoundException, SQLException;
	
	
}
