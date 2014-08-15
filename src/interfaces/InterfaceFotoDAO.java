package interfaces;

import java.sql.SQLException;
import java.util.List;

import beans.Foto;

public interface InterfaceFotoDAO {

	/* MÉTODOS PARA PERSISTENCIA DA ENTIDADE FOTO*/
	public void salvar( Foto foto ) throws ClassNotFoundException, SQLException;
	public void atualizar( Foto foto ) throws ClassNotFoundException, SQLException;
	public void excluir( Integer foto ) throws ClassNotFoundException, SQLException;
	public List<Foto> getLista( Integer idUser ) throws ClassNotFoundException, SQLException;
	public Foto buscar( Integer idFoto, Integer idUser ) throws ClassNotFoundException, SQLException;
		
}
