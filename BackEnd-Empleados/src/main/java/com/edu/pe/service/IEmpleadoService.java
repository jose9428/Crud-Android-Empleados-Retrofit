package com.edu.pe.service;

import java.util.List;

import com.edu.pe.models.Empleado;

public interface IEmpleadoService {

	public void Guardar(Empleado e);
	
	public void Eliminar(Long id);
	
	public List<Empleado> Listado();
	
	public Empleado BuscarPorId(Long id);
}
