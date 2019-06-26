package transacciones;

import java.sql.*;

public class Inserta_Clientes_Pedidos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_curso_sql", "root", "");

			Statement miStatement = miConexion.createStatement();

			String instruccionSql_1 = "INSERT INTO CLIENTES (CÓDIGOCLIENTE, EMPRESA, DIRECCIÓN) VALUES ('CT84', 'EJEMPLO', 'P BOTANICO')";

			miStatement.executeUpdate(instruccionSql_1);

			String instruccionSql_2 = "INSERT INTO PEDIDOS (NÚMERODEPEDIDO, CÓDIGOCLIENTE,FECHADEPEDIDO) VALUES('82', 'CT84', '11/03/2000')";

			miStatement.executeUpdate(instruccionSql_2);

			System.out.println("Datos INSERTADOS correctamente");

		} catch (Exception e) {

			System.out.println("ERROR EN LA INSERCIÓN DE DATOS!!");

			e.printStackTrace();

		}

	}

}
