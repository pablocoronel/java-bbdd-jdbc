package conectaDB;

import java.sql.*;

public class Consulta_Preparada {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			/**
			 * 1ro crear conexion
			 */
			String url_conexion = "jdbc:mysql://localhost:3306/jdbc_curso";
			String user_conexion = "root";
			String password_conexion = "";

			Connection conexion = DriverManager.getConnection(url_conexion, user_conexion, password_conexion);

			/**
			 * 2do preparar consulta
			 */
			String sql = "SELECT NOMBREARTÍCULO, SECCIÓN, PAÍSDEORIGEN FROM PRODUCTOS WHERE SECCIÓN = ? AND PAÍSDEORIGEN = ?";
			PreparedStatement mi_sentencia = conexion.prepareStatement(sql);

			/**
			 * 3ro establecer parametros de consulta
			 */
			mi_sentencia.setString(1, "deportes");
			mi_sentencia.setString(2, "USA");

			/**
			 * 4to ejecutar y recorrer la consulta
			 */
			ResultSet rs = mi_sentencia.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}

			rs.close();


			///////////////////////////////////////////////////////////////
			/**
			 * REUTILIZACION DE CONSULTA PREPARADA
			 */
			System.out.println();
			System.out.println("Reutilizacion de consulta");

			/**
			 * 3ro establecer parametros de consulta
			 */
			mi_sentencia.setString(1, "cerámica");
			mi_sentencia.setString(2, "China");

			/**
			 * 4to ejecutar y recorrer la consulta
			 */
			rs = mi_sentencia.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}

			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
