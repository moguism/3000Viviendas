-- Insertar datos en la tabla `comunidades`
INSERT INTO `mydb`.`comunidades` (`nombre`, `direccion`) VALUES ('Prueba Comunidad', 'Dirección de Prueba');

-- Insertar datos en la tabla `bloques`
INSERT INTO `mydb`.`bloques` (`comunidad_id`,`numero`) VALUES (1,69);

-- Insertar datos en la tabla `contratantes`
INSERT INTO `mydb`.`contratantes` (`nombre`) VALUES ('Prueba Contratante');

-- Insertar datos en la tabla `contratos`
INSERT INTO `mydb`.`contratos` (`contratante_id`, `created_at`, `comunidad_id`) VALUES (1, '2024-05-27', 1);

-- Insertar datos en la tabla `tipos_deudas`
INSERT INTO `mydb`.`tipos_deudas` (`nombre`) VALUES ('Prueba Tipo Deuda');

-- Insertar datos en la tabla `deudas`
INSERT INTO `mydb`.`deudas` (`tipo_deuda_id`, `comunidad_id`, `fecha`, `monto`) VALUES (1, 1, '2024-05-27', 100.00);

-- Insertar datos en la tabla `tipos_gastos`
INSERT INTO `mydb`.`tipos_gastos` (`nombre`) VALUES ('Prueba Tipo Gasto');

-- Insertar datos en la tabla `gastos`
INSERT INTO `mydb`.`gastos` (`monto`, `fecha`, `tipo_gasto_id`, `comunidad_id`) VALUES (50.00, '2024-05-27', 1, 1);

-- Insertar datos en la tabla `tipos_ingresos`
INSERT INTO `mydb`.`tipos_ingresos` (`nombre`) VALUES ('Prueba Tipo Ingreso');

-- Insertar datos en la tabla `ingresos`
INSERT INTO `mydb`.`ingresos` (`monto`, `fecha`, `tipo_ingreso_id`, `comunidad_id`) VALUES (200.00, '2024-05-27', 1, 1);

-- Insertar datos en la tabla `tipos_locales`
INSERT INTO `mydb`.`tipos_locales` (`nombre`) VALUES ('Prueba Tipo Local');

-- Insertar datos en la tabla `locales`
INSERT INTO `mydb`.`locales` (`comunidad_id`, `tipo_local_id`, `nombre`) VALUES (1, 1, 'Local de Prueba');

-- Insertar datos en la tabla `mensualidades`
INSERT INTO `mydb`.`mensualidades` (`fecha`, `cuantia`) VALUES ('2024-05-01', 150.00);

-- Insertar datos en la tabla `tipos_reuniones`
INSERT INTO `mydb`.`tipos_reuniones` (`nombre`) VALUES ('Prueba Tipo Reunion');

-- Insertar datos en la tabla `reuniones`
INSERT INTO `mydb`.`reuniones` (`tipo_reunion_id`, `fecha`, `descripcion`, `bloque_id`) VALUES (1, '2024-05-27', 'Descripción de Prueba', 1);

-- Insertar datos en la tabla `roles`
INSERT INTO `mydb`.`roles` (`nombre`) VALUES ('Prueba Rol');

-- Insertar datos en la tabla `vecinos`
INSERT INTO `mydb`.`vecinos` (`nombre`, `rol_id`) VALUES ('Prueba Vecino', 1);

-- Insertar datos en la tabla `viviendas`
INSERT INTO `mydb`.`viviendas` (`bloque_id`, `escalera`, `planta`, `puerta`, `letra`, `vecino_id`, `mensualidad_id`) VALUES (1, 'A', '1', '1', 'A', 1, 1);

