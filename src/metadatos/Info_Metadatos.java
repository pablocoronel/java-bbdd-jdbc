package metadatos;

import java.sql.*;

public class Info_Metadatos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// ejecutar metodo
		mostrar_info_BBDD();
		System.out.println("");
		mostrar_info_Tablas();
	}

	/*
	 * metodo para metadata de la BD
	 */
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

	/*
	 * metodo para metedata de Tabla
	 */
	static void mostrar_info_Tablas() {
		Connection mi_conexion = null;

		try {
			mi_conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_pruebas", "root", "");

			/*
			 * obtener metadatos
			 */
			DatabaseMetaData datos_BBDD = mi_conexion.getMetaData();
			ResultSet mi_resulset = null;

			/*
			 * mostrar la info obtenida
			 */
			System.out.println("Lista de tablas");
			mi_resulset = datos_BBDD.getTables(null, null, null, null);

			while (mi_resulset.next()) {
				// "TABLE_NAME" es el noombre de la columna que tienen todas las tablas por
				// defecto

				System.out.println("Tabla: " + mi_resulset.getString("TABLE_NAME"));
			}

			/*
			 * lista de campos de la tabla Productos
			 */
			System.out.println("");
			System.out.println("Campos de Productos");

			mi_resulset = datos_BBDD.getColumns(null, null, "productos", null);

			while (mi_resulset.next()) {
				// "COLUMN_NAME" es el noombre de la columna que tienen todas las columnas para
				// una tabla

				System.out.println("Tabla: " + mi_resulset.getString("COLUMN_NAME"));
			}
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
