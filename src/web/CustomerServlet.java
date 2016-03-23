package web;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import factory.DAOFactory;

import vo.Customer;

public class CustomerServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	
	public void doGet(HttpServletRequest req,HttpServletResponse resp){
		this.doPost(req, resp);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse resp){
		try {
			req.setCharacterEncoding("GBK");
			
//			String results=req.getQueryString();
//			String[] s=results.split("=");
//			String requestMethod=s[1];
			String requestMethod=req.getParameter("action");
			//System.out.println(requestMethod);
			if("login".equals(requestMethod)){
				String username=req.getParameter("username");
				String password=req.getParameter("password");
				//System.out.println(requestMethod+username+password);
				Customer cus=DAOFactory.getCustomerDAOInstance().findByNameAndPwd(username, password);
				if(cus!=null){
					JSONObject object=new JSONObject();
					String ID=cus.getCustomer_Id();
					String realName=cus.getCustomer_name();
					String IDcard=cus.getId_card_numb();
					String telNum=cus.getTel_numb();
					String email=cus.getCustomer_email();
					String birthday=cus.getCustomer_birthday();
					object.put("ID", ID);
					object.put("username", username);
					object.put("password", password);
					object.put("realName", realName);
					object.put("IDcard", IDcard);
					object.put("telNum", telNum);
					object.put("email", email);
					object.put("birthday", birthday);
					try {
						PrintWriter out=resp.getWriter();
						resp.setContentType("text/html");  
					    resp.setHeader("Cache-Control", "no-store");  
					    resp.setHeader("Pragma", "no-cache");  
					    resp.setDateHeader("Expires", 0);
					    out.write(object.toString());
//					    if(s!=null&&("").equals(s)){
//					    	out.write(s);
//					    }else{
//					    	out.write("fail");
//					  					     
//							out.print("<html>");
//							out.print("<head><title>fanhui</title></head>");
//							out.print("<body>");
//							out.print("<h1>");
//							out.print(cus.getCustomer_name());
//							out.print("</h1></body>");
//							out.print("</html>");
//							out.flush();
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					PrintWriter out;
					try {
						out = resp.getWriter();
						resp.setContentType("text/html");  
					    resp.setHeader("Cache-Control", "no-store");  
					    resp.setHeader("Pragma", "no-cache");  
					    resp.setDateHeader("Expires", 0);
					    out.write("fail");
					    out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
					
				
			}else if("register".equals(requestMethod)){
				
				Customer cus=new Customer();
				String username=req.getParameter("username");
				String realname=req.getParameter("realname");
				String IDcard=req.getParameter("IDcard");
				String email=req.getParameter("email");
				String birthday=req.getParameter("birthday");
				String telNum=req.getParameter("telNum");
				String password=req.getParameter("password");
				//生成不会重复的ID
				SimpleDateFormat sdf=new SimpleDateFormat("MMddhhmmss");
				String id=sdf.format(new Date());
				
				cus.setCustomer_Id(id);
				cus.setCustomer_username(username);
				cus.setCustomer_name(realname);
				cus.setCustomer_pwd(password);
				cus.setId_card_numb(IDcard);
				cus.setTel_numb(telNum);
				cus.setCustomer_birthday(birthday);
				cus.setCustomer_email(email);
				
				try {
					PrintWriter out=resp.getWriter();
					if(DAOFactory.getCustomerDAOInstance().insert(cus)){
						resp.sendRedirect("successRegist.html");
						//System.out.println("true");
					}else{
						resp.sendRedirect("failRegist.html");
						//System.out.println("false");
					}
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				System.out.println("没找到");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
