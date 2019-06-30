package aplicacion_final;

import java.awt.*;
import java.sql.*;

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

		add(comboTablas, BorderLayout.NORTH);

		// conectar a la BD y cargarlo en el combo box
		conectarBBDD();
		getTablas();

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
