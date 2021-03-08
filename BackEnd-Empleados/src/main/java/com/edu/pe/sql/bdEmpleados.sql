DROP DATABASE IF EXISTS bdempleado;

CREATE DATABASE bdempleado;

USE bdempleado;

CREATE TABLE Empleado(
	id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(50),
    ape_paterno VARCHAR(50),
    ape_materno VARCHAR(50),
    genero CHAR(1),
    fecha_nacimiento DATE,
    fecha_registro DATETIME,
    correo VARCHAR(60),
    sueldo DECIMAL(8,2)
);

INSERT INTO EMPLEADO VALUES(NULL , 'Juan Alberto' , 'Caceres' , 'Sanchez' ,'M' , '1998-12-15' , NOW(),'juan-32@gmail.com' , 3500);
INSERT INTO EMPLEADO VALUES(NULL , 'Xiomara' , 'Guzman' , 'Salvatierra' ,'F' , '2001-02-11' , NOW(),'xiomi-guzman@gmail.com' , 1950);
INSERT INTO EMPLEADO VALUES(NULL , 'Rodrigo' , 'Paucar' , 'Guispe' ,'M' , '2000-01-14' , NOW(),'rodrigo-54@gmail.com' , 2875);

SELECT * FROM EMPLEADO;
