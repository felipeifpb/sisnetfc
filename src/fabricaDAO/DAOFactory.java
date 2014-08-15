package fabricaDAO;

import dao.FotoDAO;
import dao.GrupoDAO;
import dao.TopicoDAO;
import dao.UsuarioDAO;
import interfaces.InterfaceFotoDAO;
import interfaces.InterfaceGrupoDAO;
import interfaces.InterfaceTopicoDAO;
import interfaces.InterfaceUsuarioDAO;

public class DAOFactory implements DAOFactoryInterface {

	@Override
	public InterfaceFotoDAO createFotoDAO() {
		return new FotoDAO();
	}

	@Override
	public InterfaceGrupoDAO createGrupoDAO() {
		return new GrupoDAO();
	}

	@Override
	public InterfaceTopicoDAO createTopicoDAO() {
		return new TopicoDAO();
	}

	@Override
	public InterfaceUsuarioDAO createUsuarioDAO() {
		return new UsuarioDAO();
	}

}
