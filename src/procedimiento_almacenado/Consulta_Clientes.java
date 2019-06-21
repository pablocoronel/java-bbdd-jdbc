package procedimiento_almacenado;

import java.sql.*;

public class Consulta_Clientes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			// conexion a la bd
			String url_conexion = "jdbc:mysql://localhost:3306/jdbc_curso_sql";
			String user_conexion = "root";
			String password_conexion = "";

			Connection mi_conexion = DriverManager.getConnection(url_conexion, user_conexion, password_conexion);

			CallableStatement mi_sentencia = mi_conexion.prepareCall("{call MUESTRA_CLIENTES}");

			ResultSet rs = mi_sentencia.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}

			rs.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
