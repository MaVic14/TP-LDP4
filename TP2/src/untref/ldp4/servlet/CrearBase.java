package untref.ldp4.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CrearBase
 */
@WebServlet("/CrearBase")
public class CrearBase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearBase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("CrearBase.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("crear") == null  ){
			response.sendRedirect("CrearBase.jsp");
		}
		
		PrintWriter out = response.getWriter();
		
		Connection conexion = Global.getConexionDb("mysql");
		if(conexion == null){
			out.println("No se pudo establecer conexi�n con la base de datos");
			return;
		}
		Statement statement = null;
		
		try{
			statement = conexion.createStatement();
			statement.execute("DROP DATABASE IF EXISTS " + Global.nombreDb);
			statement.execute("CREATE DATABASE " + Global.nombreDb);
			
			conexion.close();
			conexion = Global.getConexionDb(Global.nombreDb);
			
			if(conexion == null){
				out.println("2 No se pudo establecer conexi�n con la base de datos");
				return;
			}
			
			statement = conexion.createStatement();
			statement.execute("CREATE TABLE usuario( id INT PRIMARY KEY AUTO_INCREMENT, " +
													"nombre_usuario VARCHAR(50), " +
													"password CHAR(32), " +
													"mail VARCHAR(50), " +
													"es_activo TINYINT DEFAULT 0," +
													"fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
			
			statement.execute("CREATE TABLE producto( id INT PRIMARY KEY AUTO_INCREMENT, " +
													 "nombre_producto VARCHAR(30), " + 
													 "descripcion VARCHAR(100), " + 
													 "imagen VARCHAR(50) )");
			conexion.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		out.print("Base creada con correctamente");
	}
		
}
