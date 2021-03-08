package com.edu.pe.models;

public class Empleado {
    private int id_empleado;
    private String nombres;
    private String ape_paterno;
    private String ape_materno;
    private String genero;
    private String fecha_nacimiento;
    private String fecha_registro;
    private String correo;
    private double sueldo;

    @Override
    public String toString() {
        return "Empleado{" +
                "id_empleado=" + id_empleado +
                ", nombres='" + nombres + '\'' +
                ", ape_paterno='" + ape_paterno + '\'' +
                ", ape_materno='" + ape_materno + '\'' +
                ", genero='" + genero + '\'' +
                ", fecha_nacimiento='" + fecha_nacimiento + '\'' +
                ", fecha_registro='" + fecha_registro + '\'' +
                ", correo='" + correo + '\'' +
                ", sueldo=" + sueldo +
                '}';
    }

    public String NomGenero(){
        return genero.equalsIgnoreCase("M" )?"Masculino":"Femenino";
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
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

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
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
}
