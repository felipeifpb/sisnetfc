package dao;

import interfaces.AbstractDAO;
import interfaces.InterfaceUsuarioDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Mensagem;
import beans.Usuario;

public class UsuarioDAO extends AbstractDAO implements InterfaceUsuarioDAO {
	
	private ResultSet resultados;
	private List<Usuario> listaDeUsuarios;
	private Usuario usuario = null;
	private List<Usuario> listaAmigos = null;
	
	public UsuarioDAO(){}

	/* MÉTODOS PARA PERSISTENCIA DE USUARIOS */
	public void salvar( Usuario user) throws ClassNotFoundException, SQLException{
		
		abrirConexao();
		
		String sql = 	"insert into usuario "
						+ "(id_usuario, nome_completo, apelido, cidade, foto_perfil, data_nascimento, email, senha, status_perfil) "
						+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		prepararSql = connection.prepareStatement(sql);
		
		prepararSql.setInt(1, user.getId() );
		prepararSql.setString( 2, user.getNome() );
		prepararSql.setString( 3, user.getApelido() );
		prepararSql.setString( 4, user.getCidade() );
		prepararSql.setString( 5, user.getFotoPerfil() );
		prepararSql.setDate( 6, user.getDataDeNascimento() );
		prepararSql.setString( 7, user.getEmail() );
		prepararSql.setString( 8, user.getSenha() );
		prepararSql.setBoolean( 9, user.getStatusPerfil() );
		
		prepararSql.executeUpdate();
		
		fecharConexao();
		
	}

	public void atualizar( Usuario user ) throws ClassNotFoundException, SQLException{
		
		abrirConexao();
		
		String sql = 	"update usuario set "
						+ " nome_completo = ?, apelido = ?, cidade = ?, foto_perfil = ?, "
						+ " data_nascimento = ?, email = ?, senha = ?, status_perfil = ? "
						+ " where id_usuario = ? ";
		
		prepararSql = connection.prepareStatement(sql);
		
		prepararSql.setString( 1, user.getNome() );
		prepararSql.setString( 2, user.getApelido() );
		prepararSql.setString( 3, user.getCidade() );
		prepararSql.setString( 4, user.getFotoPerfil() );
		prepararSql.setDate( 5, user.getDataDeNascimento() );
		prepararSql.setString( 6, user.getEmail() );
		prepararSql.setString( 7, user.getSenha() );
		prepararSql.setBoolean( 8, user.getStatusPerfil() );
		prepararSql.setInt(9, user.getId() );
		
		prepararSql.executeUpdate();
		
		fecharConexao();
		
	}

	public void excluir( Usuario user ) throws ClassNotFoundException, SQLException{
		
		abrirConexao();
		
		String sql = "delete from usuario where id_usuario = ? ";
		
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setInt(1, user.getId() );
		prepararSql.executeUpdate();
		
		fecharConexao();

	}

	public List<Usuario> getLista() throws ClassNotFoundException, SQLException{
		
		abrirConexao();
		
		String sql = "select * from usuario";
		
		prepararSql = connection.prepareStatement(sql);
			
		resultados = prepararSql.executeQuery();
		
		listaDeUsuarios = new ArrayList<Usuario>();
				
		while( resultados.next() ){
			
			usuario = getResultSet( resultados );
			
			listaDeUsuarios.add(usuario);
			
		}
		
		fecharConexao();
		
		return listaDeUsuarios;
	}
	
	public Usuario pesquisar(Integer idUser) throws ClassNotFoundException, SQLException{
		
		abrirConexao();
		
		String sql = "select * from usuario where id_usuario = ? ";
		
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setInt(1, idUser );
		resultados = prepararSql.executeQuery();
		
		if( resultados.next() ){
			
			usuario = getResultSet(resultados);
						
		}
		
		fecharConexao();
		
		return usuario;
	}

	@Override
	public boolean existeEmail(String email) throws ClassNotFoundException, SQLException {
		
		abrirConexao();
		
		String sql = " select * from usuario where email iLike ? ";
		
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setString(1, email);
		resultados = prepararSql.executeQuery();
		
		boolean booleanResult = resultados.next(); 
		
		fecharConexao();
		
		return booleanResult;
		
	}

	@Override
	public Usuario pesquisarPorEmail(String email) throws ClassNotFoundException, SQLException {
		
		abrirConexao();
		
		String sql = "select * from usuario where email = ? ";
		
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setString(1, email );
		resultados = prepararSql.executeQuery();
		
		if( resultados.next() ){
			
			usuario = getResultSet( resultados );
						
		}
		
		fecharConexao();
		
		return usuario;
		
	}

	public Usuario verificaLogin(String email, String senha) throws ClassNotFoundException, SQLException{
		abrirConexao();
		
		String sql = "select * from usuario where email = ? AND senha = ? ";
		
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setString(1, email);
		prepararSql.setString(2, senha);
		resultados = prepararSql.executeQuery();

		if( resultados.next() ){
			
			 usuario = getResultSet( resultados );
			 
		}	
		
		return usuario;
		
	}

	public void desativaConta(Usuario user) throws ClassNotFoundException, SQLException{
		
		abrirConexao();
		
		String sql = "update usuario set status_perfil = ? where id_usuario = ? ";
		prepararSql  = connection.prepareStatement(sql);
		prepararSql.setBoolean(1, user.getStatusPerfil());
		prepararSql.setInt(2,user.getId());
			
	    prepararSql.executeUpdate();
	    
		fecharConexao();
		
	}
	
	public void ativaConta(Usuario user) throws ClassNotFoundException, SQLException{

		abrirConexao();
		
		String sql = "update usuario set status_perfil = ? where id_usuario = ?  ";
		prepararSql  = connection.prepareStatement(sql);
		prepararSql.setBoolean(1, user.getStatusPerfil());
		prepararSql.setInt(2,user.getId());
		
		prepararSql.executeUpdate();
		
		fecharConexao();
	}

	@Override
	public List<Usuario> listarAmigos( Integer user ) throws ClassNotFoundException,SQLException {
		
		abrirConexao();
		
		listaAmigos = new ArrayList<Usuario>();
		
		String sql = "select * from relacionamento join usuario on id_usuario = cod_amigo where cod_proprietario = ? and solicitacao = 'CONFIRMADA' order by nome_completo"; 
		
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setInt( 1, user );
		resultados = prepararSql.executeQuery();
		
		while( resultados.next() ){
			
			usuario = getResultSet( resultados );
			listaAmigos.add( usuario );
			
		}
				
		fecharConexao();
		
		if( listaAmigos.size() == 0 ) return null;
		
		return listaAmigos;
		
	}
	
	/* recebe um resultset e retorna um objeto usuario preenchido */
	private Usuario getResultSet( ResultSet result ) throws SQLException{
		
		Usuario usuario = new Usuario();
		
		usuario.setId( result.getInt("id_usuario") );
		usuario.setNome( result.getString("nome_completo") );
		usuario.setApelido( result.getString("apelido") );
		usuario.setCidade( result.getString("cidade") );
		usuario.setDataDeNascimento( result.getDate("data_nascimento") );
		usuario.setEmail( result.getString("email") );
		usuario.setSenha( result.getString("senha") );
		usuario.setFotoPerfil( result.getString("foto_perfil") );
		usuario.setStatusPerfil( result.getBoolean("status_perfil") );
		
		return usuario;
		
	}

	@Override
	public List<Usuario> getListaSolicitacoesDeAmizade(Integer idUser) throws ClassNotFoundException, SQLException {
		
		abrirConexao();
		
		String sql = "select * from usuario as u join relacionamento as r on r.cod_proprietario = u.id_usuario where r.cod_amigo = ? and solicitacao = 'PENDENTE' ";
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setInt( 1, idUser );
		
		resultados = prepararSql.executeQuery();
		
		listaAmigos = new ArrayList<>();
		
		while( resultados.next() ){
			
			usuario = getResultSet( resultados );
			listaAmigos.add( usuario );
			
		}
		
		fecharConexao();
		
		if( listaAmigos.size() == 0 ) return null;
		
		return listaAmigos;
		
	}

	@Override
	public void aceitarSolicitacao(Integer idAmigo, Integer idProprietario)throws ClassNotFoundException, SQLException {
		
		abrirConexao();
		
		String sql = "update relacionamento set solicitacao = 'CONFIRMADA' where cod_proprietario = ? AND cod_amigo = ?";
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setInt(1, idAmigo);
		prepararSql.setInt(2, idProprietario);
		
		prepararSql.executeUpdate();
		
		String slq2 = "insert into relacionamento(cod_proprietario, cod_amigo, tipo, solicitacao)values(?, ?, 'AMIGO', 'CONFIRMADA'  ) ";
		prepararSql = connection.prepareStatement(slq2);
		prepararSql.setInt(1, idProprietario);
		prepararSql.setInt(2, idAmigo);
		
		prepararSql.executeUpdate();
		
		fecharConexao();
		
	}
	
	@Override
	public void rejeitarSolicitacao(Integer idAmigo, Integer idProprietario)throws ClassNotFoundException, SQLException {
		abrirConexao();
		
		String sql = "delete from relacionamento where cod_proprietario = ? AND cod_amigo = ?";
		prepararSql = connection.prepareStatement( sql );
		prepararSql.setInt( 1, idAmigo );
		prepararSql.setInt( 2, idProprietario );
		
		prepararSql.executeUpdate();
		
		fecharConexao();
	}

	@Override
	public void desfazerAmizade(Integer idProprietario, Integer idAmigo) throws ClassNotFoundException, SQLException {
		
		abrirConexao();
		
		String sql = " delete from relacionamento where cod_proprietario = ? AND cod_amigo = ?";
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setInt(1, idProprietario);
		prepararSql.setInt(2, idAmigo);
		
		prepararSql.executeUpdate();
		
		prepararSql.setInt(1, idAmigo);
		prepararSql.setInt(2, idProprietario);
		
		prepararSql.executeUpdate();
		
		fecharConexao();
		
	}		
	
	@Override
	public String verificaAmizade(int idUsuario,int idUsuarioSolicitado) throws ClassNotFoundException, SQLException{
		
		abrirConexao();
		
		String sql = "select R.solicitacao from relacionamento AS R where R.cod_proprietario = ? AND R.cod_amigo = ?";
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setInt(1, idUsuario);
		prepararSql.setInt(2, idUsuarioSolicitado);
		
		ResultSet resultado = prepararSql.executeQuery();
	
		if(resultado.next()){
			return resultado.getString("solicitacao");
		}
		
		fecharConexao();
		
		return null;
		
	}
	
	@Override
	public void solicitaAmizade(int idUsuario,int idUsuarioSolicitado) throws ClassNotFoundException, SQLException{
		abrirConexao();
		
		String sql= "insert into relacionamento (cod_proprietario, cod_amigo, tipo, solicitacao)values(?, ?, ?, ?) ";
		
		prepararSql = connection.prepareStatement(sql);
		
		prepararSql.setInt(1, idUsuario);
		prepararSql.setInt(2, idUsuarioSolicitado);
		prepararSql.setString(3,"AMIGO");
		prepararSql.setString(4, "PENDENTE");
		
		prepararSql.execute();
		
		fecharConexao();
	}
	
	@Override
	public void cancelarSolicitacao(int idUsuario,int idUsuarioSolicitado) throws ClassNotFoundException, SQLException{
		abrirConexao();
		
		String sql = "delete from relacionamento where cod_proprietario = ? and cod_amigo = ?";
		
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setInt(1, idUsuario);
		prepararSql.setInt(2, idUsuarioSolicitado);
		
		prepararSql.execute();
		
		fecharConexao();
		
	}

	@Override
	public List<Usuario> buscarUsuarios(String nome)throws ClassNotFoundException, SQLException {
		
		abrirConexao();
		
		String sql = "select * from usuario as u where u.nome_completo LIKE '%?%' ";
		sql = sql.replace("?", nome);
		prepararSql = connection.prepareStatement(sql);
				
		resultados = prepararSql.executeQuery();
		
		listaAmigos = new ArrayList<>();
		
		while( resultados.next() ){
			
			usuario = getResultSet( resultados );
			listaAmigos.add( usuario );
			
		}
		
		fecharConexao();
		
		if( listaAmigos.size() == 0 ) return null;
		
		return listaAmigos;
	}

	public List<Usuario> getListaDeUsuariosParticipantesDeUmGrupo( Integer idGrupo ) throws ClassNotFoundException, SQLException{
		
		abrirConexao();
		
		String sql = "select * from usuario as u join participa_grupo as pg on u.id_usuario = pg.cod_usuario_participante where pg.cod_grupo = ?";
		
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setInt(1, idGrupo );
		
		resultados = prepararSql.executeQuery();
		listaDeUsuarios = new ArrayList<>();
				
		while( resultados.next() ){
			listaDeUsuarios.add( getResultSet(resultados) );
			
		}
		
		fecharConexao();
		return listaDeUsuarios;
		
	}
	
	
	/* MÉTODOS PARA PERSISTENCIA DE MENSAGEM */
	public void addMensagem(Mensagem mensagem,int idRemetente, int idDestinatario) throws ClassNotFoundException, SQLException{
		abrirConexao();
		
		String sql = "insert into mensagem (mensagem,data)values(?,?) returning id_mensagem";
		
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setString(1,mensagem.getMensagem());
		prepararSql.setDate(2,mensagem.getDataDaMensagem() );
		
	    resultados = prepararSql.executeQuery();
	    
	    while(resultados.next()){	    	
	    	mensagem.setId(resultados.getInt("id_mensagem"));
	    }
	    
		String sql2 = "insert into enviar_mensagem (cod_mensagem,cod_remetente,cod_destinatario)values(?,?,?)";
		prepararSql = connection.prepareStatement(sql2);
		prepararSql.setInt(1,mensagem.getId());
		prepararSql.setInt(2, idRemetente);
		prepararSql.setInt(3, idDestinatario);
		
		prepararSql.execute();
		
	}
		
	
	/* MÉTODOS PARA PERSISTENCIA DE RELACIONAMENTOS */
	
}
