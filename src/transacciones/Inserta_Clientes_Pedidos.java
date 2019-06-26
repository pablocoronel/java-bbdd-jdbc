package transacciones;

import java.sql.*;

public class Inserta_Clientes_Pedidos {

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

			String instruccionSql_1 = "INSERT INTO CLIENTES (CÓDIGOCLIENTE, EMPRESA, DIRECCIÓN) VALUES ('CT84', 'EJEMPLO', 'P BOTANICO')";

			miStatement.executeUpdate(instruccionSql_1);

			String instruccionSql_2 = "INSERT INTO PEDIDOS (NÚMERODEPEDIDO, CÓDIGOCLIENTE,FECHADEPEDIDO) VALUES('82', 'CT84', '2000-11-03')";

			miStatement.executeUpdate(instruccionSql_2);

			/**
			 * 3) final de las sentencias que pertenecen al grupo de la transaccion
			 */
			miConexion.commit();

			System.out.println("Datos INSERTADOS correctamente");

		} catch (Exception e) {

			System.out.println("ERROR EN LA INSERCIÓN DE DATOS!!");

			/**
			 * 4) Rollback para deshacer las ejecuciones SQL en caso de alguna falla
			 */
			try {
				miConexion.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			e.printStackTrace();

		}

	}

}
