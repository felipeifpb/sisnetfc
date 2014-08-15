package dao;

import interfaces.AbstractDAO;
import interfaces.InterfaceTopicoDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.ComentarioTopico;
import beans.Topico;
import beans.Usuario;

public class TopicoDAO extends AbstractDAO implements InterfaceTopicoDAO {
	
	private ResultSet resultados;
	private List<Topico> listaDeTopicos;
	private List<ComentarioTopico> listaComentariosTopico;
	
	public TopicoDAO() {}
	
	@Override
	public void salva( Topico topico ) throws ClassNotFoundException, SQLException {
		
		abrirConexao();
		
		String sql = "insert into topico(cod_usuario, cod_grupo, nome, data) values( ?, ?, ?, ? )";
		
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setInt( 1, topico.getUsuario().getId() );
		prepararSql.setInt( 2, topico.getGrupo().getId() );
		prepararSql.setString( 3, topico.getNomeTopico() );
		prepararSql.setDate( 4, topico.getDataCriacao() );
		prepararSql.execute();
		
		fecharConexao();
		
	}
	
	@Override
	public void atualiza( Topico topico ) throws ClassNotFoundException, SQLException {
		
		abrirConexao();
		
		String sql = "update topico set nome = ? where id_topico = ? ";
		prepararSql = connection.prepareStatement(sql);
		
		prepararSql.setString(1, topico.getNomeTopico() );
		prepararSql.setInt(2, topico.getId() );
		prepararSql.execute();
		fecharConexao();
	}
	
	@Override
	public void exclui( Integer topico ) throws ClassNotFoundException, SQLException {
		
		abrirConexao();
		
		String sql = "delete from topico where id_topico = ?";
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setInt(1, topico );
		prepararSql.execute();
		fecharConexao();
	}
	
	@Override
	public Topico buscar( Integer idTopico, Integer codUser ) throws ClassNotFoundException, SQLException {
		
		abrirConexao();
		
		String sql = "select * from topico where id_topico = ? and cod_usuario = ?";
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setInt( 1, idTopico );
		prepararSql.setInt( 2, codUser );
		resultados = prepararSql.executeQuery();
		
		Topico topico = null;
		
		if(resultados.next()) {
			
			topico = getResultTopico(resultados);
			
		}
		
		return topico;
		
	}
	
	@Override
	public List<Topico> getLista( Integer id  ) throws ClassNotFoundException, SQLException {
		
		abrirConexao();
		
		String sql = "select * from topico where cod_grupo = ?";
		
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setInt(1, id );
		resultados = prepararSql.executeQuery();
		
		listaDeTopicos = new ArrayList<Topico>();
		
		while(resultados.next()) {
			
			Topico post = getResultTopico(resultados);
			listaDeTopicos.add(post);
			
		}
		
		fecharConexao();
		
		return listaDeTopicos;
		
	}
	
	private Topico getResultTopico( ResultSet resultados ) throws SQLException{
		Topico post = new Topico();
		
		post.setId(resultados.getInt("id_topico"));
		post.getUsuario().setId( resultados.getInt("cod_usuario") );
		post.getGrupo().setId( resultados.getInt("cod_grupo") );
		post.setNomeTopico(resultados.getString("nome") );
		post.setDataCriacao( resultados.getDate("data") );
		
		return post;
		
	}
	
	
	/* MÉTODOS PARA PERSISTENCIA DE COMENTARIOS */
	@Override
	public void persisteComentario(ComentarioTopico comentario) throws ClassNotFoundException, SQLException {
		
		abrirConexao();
		
		String sql = "insert into comentario_topico ( cod_usuario, cod_topico, data, comentario_topico ) values ( ? , ? , ? , ? )";
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setInt( 1, comentario.getUsuario().getId() );
		prepararSql.setInt(2, comentario.getTopico().getId() );
		prepararSql.setDate(3, comentario.getDataComentario() );
		prepararSql.setString(4, comentario.getComentario() );
		
		prepararSql.executeUpdate();
		
		fecharConexao();
		
	}

	@Override
	public void atualizarComentario(ComentarioTopico comentario) throws ClassNotFoundException, SQLException {
		
		abrirConexao();
		
		String sql = " update comentario_topico set comentario_topico = ?, data = ? where id_comentario_topico = ? ";
		
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setString( 1, comentario.getComentario() );
		prepararSql.setDate( 2, comentario.getDataComentario() );
		prepararSql.setInt( 3, comentario.getId() );
		prepararSql.executeUpdate();
		
		fecharConexao();
		
	}

	@Override
	public void excluirComentario(Integer idComentario) throws ClassNotFoundException, SQLException {
		
		abrirConexao();
		
		String sql = " delete from comentario_topico where id_comentario_topico = ? ";
		
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setInt( 1, idComentario );
		prepararSql.executeUpdate();
		
		fecharConexao();
		
	}

	@Override
	public List<ComentarioTopico> listarComentarios(Integer codTopico) throws ClassNotFoundException, SQLException {
		abrirConexao();
		
		String sql = " select * from comentario_topico where cod_topico = ? ";
		
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setInt( 1, codTopico );
		resultados = prepararSql.executeQuery();
		
		listaComentariosTopico = new ArrayList<ComentarioTopico>();
		
		String sql2="select * from usuario where id_usuario = ?";
		prepararSql = connection.prepareStatement(sql2);
		
		while( resultados.next() ){
			ComentarioTopico comentario = new ComentarioTopico();
			comentario.setId( resultados.getInt("id_comentario_topico") );
			
			prepararSql.setInt(1, resultados.getInt("cod_usuario"));
			ResultSet result = prepararSql.executeQuery();
			
			if( result.next() ){
				comentario.setUsuario( getResultSetUser(result)  );
			}
			
			comentario.getTopico().setId( resultados.getInt("cod_topico") );
			comentario.setDataComentario( resultados.getDate("data") );
			comentario.setComentario( resultados.getString("comentario_topico") );
			listaComentariosTopico.add(comentario);
		}
		
		fecharConexao();
		
		return listaComentariosTopico;
		
	}

	private Usuario getResultSetUser( ResultSet result ) throws SQLException{
		
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
	
	
}