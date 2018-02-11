package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import Entity.User;

public class UserDao {
	private DataSource dataSource;
	User user = null;
	ArrayList<User> userList= null;

	public void setDataSource(DataSource dataSource) {
	
		this.dataSource = dataSource;
	}
	public void delete(int id) {
		String sql  = "DELETE FROM user WHERE id=?";
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,id);
			ps.executeUpdate();
			ps.close();
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public boolean insert(User user){

		String sql = "INSERT INTO user " +
				"(name,username,password,isAdmin) VALUES (?, ?, ?, ?)";
		Connection conn = null;
		

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, user.getName());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setInt(4, user.getType());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			return false;

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {return false;}
			}
		}
		return true;
	}
	public void update(User user){
		String sql = "UPDATE user SET name =? , username = ? , password = ? ,isAdmin = ? WHERE id = ? ";
		
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, user.getName());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setInt(4, user.getType());
			ps.setInt(5, user.getId());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	
	public User getUser(int id){

		String sql = "SELECT * FROM user WHERE id = ?";
		
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				this.user = new User(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getString("username"),
					rs.getString("password"),
					rs.getInt("isAdmin")
				);
			}
			rs.close();
			ps.close();
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	public User getUser(String username){

		String sql = "SELECT * FROM user WHERE username = ? ";
		
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				this.user = new User(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getString("username"),
					rs.getString("password"),
					rs.getInt("isAdmin")
				);
			}
			rs.close();
			ps.close();
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public ArrayList<User> getUser(){
		userList = new ArrayList<User>();
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from user");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) 
				this.userList.add(new User(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getInt("isAdmin")
				));
			
			rs.close();
			ps.close();
			return userList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		
	}

}
