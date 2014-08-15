package interfaces;

import java.sql.SQLException;
import java.util.List;

import beans.Grupo;
import beans.Usuario;

public interface InterfaceGrupoDAO {

	public int salva(Grupo grupo) throws ClassNotFoundException, SQLException;
	public void atualiza( Grupo grupo ) throws ClassNotFoundException, SQLException;
	public void exclui( int idGrupo ) throws ClassNotFoundException, SQLException;
	public Grupo buscar(Integer id) throws ClassNotFoundException, SQLException;
	public List<Grupo> getLista( Integer id ) throws ClassNotFoundException, SQLException;
	public void participar(Grupo grupo, Usuario usuario) throws ClassNotFoundException, SQLException;
	public List<Grupo> getGruposParticiante( Integer id ) throws ClassNotFoundException, SQLException;
	public void sairDoGrupo(Integer idGrupo, Integer idUser)throws ClassNotFoundException, SQLException;
	public List<Grupo> listarGrupos( String nome ) throws ClassNotFoundException, SQLException;
	public boolean verificaSeUsuarioParticipaDoGrupo( Integer idGrupo, Integer idUser ) throws ClassNotFoundException, SQLException;

}
