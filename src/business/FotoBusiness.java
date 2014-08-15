package business;

import java.sql.SQLException;
import java.util.List;

import beans.Foto;
import fabricaDAO.CreateFactoryDAO;
import fabricaDAO.DAOFactoryInterface;
import interfaces.InterfaceFotoDAO;
import interfaces.FotoBusinessInterface;

public class FotoBusiness implements FotoBusinessInterface {

	private DAOFactoryInterface fabrica;
	private InterfaceFotoDAO fotoDAO;
	
	public FotoBusiness() {
		this.fabrica = CreateFactoryDAO.createFactoryDAO();
		this.fotoDAO = fabrica.createFotoDAO();
	}
	
	@Override
	public void salvarFoto(Foto foto) throws ClassNotFoundException,SQLException {
		fotoDAO.salvar(foto);
	}

	@Override
	public List<Foto> getListaFoto(Integer idUser)throws ClassNotFoundException, SQLException {
		return fotoDAO.getLista(idUser);
	}

	@Override
	public void excluir(Integer foto) throws ClassNotFoundException, SQLException {
		fotoDAO.excluir(foto);
	}

	
	
	
}
