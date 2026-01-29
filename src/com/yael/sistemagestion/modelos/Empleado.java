package com.yael.sistemagestion.modelos;

public abstract class Empleado {
	protected String nombreCompleto;
	protected int edad;
	protected String curp;
	protected double sueldoBase;
	protected int id;
	
	
	public Empleado(String nombreCompleto, int edad, String curp, double sueldoBase) {
		this.nombreCompleto = nombreCompleto;
		this.edad = edad;
		this.curp = curp;
		this.sueldoBase = sueldoBase;
	}
	
	public abstract double calcularSalario();

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public double getSueldoBase() {
		return sueldoBase;
	}

	public void setSueldoBase(double sueldoBase) {
		this.sueldoBase = sueldoBase;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
}
