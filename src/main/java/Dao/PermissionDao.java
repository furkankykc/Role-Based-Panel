package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

import Entity.Permission;

public class PermissionDao {
	private DataSource dataSource;
	Permission permission = null;
	ArrayList<Permission> permissions= null;

	public void setDataSource(DataSource dataSource) {
	
		this.dataSource = dataSource;
	}
	public void delete(int id) {
		String sql  = "DELETE FROM permission WHERE id=?";
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


	public boolean insert(Permission permission){

		String sql = "INSERT INTO permission " +
				"(name) VALUES (?)";
		Connection conn = null;
		

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, permission.getName());
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
	public void update(Permission permission){
		String sql = "UPDATE permission SET  name = ? where id = ?  ";
		//System.out.println("updating : "+permission);
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, permission.getName());
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

	
	
	public Permission getPermission(int id){

		String sql = "SELECT * FROM permission WHERE id = ?";
		
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				this.permission = new Permission(
					rs.getInt("id"),
					rs.getString("name")
				);
			}
			rs.close();
			ps.close();
			return permission;
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
	
	
	
	public ArrayList<Permission> getPermissions(){
		permissions = new ArrayList<Permission>();
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from permission");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) 
				this.permissions.add(new Permission(
						rs.getInt("id"),
						rs.getString("name")
				));
			
			rs.close();
			ps.close();
			return permissions;
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
