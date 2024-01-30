package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.ProductDao;
import dto.ProductDto;
@WebServlet("/addproduct")
@MultipartConfig(maxFileSize = 1024*1024*5)
public class addproduct extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		double price=Double.parseDouble(req.getParameter("price"));
		String brand=req.getParameter("brand");
		double discount=Double.parseDouble(req.getParameter("discount"));
		Part image=req.getPart("image");
		
		ProductDto p=new ProductDto();
		p.setImage(image.getInputStream().readAllBytes());
		p.setId(id);
		p.setName(name);
		p.setPrice(price);
		p.setBrand(brand);
		p.setDiscount(discount);
		
		p.setImage(image.getInputStream().readAllBytes());
		
		ProductDao dao=new ProductDao();
		
		try {
		int res=	dao.SaveProduct(p);

			HttpSession session=req.getSession();
		String email=(String)session.getAttribute("email");
			if(res ==1) {
				req.setAttribute("products",dao.getAll());

				RequestDispatcher dispatcher=req.getRequestDispatcher("home.jsp");
				dispatcher.include(req, resp);
				}
			else {
				req.setAttribute("message", "product save failed");
				RequestDispatcher dispatcher=req.getRequestDispatcher("addproduct.jsp");
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
