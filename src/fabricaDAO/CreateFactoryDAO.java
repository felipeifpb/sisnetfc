package fabricaDAO;


public class CreateFactoryDAO {

	public static DAOFactoryInterface createFactoryDAO(){
		return new DAOFactory();
	}
	
}
