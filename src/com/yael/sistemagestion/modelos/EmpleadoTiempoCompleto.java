package com.yael.sistemagestion.modelos;

public class EmpleadoTiempoCompleto extends Empleado{
	private double bono;
	
	public EmpleadoTiempoCompleto(String nombreCompleto, int edad, String curp, 
			double sueldoBase,double bono) {
		super(nombreCompleto, edad, curp, sueldoBase);
		this.bono = bono;
	}

	@Override
	public double calcularSalario() {
		return getSueldoBase() + this.bono;
	}

	public double getBono() {
		return bono;
	}

	public void setBono(double bono) {
		this.bono = bono;
	}
	
	

}
