package Dao;

import java.security.cert.TrustAnchor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

import Entity.Group;

public class GroupDao {
	private DataSource dataSource;
	Group group = null;
	ArrayList<Group> groups= null;

	public void setDataSource(DataSource dataSource) {
	
		this.dataSource = dataSource;
	}
	public void delete(int id) {
		String sql  = "DELETE FROM groups WHERE id=?";
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


	public boolean insert(Group group){

		String sql = "INSERT INTO groups " +
				"(name) VALUES (?)";
		Connection conn = null;
		

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, group.getName());
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
	public void update(Group group){
		String sql = "UPDATE groups SET  name = ? where id = ?  ";
		//System.out.println("updating : "+group);
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, group.getName());
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

	
	
	public Group getGroup(int id){

		String sql = "SELECT * FROM groups WHERE id = ?";
		
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				this.group = new Group(
					rs.getInt("id"),
					rs.getString("name")
				);
			}
			rs.close();
			ps.close();
			return group;
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
	public void deletePerms(int groupId) {
		String sql  = "DELETE FROM group_perms WHERE group_id=?";
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,groupId);
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
	public boolean getAccess(int groupId,int permId){
		ArrayList<Integer> group_perms = new ArrayList<Integer>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from group_perms where group_id = ? and permission_id = ?");
			ps.setInt(1, groupId);
            ps.setInt(2, permId);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
				return true;
			rs.close();
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
	return false;

	}

	public ArrayList<Group> getGroupPerms(int groupId){
		ArrayList<Integer> group_perms = new ArrayList<Integer>();
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("select permission_id from group_perms where group_id = ? ");
			ps.setInt(1, groupId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) 
				group_perms.add(rs.getInt("id"));
			rs.close();
			ps.close();
			return groups;
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


	public void setGroupPerms(int groupId,String []perms ){

		deletePerms(groupId);
		for (String permId:perms
			 ) {
			setGroupPerms(groupId,Integer.parseInt(permId));

		}
	}
	private boolean setGroupPerms(int groupId,int permId){

		String sql = "INSERT INTO group_perms " +
				"(group_id,permission_id) VALUES (?,?)";
		Connection conn = null;


		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, groupId);
			ps.setInt(2, permId);
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


	public ArrayList<Group> getGroups(){
		groups = new ArrayList<Group>();
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from groups ORDER BY id");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) 
				this.groups.add(new Group(
						rs.getInt("id"),
						rs.getString("name")
				));
			
			rs.close();
			ps.close();
			return groups;
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
