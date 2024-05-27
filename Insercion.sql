-- Insertar datos en la tabla `comunidades`
INSERT INTO `mydb`.`comunidades` (`nombre`, `direccion`) VALUES
('Comunidad A', 'Direccion A'),
('Comunidad B', 'Direccion B');

-- Insertar datos en la tabla `bloques`
INSERT INTO `mydb`.`bloques` (`comunidad_id`) VALUES
(1), -- Referencia a `Comunidad A`
(2); -- Referencia a `Comunidad B`

-- Insertar datos en la tabla `contratantes`
INSERT INTO `mydb`.`contratantes` (`nombre`) VALUES
('Contratante A'),
('Contratante B');

-- Insertar datos en la tabla `contratos`
INSERT INTO `mydb`.`contratos` (`contratante_id`, `created_at`, `comunidad_id`) VALUES
(1, '2023-01-01', 1), -- Referencia a `Contratante A` y `Comunidad A`
(2, '2023-02-01', 2); -- Referencia a `Contratante B` y `Comunidad B`

-- Insertar datos en la tabla `tipos_deudas`
INSERT INTO `mydb`.`tipos_deudas` (`nombre`) VALUES
('Tipo Deuda A'),
('Tipo Deuda B');

-- Insertar datos en la tabla `deudas`
INSERT INTO `mydb`.`deudas` (`tipo_deuda_id`, `comunidad_id`, `created_at`, `cuantia`, `updated_at`) VALUES
(1, 1, '2023-01-01', 1000.0, '2023-01-15'), -- Referencia a `Tipo Deuda A` y `Comunidad A`
(2, 2, '2023-02-01', 2000.0, '2023-02-15'); -- Referencia a `Tipo Deuda B` y `Comunidad B`

-- Insertar datos en la tabla `tipos_gastos`
INSERT INTO `mydb`.`tipos_gastos` (`nombre`) VALUES
('Tipo Gasto A'),
('Tipo Gasto B');

-- Insertar datos en la tabla `gastos`
INSERT INTO `mydb`.`gastos` (`monto`, `fecha`, `tipo_gasto_id`, `comunidad_id`) VALUES
(500.0, '2023-01-10', 1, 1), -- Referencia a `Tipo Gasto A` y `Comunidad A`
(1500.0, '2023-02-10', 2, 2); -- Referencia a `Tipo Gasto B` y `Comunidad B`

-- Insertar datos en la tabla `tipos_ingresos`
INSERT INTO `mydb`.`tipos_ingresos` (`nombre`) VALUES
('Tipo Ingreso A'),
('Tipo Ingreso B');

-- Insertar datos en la tabla `ingresos`
INSERT INTO `mydb`.`ingresos` (`monto`, `fecha`, `tipo_ingreso_id`, `comunidad_id`) VALUES
(2000.0, '2023-01-20', 1, 1), -- Referencia a `Tipo Ingreso A` y `Comunidad A`
(3000.0, '2023-02-20', 2, 2); -- Referencia a `Tipo Ingreso B` y `Comunidad B`

-- Insertar datos en la tabla `tipos_locales`
INSERT INTO `mydb`.`tipos_locales` (`nombre`) VALUES
('Tipo Local A'),
('Tipo Local B');

-- Insertar datos en la tabla `locales`
INSERT INTO `mydb`.`locales` (`comunidad_id`, `tipo_local_id`, `nombre`) VALUES
(1, 1, 'Local A1'), -- Referencia a `Comunidad A` y `Tipo Local A`
(2, 2, 'Local B1'); -- Referencia a `Comunidad B` y `Tipo Local B`

-- Insertar datos en la tabla `mensualidades`
INSERT INTO `mydb`.`mensualidades` (`fecha`, `cuantia`) VALUES
('2023-01-01', 100.0),
('2023-02-01', 200.0);

-- Insertar datos en la tabla `roles`
INSERT INTO `mydb`.`roles` (`nombre`) VALUES
('Rol A'),
('Rol B');

-- Insertar datos en la tabla `vecinos`
INSERT INTO `mydb`.`vecinos` (`nombre`, `rol_id`) VALUES
('Vecino A', 1), -- Referencia a `Rol A`
('Vecino B', 2); -- Referencia a `Rol B`

-- Insertar datos en la tabla `viviendas`
INSERT INTO `mydb`.`viviendas` (`bloque_id`, `escalera`, `planta`, `puerta`, `letra`, `vecino_id`) VALUES
(1, 'Escalera A', 'Planta 1', 'Puerta 1', 'A', 1), -- Referencia a `Bloque 1` y `Vecino A`
(2, 'Escalera B', 'Planta 2', 'Puerta 2', 'B', 2); -- Referencia a `Bloque 2` y `Vecino B`

-- Insertar datos en la tabla `mensualidades_viviendas`
INSERT INTO `mydb`.`vecinos_mensualidades` (`mensualidad_id`, `vecino_id`, `fecha_pago`) VALUES
(1, 1, '2023-01-15'), -- Referencia a `Mensualidad 1` y `Vivienda 1`
(2, 2, '2023-02-15'); -- Referencia a `Mensualidad 2` y `Vivienda 2`

-- Insertar datos en la tabla `tipos_reuniones`
INSERT INTO `mydb`.`tipos_reuniones` (`nombre`) VALUES
('Tipo Reunión A'),
('Tipo Reunión B');

-- Insertar datos en la tabla `reuniones`
INSERT INTO `mydb`.`reuniones` (`tipo_reunion_id`, `fecha`, `descripcion`, `bloque_id`) VALUES
(1, '2023-01-20', 'Reunión A1', 1), -- Referencia a `Tipo Reunión A` y `Bloque 1`
(2, '2023-02-20', 'Reunión B1', 2); -- Referencia a `Tipo Reunión B` y `Bloque 2`

