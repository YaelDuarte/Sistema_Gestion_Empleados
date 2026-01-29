package com.yael.sistemagestion.ui;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import com.yael.sistemagestion.modelos.Empleado;
import java.text.DecimalFormat;

public class EmpleadoTableModel extends AbstractTableModel {

    /**
	 * 
	 */
	private final DecimalFormat df = new DecimalFormat("#,##0.00");
	private static final long serialVersionUID = 1L;
	private List<Empleado> empleados;
    private final String[] columnas = {
            "ID", "Nombre", "Edad", "CURP", "Tipo", "Salario"
    };

    public EmpleadoTableModel(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    @Override
    public int getRowCount() {
        return empleados.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Empleado e = empleados.get(rowIndex);

        switch (columnIndex) {
        case 0: return e.getId(); 
        case 1: return e.getNombreCompleto();
        case 2: return e.getEdad();
        case 3: return e.getCurp();
        case 4: return e.getClass().getSimpleName();
        case 5: return df.format(e.calcularSalario());
        default: return null;
        }
    }

    public Empleado getEmpleadoAt(int fila) {
        return empleados.get(fila);
    }

    public void actualizarDatos(List<Empleado> nuevos) {
        this.empleados = nuevos;
        fireTableDataChanged();
    }
}

