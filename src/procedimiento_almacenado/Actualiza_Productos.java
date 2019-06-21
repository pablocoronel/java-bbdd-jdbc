package procedimiento_almacenado;

import java.sql.*;

import javax.swing.JOptionPane;

public class Actualiza_Productos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int nombre_precio = Integer.parseInt(JOptionPane.showInputDialog("Introduce precio"));
		String nombre_articulo = JOptionPane.showInputDialog("Introduce nombre articulo");

		try {
			// conexion a la bd
			String url_conexion = "jdbc:mysql://localhost:3306/jdbc_curso_sql";
			String user_conexion = "root";
			String password_conexion = "";

			Connection mi_conexion = DriverManager.getConnection(url_conexion, user_conexion, password_conexion);

			CallableStatement mi_sentencia = mi_conexion.prepareCall("{call ACTUALIZA_PRODUCTO(?,?)}");

			mi_sentencia.setInt(1, nombre_precio);
			mi_sentencia.setString(2, nombre_articulo);
			mi_sentencia.execute();

			System.out.println("actualizacion ok");

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
