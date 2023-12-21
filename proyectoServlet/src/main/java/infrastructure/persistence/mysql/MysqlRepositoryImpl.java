package infrastructure.persistence.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import infrastructure.persistence.IPersistencia;
import modelos.Usuario;

public  class MysqlRepositoryImpl implements IPersistencia {
private Connection connection;
	
	public MysqlRepositoryImpl() {
		
		connection = ConexionBD.getConnection();
	}
	
	
	@Override
	public void guardar(Usuario newUsuario) {
		
		String query = "INSERT INTO usuarios (nombre, apellido, telefono, email) VALUES (?,?,?,?)";
		
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1 , newUsuario.getNombre());
			statement.setString(2 , newUsuario.getApellido());
			statement.setString(3 , newUsuario.getTelefono());
			statement.setString(4 , newUsuario.getEmail());
			
			statement.execute();
			  // Hacer el commit de la transacción
	        connection.commit();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//agregué try catch
		try {
            connection.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		
	}

	@Override
	public ArrayList<Usuario> listarUsuarios() {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		String query = "SELECT * FROM usuarios";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
			int id = resultSet.getInt("id");
			String nombre = resultSet.getString("nombre");
			String apellido = resultSet.getString("apellido");
			String telefono = resultSet.getString("telefono");
			String email = resultSet.getString("email");
			
			
			//Crear un nuevo objeto Usuario con los datos obtenidos
			Usuario usuario = new Usuario(id, nombre, apellido, telefono, email);
			
			usuarios.add(usuario);
			
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return usuarios;
	}

	@Override
	public Usuario getUsuarioPorId(String id) {
		return null;
	   
	}



	@Override
	public Usuario update(Usuario usuario) {
		return usuario;
	  
	}


	@Override
	public void eliminar(int id) {
		 String query = "DELETE FROM usuarios WHERE id = ?";

	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setInt(1, id);
	            statement.execute();
	            connection.commit();
	            System.out.println("Usuario con ID " + id + " eliminado");
	        } catch (SQLException e) {
	            e.printStackTrace();
	           
	        }
	    }

}
