package br.edu.insper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.edu.insper.model.Organization;
import br.edu.insper.model.Usuarios;

public class DAO {
	private Connection connection = null;
	
	public DAO() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost/projeto_crud", "root", "Lilic@1430");
	}
	
	
	public Usuarios login(String usuario, Integer senha) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM usuarios WHERE usuario = ? AND senha = ?");
		ps.setString(1, usuario);
		ps.setInt(2, senha);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			Usuarios user = new Usuarios();
			user.setUsuario(rs.getString("usuario"));
			user.setSenha(rs.getInt("senha"));
			user.setUsuario_id(rs.getInt("usuario_id"));
			return user;
		}
		return null;
	}
	
	public void register(String usuario, Integer senha) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("INSERT INTO usuarios (usuario, senha) VALUES (?,?)");
		ps.setString(1, usuario);
		ps.setInt(2, senha);
		ps.execute();
		ps.close();
	}
	
	public Usuarios getUsuario(int user_id) {
		PreparedStatement ps;
		Usuarios usuario = new Usuarios();
		try {
			ps = connection.prepareStatement("SELECT * FROM usuarios WHERE usuario_id = ?");
			ps.setInt(1, user_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				usuario.setUsuario_id(rs.getInt("usuario_id"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usuario;
	}
	
	
	public List<Organization> getLista(int id_user) throws SQLException {
		List<Organization> organizations = new ArrayList<Organization>();
				
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM organization WHERE id_user = ?");
			stmt.setInt(1, id_user);
			ResultSet rs = stmt.executeQuery();
		
			while (rs.next()) {
				Organization organization = new Organization();
				
				organization.setId(rs.getInt("id"));
				organization.setId_user(rs.getInt("id_user"));
				
				organization.setToDo(rs.getString("todo"));
				
				Calendar deadline = Calendar.getInstance();
				deadline.setTime(rs.getDate("deadline"));
				organization.setDeadline(deadline);
				
				organizations.add(organization);
			}
		
			rs.close();
			stmt.close();
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return organizations;
	}
	
	public void adicionaTask(String id_user, String todo, String deadlineStg) throws SQLException {
		String sql = "INSERT INTO organization" + "(id_user, todo, deadline) values(?, ?, ?)";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, id_user);
		stmt.setString(2, todo);
		stmt.setString(3, deadlineStg);
		
		stmt.execute();
		stmt.close();
	}
	
	public void atualizaTask(String todo, String id_user, String organization_id, String deadlineStg) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("UPDATE organization SET id_user = ?, todo = ?, deadline = ? WHERE id = ?");
		stmt.setInt(1, Integer.valueOf(id_user));
		stmt.setString(2, todo);
		stmt.setString(3, deadlineStg);
		stmt.setInt(4, Integer.valueOf(organization_id));
		stmt.execute();
		stmt.close();
		
	}
	
	public Organization getToDo(String organization_id) {
		PreparedStatement ps;
		Organization organization = new Organization();
		try {
			ps = connection.prepareStatement("SELECT * FROM organization WHERE id = ?");
			ps.setInt(1, Integer.valueOf(organization_id));
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				organization.setId(rs.getInt("id"));
				organization.setId_user(rs.getInt("id_user"));
				
				organization.setToDo(rs.getString("todo"));
				
				Calendar deadline = Calendar.getInstance();
				deadline.setTime(rs.getDate("deadline"));;
				organization.setDeadline(deadline);
				
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return organization;
	}
	
	
	public void remove(String id) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("DELETE FROM organization WHERE id=?");
		
		stmt.setInt(1, Integer.valueOf(id));
		stmt.execute();
		stmt.close();
		
	}
	
	public void atualiza(Organization organization) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("UPDATE organization SET todo=?, doing=?, done=? WHERE id=?");
		stmt.setNString(1, organization.getToDo());
		stmt.setNString(2, organization.getDoing());
		stmt.setNString(3, organization.getDone());
		stmt.setInt(4, organization.getId());

		stmt.execute();
		stmt.close();
	}
	
	
	
	
	public void close() throws SQLException {
		connection.close();
	}
}