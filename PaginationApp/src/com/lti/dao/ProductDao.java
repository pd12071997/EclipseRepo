package com.lti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lti.exception.DataAccessException;
import com.lti.model.Product;

//Data Access Object
//classes which contain code to interact
//with the DB are commonly referred to as
//DAO classes
public class ProductDao {

	public List<Product> fetch(int from, int to) throws DataAccessException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "sys as sysdba","Newuser123");
			String sql = "select * from (select p.*, rownum r from Product p) where r between ? and ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, from);
			stmt.setInt(2, to);
			rs = stmt.executeQuery();
			
			List<Product> list = new ArrayList<Product>();
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setName(rs.getString(2));
				product.setPrice(rs.getDouble(3));
				product.setQuantity(rs.getInt(4));
				list.add(product);
			}
			return list;
		}
		catch (Exception e) {
			throw new DataAccessException("Problem while fetching", e);
		}
		finally {
			try { rs.close(); } catch (Exception e) { }
			try { stmt.close(); } catch (Exception e) { }
			try { conn.close(); } catch (Exception e) { }
		}
	}
}
