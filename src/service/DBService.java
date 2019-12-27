package service;

import java.sql.*;

class DBService {
	public Connection getConnection() throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		//Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/puzzle", "root", "java1234");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nextn1028", "nextn1028", "kjh1656!@");
		return conn;
	}
}
