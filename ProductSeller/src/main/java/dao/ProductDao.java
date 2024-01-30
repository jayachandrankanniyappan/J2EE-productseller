package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import dto.ProductDto;

public class ProductDao {
	public Connection getconnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/productseller?user=root&password=root");
	}
	
	public int SaveProduct(ProductDto product) throws ClassNotFoundException, SQLException {
		Connection conn=getconnection();
		PreparedStatement pst=conn.prepareStatement("insert into product values(?,?,?,?,?,?)");
		pst.setInt(1,product.getId());
		pst.setString(2,product.getName());
		pst.setDouble(3,product.getPrice());
		pst.setString(4,product.getBrand());
		pst.setDouble(5,product.getDiscount());
		Blob image=new SerialBlob(product.getImage());
		pst.setBlob(6, image);
		int res=pst.executeUpdate();
		return res;
	}
	
	public int UpdateProduct(ProductDto product) throws ClassNotFoundException, SQLException {
	    Connection conn=getconnection();
	    PreparedStatement pst=conn.prepareStatement("update product set name=?,price=?,brand=?,discount=?,image=? where id=?");
	    
		pst.setString(1,product.getName());
		pst.setDouble(2,product.getPrice());
		pst.setString(3,product.getBrand());
		pst.setDouble(4,product.getDiscount());
		Blob image=new SerialBlob(product.getImage());
		pst.setBlob(5, image);
		pst.setInt(6,product.getId());
		int res=pst.executeUpdate();
		return res;
	}
	public int deleteProduct(int id) throws ClassNotFoundException, SQLException {
		Connection conn=getconnection();
		PreparedStatement pst=conn.prepareStatement("delete from product where id=?");
		pst.setInt(1, id);
		int res=pst.executeUpdate();
		return res;
	}
	
 	public ProductDto FindById(int id) throws ClassNotFoundException, SQLException {
		Connection conn=getconnection();
		PreparedStatement pst=conn.prepareStatement("select * from product where id=?");
		pst.setInt(1, id);
		ResultSet rs=pst.executeQuery();
		rs.next();
		ProductDto product=new ProductDto();
		product.setId(rs.getInt(1));
		product.setName(rs.getString(2));
		product.setPrice(rs.getDouble(3));
		product.setBrand(rs.getString(4));
		product.setDiscount(rs.getDouble(5));
		Blob blob=rs.getBlob(6);
		byte[] image=blob.getBytes(1,(int)blob.length());
		product.setImage(image);
		
		conn.close();
		return product;
	}
	public List<ProductDto> getAll() throws ClassNotFoundException, SQLException{
		Connection conn=getconnection();
		PreparedStatement pst=conn.prepareStatement("select * from product");
		ResultSet rs=pst.executeQuery();
		List<ProductDto>products=new ArrayList<ProductDto>();
		while(rs.next()) {
			ProductDto product=new ProductDto();
			product.setId(rs.getInt(1));
			product.setName(rs.getString(2));
			product.setPrice(rs.getDouble(3));
			product.setBrand(rs.getString(4));
			product.setDiscount(rs.getDouble(5));
			Blob blob=rs.getBlob(6);
			byte[] image=blob.getBytes(1,(int)blob.length());
			product.setImage(image);
			
			products.add(product);
		}
		return products;	
	}
	
}
