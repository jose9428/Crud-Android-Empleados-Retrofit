package com.edu.pe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.pe.models.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{

}
