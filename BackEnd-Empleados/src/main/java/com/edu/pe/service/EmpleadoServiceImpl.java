package com.edu.pe.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.pe.models.Empleado;
import com.edu.pe.repository.EmpleadoRepository;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService{

	@Autowired
	private EmpleadoRepository repo;
	
	@Transactional
	@Override
	public void Guardar(Empleado e) {
		repo.save(e);
	}

	@Override
	public void Eliminar(Long id) {
		repo.deleteById(id);
	}

	@Override
	public List<Empleado> Listado() {
		return repo.findAll();
	}

	@Override
	public Empleado BuscarPorId(Long id) {
		return repo.findById(id).orElse(null);
	}

}
