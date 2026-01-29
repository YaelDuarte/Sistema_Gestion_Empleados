package com.yael.sistemagestion.servicelogica;

import java.util.List;

import com.yael.sistemagestion.modelos.Empleado;

public interface Service {
	void agregarEmpleado(Empleado empleado);
	void actualizarEmpleado(Empleado empleado);
	void eliminarEmpleaod(int id);
	List<Empleado> mostrarEmpleados();
}
