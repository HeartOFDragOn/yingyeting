package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

import vo.Customer;
import DAO.CustomerDAO;

public class CustomerDAOImpl implements CustomerDAO {

	private Connection conn=null;
	private PreparedStatement pstmt=null;
	
	public CustomerDAOImpl(Connection conn){
		this.conn=conn;
	}
	
	@Override
	public boolean insert(Customer cus) {
		boolean flag=false;
		String sql="INSERT INTO t_customer VALUES (?,?,?,?,?,?,?,?)";
		try {
			this.pstmt=this.conn.prepareStatement(sql);
			this.pstmt.setString(1,cus.getCustomer_Id());
			this.pstmt.setString(2,cus.getCustomer_username());
			this.pstmt.setString(3,cus.getCustomer_name());
			this.pstmt.setString(4,cus.getId_card_numb());
			this.pstmt.setString(5,cus.getTel_numb());
			this.pstmt.setString(6,cus.getCustomer_birthday());
			this.pstmt.setString(7,cus.getCustomer_email());
			this.pstmt.setString(8,cus.getCustomer_pwd());
			if(this.pstmt.executeUpdate()>0){
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return flag;
	}

	@Override
	public Customer findByNameAndPwd(String username, String password) {
		Customer cus=null;
		String sql="SELECT * FROM t_customer WHERE Customer_username=? AND Custmomer_pwd=?";
		try {
			this.pstmt=this.conn.prepareStatement(sql);
			this.pstmt.setString(1, username);
			this.pstmt.setString(2, password);
			ResultSet rs=this.pstmt.executeQuery();
			if(rs.next()){
				cus=new Customer();
				cus.setCustomer_Id(rs.getString(1));
				cus.setCustomer_username(rs.getString(2));
				cus.setCustomer_name(rs.getString(3));
				cus.setId_card_numb(rs.getString(4));
				cus.setTel_numb(rs.getString(5));
				cus.setCustomer_birthday(rs.getString(6));
				cus.setCustomer_email(rs.getString(7));
				cus.setCustomer_pwd(rs.getString(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cus;
	}

	@Override
	public boolean updatePass(String username,String oldPassword,String newPassword) {
		boolean flag=false;
		String sql="UPDATE t_customer SET Custmomer_pwd=? WHERE Customer_username=? AND Custmomer_pwd=?";
		try {
			this.pstmt=this.conn.prepareStatement(sql);
			this.pstmt.setString(1,newPassword);
			this.pstmt.setString(2,username);
			this.pstmt.setString(3,oldPassword);
			if(this.pstmt.executeUpdate()>0){
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return flag;
	}

	@Override
	public boolean updateAll(Customer oldCus,Customer newCus) {
		boolean flag=false;
		String name=oldCus.getCustomer_username();
		String pass=oldCus.getCustomer_pwd();
		if(delete(name,pass)){
			if(insert(newCus)){
				flag=true;
			}
		}
		return flag;
	}

	@Override
	public boolean delete(String username, String password) {
		boolean flag=false;
		String sql="DELETE FROM t_customer WHERE Customer_username=? AND Custmomer_pwd=?";
		try {
			this.pstmt=this.conn.prepareStatement(sql);
			this.pstmt.setString(1,username);
			this.pstmt.setString(2,password);
			if(this.pstmt.executeUpdate()>0){
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return flag;
	}

	

}
