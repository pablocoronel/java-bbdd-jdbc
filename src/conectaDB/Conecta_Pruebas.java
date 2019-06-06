package conectaDB;

import java.sql.*;

public class Conecta_Pruebas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// conexion a la bd
		String url_conexion = "jdbc:mysql://localhost:3306/jdbc_pruebas";
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
			 * 3ro: ejecutar SQL
			 */
			ResultSet mi_resulset = mi_statement.executeQuery("SELECT * FROM PRODUCTOS");

			/**
			 * 4to usar los datos devueltos
			 */

			while (mi_resulset.next()) {
				String nombre_articulo = mi_resulset.getString("NOMBREARTÍCULO");
				String codigo_articulo = mi_resulset.getString("CÓDIGOARTÍCULO");
				String precio = mi_resulset.getString("PRECIO");

				System.out.println(nombre_articulo + " " + codigo_articulo + " " + precio);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
