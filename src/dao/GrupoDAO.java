package dao;

import interfaces.AbstractDAO;
import interfaces.InterfaceGrupoDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Grupo;
import beans.Usuario;


public class GrupoDAO extends AbstractDAO implements InterfaceGrupoDAO{
	
	private ResultSet resultados;
	private List<Grupo> listaDeGrupos;
	
	public GrupoDAO() {}
	
	@Override
	public int salva( Grupo grupo ) throws ClassNotFoundException, SQLException {
		
		abrirConexao();
		
		String sql = "insert into grupo(cod_usuario_criador, descricao, fundador, data, nome) values( ?, ?, ?, ?, ? ) returning id_grupo";
		
		prepararSql = connection.prepareStatement(sql);
	
		prepararSql.setInt(1, grupo.getUsuario().getId() );
		prepararSql.setString(2, grupo.getDescricao() );
		prepararSql.setString(3, grupo.getNomeFundador() );
		prepararSql.setDate(4, grupo.getDataDeCriacao() );
		prepararSql.setString(5, grupo.getNomeDoGrupo() );
		
		
		resultados =  prepararSql.executeQuery();
		int id = 0;
		
		if( resultados.next() ){
			id = resultados.getInt("id_grupo");
		}
		
		fecharConexao();
		
		return id;
	}
	
	@Override
	public void atualiza( Grupo grupo ) throws ClassNotFoundException, SQLException {
		
		abrirConexao();
		
		String sql = "update grupo set descricao = ?, nome = ? where id_grupo = ? ";
		
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setString(1, grupo.getDescricao());
		prepararSql.setString(2, grupo.getNomeDoGrupo());
		prepararSql.setInt(3, grupo.getId() );
		
		prepararSql.execute();
		fecharConexao();
	}
	
	@Override
	public void exclui( int idGrupo ) throws ClassNotFoundException, SQLException {
		
		abrirConexao();
		
		String sql = "delete from grupo where id_grupo = ? ";
		
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setInt(1, idGrupo );
		prepararSql.execute();
		fecharConexao();
	}
	
	@Override
	public Grupo buscar(Integer id) throws ClassNotFoundException, SQLException {
		
		abrirConexao();
		
		String sql = "select * from grupo where id_grupo = ?";
		
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setInt(1, id);
		resultados = prepararSql.executeQuery();
		
		Grupo grupo = new Grupo();
		
		if( resultados.next() ){
			
			grupo.setId( resultados.getInt("id_grupo") );
			
			
			sql = "select * from usuario where id_usuario = ?";
			prepararSql = connection.prepareStatement(sql);
			prepararSql.setInt(1, resultados.getInt("cod_usuario_criador") );
						
			ResultSet result1 = prepararSql.executeQuery();
			
			if( result1.next() ){
				grupo.setUsuario( getResultSet(result1) );
			}
			
			grupo.setDescricao( resultados.getString("descricao") );
			grupo.setNomeFundador( resultados.getString("fundador") );
			grupo.setDataDeCriacao( resultados.getDate("data") );
			grupo.setNomeDoGrupo( resultados.getString("nome") );
			
			sql = "select * from participa_grupo as pg join usuario as u on u.id_usuario = pg.cod_usuario_participante where pg.cod_grupo = ?";
			prepararSql = connection.prepareStatement(sql);
			prepararSql.setInt(1, grupo.getId() );
			
			ResultSet result2 = prepararSql.executeQuery();
			
			while( result2.next() ){
				grupo.getListaUsuarios().add( getResultSet(result2)   );
			}
			
		}
		
		fecharConexao();
		return grupo;
		
	}
	
	@Override
	public List<Grupo> getLista( Integer id ) throws ClassNotFoundException, SQLException {
		
		abrirConexao();
		
		String sql = "select * from grupo where cod_usuario = ? ";
		
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setInt( 1, id );
		resultados = prepararSql.executeQuery();
		
		listaDeGrupos = new ArrayList<Grupo>();
		
		while(resultados.next()) {
			
			Grupo grupo = new Grupo();
			
			grupo.setId( resultados.getInt("id_grupo") );
			grupo.getUsuario().setId( resultados.getInt("cod_usuario_criador") );
			grupo.setDescricao( resultados.getString("descricao") );
			grupo.setNomeFundador( resultados.getString("fundador") );
			grupo.setDataDeCriacao( resultados.getDate("data") );
			grupo.setNomeDoGrupo( resultados.getString("nome") );
			
			listaDeGrupos.add( grupo );
			
		}
		
		fecharConexao();
		return listaDeGrupos;
	}

	@Override
	public void participar(Grupo grupo, Usuario usuario) throws ClassNotFoundException, SQLException {
		
		abrirConexao();
		
		String sql = "insert into participa_grupo(cod_usuario_participante, cod_grupo) values(?, ?)";
		prepararSql = connection.prepareStatement(sql);
		
		prepararSql.setInt( 1, usuario.getId() );
		prepararSql.setInt( 2, grupo.getId() );
		
		prepararSql.executeUpdate();
		
		fecharConexao();
		
	}

	@Override
	public List<Grupo> getGruposParticiante(Integer id) throws ClassNotFoundException, SQLException {
		
		abrirConexao();
		
		String sql = "select * from grupo as g join participa_grupo as pg on g.id_grupo = pg.cod_grupo where pg.cod_usuario_participante = ? order by g.nome";
		prepararSql = connection .prepareStatement(sql);
		
		prepararSql.setInt(1, id);
		resultados = prepararSql.executeQuery();
		
		listaDeGrupos = new ArrayList<Grupo>();
		
		String consultaUsuario = "select * from usuario where id_usuario = ?";
		prepararSql = connection.prepareStatement(consultaUsuario);
				
		while(resultados.next()) {
			
			Grupo grupo = new Grupo();
			
			grupo.setId( resultados.getInt("id_grupo") );
			
			prepararSql.setInt(1, resultados.getInt("cod_usuario_criador") );
			
			ResultSet result = prepararSql.executeQuery();
			
			if( result.next() ){
				
				grupo.setUsuario( getResultSet(result) );
				
			}
			
			grupo.setDescricao( resultados.getString("descricao") );
			grupo.setNomeFundador( resultados.getString("fundador") );
			grupo.setDataDeCriacao( resultados.getDate("data") );
			grupo.setNomeDoGrupo( resultados.getString("nome") );
			
			listaDeGrupos.add( grupo );
			
		}
	
		fecharConexao();
		return listaDeGrupos;
		
	}

	@Override
	public void sairDoGrupo(Integer idGrupo, Integer idUser) throws ClassNotFoundException, SQLException {
				
		abrirConexao();
					  
		String sql = "delete from participa_grupo where cod_usuario_participante = ? AND cod_grupo = ?";
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setInt(1, idUser);
		prepararSql.setInt(2, idGrupo);
		
		prepararSql.executeUpdate();
		
		fecharConexao();
		
	}

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

	
	public List<Grupo> listarGrupos( String nome ) throws ClassNotFoundException, SQLException{

		abrirConexao();
		
		String sql = "select * from grupo as g where g.nome Like '%?%' ";
		sql = sql.replace("?", nome);
		
		prepararSql = connection.prepareStatement(sql);
		
		resultados = prepararSql.executeQuery();
		
		listaDeGrupos = new ArrayList<Grupo>();
		
		String consultaUsuario = "select * from usuario where id_usuario = ?";
		prepararSql = connection.prepareStatement(consultaUsuario);
				
		while( resultados.next() ) {
			
			Grupo grupo = new Grupo();
			
			grupo.setId( resultados.getInt("id_grupo") );
			prepararSql.setInt(1, resultados.getInt("cod_usuario_criador") );
			ResultSet result = prepararSql.executeQuery();
			
			if( result.next() ){
				grupo.setUsuario( getResultSet(result) );
			}
			
			grupo.setDescricao( resultados.getString("descricao") );
			grupo.setNomeFundador( resultados.getString("fundador") );
			grupo.setDataDeCriacao( resultados.getDate("data") );
			grupo.setNomeDoGrupo( resultados.getString("nome") );
			
			listaDeGrupos.add( grupo );
			
		}
		
		fecharConexao();
		
		return listaDeGrupos;
	}
	
	@Override
	public boolean verificaSeUsuarioParticipaDoGrupo( Integer idGrupo, Integer idUser ) throws ClassNotFoundException, SQLException{
		
		abrirConexao();
		
		String sql = "select * from participa_grupo where cod_usuario_participante = ? AND cod_grupo = ?";
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setInt(1, idUser);
		prepararSql.setInt(2, idGrupo);
		
		resultados = prepararSql.executeQuery();
		
		boolean resu = resultados.next();
		
		fecharConexao();
		
		return resu;
		
	}
	
}
