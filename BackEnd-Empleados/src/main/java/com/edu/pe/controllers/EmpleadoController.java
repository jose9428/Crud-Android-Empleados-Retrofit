package com.edu.pe.controllers;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.pe.models.Empleado;
import com.edu.pe.service.IEmpleadoService;


@RestController
@RequestMapping("/api/empleados")
@CrossOrigin
public class EmpleadoController {
	
	@Autowired
	private IEmpleadoService service;

	@GetMapping()
	public ResponseEntity<List<Empleado>> getListEmpleados(){
		List<Empleado> lista = service.Listado();
		
		return new ResponseEntity<List<Empleado>>(lista ,HttpStatus.OK);
	}
	
	 @GetMapping(path = {"/{id}"})
	public ResponseEntity<Empleado> getEmpleado(@PathVariable("id") Long id){
		Empleado e = service.BuscarPorId(id);
		if(e !=null) {
			System.out.println(e.toString());
			return new ResponseEntity<Empleado>(e ,HttpStatus.OK);
		}else {
			return ResponseEntity.status(HttpStatus.FOUND).build();
		}
	}
	
	@PostMapping()
	public ResponseEntity GuardarEmpleado(@RequestBody Empleado e) {
		try {
			e.setFecha_registro(Calendar.getInstance());
			service.Guardar(e);
			return ResponseEntity.status(HttpStatus.OK).build();	
		}catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity Eliminar(@PathVariable("id") Long id){
		Empleado e = service.BuscarPorId(id);
		
		if(e !=null) {
			service.Eliminar(id);
			
			return ResponseEntity.ok(true);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	
	@PutMapping(path = {"/{id}"})
	public ResponseEntity EditarEmpleado(@RequestBody Empleado e , @PathVariable("id") Long id) {
		try {
			e.setId_empleado(id);
			service.Guardar(e);
			return ResponseEntity.status(HttpStatus.OK).build();	
		}catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
