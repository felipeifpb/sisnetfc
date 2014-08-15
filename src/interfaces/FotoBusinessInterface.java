package interfaces;

import java.sql.SQLException;

import beans.Foto;

import java.util.List;

public interface FotoBusinessInterface {
	
	public void salvarFoto( Foto foto )throws ClassNotFoundException, SQLException;
	public List<Foto> getListaFoto( Integer idUser )throws ClassNotFoundException, SQLException;
	public void excluir( Integer foto ) throws ClassNotFoundException, SQLException;

}
