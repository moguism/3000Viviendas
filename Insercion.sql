-- Inserciones para la tabla roles
INSERT INTO `mydb`.`roles` (`nombre`) VALUES 
('Presidente'),
('Secretario'),
('Tesorero'),
('Vocal');

-- Inserciones para la tabla tipos_gastos
INSERT INTO `mydb`.`tipos_gastos` (`nombre`) VALUES 
('Mantenimiento'),
('Limpieza'),
('Seguridad');

-- Inserciones para la tabla tipos_ingresos
INSERT INTO `mydb`.`tipos_ingresos` (`nombre`) VALUES 
('Cuota Mensual'),
('Donación'),
('Subvención');

-- Inserciones para la tabla comunidades
INSERT INTO `mydb`.`comunidades` (`nombre`, `direccion`) VALUES 
('Comunidad A', 'Calle Falsa 123'),
('Comunidad B', 'Avenida Siempre Viva 742');

-- Inserciones para la tabla bloques
INSERT INTO `mydb`.`bloques` (`comunidad_id`) VALUES 
(1),
(2);

-- Inserciones para la tabla mensualidades
INSERT INTO `mydb`.`mensualidades` (`fecha`, `cuantia`) VALUES 
('2024-01-01', 50.00),
('2024-02-01', 50.00);

-- Inserciones para la tabla tipos_deudas
INSERT INTO `mydb`.`tipos_deudas` (`nombre`) VALUES 
('Deuda Mantenimiento'),
('Deuda Limpieza');

-- Inserciones para la tabla deudas
INSERT INTO `mydb`.`deudas` (`tipo_deuda_id`, `comunidad_id`, `created_at`, `cuantia`, `updated_at`) VALUES 
(1, 1, '2024-01-01', 200.00, '2024-01-01'),
(2, 2, '2024-02-01', 150.00, '2024-02-01');

-- Inserciones para la tabla tipos_locales
INSERT INTO `mydb`.`tipos_locales` (`nombre`) VALUES 
('Comercial'),
('Residencial');

-- Inserciones para la tabla locales
INSERT INTO `mydb`.`locales` (`comunidad_id`, `tipo_local_id`, `nombre`) VALUES 
(1, 1, 'Tienda A'),
(2, 2, 'Departamento B');

-- Inserciones para la tabla contratantes
INSERT INTO `mydb`.`contratantes` (`nombre`) VALUES 
('Empresa A'),
('Empresa B');

-- Inserciones para la tabla contratos
INSERT INTO `mydb`.`contratos` (`contratante_id`, `created_at`, `comunidad_id`) VALUES 
(1, '2024-01-01', 1),
(2, '2024-02-01', 2);

-- Inserciones para la tabla vecinos
INSERT INTO `mydb`.`vecinos` (`nombre`, `vivienda_id`, `rol_id`) VALUES 
('Vecino 1', 1, 1),
('Vecino 2', 2, 2);

-- Inserciones para la tabla viviendas
INSERT INTO `mydb`.`viviendas` (`bloque_id`, `escalera`, `planta`, `puerta`, `letra`, `vecino_id`) VALUES 
(1, 'A', '1', '1', 'A', 1),
(2, 'B', '2', '2', 'B', 2);

-- Inserciones para la tabla vecinos_mensualidades
INSERT INTO `mydb`.`mensualidades_viviendas` (`vivienda_id`, `mensualidad_id`, `fecha_pago`) VALUES 
(1, 1, '2024-01-05'),
(2, 2, '2024-02-05');

-- Inserciones para la tabla tipos_reuniones
INSERT INTO `mydb`.`tipos_reuniones` (`nombre`) VALUES 
('Ordinaria'),
('Extraordinaria');

-- Inserciones para la tabla reuniones
INSERT INTO `mydb`.`reuniones` (`tipo_reunion_id`, `fecha`, `descripcion`) VALUES 
(1, '2024-01-10', "Hola"),
(2, '2024-02-10', "Hola");

-- Inserciones para la tabla reuniones_vecinos
INSERT INTO `mydb`.`reuniones_vecinos` (`reunion_id`, `vecino_id`) VALUES 
(1, 1),
(2, 2);

-- Inserciones para la tabla ingresos
INSERT INTO `mydb`.`ingresos` (`monto`, `fecha`, `tipo_ingreso_id`, `comunidad_id`) VALUES 
(1000.00, '2024-01-15', 1, 1),
(500.00, '2024-02-15', 2, 2);

-- Inserciones para la tabla gastos
INSERT INTO `mydb`.`gastos` (`monto`, `fecha`, `tipo_gasto_id`, `comunidad_id`) VALUES 
(200.00, '2024-01-20', 1, 1),
(150.00, '2024-02-20', 2, 2);
