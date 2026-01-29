package com.yael.sistemagestion.modelos;

public class EmpleadoPorHoras extends Empleado{
	private int horasTrabajadas;
	private double sueldoPorHora;

	public EmpleadoPorHoras(String nombreCompleto, int edad, String curp, 
			int horasTrabajadas,double sueldoPorHora) {
		super(nombreCompleto, edad, curp, 0);
		this.horasTrabajadas = horasTrabajadas;
		this.sueldoPorHora = sueldoPorHora;
	}

	@Override
	public double calcularSalario() {
		return this.horasTrabajadas + this.sueldoPorHora;
	}

	public int getHorasTrabajadas() {
		return horasTrabajadas;
	}

	public double getSueldoPorHora() {
		return sueldoPorHora;
	}

	public void setHorasTrabajadas(int horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;
	}

	public void setSueldoPorHora(double sueldoPorHora) {
		this.sueldoPorHora = sueldoPorHora;
	}
	
	

}
