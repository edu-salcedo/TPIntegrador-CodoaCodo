package proyectoServlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import infrastructure.persistence.IPersistencia;
import infrastructure.persistence.mysql.MysqlRepositoryImpl;
import mappers.MapperJson;
import modelos.Usuario;

@WebServlet("/endpoint/*")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// private IPersistencia persistence = new MemoryRepositoryImpl();
	    private IPersistencia persistence = new MysqlRepositoryImpl();
	    private MapperJson mapper = new MapperJson();
	    
	    public Controlador() {
	        
	    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			ArrayList<Usuario> lista = persistence.listarUsuarios();
			String listaUsuariosJson = mapper.toJson(lista);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			response.getWriter().write(listaUsuariosJson);
			
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			String nombre = request.getParameter("nombreInput");
			String apellido = request.getParameter("apellidoInput");
			String telefono = request.getParameter("inputTel");
			String email = request.getParameter("inputEmail");
			
			Usuario usuario = new Usuario (nombre, apellido, telefono, email);
			
			// persistir el usuario
			persistence.guardar(usuario);
				
		
			String usuarioJson = mapper.toJson(usuario); // usamos Jackson como dependencia que agregamos al pom.xml

			response.getWriter().write(usuarioJson);
			
       }
		
        //prueba método doDelete
		protected void doDelete(HttpServletRequest request, HttpServletResponse response)
		            throws ServletException, IOException {
			 //String idParam = request.getPathInfo();
		        String idParam = request.getParameter("id");
		        
		        if (idParam != null && !idParam.isEmpty()) {
		            try {
		            	 int id = Integer.parseInt(idParam);
		                persistence.eliminar(id);

		                response.setContentType("text/plain");
		                response.getWriter().write("Usuario eliminado exitosamente.");
		            } catch (NumberFormatException e) {
		                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de usuario no válido.");
		            }
		        } else {
		            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Se requiere un ID de usuario.");
		        }
		    }
		
	
		
		
}
		

		
		
		

