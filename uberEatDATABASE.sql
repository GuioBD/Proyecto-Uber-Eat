-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb--
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS uberEat DEFAULT CHARACTER SET utf8 ;
USE uberEat ;

-- -----------------------------------------------------
-- Table Usuario---
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Usuario (
  `idUsuario` VARCHAR(10) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `funcion` VARCHAR(45) NOT NULL,
  `contraseña` VARCHAR(8) NOT NULL,
  `tipoNegocio` VARCHAR(45) NOT NULL,
  `codigoPostal` VARCHAR(5) NOT NULL,
  `Telefono` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;
INSERT INTO Usuario(idUsuario,nombre,funcion,contraseña,tipoNegocio,codigoPostal,telefono) VALUES ("res03531","erick melendez","cocinero","fghG5+f","restaurante","68403","532818792537");
INSERT INTO Usuario(idUsuario,nombre,funcion,contraseña,tipoNegocio,codigoPostal,telefono) VALUES ("pan02532","carlos perez","vendedor","reE45/d","panaderia","68402","532818729353");
INSERT INTO Usuario(idUsuario,nombre,funcion,contraseña,tipoNegocio,codigoPostal,telefono) VALUES ("res04553","jose lopez","administrador","u8iEi/d","restaurante","68504","532818729231");
delete from Usuario where idUsuario = "res04553";
DROP PROCEDURE IF EXISTS usuarios_Consultar_SP;
	DELIMITER //
    CREATE PROCEDURE usuarios_Consultar_SP(IN In_idUsuario VARCHAR(10))
    BEGIN
	select *from Usuario;
	END//
    DELIMITER ;
    CALL usuarios_Consultar_SP('');

DROP PROCEDURE IF EXISTS usuarios_Insertar_SP;
    DELIMITER //
    CREATE PROCEDURE usuarios_Insertar_SP(IN In_idUsuario VARCHAR(10), IN In_nombre VARCHAR(45), IN In_funcion VARCHAR(45), IN In_contraseña VARCHAR(8),IN In_tipoNegocio VARCHAR(45),IN In_codigoPostal VARCHAR(5),IN In_telefono VARCHAR(45))
    BEGIN
		INSERT INTO Usuario(idUsuario,nombre,funcion,contraseña,tipoNegocio,codigoPostal,telefono) VALUES (In_idUsuario, In_nombre, In_funcion,In_contraseña,In_tipoNegocio,In_codigoPostal,In_telefono);
	END//
    DELIMITER ;
    CALL usuarios_Insertar_SP("sup56523","juan","administrador","56jf/6C","super","69456","522567843222");    

-- -----------------------------------------------------
-- Table Negocio
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Negocio (
  `idUsuario` VARCHAR(10) NOT NULL,
  `nombreNegocio` VARCHAR(45) NOT NULL,
  `numeroUbicaciones` VARCHAR(3) NOT NULL,
  PRIMARY KEY (`nombreNegocio`),
  INDEX `idUsuario_idx` (`idUsuario` ASC) VISIBLE,
  CONSTRAINT `idUsuario`
    FOREIGN KEY (`idUsuario`)
    REFERENCES Usuario (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
INSERT INTO Negocio(idUsuario,nombreNegocio,numeroUbicaciones) VALUES ("res03531","lapalapa","5");
INSERT INTO Negocio(idUsuario,nombreNegocio,numeroUbicaciones) VALUES ("pan02532","cafepinole","1");

DROP PROCEDURE IF EXISTS negocios_Consultar_SP;
	DELIMITER //
    CREATE PROCEDURE negocios_Consultar_SP(IN In_nombreNegocio VARCHAR(45))
    BEGIN
	select *from Negocio;
	END//
    DELIMITER ;
    CALL negocios_Consultar_SP('');

DROP PROCEDURE IF EXISTS negocios_Insertar_SP;
    DELIMITER //
    CREATE PROCEDURE negocios_Insertar_SP(IN In_idUsuario VARCHAR(10), IN In_nombreNegocio VARCHAR(45), IN In_numeroUbicaciones VARCHAR(3))
    BEGIN
		INSERT INTO Negocio(idUsuario,nombreNegocio,numeroUbicaciones) VALUES (In_idUsuario, In_nombreNegocio, In_numeroUbicaciones);
	END//
    DELIMITER ;
    CALL negocios_Insertar_SP("sup56523","lores","4");    

-- -----------------------------------------------------
-- Table Ubicacion--
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Ubicacion (
  `idUbicacion` VARCHAR(8) NOT NULL,
  `nombreNegocio` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `ciudad` VARCHAR(45) NOT NULL,
  `piso` VARCHAR(45) NULL,
  PRIMARY KEY (`idUbicacion`),
  INDEX `nombreNegocio_idx` (`nombreNegocio` ASC) VISIBLE,
  CONSTRAINT `nombreNegocio`
    FOREIGN KEY (`nombreNegocio`)
    REFERENCES Negocio(`nombreNegocio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
INSERT INTO Ubicacion(idUbicacion,nombreNegocio,direccion,ciudad,piso) VALUES ("123","cafepinole","calle cisne #2","loma bonita","45");
INSERT INTO Ubicacion(idUbicacion,nombreNegocio,direccion,ciudad,piso) VALUES ("467","lores","calle bugambilias s/n","villa azueta","742");


DROP PROCEDURE IF EXISTS ubicaciones_Consultar_SP;
	DELIMITER //
    CREATE PROCEDURE ubicaciones_Consultar_SP(IN In_idUbicacion VARCHAR(8))
    BEGIN
	select *from Ubicacion;
	END//
    DELIMITER ;
    CALL ubicaciones_Consultar_SP('');
    
DROP PROCEDURE IF EXISTS ubicaciones_Insertar_SP;
    DELIMITER //
    CREATE PROCEDURE ubicaciones_Insertar_SP(IN In_idUbicacion VARCHAR(8), IN In_nombreNegocio VARCHAR(45), IN In_direccion VARCHAR(45),IN In_ciudad VARCHAR(45),IN In_piso VARCHAR(45))
    BEGIN
		INSERT INTO Ubicacion(idUbicacion,nombreNegocio,direccion,ciudad,piso) VALUES (In_idUbicacion,In_nombreNegocio, In_direccion, In_ciudad,In_piso);
	END//
    DELIMITER ;
    CALL ubicaciones_Insertar_SP("453","lapalapa","calle queretaro #22","loma bonita","4");  

DROP PROCEDURE IF EXISTS ubicaciones_Delete_SP;
    DELIMITER //
    CREATE PROCEDURE ubicaciones_Delete_SP(IN In_idUbicacion VARCHAR(8))
    BEGIN
    DELETE FROM  Ubicacion WHERE idUbicacion = In_idUbicacion;
	END//
    DELIMITER ;
	CALL ubicaciones_Delete_SP("453");  
-- -----------------------------------------------------
-- Table Producto--
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Producto (
  `NombreNegocio` VARCHAR(45) NOT NULL,
  `nombreProducto` VARCHAR(45) NOT NULL,
  `costo` VARCHAR(45) NOT NULL,
  `idProducto` VARCHAR(8) NOT NULL,
  INDEX `nombresNegocio_idx` (`NombreNegocio` ASC) VISIBLE,
  PRIMARY KEY (`idProducto`),
  CONSTRAINT `NombresNegocio`
    FOREIGN KEY (`NombreNegocio`)
    REFERENCES Negocio (`nombreNegocio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
INSERT INTO Producto(idProducto,NombreNegocio,nombreProducto,costo) VALUES ("23454321","lapalapa","parillada","350$");

DROP PROCEDURE IF EXISTS productos_Consultar_SP;
	DELIMITER //
    CREATE PROCEDURE productos_Consultar_SP(IN In_idProducto VARCHAR(8))
    BEGIN
	select *from Producto;
	END//
    DELIMITER ;
    CALL productos_Consultar_SP('');

DROP PROCEDURE IF EXISTS productos_Insertar_SP;
    DELIMITER //
    CREATE PROCEDURE productos_Insertar_SP(IN In_idProducto VARCHAR(8), IN In_NombreNegocio VARCHAR(45), IN In_nombreProducto VARCHAR(45),IN In_costo VARCHAR(45))
    BEGIN
		INSERT INTO Producto(idProducto,NombreNegocio,nombreProducto,costo) VALUES (In_idProducto,In_NombreNegocio, In_nombreProducto, In_costo);
	END//
    DELIMITER ;
    CALL productos_Insertar_SP("3456543","lapalapa","coctel de camaron","80$");  

DROP PROCEDURE IF EXISTS productos_Delete_SP;
    DELIMITER //
    CREATE PROCEDURE productos_Delete_SP(IN In_idProducto VARCHAR(8))
    BEGIN
    DELETE FROM  Producto WHERE idProducto = In_idProducto;
	END//
    DELIMITER ;
	CALL productos_Delete_SP("3456543");  
-- -----------------------------------------------------
-- Table `mydb`.`Ventas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Ventas (
  `cantidad` VARCHAR(45) NOT NULL,
  `idProducto` VARCHAR(8) NOT NULL,
  `tarifa` VARCHAR(45) NOT NULL,
  `descuento` VARCHAR(3) NOT NULL,
  INDEX `idProducto_idx` (`idProducto` ASC) VISIBLE,
  CONSTRAINT `idProducto`
    FOREIGN KEY (`idProducto`)
    REFERENCES Producto (`idProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
INSERT INTO Ventas(cantidad,idProducto,tarifa,descuento) VALUES ("3","3456543","tarifa1","30%");

DROP PROCEDURE IF EXISTS ventas_Consultar_SP;
	DELIMITER //
    CREATE PROCEDURE ventas_Consultar_SP()
    BEGIN
	select *from Ventas;
	END//
    DELIMITER ;
    CALL ventas_Consultar_SP();
    
DROP PROCEDURE IF EXISTS ventas_Insertar_SP;
    DELIMITER //
    CREATE PROCEDURE ventas_Insertar_SP(IN In_cantidad VARCHAR(45), IN In_idProducto VARCHAR(8), IN In_tarifa VARCHAR(45),IN In_descuento VARCHAR(3))
    BEGIN
		INSERT INTO Ventas(cantidad,idProducto,tarifa,descuento) VALUES (In_cantidad,In_idProducto, In_tarifa, In_descuento);
	END//
    DELIMITER ;
    CALL ventas_Insertar_SP("4","23454321","tarifa2","15%");  


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
