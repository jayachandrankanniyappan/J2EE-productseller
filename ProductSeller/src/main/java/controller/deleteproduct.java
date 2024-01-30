package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
@WebServlet("/delete")
public class deleteproduct extends HttpServlet  {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	int id=Integer.parseInt(req.getParameter("id"));
    	ProductDao dao=new ProductDao();
    	try {
			int res=dao.deleteProduct(id);
			if(res==1) {
				req.setAttribute("products",dao.getAll());
				RequestDispatcher dispatcher=req.getRequestDispatcher("home.jsp");
				dispatcher.include(req, resp);
			}
			else {
				req.setAttribute("message","product delete failed");
				RequestDispatcher dispatcher=req.getRequestDispatcher("home.jsp");
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
