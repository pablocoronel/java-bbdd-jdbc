package transacciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Transaccion_Productos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection miConexion = null;

		try {

			miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_curso_sql", "root", "");

			/**
			 * 1) declarar que es una transaccion - false-
			 */
			miConexion.setAutoCommit(false);

			/**
			 * 2) sentencias SQL
			 */
			Statement miStatement = miConexion.createStatement();

			String instruccionSql_1 = "DELETE FROM productos WHERE PAÍS_DE_ORIGEN = 'ITALIA'";

			String instruccionSql_2 = "DELETE FROM productos WHERE PRECIO > 300";

			String instruccionSql_3 = "UPDATE productos SET PRECIO = PRECIO * 1.15";

			boolean ejecutar = ejecutar_transaccion();

			if (ejecutar) {
				miStatement.executeUpdate(instruccionSql_1);
				miStatement.executeUpdate(instruccionSql_2);
				miStatement.executeUpdate(instruccionSql_3);

				miConexion.commit();

				System.out.println("Transaccion ejecutada");
			} else {
				System.out.println("No se realizaron cambios en la BBDD");
			}

		} catch (Exception e) {
			// TODO: handle exception
			try {
				miConexion.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			System.out.println("ERROR EN LA TRANSACCION");
			e.printStackTrace();

		}
	}

	static boolean ejecutar_transaccion() {
		boolean resultado = false;
		String ejecucion = JOptionPane.showInputDialog("¿Ejecutar transacción?");

		if (ejecucion.equalsIgnoreCase("si")) {
			resultado = true;
		}

		return resultado;
	}
}
