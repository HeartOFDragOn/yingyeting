package proxy;

import dbc.DatabaseConnection;
import vo.Customer;
import DAO.CustomerDAO;
import DAOImpl.CustomerDAOImpl;

public class CustomerDAOProxy implements CustomerDAO{

	private DatabaseConnection dbc=null;
	private CustomerDAO dao=null;
	
	public CustomerDAOProxy(){
		this.dbc=new DatabaseConnection();
		this.dao=new CustomerDAOImpl(this.dbc.getConn());
	}
	
	@Override
	public boolean insert(Customer cus) {
		boolean flag=false;
		if(this.dao.findByNameAndPwd(cus.getCustomer_username(), cus.getCustomer_pwd())==null){
			flag=this.dao.insert(cus);
		}
		this.dbc.close();
		return flag;
	}


	@Override
	public Customer findByNameAndPwd(String username, String password) {
		Customer cus=this.dao.findByNameAndPwd(username, password);
		this.dbc.close();
		return cus;
	}

	@Override
	public boolean updatePass(String username,String oldPassword,String newPassword) {
		boolean flag=this.dao.updatePass(username, oldPassword, newPassword);
		this.dbc.close();
		return flag;
	}

	@Override
	public boolean updateAll(Customer oldCus,Customer newCus) {
		boolean flag=this.dao.updateAll(oldCus, newCus);
		this.dbc.close();
		return flag;
	}

	@Override
	public boolean delete(String username, String password) {
		boolean flag=this.dao.delete(username, password);
		this.dbc.close();
		return flag;
	}

}
