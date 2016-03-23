package DAO;
import vo.Customer;
public interface CustomerDAO {
	public boolean insert(Customer cus);
	public boolean updatePass(String username,String oldPassword,String newPassword);
	public Customer findByNameAndPwd(String username,String password);
	public boolean updateAll(Customer oldCus,Customer newCus);
	public boolean delete(String username,String password);
}
