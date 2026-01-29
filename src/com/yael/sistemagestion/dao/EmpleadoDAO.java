package com.yael.sistemagestion.dao;

import java.util.List;
import com.yael.sistemagestion.modelos.*;

public interface EmpleadoDAO {
	void agregarEmpleado(Empleado empleado);
	void eliminarEmpleado(int id);
	List<Empleado> listarEmpleados();
	void actualizarEmpleado(Empleado empleado);
}
