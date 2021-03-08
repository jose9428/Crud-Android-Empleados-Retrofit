package com.edu.pe.models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Empleado")
public class Empleado implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_empleado;
	
	private String nombres;
	
	private String ape_paterno;
	
	private String ape_materno;
	
	private String genero;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
    private Date fecha_nacimiento;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") // HH:mm:ss
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fecha_registro;
	
	private String correo;
	
	private double sueldo;

	public Long getId_empleado() {
		return id_empleado;
	}

	public void setId_empleado(Long id_empleado) {
		this.id_empleado = id_empleado;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApe_paterno() {
		return ape_paterno;
	}

	public void setApe_paterno(String ape_paterno) {
		this.ape_paterno = ape_paterno;
	}

	public String getApe_materno() {
		return ape_materno;
	}

	public void setApe_materno(String ape_materno) {
		this.ape_materno = ape_materno;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public Calendar getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Calendar fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
