package metadatos;

import java.sql.*;

public class Info_Metadatos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// ejecutar metodo
		mostrar_info_BBDD();
	}

	static void mostrar_info_BBDD() {
		Connection mi_conexion = null;

		try {
			mi_conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_pruebas", "root", "");

			/*
			 * obtener metadatos
			 */
			DatabaseMetaData datos_BBDD = mi_conexion.getMetaData();

			/*
			 * mostrar la info obtenida
			 */
			System.out.println("Gestor de BD: " + datos_BBDD.getDatabaseProductName());
			System.out.println("Version del Gestor: " + datos_BBDD.getDatabaseProductVersion());
			System.out.println("Driver: " + datos_BBDD.getDriverName());
			System.out.println("version del driver: " + datos_BBDD.getDriverVersion());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				mi_conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
