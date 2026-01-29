package com.yael.sistemagestion.servicelogica;

import java.util.List;

import javax.swing.JOptionPane;

import com.yael.sistemagestion.dao.EmpleadoDAO;
import com.yael.sistemagestion.dao.EmpleadosDAOImp;
import com.yael.sistemagestion.modelos.Empleado;

public class ServiceLogica implements Service{
	
	private EmpleadoDAO empleadoDAO;
	
	public ServiceLogica() {
		this.empleadoDAO = new EmpleadosDAOImp();
	}

	@Override
	public void agregarEmpleado(Empleado empleado) {
		if(empleado==null) {
			 throw new IllegalArgumentException("El empleado no puede ser null");
		}
		empleadoDAO.agregarEmpleado(empleado);
	}

	@Override
	public void actualizarEmpleado(Empleado empleado) {
		if(empleado.getId() <= 0) {
			throw new IllegalArgumentException("ID inválido para actualizar");
		}
		empleadoDAO.actualizarEmpleado(empleado);
	}

	@Override
	public void eliminarEmpleaod(int id) {
		if(id <= 0) {
			throw new IllegalArgumentException("ID inválido, no existente");
		}
		empleadoDAO.eliminarEmpleado(id);
		
	}

	@Override
	public List<Empleado> mostrarEmpleados() {
		if(empleadoDAO.listarEmpleados().isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay empleados para mostrar");
		}
		return empleadoDAO.listarEmpleados();
	}

}
