package factory;

import proxy.CustomerDAOProxy;
import DAO.CustomerDAO;

public class DAOFactory {
	public static CustomerDAO getCustomerDAOInstance(){
		return new CustomerDAOProxy();
	}
}
