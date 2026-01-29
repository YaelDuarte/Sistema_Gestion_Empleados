package com.yael.sistemagestion.ui;

import javax.swing.*;
import java.awt.*;

import com.yael.sistemagestion.modelos.*;
import com.yael.sistemagestion.servicelogica.*;

public class PanelEmpleado extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre, txtEdad, txtCurp;
    private JTextField txtSueldoBase, txtBono;
    private JTextField txtHoras, txtPagoHora;

    private JComboBox<String> comboTipo;
    private JButton btnGuardar;
    private JButton btnEliminar;

    private JPanel panelTiempoCompleto, panelPorHoras;
    
    private JTable tabla;
    private EmpleadoTableModel tableModel;


    private Service empleadoService;

    public PanelEmpleado() {
        empleadoService = new ServiceLogica();
        setLayout(new BorderLayout(10, 10));

        add(panelFormulario(), BorderLayout.NORTH);
        add(panelDinamico(), BorderLayout.CENTER);
        add(panelTabla(), BorderLayout.EAST);
        add(panelBoton(), BorderLayout.SOUTH);

        actualizarPanel();
    }


    // ---------------- FORMULARIO BASE ----------------
    private JPanel panelFormulario() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));

        txtNombre = new JTextField();
        txtEdad = new JTextField();
        txtCurp = new JTextField();

        comboTipo = new JComboBox<>(new String[]{"TIEMPO_COMPLETO", "POR_HORAS"});
        comboTipo.addActionListener(e -> actualizarPanel());

        panel.add(new JLabel("Nombre completo:"));
        panel.add(txtNombre);

        panel.add(new JLabel("Edad:"));
        panel.add(txtEdad);

        panel.add(new JLabel("CURP:"));
        panel.add(txtCurp);

        panel.add(new JLabel("Tipo de empleado:"));
        panel.add(comboTipo);

        return panel;
    }

    // ---------------- PANELES DINÁMICOS ----------------
    private JPanel panelDinamico() {
        JPanel contenedor = new JPanel(new CardLayout());

        panelTiempoCompleto = crearPanelTiempoCompleto();
        panelPorHoras = crearPanelPorHoras();

        contenedor.add(panelTiempoCompleto, "TIEMPO_COMPLETO");
        contenedor.add(panelPorHoras, "POR_HORAS");

        return contenedor;
    }

    private JPanel crearPanelTiempoCompleto() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        txtSueldoBase = new JTextField(8); // ancho pequeño
        txtBono = new JTextField(8);

        panel.add(new JLabel("Sueldo base:"));
        panel.add(txtSueldoBase);

        panel.add(new JLabel("Bono:"));
        panel.add(txtBono);

        return panel;
    }

    private JPanel crearPanelPorHoras() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        txtHoras = new JTextField(6);
        txtPagoHora = new JTextField(6);

        panel.add(new JLabel("Horas trabajadas:"));
        panel.add(txtHoras);

        panel.add(new JLabel("Pago por hora:"));
        panel.add(txtPagoHora);

        return panel;
    }


    // ---------------- BOTÓN ----------------
    private JPanel panelBoton() {
        JPanel panel = new JPanel();
        btnGuardar = new JButton("Guardar Empleado");
        btnEliminar = new JButton("Eliminar Empleado");
        
        btnGuardar.addActionListener(e -> guardarEmpleado());
        btnEliminar.addActionListener(e -> eliminarEmpleado());
        panel.add(btnGuardar);
        panel.add(btnEliminar);
        
        return panel;
    }
    
    // ---- TABLA PARA LOS EMPLEADOS--------------
    private JScrollPane panelTabla() {
        tableModel = new EmpleadoTableModel(
                empleadoService.mostrarEmpleados()
        );

        tabla = new JTable(tableModel);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        ajustarColumnas();
        return new JScrollPane(tabla);
    }
    
    private void ajustarColumnas() {
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);   // ID
        tabla.getColumnModel().getColumn(1).setPreferredWidth(200);  // Nombre
        tabla.getColumnModel().getColumn(2).setPreferredWidth(50);   // Edad
        tabla.getColumnModel().getColumn(3).setPreferredWidth(200);  // CURP
        tabla.getColumnModel().getColumn(4).setPreferredWidth(200);  // Tipo
        tabla.getColumnModel().getColumn(5).setPreferredWidth(100);   // Salario
    }
     
    
    // ------ELIMINAR A LOS EMPLEADOS---------
    private void eliminarEmpleado() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "⚠️ Selecciona un empleado de la tabla",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Empleado empleado = tableModel.getEmpleadoAt(fila);

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Eliminar al empleado:\n" + empleado.getNombreCompleto() + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            empleadoService.eliminarEmpleaod(empleado.getId());

            tableModel.actualizarDatos(
                    empleadoService.mostrarEmpleados()
            );
            ajustarColumnas();

            JOptionPane.showMessageDialog(this, "Empleado eliminado");
        }
    }



    // ---------------- LÓGICA ----------------
    private void actualizarPanel() {
        CardLayout cl = (CardLayout) ((JPanel) getComponent(1)).getLayout();
        cl.show((JPanel) getComponent(1), comboTipo.getSelectedItem().toString());
    }

    private void guardarEmpleado() {
        try {
            String nombre = txtNombre.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            String curp = txtCurp.getText();

            Empleado empleado;

            if (comboTipo.getSelectedItem().equals("TIEMPO_COMPLETO")) {
                double sueldoBase = Double.parseDouble(txtSueldoBase.getText());
                double bono = Double.parseDouble(txtBono.getText());

                empleado = new EmpleadoTiempoCompleto(
                        nombre, edad, curp, sueldoBase, bono
                );
            } else {
                int horas = Integer.parseInt(txtHoras.getText());
                double pagoHora = Double.parseDouble(txtPagoHora.getText());

                empleado = new EmpleadoPorHoras(
                        nombre, edad, curp, horas, pagoHora
                );
                ajustarColumnas();
            }

            empleadoService.agregarEmpleado(empleado);

            JOptionPane.showMessageDialog(this, "Empleado guardado correctamente");
            limpiarCampos();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al guardar: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        tableModel.actualizarDatos(
        	    empleadoService.mostrarEmpleados()
        	);

    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtEdad.setText("");
        txtCurp.setText("");
        txtSueldoBase.setText("");
        txtBono.setText("");
        txtHoras.setText("");
        txtPagoHora.setText("");
    }
}


