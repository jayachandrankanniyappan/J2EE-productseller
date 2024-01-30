package controller;

//import java.io.IOException;
//import java.sql.SQLException;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//
//import dao.ProductDao;
//import dto.ProductDto;
//@WebServlet("/updateproduct")
//@MultipartConfig(maxFileSize = 1024*1024*100)
//public class updateproduct extends HttpServlet {
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		int id=Integer.parseInt(req.getParameter("User-Id"));
//		
//		String ProductName=req.getParameter("User-Product");
//		double price=Double.parseDouble(req.getParameter("User-price"));
//		String Brand=req.getParameter("User-Brand");
//		double discount=Double.parseDouble(req.getParameter("User-Discount"));
//	    Part image=req.getPart("User-image");
//	    
//		
//		ProductDto p=new ProductDto();
//		p.setId(id);
//		p.setBrand(Brand);
//		p.setDiscount(discount);
//		p.setPrice(price);
//		p.setName(ProductName);
//		p.setImage(image.getInputStream().readAllBytes());
//		
//		
//		ProductDao Pdao=new ProductDao();
//		
//		
//		try {
//			
//			if(image.getSize()>1) {
//		     Pdao.UpdateProduct(p);
//		     req.setAttribute("products", Pdao.getAll());
//		     RequestDispatcher dispatcher=req.getRequestDispatcher("home.jsp");
//		     dispatcher.include(req, resp);
//			}
//			else
//			{
//				 ProductDto p1=Pdao.FindById(id);
//				p.setImage(p1.getImage());
//				Pdao.UpdateProduct(p);
//			     req.setAttribute("products", Pdao.getAll());
//			     RequestDispatcher dispatcher=req.getRequestDispatcher("home.jsp");
//			     dispatcher.include(req, resp);
//				
//			}
//		} 
//	
//		catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
//	}
//
//}

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;

import dao.ProductDao;
import dto.ProductDto;

@WebServlet("/editproduct")
@MultipartConfig(maxFileSize = 1024*1024*5)
public class updateproduct extends HttpServlet
{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		double price = Double.parseDouble(req.getParameter("price"));
		String brand = req.getParameter("brand");
		double discount = Double.parseDouble(req.getParameter("discount"));
	    Part image = req.getPart("image");
		
		dto.ProductDto product = new dto.ProductDto();
		product.setId(id);
		product.setName(name);
		product.setPrice(price);
		product.setBrand(brand);
		product.setDiscount(discount);
		product.setImage(image.getInputStream().readAllBytes());
		
		dao.ProductDao pdao = new dao.ProductDao();
		
		try {
			if(image.getSize()>1)
			{
				pdao.UpdateProduct(product);
				req.setAttribute("products", pdao.getAll());
				RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
				dispatcher.include(req, resp);
			}
			else
			{
				dto.ProductDto oldProduct = pdao.FindById(id);
				byte[] img = oldProduct.getImage();
				product.setImage(img);
				
				pdao.UpdateProduct(product);
				req.setAttribute("products", pdao.getAll());
				RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
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
