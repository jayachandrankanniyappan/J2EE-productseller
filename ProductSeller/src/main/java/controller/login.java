package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDao;
import dao.SellerDao;
import dto.SellerDto;
@WebServlet("/login")
public class login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String email=req.getParameter("email");
    	String password=req.getParameter("password");
    	
    	SellerDao sdao=new SellerDao();
    	ProductDao pdao=new ProductDao();
    	try {
			SellerDto seller= sdao.findByEmail(email);
			if(seller !=null) {
				
				if(seller.getPassword().equals(password)) {
					HttpSession session=req.getSession();
					session.setAttribute("email", email);
					req.setAttribute("products",pdao.getAll());
					RequestDispatcher dispatcher=req.getRequestDispatcher("home.jsp");
					dispatcher.include(req, resp);
				}
				else {
					req.setAttribute("message", "Wrong password");
					RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
					dispatcher.include(req, resp);
				}
			}
			else {
				req.setAttribute("message", "login failed");
				RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
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
