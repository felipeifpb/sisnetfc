package fabricaDAO;

import interfaces.InterfaceFotoDAO;
import interfaces.InterfaceGrupoDAO;
import interfaces.InterfaceTopicoDAO;
import interfaces.InterfaceUsuarioDAO;

public interface DAOFactoryInterface {

	public InterfaceFotoDAO createFotoDAO();
	public InterfaceGrupoDAO createGrupoDAO();
	public InterfaceTopicoDAO createTopicoDAO();
	public InterfaceUsuarioDAO createUsuarioDAO();
	
}
