package modelos;

import java.util.UUID;

public class Usuario {
private static int contador;
	
	private int id;
	private UUID idUsuario;
	private String nombre;
	private String apellido;
	private String telefono;
	private String email;
	
	
	public Usuario(String nombre, String apellido, String telefono, String email) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.id = ++contador;
		this.idUsuario = UUID.randomUUID();
	}

	public Usuario(int id, String nombre, String apellido, String telefono, String email) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono; 
		this.email = email; 
		this.idUsuario = UUID.randomUUID();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getId() {
		return id;
	}
	
	

	public UUID getIdUsuario() {
		return idUsuario;
	}

	 @Override
	    public String toString() {
	        return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + ", email=" + email + "]";
	    }

	public void setId(int id) {
		this.id = id;
		
	}
	
	

}
