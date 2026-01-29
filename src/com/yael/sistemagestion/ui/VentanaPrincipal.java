package com.yael.sistemagestion.ui;

import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 public VentanaPrincipal() {
	        setTitle("Sistema de Gestión de Empleados");
	        setSize(600, 400);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        add(new PanelEmpleado());

	        setVisible(true);
	    }

}
