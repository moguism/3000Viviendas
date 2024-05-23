-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`vecinos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`vecinos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `vivienda_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`gastos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`gastos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ingresos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ingresos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`comunidades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`comunidades` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`bloques`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`bloques` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comunidad_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Bloques_Comunidades_idx` (`comunidad_id` ASC) VISIBLE,
  CONSTRAINT `fk_Bloques_Comunidades`
    FOREIGN KEY (`comunidad_id`)
    REFERENCES `mydb`.`comunidades` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`mensualidades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`mensualidades` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `cuantia` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `fecha_UNIQUE` (`fecha` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`comisiones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`comisiones` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` DATE NOT NULL,
  `updated_at` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tipos_deudas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tipos_deudas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`deudas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`deudas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo_deuda_id` INT NOT NULL,
  `comunidad_id` INT NOT NULL,
  `created_at` DATE NOT NULL,
  `cuantia` DOUBLE NOT NULL,
  `updated_at` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Deudas_TiposDeuda1_idx` (`tipo_deuda_id` ASC) VISIBLE,
  INDEX `fk_Deudas_Comunidades1_idx` (`comunidad_id` ASC) VISIBLE,
  CONSTRAINT `fk_Deudas_TiposDeuda1`
    FOREIGN KEY (`tipo_deuda_id`)
    REFERENCES `mydb`.`tipos_deudas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Deudas_Comunidades1`
    FOREIGN KEY (`comunidad_id`)
    REFERENCES `mydb`.`comunidades` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tipos_locales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tipos_locales` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`locales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`locales` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comunidad_id` INT NOT NULL,
  `tipo_local_id` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Locales_Comunidades1_idx` (`comunidad_id` ASC) VISIBLE,
  INDEX `fk_Locales_TiposLocales1_idx` (`tipo_local_id` ASC) VISIBLE,
  CONSTRAINT `fk_Locales_Comunidades1`
    FOREIGN KEY (`comunidad_id`)
    REFERENCES `mydb`.`comunidades` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Locales_TiposLocales1`
    FOREIGN KEY (`tipo_local_id`)
    REFERENCES `mydb`.`tipos_locales` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`contratantes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`contratantes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tipos_contratos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tipos_contratos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`contratos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`contratos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `contratante_id` INT NOT NULL,
  `tipo_contrato_id` INT NOT NULL,
  `created_at` DATE NOT NULL,
  `comunidad_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Contrato_Contratante1_idx` (`contratante_id` ASC) VISIBLE,
  INDEX `fk_Contrato_TiposContrato1_idx` (`tipo_contrato_id` ASC) VISIBLE,
  INDEX `fk_Contrato_Comunidades1_idx` (`comunidad_id` ASC) VISIBLE,
  CONSTRAINT `fk_Contrato_Contratante1`
    FOREIGN KEY (`contratante_id`)
    REFERENCES `mydb`.`contratantes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Contrato_TiposContrato1`
    FOREIGN KEY (`tipo_contrato_id`)
    REFERENCES `mydb`.`tipos_contratos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Contrato_Comunidades1`
    FOREIGN KEY (`comunidad_id`)
    REFERENCES `mydb`.`comunidades` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`vecinos_mensualidades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`vecinos_mensualidades` (
  `vecino_id` INT NOT NULL,
  `mensualidad_id` INT NOT NULL,
  `fecha_pago` DATE NOT NULL,
  INDEX `fk_vecinos_has_Mensualidades_Mensualidades1_idx` (`mensualidad_id` ASC) VISIBLE,
  INDEX `fk_vecinos_has_Mensualidades_vecinos1_idx` (`vecino_id` ASC) VISIBLE,
  CONSTRAINT `fk_vecinos_has_Mensualidades_vecinos1`
    FOREIGN KEY (`vecino_id`)
    REFERENCES `mydb`.`vecinos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vecinos_has_Mensualidades_Mensualidades1`
    FOREIGN KEY (`mensualidad_id`)
    REFERENCES `mydb`.`mensualidades` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`comunidades_ingresos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`comunidades_ingresos` (
  `comunidad_id` INT NOT NULL,
  `ingreso_id` INT NOT NULL,
  `created_at` DATE NOT NULL,
  `monto` DOUBLE NOT NULL,
  INDEX `fk_Comunidades_has_Ingresos_Ingresos1_idx` (`ingreso_id` ASC) VISIBLE,
  INDEX `fk_Comunidades_has_Ingresos_Comunidades1_idx` (`comunidad_id` ASC) VISIBLE,
  CONSTRAINT `fk_Comunidades_has_Ingresos_Comunidades1`
    FOREIGN KEY (`comunidad_id`)
    REFERENCES `mydb`.`comunidades` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comunidades_has_Ingresos_Ingresos1`
    FOREIGN KEY (`ingreso_id`)
    REFERENCES `mydb`.`ingresos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`comunidades_gastos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`comunidades_gastos` (
  `comunidad_id` INT NOT NULL,
  `gasto_id` INT NOT NULL,
  `created_at` DATE NOT NULL,
  `monto` DOUBLE NOT NULL,
  INDEX `fk_Comunidades_has_gastos_gastos1_idx` (`gasto_id` ASC) VISIBLE,
  INDEX `fk_Comunidades_has_gastos_Comunidades1_idx` (`comunidad_id` ASC) VISIBLE,
  CONSTRAINT `fk_Comunidades_has_gastos_Comunidades1`
    FOREIGN KEY (`comunidad_id`)
    REFERENCES `mydb`.`comunidades` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comunidades_has_gastos_gastos1`
    FOREIGN KEY (`gasto_id`)
    REFERENCES `mydb`.`gastos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tipos_reuniones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tipos_reuniones` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`reuniones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`reuniones` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo_reunion_id` INT NOT NULL,
  `fecha` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Reuniones_TipoReunion1_idx` (`tipo_reunion_id` ASC) VISIBLE,
  CONSTRAINT `fk_Reuniones_TipoReunion1`
    FOREIGN KEY (`tipo_reunion_id`)
    REFERENCES `mydb`.`tipos_reuniones` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`reuniones_vecinos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`reuniones_vecinos` (
  `reunion_id` INT NOT NULL,
  `vecino_id` INT NOT NULL,
  INDEX `fk_Reuniones_has_vecinos_vecinos1_idx` (`vecino_id` ASC) VISIBLE,
  INDEX `fk_Reuniones_has_vecinos_Reuniones1_idx` (`reunion_id` ASC) VISIBLE,
  CONSTRAINT `fk_Reuniones_has_vecinos_Reuniones1`
    FOREIGN KEY (`reunion_id`)
    REFERENCES `mydb`.`reuniones` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reuniones_has_vecinos_vecinos1`
    FOREIGN KEY (`vecino_id`)
    REFERENCES `mydb`.`vecinos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`eventos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`eventos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `NombreEvento_UNIQUE` (`nombre` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`reuniones_eventos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`reuniones_eventos` (
  `reunion_id` INT NOT NULL,
  `evento_id` INT NOT NULL,
  INDEX `fk_Reuniones_has_Eventos_Eventos1_idx` (`evento_id` ASC) VISIBLE,
  INDEX `fk_Reuniones_has_Eventos_Reuniones1_idx` (`reunion_id` ASC) VISIBLE,
  CONSTRAINT `fk_Reuniones_has_Eventos_Reuniones1`
    FOREIGN KEY (`reunion_id`)
    REFERENCES `mydb`.`reuniones` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reuniones_has_Eventos_Eventos1`
    FOREIGN KEY (`evento_id`)
    REFERENCES `mydb`.`eventos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`comisiones_vecinos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`comisiones_vecinos` (
  `comision_id` INT NOT NULL,
  `vecino_id` INT NOT NULL,
  `rol_id` INT NOT NULL,
  `created_at` DATE NOT NULL,
  INDEX `fk_ComisionGestora_has_vecinos_vecinos1_idx` (`vecino_id` ASC) VISIBLE,
  INDEX `fk_ComisionGestora_has_vecinos_ComisionGestora1_idx` (`comision_id` ASC) VISIBLE,
  INDEX `fk_ComisionGestora_has_vecinos_Roles1_idx` (`rol_id` ASC) VISIBLE,
  CONSTRAINT `fk_ComisionGestora_has_vecinos_ComisionGestora1`
    FOREIGN KEY (`comision_id`)
    REFERENCES `mydb`.`comisiones` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ComisionGestora_has_vecinos_vecinos1`
    FOREIGN KEY (`vecino_id`)
    REFERENCES `mydb`.`vecinos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ComisionGestora_has_vecinos_Roles1`
    FOREIGN KEY (`rol_id`)
    REFERENCES `mydb`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`viviendas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`viviendas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `bloque_id` INT NOT NULL,
  `escalera` VARCHAR(45) NULL,
  `planta` VARCHAR(45) NULL,
  `puerta` VARCHAR(45) NULL,
  `letra` VARCHAR(45) NULL,
  `vecino_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Viviendas_Bloques1_idx` (`bloque_id` ASC) VISIBLE,
  INDEX `fk_viviendas_vecinos1_idx` (`vecino_id` ASC) VISIBLE,
  CONSTRAINT `fk_Viviendas_Bloques1`
    FOREIGN KEY (`bloque_id`)
    REFERENCES `mydb`.`bloques` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_viviendas_vecinos1`
    FOREIGN KEY (`vecino_id`)
    REFERENCES `mydb`.`vecinos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `mydb`.`comunidades`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`comunidades` (`id`, `nombre`, `direccion`) VALUES (1, 'Prueba', 'Prueba');

COMMIT;

