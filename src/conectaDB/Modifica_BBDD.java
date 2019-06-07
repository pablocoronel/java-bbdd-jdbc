package conectaDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Modifica_BBDD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// conexion a la bd
		String url_conexion = "jdbc:mysql://localhost:3306/jdbc_curso";
		String user_conexion = "root";
		String password_conexion = "";

		try {
			/**
			 * 1ro: crear conexion
			 */
			Connection mi_conexion = DriverManager.getConnection(url_conexion, user_conexion, password_conexion);

			/**
			 * 2do: crear object statement
			 */
			Statement mi_statement = mi_conexion.createStatement();

			/**
			 * 3ro sentencia sql
			 */

			// Insert
			// String instruccion_SQL = "INSERT INTO PRODUCTOS (CÓDIGOARTÍCULO,
			// NOMBREARTÍCULO, PRECIO) VALUES ('AR77', 'Pantalón', 25.35)";

			// Update
			//String instruccion_SQL = "UPDATE PRODUCTOS SET PRECIO = PRECIO * 2 WHERE CÓDIGOARTÍCULO = 'AR77'";

			// Delete
			String instruccion_SQL = "DELETE FROM PRODUCTOS WHERE CÓDIGOARTÍCULO = 'AR77'";
			
			mi_statement.executeUpdate(instruccion_SQL);

			System.out.println("Datos eliminados correctamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
