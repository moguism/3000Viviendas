-- -----------------------------------------------------
-- Data for table `mydb`.`comunidades`
-- -----------------------------------------------------
INSERT INTO `mydb`.`comunidades` (`nombre`, `direccion`) VALUES 
('Comunidad A', 'Direccion A'),
('Comunidad B', 'Direccion B');

-- -----------------------------------------------------
-- Data for table `mydb`.`bloques`
-- -----------------------------------------------------
INSERT INTO `mydb`.`bloques` (`comunidad_id`) VALUES 
(1),
(1),
(2);

-- -----------------------------------------------------
-- Data for table `mydb`.`vecinos`
-- -----------------------------------------------------
INSERT INTO `mydb`.`vecinos` (`nombre`, `vivienda_id`) VALUES 
('Vecino 1', 1),
('Vecino 2', 2),
('Vecino 3', 3);

-- -----------------------------------------------------
-- Data for table `mydb`.`gastos`
-- -----------------------------------------------------
INSERT INTO `mydb`.`gastos` (`nombre`) VALUES 
('Gasto 1'),
('Gasto 2');

-- -----------------------------------------------------
-- Data for table `mydb`.`ingresos`
-- -----------------------------------------------------
INSERT INTO `mydb`.`ingresos` (`nombre`) VALUES 
('Ingreso 1'),
('Ingreso 2');

-- -----------------------------------------------------
-- Data for table `mydb`.`mensualidades`
-- -----------------------------------------------------
INSERT INTO `mydb`.`mensualidades` (`fecha`, `cuantia`) VALUES 
('2024-01-01', 100.00),
('2024-02-01', 150.00);

-- -----------------------------------------------------
-- Data for table `mydb`.`comisiones`
-- -----------------------------------------------------
INSERT INTO `mydb`.`comisiones` (`created_at`, `updated_at`) VALUES 
('2024-01-01', '2024-01-01'),
('2024-02-01', '2024-02-01');

-- -----------------------------------------------------
-- Data for table `mydb`.`roles`
-- -----------------------------------------------------
INSERT INTO `mydb`.`roles` (`nombre`) VALUES 
('Rol 1'),
('Rol 2');

-- -----------------------------------------------------
-- Data for table `mydb`.`tipos_deudas`
-- -----------------------------------------------------
INSERT INTO `mydb`.`tipos_deudas` (`nombre`) VALUES 
('Tipo Deuda 1'),
('Tipo Deuda 2');

-- -----------------------------------------------------
-- Data for table `mydb`.`deudas`
-- -----------------------------------------------------
INSERT INTO `mydb`.`deudas` (`tipo_deuda_id`, `comunidad_id`, `created_at`, `cuantia`, `updated_at`) VALUES 
(1, 1, '2024-01-01', 200.00, '2024-01-01'),
(2, 2, '2024-02-01', 300.00, '2024-02-01');

-- -----------------------------------------------------
-- Data for table `mydb`.`tipos_locales`
-- -----------------------------------------------------
INSERT INTO `mydb`.`tipos_locales` (`nombre`) VALUES 
('Tipo Local 1'),
('Tipo Local 2');

-- -----------------------------------------------------
-- Data for table `mydb`.`locales`
-- -----------------------------------------------------
INSERT INTO `mydb`.`locales` (`comunidad_id`, `tipo_local_id`, `nombre`) VALUES 
(1, 1, 'Local 1'),
(2, 2, 'Local 2');

-- -----------------------------------------------------
-- Data for table `mydb`.`contratantes`
-- -----------------------------------------------------
INSERT INTO `mydb`.`contratantes` (`nombre`) VALUES 
('Contratante 1'),
('Contratante 2');

-- -----------------------------------------------------
-- Data for table `mydb`.`tipos_contratos`
-- -----------------------------------------------------
INSERT INTO `mydb`.`tipos_contratos` (`nombre`) VALUES 
('Tipo Contrato 1'),
('Tipo Contrato 2');

-- -----------------------------------------------------
-- Data for table `mydb`.`contratos`
-- -----------------------------------------------------
INSERT INTO `mydb`.`contratos` (`contratante_id`, `tipo_contrato_id`, `created_at`, `comunidad_id`) VALUES 
(1, 1, '2024-01-01', 1),
(2, 2, '2024-02-01', 2);

-- -----------------------------------------------------
-- Data for table `mydb`.`vecinos_mensualidades`
-- -----------------------------------------------------
INSERT INTO `mydb`.`vecinos_mensualidades` (`vecino_id`, `mensualidad_id`, `fecha_pago`) VALUES 
(1, 1, '2024-01-10'),
(2, 2, '2024-02-10');

-- -----------------------------------------------------
-- Data for table `mydb`.`comunidades_ingresos`
-- -----------------------------------------------------
INSERT INTO `mydb`.`comunidades_ingresos` (`comunidad_id`, `ingreso_id`, `created_at`, `monto`) VALUES 
(1, 1, '2024-01-01', 500.00),
(2, 2, '2024-02-01', 700.00);

-- -----------------------------------------------------
-- Data for table `mydb`.`comunidades_gastos`
-- -----------------------------------------------------
INSERT INTO `mydb`.`comunidades_gastos` (`comunidad_id`, `gasto_id`, `created_at`, `monto`) VALUES 
(1, 1, '2024-01-01', 400.00),
(2, 2, '2024-02-01', 600.00);

-- -----------------------------------------------------
-- Data for table `mydb`.`tipos_reuniones`
-- -----------------------------------------------------
INSERT INTO `mydb`.`tipos_reuniones` (`nombre`) VALUES 
('Tipo Reunión 1'),
('Tipo Reunión 2');

-- -----------------------------------------------------
-- Data for table `mydb`.`reuniones`
-- -----------------------------------------------------
INSERT INTO `mydb`.`reuniones` (`tipo_reunion_id`, `fecha`) VALUES 
(1, '2024-01-15'),
(2, '2024-02-15');

-- -----------------------------------------------------
-- Data for table `mydb`.`reuniones_vecinos`
-- -----------------------------------------------------
INSERT INTO `mydb`.`reuniones_vecinos` (`reunion_id`, `vecino_id`) VALUES 
(1, 1),
(2, 2);

-- -----------------------------------------------------
-- Data for table `mydb`.`eventos`
-- -----------------------------------------------------
INSERT INTO `mydb`.`eventos` (`nombre`) VALUES 
('Evento 1'),
('Evento 2');

-- -----------------------------------------------------
-- Data for table `mydb`.`reuniones_eventos`
-- -----------------------------------------------------
INSERT INTO `mydb`.`reuniones_eventos` (`reunion_id`, `evento_id`) VALUES 
(1, 1),
(2, 2);

-- -----------------------------------------------------
-- Data for table `mydb`.`comisiones_vecinos`
-- -----------------------------------------------------
INSERT INTO `mydb`.`comisiones_vecinos` (`comision_id`, `vecino_id`, `rol_id`, `created_at`) VALUES 
(1, 1, 1, '2024-01-01'),
(2, 2, 2, '2024-02-01');

-- -----------------------------------------------------
-- Data for table `mydb`.`viviendas`
-- -----------------------------------------------------
INSERT INTO `mydb`.`viviendas` (`bloque_id`, `escalera`, `planta`, `puerta`, `letra`, `vecino_id`) VALUES 
(1, 'Escalera 1', 'Planta 1', 'Puerta 1', 'A', 1),
(2, 'Escalera 2', 'Planta 2', 'Puerta 2', 'B', 2),
(3, 'Escalera 3', 'Planta 3', 'Puerta 3', 'C', 3);
