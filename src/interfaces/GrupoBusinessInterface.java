package interfaces;

import java.sql.SQLException;
import java.util.List;

import excecoes.CampoTextoVazioException;
import beans.Grupo;
import beans.Usuario;

public interface GrupoBusinessInterface {

	public int cadastrarGrupo(Grupo g) throws ClassNotFoundException, SQLException,CampoTextoVazioException;
	public List<Grupo> getListaDeGruposUsuario( Integer idUser )throws ClassNotFoundException, SQLException;
	public void participarDoGrupo( Grupo grupo, Usuario usuario ) throws ClassNotFoundException, SQLException;
	public void excluirGrupo( int idGrupo )throws ClassNotFoundException, SQLException;
	public List<Grupo> getListaDeGruposParticipantes( int idUser )throws ClassNotFoundException, SQLException;
	public void sairDoGrupo( Integer idGrupo, Integer idUser) throws ClassNotFoundException, SQLException;
	public Grupo buscar( Integer idGrupo ) throws ClassNotFoundException, SQLException;
	public List<Grupo> listarGrupos( String nome ) throws ClassNotFoundException, SQLException;
	public boolean verificaSeUsuarioParticipaDoGrupo( Integer idGrupo, Integer idUser ) throws ClassNotFoundException, SQLException;
	
}
