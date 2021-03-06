package untref.ldp4.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NuevoUsuario
 */
@WebServlet(name="NuevoUsuario", urlPatterns={"/NuevoUsuario"})
public class NuevoUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NuevoUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("NuevoUsuario.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("txtNombre") != null){

			PrintWriter out = response.getWriter();
			
			String nombreUsuario = (String) request.getParameter("txtNombre");
			String passwordUsuario = (String) request.getParameter("txtPass");
			String emailUsuario = (String) request.getParameter("txtMail");
			out.println(nombreUsuario);
			out.println(passwordUsuario);
			out.println(emailUsuario);
			try {
				Statement statement = Global.getConexionDb(Global.nombreDb).createStatement();
				statement.execute("INSERT INTO usuario(nombre_usuario, password, mail) "
														+ "VALUES('" + nombreUsuario
														+ "', '" + passwordUsuario 
														+ "', '" + emailUsuario + "')");
			
				out.println("Usuario Creado correctamente");
			} catch (SQLException e) {
				e.printStackTrace();
				out.println("Error conectando con base de datos");
			}
		}
		else{
			response.getWriter().println("(Debug) No se recibieron datos de POST");
		}
		
	}

}
