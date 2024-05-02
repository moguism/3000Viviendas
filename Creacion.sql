-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Comunidades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Comunidades` (
  `idComunidades` INT NOT NULL,
  `nombreComunidad` VARCHAR(45) NOT NULL,
  `direccionComunidad` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idComunidades`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Bloques`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Bloques` (
  `idBloques` INT NOT NULL,
  `Comunidades_idComunidades` INT NOT NULL,
  PRIMARY KEY (`idBloques`),
  INDEX `fk_Bloques_Comunidades_idx` (`Comunidades_idComunidades` ASC) VISIBLE,
  CONSTRAINT `fk_Bloques_Comunidades`
    FOREIGN KEY (`Comunidades_idComunidades`)
    REFERENCES `mydb`.`Comunidades` (`idComunidades`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Viviendas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Viviendas` (
  `idViviendas` INT NOT NULL,
  `Bloques_idBloques` INT NOT NULL,
  `Escalera` VARCHAR(45) NULL,
  `Planta` VARCHAR(45) NULL,
  `Puerta` VARCHAR(45) NULL,
  `Letra` VARCHAR(45) NULL,
  PRIMARY KEY (`idViviendas`),
  INDEX `fk_Viviendas_Bloques1_idx` (`Bloques_idBloques` ASC) VISIBLE,
  CONSTRAINT `fk_Viviendas_Bloques1`
    FOREIGN KEY (`Bloques_idBloques`)
    REFERENCES `mydb`.`Bloques` (`idBloques`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`vecinos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`vecinos` (
  `idvecinos` INT NOT NULL,
  `nombreVecino` VARCHAR(45) NULL,
  `Viviendas_idViviendas` INT NOT NULL,
  PRIMARY KEY (`idvecinos`),
  INDEX `fk_vecinos_Viviendas1_idx` (`Viviendas_idViviendas` ASC) VISIBLE,
  CONSTRAINT `fk_vecinos_Viviendas1`
    FOREIGN KEY (`Viviendas_idViviendas`)
    REFERENCES `mydb`.`Viviendas` (`idViviendas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`gastos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`gastos` (
  `idgastos` INT NOT NULL,
  `NombreGasto` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idgastos`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Ingresos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Ingresos` (
  `idingresos` INT NOT NULL,
  `NombreIngreso` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idingresos`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Mensualidades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Mensualidades` (
  `idMensualidades` INT NOT NULL,
  `Fecha` DATE NOT NULL,
  PRIMARY KEY (`idMensualidades`),
  UNIQUE INDEX `Fecha_UNIQUE` (`Fecha` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ComisionGestora`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ComisionGestora` (
  `idComisionGestora` INT NOT NULL,
  `Created_at` DATE NOT NULL,
  `Updated_at` DATE NOT NULL,
  PRIMARY KEY (`idComisionGestora`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Roles` (
  `idRoles` INT NOT NULL,
  `NombreRol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idRoles`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TiposDeuda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TiposDeuda` (
  `idTiposDeuda` INT NOT NULL,
  `NombreDeuda` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTiposDeuda`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Deudas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Deudas` (
  `idDeudas` INT NOT NULL,
  `TiposDeuda_idTiposDeuda` INT NOT NULL,
  `Comunidades_idComunidades` INT NOT NULL,
  `Created_at` DATE NOT NULL,
  PRIMARY KEY (`idDeudas`),
  INDEX `fk_Deudas_TiposDeuda1_idx` (`TiposDeuda_idTiposDeuda` ASC) VISIBLE,
  INDEX `fk_Deudas_Comunidades1_idx` (`Comunidades_idComunidades` ASC) VISIBLE,
  CONSTRAINT `fk_Deudas_TiposDeuda1`
    FOREIGN KEY (`TiposDeuda_idTiposDeuda`)
    REFERENCES `mydb`.`TiposDeuda` (`idTiposDeuda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Deudas_Comunidades1`
    FOREIGN KEY (`Comunidades_idComunidades`)
    REFERENCES `mydb`.`Comunidades` (`idComunidades`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TiposLocales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TiposLocales` (
  `idTiposLocales` INT NOT NULL,
  `NombreTipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTiposLocales`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Locales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Locales` (
  `idLocales` INT NOT NULL,
  `Comunidades_idComunidades` INT NOT NULL,
  `TiposLocales_idTiposLocales` INT NOT NULL,
  `NombreLocal` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idLocales`),
  INDEX `fk_Locales_Comunidades1_idx` (`Comunidades_idComunidades` ASC) VISIBLE,
  INDEX `fk_Locales_TiposLocales1_idx` (`TiposLocales_idTiposLocales` ASC) VISIBLE,
  CONSTRAINT `fk_Locales_Comunidades1`
    FOREIGN KEY (`Comunidades_idComunidades`)
    REFERENCES `mydb`.`Comunidades` (`idComunidades`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Locales_TiposLocales1`
    FOREIGN KEY (`TiposLocales_idTiposLocales`)
    REFERENCES `mydb`.`TiposLocales` (`idTiposLocales`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Contratante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Contratante` (
  `idContratante` INT NOT NULL,
  `NombreContratante` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idContratante`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TiposContrato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TiposContrato` (
  `Comunidades_idComunidades` INT NOT NULL,
  `idTiposContrato` INT NOT NULL,
  INDEX `fk_TiposContrato_Comunidades1_idx` (`Comunidades_idComunidades` ASC) VISIBLE,
  PRIMARY KEY (`idTiposContrato`),
  CONSTRAINT `fk_TiposContrato_Comunidades1`
    FOREIGN KEY (`Comunidades_idComunidades`)
    REFERENCES `mydb`.`Comunidades` (`idComunidades`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Contrato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Contrato` (
  `idContrato` INT NOT NULL,
  `Contratante_idContratante` INT NOT NULL,
  `TiposContrato_idTiposContrato` INT NOT NULL,
  `Created_at` DATE NOT NULL,
  PRIMARY KEY (`idContrato`),
  INDEX `fk_Contrato_Contratante1_idx` (`Contratante_idContratante` ASC) VISIBLE,
  INDEX `fk_Contrato_TiposContrato1_idx` (`TiposContrato_idTiposContrato` ASC) VISIBLE,
  CONSTRAINT `fk_Contrato_Contratante1`
    FOREIGN KEY (`Contratante_idContratante`)
    REFERENCES `mydb`.`Contratante` (`idContratante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Contrato_TiposContrato1`
    FOREIGN KEY (`TiposContrato_idTiposContrato`)
    REFERENCES `mydb`.`TiposContrato` (`idTiposContrato`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`vecinos_has_Mensualidades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`vecinos_has_Mensualidades` (
  `vecinos_idvecinos` INT NOT NULL,
  `Mensualidades_idMensualidades` INT NOT NULL,
  `FechaPago` DATE NULL,
  INDEX `fk_vecinos_has_Mensualidades_Mensualidades1_idx` (`Mensualidades_idMensualidades` ASC) VISIBLE,
  INDEX `fk_vecinos_has_Mensualidades_vecinos1_idx` (`vecinos_idvecinos` ASC) VISIBLE,
  CONSTRAINT `fk_vecinos_has_Mensualidades_vecinos1`
    FOREIGN KEY (`vecinos_idvecinos`)
    REFERENCES `mydb`.`vecinos` (`idvecinos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vecinos_has_Mensualidades_Mensualidades1`
    FOREIGN KEY (`Mensualidades_idMensualidades`)
    REFERENCES `mydb`.`Mensualidades` (`idMensualidades`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Comunidades_has_Ingresos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Comunidades_has_Ingresos` (
  `Comunidades_idComunidades` INT NOT NULL,
  `Ingresos_idingresos` INT NOT NULL,
  `Created_at` DATE NOT NULL,
  INDEX `fk_Comunidades_has_Ingresos_Ingresos1_idx` (`Ingresos_idingresos` ASC) VISIBLE,
  INDEX `fk_Comunidades_has_Ingresos_Comunidades1_idx` (`Comunidades_idComunidades` ASC) VISIBLE,
  CONSTRAINT `fk_Comunidades_has_Ingresos_Comunidades1`
    FOREIGN KEY (`Comunidades_idComunidades`)
    REFERENCES `mydb`.`Comunidades` (`idComunidades`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comunidades_has_Ingresos_Ingresos1`
    FOREIGN KEY (`Ingresos_idingresos`)
    REFERENCES `mydb`.`Ingresos` (`idingresos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Comunidades_has_gastos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Comunidades_has_gastos` (
  `Comunidades_idComunidades` INT NOT NULL,
  `gastos_idgastos` INT NOT NULL,
  `Created_at` DATE NOT NULL,
  INDEX `fk_Comunidades_has_gastos_gastos1_idx` (`gastos_idgastos` ASC) VISIBLE,
  INDEX `fk_Comunidades_has_gastos_Comunidades1_idx` (`Comunidades_idComunidades` ASC) VISIBLE,
  CONSTRAINT `fk_Comunidades_has_gastos_Comunidades1`
    FOREIGN KEY (`Comunidades_idComunidades`)
    REFERENCES `mydb`.`Comunidades` (`idComunidades`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comunidades_has_gastos_gastos1`
    FOREIGN KEY (`gastos_idgastos`)
    REFERENCES `mydb`.`gastos` (`idgastos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TipoReunion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TipoReunion` (
  `idTipoReunion` INT NOT NULL,
  `NombreTipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTipoReunion`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Reuniones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Reuniones` (
  `idReuniones` INT NOT NULL AUTO_INCREMENT,
  `TipoReunion_idTipoReunion` INT NOT NULL,
  `Fecha` DATE NOT NULL,
  PRIMARY KEY (`idReuniones`),
  INDEX `fk_Reuniones_TipoReunion1_idx` (`TipoReunion_idTipoReunion` ASC) VISIBLE,
  CONSTRAINT `fk_Reuniones_TipoReunion1`
    FOREIGN KEY (`TipoReunion_idTipoReunion`)
    REFERENCES `mydb`.`TipoReunion` (`idTipoReunion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Reuniones_has_vecinos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Reuniones_has_vecinos` (
  `Reuniones_idReuniones` INT NOT NULL,
  `vecinos_idvecinos` INT NOT NULL,
  INDEX `fk_Reuniones_has_vecinos_vecinos1_idx` (`vecinos_idvecinos` ASC) VISIBLE,
  INDEX `fk_Reuniones_has_vecinos_Reuniones1_idx` (`Reuniones_idReuniones` ASC) VISIBLE,
  CONSTRAINT `fk_Reuniones_has_vecinos_Reuniones1`
    FOREIGN KEY (`Reuniones_idReuniones`)
    REFERENCES `mydb`.`Reuniones` (`idReuniones`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reuniones_has_vecinos_vecinos1`
    FOREIGN KEY (`vecinos_idvecinos`)
    REFERENCES `mydb`.`vecinos` (`idvecinos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Eventos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Eventos` (
  `idEventos` INT NOT NULL,
  `NombreEvento` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idEventos`),
  UNIQUE INDEX `NombreEvento_UNIQUE` (`NombreEvento` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Reuniones_has_Eventos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Reuniones_has_Eventos` (
  `Reuniones_idReuniones` INT NOT NULL,
  `Eventos_idEventos` INT NOT NULL,
  INDEX `fk_Reuniones_has_Eventos_Eventos1_idx` (`Eventos_idEventos` ASC) VISIBLE,
  INDEX `fk_Reuniones_has_Eventos_Reuniones1_idx` (`Reuniones_idReuniones` ASC) VISIBLE,
  CONSTRAINT `fk_Reuniones_has_Eventos_Reuniones1`
    FOREIGN KEY (`Reuniones_idReuniones`)
    REFERENCES `mydb`.`Reuniones` (`idReuniones`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reuniones_has_Eventos_Eventos1`
    FOREIGN KEY (`Eventos_idEventos`)
    REFERENCES `mydb`.`Eventos` (`idEventos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ComisionGestora_has_vecinos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ComisionGestora_has_vecinos` (
  `ComisionGestora_idComisionGestora` INT NOT NULL,
  `vecinos_idvecinos` INT NOT NULL,
  `Roles_idRoles` INT NOT NULL,
  `Created_at` DATE NOT NULL,
  INDEX `fk_ComisionGestora_has_vecinos_vecinos1_idx` (`vecinos_idvecinos` ASC) VISIBLE,
  INDEX `fk_ComisionGestora_has_vecinos_ComisionGestora1_idx` (`ComisionGestora_idComisionGestora` ASC) VISIBLE,
  INDEX `fk_ComisionGestora_has_vecinos_Roles1_idx` (`Roles_idRoles` ASC) VISIBLE,
  CONSTRAINT `fk_ComisionGestora_has_vecinos_ComisionGestora1`
    FOREIGN KEY (`ComisionGestora_idComisionGestora`)
    REFERENCES `mydb`.`ComisionGestora` (`idComisionGestora`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ComisionGestora_has_vecinos_vecinos1`
    FOREIGN KEY (`vecinos_idvecinos`)
    REFERENCES `mydb`.`vecinos` (`idvecinos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ComisionGestora_has_vecinos_Roles1`
    FOREIGN KEY (`Roles_idRoles`)
    REFERENCES `mydb`.`Roles` (`idRoles`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
