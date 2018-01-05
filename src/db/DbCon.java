package db;
import java.io.UnsupportedEncodingException;
import java.sql.*;
//import org.gjt.mm.mysql.*;

import javax.naming.NamingException;

import bean.Customer;
public class DbCon {
	private String url = "jdbc:odbc:stu";
	public Connection con;

	protected void close() {
		try {
			con.close();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		System.err.println("delete ok!");
	}

	public Connection getConnection() {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}
		try {
			con = DriverManager.getConnection(url, "system", "system");
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		return con;
	}
}