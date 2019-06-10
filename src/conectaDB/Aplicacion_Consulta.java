package conectaDB;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	private Connection conexion_BD;
	private PreparedStatement enviaConsultaSeccion;

	private final String consultaSeccion = "SELECT NOMBRE_ART�CULO, SECCI�N, PRECIO, PA�S_DE_ORIGEN "
			+ "FROM productos WHERE SECCI�N = ?";

	// constructor
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

		botonConsulta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ejecutaConsulta();
			}
		});

		add(botonConsulta, BorderLayout.SOUTH);

		/**
		 * CONEXION CON BD
		 */
		String url = "jdbc:mysql://localhost:3306/jdbc_curso_sql";
		String user = "root";
		String password = "";
		try {
			// conexion
			this.conexion_BD = DriverManager.getConnection(url, user, password);

			// statement
			Statement statement = this.conexion_BD.createStatement();

			// consulta SECCIONES
			String sql = "SELECT DISTINCT SECCI�N FROM productos";
			ResultSet resultado = statement.executeQuery(sql);

			// recorrer
			while (resultado.next()) {
				this.secciones.addItem(resultado.getString("SECCI�N"));
			}

			/*************************************/
			// consulta PAISES
			sql = "SELECT DISTINCT PA�S_DE_ORIGEN FROM productos";
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

	// ejecuta la consulta filtrada
	private void ejecutaConsulta() {
		ResultSet rs = null;

		try {
			// obtiene la seccion elegida
			String seccion = (String) this.secciones.getSelectedItem();

			// la conexion recibe la consulta parametrizada
			enviaConsultaSeccion = this.conexion_BD.prepareStatement(this.consultaSeccion);

			// a la sentencia se le agrega el parametro
			enviaConsultaSeccion.setString(1, seccion);

			// ejecucion
			rs = enviaConsultaSeccion.executeQuery();

			// recorrido para mostrar los datos
			while (rs.next()) {
				this.resultado.append(rs.getString(1));
				this.resultado.append(", ");

				this.resultado.append(rs.getString(2));
				this.resultado.append(", ");

				this.resultado.append(rs.getString(3));
				this.resultado.append(", ");

				this.resultado.append(rs.getString(4));
				this.resultado.append("\n");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
