package interfaces;

import java.sql.SQLException;
import java.util.List;

import beans.ComentarioTopico;
import beans.Topico;

public interface InterfaceTopicoDAO {
	
	public void salva( Topico topico ) throws ClassNotFoundException, SQLException;
	public void atualiza( Topico topico ) throws ClassNotFoundException, SQLException;
	public void exclui( Integer topico ) throws ClassNotFoundException, SQLException;
	public Topico buscar( Integer idTopico, Integer codUser ) throws ClassNotFoundException, SQLException;
	public List<Topico> getLista( Integer id  ) throws ClassNotFoundException, SQLException;
	public void persisteComentario(ComentarioTopico comentario) throws ClassNotFoundException, SQLException;
	public void atualizarComentario(ComentarioTopico comentario) throws ClassNotFoundException, SQLException;
	public void excluirComentario(Integer idComentario) throws ClassNotFoundException, SQLException;
	public List<ComentarioTopico> listarComentarios(Integer codTopico) throws ClassNotFoundException, SQLException;
	
}
