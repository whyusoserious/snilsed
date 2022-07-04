package net.javaguides.usermanagmenet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagmenet.model.User;

public class UserDAO {
	
	private String dbUrl = "jdbc:mysql://localhost:3306/snils";
	private String dbUsername = "root";
	private String dbPassword = "root";

	private static final String INSERT_SNILS_SQL = "INSERT INTO snils" + " (number) VALUES " + " (?); ";
	
	private static final String SELECT_ALL_SNILS = "select * from snils";
	
	protected Connection getConn() {
		Connection conn = null;
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
	}
	
	//insert snils
	public void insertNumber(User user) throws SQLException {
		try (Connection conn = getConn();
				PreparedStatement preparedStatement = conn.prepareStatement(INSERT_SNILS_SQL)){
			preparedStatement.setLong(1, user.getNumber());
			preparedStatement.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//get all snils
	public List<User> selectAllSnilses() {
		List<User> snilses = new ArrayList<>();
		try (Connection conn = getConn();
				PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_SNILS);){
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				long number = rs.getLong("number");
				snilses.add(new User(id, number));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return snilses;
	}
}
