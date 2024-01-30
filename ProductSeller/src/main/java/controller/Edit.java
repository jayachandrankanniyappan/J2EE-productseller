package controller;

//import java.io.IOException;
//import java.sql.SQLException;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import dao.ProductDao;
//@WebServlet("/edit")
//public class editproduct extends HttpServlet {
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		
//		
//		int id=Integer.parseInt(req.getParameter("id"));
//		
//		ProductDao Pdao=new ProductDao();
//		
//		try {
//			
//			HttpSession session=req.getSession();
//			String email=(String)session.getAttribute("email");
//			String Password=(String)session.getAttribute("Password");
//			 if(email!=null && Password!=null)
//			 { 
//			
//			   
//			req.setAttribute("products", Pdao.FindById(id));
//		    	RequestDispatcher dispatcher=req.getRequestDispatcher("edit.jsp");
//		    	dispatcher.include(req, resp);
//			 }
//			 else {
//				 req.setAttribute("message","login Required");
//				 RequestDispatcher dispatcher=req.getRequestDispatcher("home.jsp");
//				 dispatcher.include(req, resp);
//				 
//			 }
//		    
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}   
//}


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDao;
import dto.ProductDto;

@WebServlet("/edit")
public class Edit extends HttpServlet
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		
        ProductDto p = new ProductDto();
		p.setId(id);
		ProductDao pdao = new ProductDao();
		
		try {
			HttpSession session = req.getSession();
			String email = (String)session.getAttribute("email");
			
			if(email != null)
			{
				req.setAttribute("product", pdao.FindById(id));
				
				RequestDispatcher dispatcher = req.getRequestDispatcher("edit.jsp");
				dispatcher.include(req, resp);
			}
			else
			{
				req.setAttribute("login_msg", "Login Required");
				RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
				dispatcher.include(req, resp);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

