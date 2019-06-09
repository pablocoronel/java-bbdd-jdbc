package conectaDB;

import javax.swing.*;

import java.awt.*;
import java.sql.*;

public class Aplicacion_Consulta {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame mimarco = new Marco_Aplicacion();

		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mimarco.setVisible(true);

	}

}

class Marco_Aplicacion extends JFrame {
	private JComboBox<String> secciones;
	private JComboBox<String> paises;
	private JTextArea resultado;

	public Marco_Aplicacion() {

		setTitle("Consulta BBDD");

		setBounds(500, 300, 400, 400);

		setLayout(new BorderLayout());

		JPanel menus = new JPanel();

		menus.setLayout(new FlowLayout());

		secciones = new JComboBox<String>();

		secciones.setEditable(false);

		secciones.addItem("Todos");

		paises = new JComboBox<String>();

		paises.setEditable(false);

		paises.addItem("Todos");

		resultado = new JTextArea(4, 50);

		resultado.setEditable(false);

		add(resultado);

		menus.add(secciones);

		menus.add(paises);

		add(menus, BorderLayout.NORTH);

		add(resultado, BorderLayout.CENTER);

		JButton botonConsulta = new JButton("Consulta");

		add(botonConsulta, BorderLayout.SOUTH);

		/**
		 * CONEXION CON BD
		 */
		String url = "jdbc:mysql://localhost:3306/jdbc_curso_sql";
		String user = "root";
		String password = "";
		try {
			// conexion
			Connection conexion_BD = DriverManager.getConnection(url, user, password);

			// statement
			Statement statement = conexion_BD.createStatement();

			// consulta SECCIONES
			String sql = "SELECT DISTINCT SECCIÓN FROM productos";
			ResultSet resultado = statement.executeQuery(sql);

			// recorrer
			while (resultado.next()) {
				this.secciones.addItem(resultado.getString("SECCIÓN"));
			}

			/*************************************/
			// consulta PAISES
			sql = "SELECT DISTINCT PAÍS_DE_ORIGEN FROM productos";
			resultado = statement.executeQuery(sql);

			while (resultado.next()) {
				this.paises.addItem(resultado.getString(1));
			}

			// cierre
			resultado.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
