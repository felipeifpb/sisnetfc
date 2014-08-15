package dao;

import interfaces.AbstractDAO;
import interfaces.InterfaceFotoDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Foto;

public class FotoDAO extends AbstractDAO implements InterfaceFotoDAO{

	private ResultSet resultados;
	private List<Foto> listaDeFotos;

	public FotoDAO() {}

	/* MÉTODOS PARA PERSISTENCIA DE FOTOS */
	
	public Foto createFoto() {
		return new Foto();
	}
	
	@Override
	public void salvar( Foto foto ) throws ClassNotFoundException, SQLException {

		abrirConexao();

		String sql = "insert into foto(cod_usuario, descricao, data, caminho_foto )values( ?, ?, ?, ? )";		
		prepararSql = connection.prepareStatement( sql );
		
		prepararSql.setInt(1, foto.getUsuario().getId() );
		prepararSql.setString(2, foto.getDescricao() );
		prepararSql.setDate(3, foto.getData() );
		prepararSql.setString(4, foto.getCaminhoFoto() );
		prepararSql.executeUpdate();
		fecharConexao();
	}

	public void atualizar( Foto foto ) throws ClassNotFoundException, SQLException {

		abrirConexao();

		String sql = "update foto set descricao = ? where id_foto = ?";
		prepararSql = connection.prepareStatement(sql);
		
		prepararSql.setString( 1, foto.getDescricao() );
		prepararSql.setInt( 2, foto.getId() );
		prepararSql.executeUpdate();
		fecharConexao();
	}

	public void excluir( Integer foto ) throws ClassNotFoundException, SQLException {

		abrirConexao();

		String sql = "delete from foto where id_foto = ?";
		prepararSql = connection.prepareStatement(sql);
				
		prepararSql.setInt( 1, foto );
		prepararSql.executeUpdate();
		fecharConexao();
	}

	public List<Foto> getLista( Integer idUser ) throws ClassNotFoundException, SQLException {

		abrirConexao();

		String sql = "select * from foto where cod_usuario = ? ";
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setInt(1, idUser );
		resultados = prepararSql.executeQuery();
		
		listaDeFotos = new ArrayList<Foto>();
		
		while (resultados.next()) {

			Foto foto = createFoto();

			foto.setId( resultados.getInt("id_foto") );
			foto.getUsuario().setId( resultados.getInt("cod_usuario") );
			foto.setDescricao( resultados.getString("descricao") );
			foto.setData( resultados.getDate("data") );
			foto.setCaminhoFoto( resultados.getString("caminho_foto") );
			listaDeFotos.add(foto);	
		}
		fecharConexao();
		return listaDeFotos;
	}

	public Foto buscar( Integer idFoto, Integer idUser ) throws ClassNotFoundException, SQLException {

		abrirConexao();

		String sql = "select * from foto where id_foto = ? and cod_usuario = ?";
		prepararSql = connection.prepareStatement(sql);
		prepararSql.setInt(1, idFoto );
		prepararSql.setInt(2, idUser);
		resultados = prepararSql.executeQuery();
		
		Foto foto = createFoto();

		if( resultados.next() ) {
			
			foto.setId( resultados.getInt("id_foto") );
			foto.getUsuario().setId( resultados.getInt("cod_usuario") );
			foto.setDescricao( resultados.getString("descricao") );
			foto.setCaminhoFoto( resultados.getString("caminho_foto") );
			foto.setData( resultados.getDate("data") );
		}
		return foto;
	}
	
}
