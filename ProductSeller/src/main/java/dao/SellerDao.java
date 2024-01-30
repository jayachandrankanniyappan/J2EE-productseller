package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.SellerDto;

public class SellerDao {
	public int SaveSeller(SellerDto seller) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/productseller?user=root&password=root");
		PreparedStatement pst=conn.prepareStatement("insert into seller values(?,?,?,?,?)");
		pst.setInt(1,seller.getId());
		pst.setString(2,seller.getName());
		pst.setLong(3,seller.getContact());
		pst.setString(4,seller.getEmail());
		pst.setString(5,seller.getPassword());
		int res=pst.executeUpdate();
		conn.close();
		return res;
	}
	
	public SellerDto findByEmail(String email) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/productseller?user=root&password=root");
		PreparedStatement pst=conn.prepareStatement("select * from seller where email=?");
		pst.setString(1, email);
		ResultSet rs=pst.executeQuery();
		
		if(rs.next()) {
			SellerDto seller=new SellerDto();
			seller.setId(rs.getInt(1));
			seller.setName(rs.getString(2));
			seller.setContact(rs.getLong(3));
			seller.setEmail(rs.getString(4));
			seller.setPassword(rs.getString(5));
			conn.close();
			return seller;
		}
		return null;
		
	}

}
