package interfaces;

import java.sql.SQLException;
import java.util.List;

import excecoes.CampoTextoVazioException;
import beans.ComentarioTopico;
import beans.Topico;

public interface TopicoBusinessInterface {

	public void gravar( Topico topico ) throws ClassNotFoundException, SQLException, CampoTextoVazioException;
	public void atualizar( Topico topico ) throws ClassNotFoundException, SQLException;
	public void excluir( Integer topico ) throws ClassNotFoundException, SQLException;
	public Topico buscar( Integer idTopico, Integer codUser ) throws ClassNotFoundException, SQLException;
	public List<Topico> getLista( Integer id  ) throws ClassNotFoundException, SQLException;
	public void gravarComentario(ComentarioTopico comentario) throws ClassNotFoundException, SQLException;
	public void atualizarComentario(ComentarioTopico comentario) throws ClassNotFoundException, SQLException;
	public void excluirComentario(Integer idComentario) throws ClassNotFoundException, SQLException;
	public List<ComentarioTopico> listarComentarios(Integer codTopico) throws ClassNotFoundException, SQLException;
	
}
