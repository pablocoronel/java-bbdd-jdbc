package aplicacion_final;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

public class AplicacionUniversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MarcoBBDD mimarco = new MarcoBBDD();

		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mimarco.setVisible(true);

	}

}

class MarcoBBDD extends JFrame {

	public MarcoBBDD() {

		setBounds(300, 300, 700, 700);

		LaminaBBDD milamina = new LaminaBBDD();

		add(milamina);

	}

}

class LaminaBBDD extends JPanel {
	private JComboBox comboTablas;
	private JTextArea areaInformacion;
	private Connection mi_conexion;

	public LaminaBBDD() {

		setLayout(new BorderLayout());

		comboTablas = new JComboBox();

		areaInformacion = new JTextArea();

		add(areaInformacion, BorderLayout.CENTER);

		// conectar a la BD y cargarlo en el combo box
		conectarBBDD();
		getTablas();

		// evento para ver el contenido de las tablas en pantall
		comboTablas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String nombre_tabla = (String) comboTablas.getSelectedItem();

				mostrarInfoTabla(nombre_tabla);
			}
		});

		add(comboTablas, BorderLayout.NORTH);

	}

	protected void mostrarInfoTabla(String nombre_tabla) {
		// listado del nombre de campos de la tabla
		ArrayList<String> campos = new ArrayList<String>();

		String consulta = "SELECT * FROM " + nombre_tabla;

		try {
			// limpiar la pantalla
			areaInformacion.setText("");

			Statement mi_statement = mi_conexion.createStatement();
			ResultSet rs = mi_statement.executeQuery(consulta);

			// metadatos
			ResultSetMetaData rs_BBDD = rs.getMetaData();

			// agrega cada nombre de campo
			for (int i = 1; i < rs_BBDD.getColumnCount(); i++) {
				campos.add(rs_BBDD.getColumnLabel(i));
			}

			// recorrer el resultset de datos
			while (rs.next()) {
				// agrega el value de cada campo segun su nombre
				for (String cada_campo : campos) {
					areaInformacion.append(rs.getString(cada_campo) + " ");
				}

				areaInformacion.append("\n");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// conectar a la bd
	public void conectarBBDD() {
		this.mi_conexion = null;

		try {
			this.mi_conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_curso_sql", "root", "");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// obtener tablas de la BD
	public void getTablas() {
		ResultSet rs = null;

		try {
			DatabaseMetaData datos_BBDD = mi_conexion.getMetaData();

			rs = datos_BBDD.getTables(null, null, null, null);

			while (rs.next()) {
				this.comboTablas.addItem(rs.getString("TABLE_NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
