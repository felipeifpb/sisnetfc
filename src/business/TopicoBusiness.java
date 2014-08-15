package business;

import java.sql.SQLException;
import java.util.List;

import excecoes.CampoTextoVazioException;
import fabricaDAO.CreateFactoryDAO;
import fabricaDAO.DAOFactoryInterface;
import beans.ComentarioTopico;
import beans.Topico;
import interfaces.InterfaceTopicoDAO;
import interfaces.TopicoBusinessInterface;

public class TopicoBusiness implements TopicoBusinessInterface {

	
	private DAOFactoryInterface fabrica;
	private InterfaceTopicoDAO topicoDAO;
	
	public TopicoBusiness(){
		this.fabrica = CreateFactoryDAO.createFactoryDAO();
		this.topicoDAO = fabrica.createTopicoDAO();
	}
	
	@Override
	public void gravar(Topico topico) throws ClassNotFoundException, SQLException, CampoTextoVazioException {
		
		if( topico.getNomeTopico().equals("") || topico.getNomeTopico() == null ){
			throw new CampoTextoVazioException("Campo de texto vazio");
		}
		
		topicoDAO.salva( topico );
		
	}

	@Override
	public void atualizar(Topico topico) throws ClassNotFoundException, SQLException {
		
	}

	@Override
	public void excluir( Integer topico ) throws ClassNotFoundException, SQLException {
		topicoDAO.exclui( topico );
	}

	@Override
	public Topico buscar(Integer idTopico, Integer codUser) throws ClassNotFoundException, SQLException {
		return topicoDAO.buscar(idTopico, codUser);
	}

	@Override
	public List<Topico> getLista(Integer id) throws ClassNotFoundException, SQLException {
		return topicoDAO.getLista(id);
	}

	@Override
	public void gravarComentario(ComentarioTopico comentario) throws ClassNotFoundException, SQLException {
		topicoDAO.persisteComentario(comentario);
	}

	@Override
	public void atualizarComentario(ComentarioTopico comentario) throws ClassNotFoundException, SQLException {
		
	}

	@Override
	public void excluirComentario(Integer idComentario) throws ClassNotFoundException, SQLException {
		topicoDAO.excluirComentario(idComentario);
	}

	@Override
	public List<ComentarioTopico> listarComentarios(Integer codTopico) throws ClassNotFoundException, SQLException {
		return topicoDAO.listarComentarios(codTopico);
	}

}
