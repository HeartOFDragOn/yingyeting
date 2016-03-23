package test;

import factory.DAOFactory;
import vo.Customer;

public class test {
	public static void main(String[] args){
		
		Customer cus1=new Customer();
		Customer cus2=new Customer();
		cus1.setCustomer_Id("1313042044");
		cus1.setCustomer_username("她的心");
		cus1.setCustomer_name("仲其深");
		cus1.setId_card_numb("320340199512120317");
		cus1.setTel_numb("18860976657");
		cus1.setCustomer_birthday("1995-12-12");
		cus1.setCustomer_email("446616008@qq.com");
		cus1.setCustomer_pwd("19951212jkl");
		
		String username="龙之心0";
		String password="23344350";
		cus2=DAOFactory.getCustomerDAOInstance().findByNameAndPwd(username, password);
		DAOFactory.getCustomerDAOInstance().updateAll(cus2,cus1);
		
//		Customer cus=null;
//		for(int i=0;i<5;i++){
//			cus=new Customer();
//			cus.setCustomer_Id("123465"+i+"456");
//			cus.setCustomer_username("龙之心"+i);
//			cus.setCustomer_name("仲其深"+i);
//			cus.setId_card_numb("123456777"+i);
//			cus.setTel_numb("12345678990");
//			cus.setCustomer_birthday("1995-12-"+i+1);
//			cus.setCustomer_email(i+"1234@qq.com");
//			cus.setCustomer_pwd("2334435"+i);
//			DAOFactory.getCustomerDAOInstance().insert(cus);
//		}
	}
}
