package untref.ldp4.servlet;

import java.sql.*;
import java.sql.Connection;

//import com.mysql.jdbc.*;

public class Global {

	
	//Conexion Mysql
	public final static String mysqlDrv = "com.mysql.jdbc.Driver";
	public final static String mysqlDb = "jdbc:mysql://localhost:3306";
	
	private final static String usuarioMysql = "root";
	private final static String passwordMysql = ""; //"lp4untref";
	public  final static String nombreDb = "tp2lp4";
	//Java Mail
	
	public final static String usuarioMail = "mail@dominio.com";
	public final static String passwordMail = "miContraseņa";
	
	//Fin declaraciones globales
	
	public Global(){}
	
	public static synchronized Connection getConexionDb(String db){

		if(db != null && !db.equals("")){
			db = "/" + db;
		}
			
		Connection conexion = null;
		try {
			Class.forName(mysqlDrv).newInstance();
			String a = mysqlDb + db;
			conexion = DriverManager.getConnection(a ,usuarioMysql,passwordMysql);

		}catch (Exception e){
			e.printStackTrace();
			conexion = null;
		}

		return conexion;
	}
}
