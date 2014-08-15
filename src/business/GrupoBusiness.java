package business;

import java.sql.SQLException;
import java.util.List;

import beans.Grupo;
import beans.Usuario;
import excecoes.CampoTextoVazioException;
import fabricaDAO.CreateFactoryDAO;
import fabricaDAO.DAOFactoryInterface;
import interfaces.InterfaceGrupoDAO;
import interfaces.GrupoBusinessInterface;


public class GrupoBusiness implements GrupoBusinessInterface {
	
	private DAOFactoryInterface fabrica;
	private InterfaceGrupoDAO grupo;
	
	public GrupoBusiness() {
		this.fabrica = CreateFactoryDAO.createFactoryDAO();
		this.grupo = fabrica.createGrupoDAO();
				
	}
	
	@Override
	public int cadastrarGrupo(Grupo g) throws ClassNotFoundException, SQLException,CampoTextoVazioException{
		if( g.getDescricao() == null || g.getDescricao().equals("") || g.getNomeDoGrupo() == null || g.getNomeDoGrupo().equals("") || g.getNomeFundador() == null || g.getNomeFundador().equals("") ){
			throw new CampoTextoVazioException("Campo obrigatório.");
		}
		
		return grupo.salva(g);
	}

	@Override
	public void participarDoGrupo(Grupo grupo, Usuario usuario) throws ClassNotFoundException, SQLException {
		this.grupo.participar(grupo, usuario);
	}

	@Override
	public List<Grupo> getListaDeGruposUsuario(Integer idUser) throws ClassNotFoundException, SQLException {
		return grupo.getLista(idUser);
	}

	@Override
	public void excluirGrupo(int idGrupo) throws ClassNotFoundException, SQLException {
		grupo.exclui(idGrupo);
	}

	@Override
	public List<Grupo> getListaDeGruposParticipantes(int idUser) throws ClassNotFoundException, SQLException {
		return grupo.getGruposParticiante( idUser );
	}

	@Override
	public void sairDoGrupo(Integer idGrupo, Integer idUser)throws ClassNotFoundException, SQLException {
		grupo.sairDoGrupo(idGrupo, idUser);		
	}

	@Override
	public Grupo buscar(Integer idGrupo) throws ClassNotFoundException, SQLException {
		return grupo.buscar(idGrupo);
	}

	@Override
	public List<Grupo> listarGrupos( String nome ) throws ClassNotFoundException,SQLException {
		return grupo.listarGrupos(nome);
	}

	@Override
	public boolean verificaSeUsuarioParticipaDoGrupo(Integer idGrupo,Integer idUser) throws ClassNotFoundException, SQLException {
		return grupo.verificaSeUsuarioParticipaDoGrupo(idGrupo, idUser);
	}

}
