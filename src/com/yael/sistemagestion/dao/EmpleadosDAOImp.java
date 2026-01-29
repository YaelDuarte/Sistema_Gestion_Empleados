package com.yael.sistemagestion.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.yael.sistemagestion.config.ConexionBD;
import com.yael.sistemagestion.modelos.*;

public class EmpleadosDAOImp implements EmpleadoDAO{

	@Override
	public void agregarEmpleado(Empleado empleado) {
		String sqlQ = "INSERT INTO empleados"
				+ "(nombre_completo, edad, curp, sueldo_base, tipo_empleado, bono, horas_trabajadas, pago_por_hora)"
				+"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try(Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sqlQ)){
			
			ps.setString(1, empleado.getNombreCompleto());
			ps.setInt(2, empleado.getEdad());
			ps.setString(3, empleado.getCurp());
			ps.setDouble(4, empleado.getSueldoBase());
			
			if(empleado instanceof EmpleadoTiempoCompleto) {
				EmpleadoTiempoCompleto etc = (EmpleadoTiempoCompleto) empleado;
				ps.setString(5,"TIEMPO_COMPLETO");
				ps.setDouble(6, etc.getBono());
				ps.setNull(7, Types.INTEGER);
	            ps.setNull(8, Types.DOUBLE);
			}else {
				EmpleadoPorHoras eph = (EmpleadoPorHoras) empleado;
				ps.setString(5,"POR_HORAS");
				ps.setNull(6, Types.DOUBLE);
				ps.setInt(7, eph.getHorasTrabajadas());
	            ps.setDouble(8, eph.getSueldoPorHora());
			}
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void eliminarEmpleado(int id) {
		String sqlQ = "DELETE FROM empleados WHERE id = ?";
		
		try (Connection conn = ConexionBD.getConnection();
		         PreparedStatement ps = conn.prepareStatement(sqlQ)){
			ps.setInt(1, id);
	        ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Empleado> listarEmpleados() {
		List<Empleado> empleados = new ArrayList<Empleado>();
		String sqlQ = "SELECT * FROM empleados";
		
		try (Connection conn = ConexionBD.getConnection();
		         PreparedStatement ps = conn.prepareStatement(sqlQ);
		         ResultSet rs = ps.executeQuery()) {
			
			while(rs.next()) {
				String tipo = rs.getString("tipo_empleado");
				
				if("TIEMPO_COMPLETO".equals(tipo)) {
					EmpleadoTiempoCompleto etc = new EmpleadoTiempoCompleto(
						    rs.getString("nombre_completo"),
						    rs.getInt("edad"),
						    rs.getString("curp"),
						    rs.getDouble("sueldo_base"),
						    rs.getDouble("bono")
						);
						etc.setId(rs.getInt("id"));
						empleados.add(etc);
					
				}else {
					EmpleadoPorHoras eph = new EmpleadoPorHoras(
						    rs.getString("nombre_completo"),
						    rs.getInt("edad"),
						    rs.getString("curp"),
						    rs.getInt("horas_trabajadas"),
						    rs.getDouble("pago_por_hora")
						);
						eph.setId(rs.getInt("id"));
						empleados.add(eph);
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return empleados;
	}

	@Override
	public void actualizarEmpleado(Empleado empleado) {
	    String sql = "UPDATE empleados SET "
	            + "nombre_completo = ?, edad = ?, curp = ?, sueldo_base = ?, "
	            + "tipo_empleado = ?, bono = ?, horas_trabajadas = ?, pago_por_hora = ? "
	            + "WHERE id = ?";

	    try (Connection conn = ConexionBD.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, empleado.getNombreCompleto());
	        ps.setInt(2, empleado.getEdad());
	        ps.setString(3, empleado.getCurp());
	        ps.setDouble(4, empleado.getSueldoBase());

	        if (empleado instanceof EmpleadoTiempoCompleto) {
	            EmpleadoTiempoCompleto etc = (EmpleadoTiempoCompleto) empleado;
	            ps.setString(5, "TIEMPO_COMPLETO");
	            ps.setDouble(6, etc.getBono());
	            ps.setNull(7, Types.INTEGER);
	            ps.setNull(8, Types.DOUBLE);
	        } else {
	            EmpleadoPorHoras eph = (EmpleadoPorHoras) empleado;
	            ps.setString(5, "POR_HORAS");
	            ps.setNull(6, Types.DOUBLE);
	            ps.setInt(7, eph.getHorasTrabajadas());
	            ps.setDouble(8, eph.getSueldoPorHora());
	        }

	        ps.setInt(9, empleado.getId());

	        ps.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}


