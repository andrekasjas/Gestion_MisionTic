-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 31-07-2022 a las 05:40:13
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `reto_empresa`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` bigint(20) NOT NULL,
  `telefono` varchar(60) NOT NULL,
  `persona_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `telefono`, `persona_id`) VALUES
(30, '12', 80),
(31, '5000', 81),
(32, '315', 82),
(33, '3522', 83),
(34, '3152', 84),
(35, '10000', 85),
(36, '20000', 98),
(37, '20000', 99);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente_empresa`
--

CREATE TABLE `cliente_empresa` (
  `id` bigint(20) NOT NULL,
  `cliente_id` bigint(20) NOT NULL,
  `empresa_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cliente_empresa`
--

INSERT INTO `cliente_empresa` (`id`, `cliente_id`, `empresa_id`) VALUES
(2, 30, 6),
(3, 30, 7),
(5, 32, 7),
(6, 33, 6),
(7, 34, 6),
(8, 35, 6),
(9, 36, 7),
(10, 37, 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `directivo`
--

CREATE TABLE `directivo` (
  `id` bigint(20) NOT NULL,
  `categoria` int(11) NOT NULL,
  `empleado_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `directivo`
--

INSERT INTO `directivo` (`id`, `categoria`, `empleado_id`) VALUES
(2, 3, 43),
(3, 1, 45),
(4, 3, 49),
(5, 3, 51),
(6, 1, 57),
(7, 1, 59);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id` bigint(20) NOT NULL,
  `sueldo` int(11) NOT NULL,
  `directivo_id` bigint(20) DEFAULT NULL,
  `persona_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id`, `sueldo`, `directivo_id`, `persona_id`) VALUES
(42, 255555, 4, 81),
(43, 100, NULL, 82),
(44, 10000, 3, 83),
(45, 5000000, NULL, 84),
(46, 10000, 5, 85),
(48, 321321, 2, 86),
(49, 10000, NULL, 87),
(50, 20000, 7, 88),
(51, 10000, NULL, 89),
(52, 20000, 7, 90),
(53, 111111, 7, 91),
(54, 2000, 6, 92),
(55, 2000, 6, 93),
(56, 10000, 6, 94),
(57, 200050, NULL, 95),
(58, 10000, 7, 96),
(59, 20500, NULL, 97);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `cif` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`id`, `nombre`, `cif`) VALUES
(6, 'ANDRES S.A.S', '1'),
(7, 'ERIKA S.A.S', '2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa_empleado`
--

CREATE TABLE `empresa_empleado` (
  `id` bigint(20) NOT NULL,
  `empresa_id` bigint(20) NOT NULL,
  `empleado_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `empresa_empleado`
--

INSERT INTO `empresa_empleado` (`id`, `empresa_id`, `empleado_id`) VALUES
(1, 7, 42),
(3, 6, 44),
(4, 6, 45),
(5, 7, 46),
(6, 7, 49),
(7, 7, 50),
(8, 7, 51),
(9, 7, 52),
(10, 7, 53),
(11, 6, 54),
(12, 6, 55),
(13, 6, 56),
(14, 6, 57),
(15, 7, 58),
(16, 7, 59);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `documento` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id`, `nombre`, `fecha_nacimiento`, `documento`) VALUES
(80, 'compartido dos empresas', '2000-10-10', '12'),
(81, 'cliente y emplead', '1996-05-01', '5'),
(82, 'cliente y directiv', '2000-02-19', '87'),
(83, 'cliente empleado', '2000-10-10', '987'),
(84, 'directivo cliente', '2000-11-19', '654'),
(85, 'otro', '2000-02-02', '325'),
(86, 'prueba', '2022-07-13', '123123123123'),
(87, 'prueba1', '1000-10-10', 'prueba1'),
(88, 'nuevo empleado', '2000-10-10', 'nuevo empleado'),
(89, 'directivo1sub', '2005-05-10', 'directivo1sub'),
(90, 'nuevosalfa', '2000-10-10', 'nuevosalfaa'),
(91, 'nuevsssss', '2000-05-15', 'nuevssssss'),
(92, 'empleado', '2000-12-20', 'empleado'),
(93, 'empleado2', '2000-10-10', 'empleado2'),
(94, 'empleado3', '1000-12-10', 'empleado3'),
(95, 'directivocon3', '2000-10-10', 'directivocon3'),
(96, 'empleadoas', '2000-10-10', 'empleadoas'),
(97, 'directivomas', '2000-12-10', 'directivomas'),
(98, 'mascliente', '1000-10-10', 'mascliente'),
(99, 'otroclienteprueba', '1000-01-10', 'otrocluientepie');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `persona_id_2` (`persona_id`),
  ADD KEY `persona_id` (`persona_id`);

--
-- Indices de la tabla `cliente_empresa`
--
ALTER TABLE `cliente_empresa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cliente_id` (`cliente_id`),
  ADD KEY `empresa_id` (`empresa_id`);

--
-- Indices de la tabla `directivo`
--
ALTER TABLE `directivo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `empleado_id` (`empleado_id`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `persona_id_2` (`persona_id`),
  ADD KEY `persona_id` (`persona_id`);

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `empresa_empleado`
--
ALTER TABLE `empresa_empleado`
  ADD PRIMARY KEY (`id`),
  ADD KEY `empresa_id` (`empresa_id`),
  ADD KEY `empleado_id` (`empleado_id`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `documento` (`documento`),
  ADD UNIQUE KEY `documento_2` (`documento`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT de la tabla `cliente_empresa`
--
ALTER TABLE `cliente_empresa`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `directivo`
--
ALTER TABLE `directivo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT de la tabla `empresa`
--
ALTER TABLE `empresa`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `empresa_empleado`
--
ALTER TABLE `empresa_empleado`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `cliente_empresa`
--
ALTER TABLE `cliente_empresa`
  ADD CONSTRAINT `cliente_empresa_ibfk_1` FOREIGN KEY (`empresa_id`) REFERENCES `empresa` (`id`),
  ADD CONSTRAINT `cliente_empresa_ibfk_2` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);

--
-- Filtros para la tabla `directivo`
--
ALTER TABLE `directivo`
  ADD CONSTRAINT `directivo_ibfk_1` FOREIGN KEY (`empleado_id`) REFERENCES `empleado` (`id`);

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `empresa_empleado`
--
ALTER TABLE `empresa_empleado`
  ADD CONSTRAINT `empresa_empleado_ibfk_1` FOREIGN KEY (`empleado_id`) REFERENCES `empleado` (`id`),
  ADD CONSTRAINT `empresa_empleado_ibfk_2` FOREIGN KEY (`empresa_id`) REFERENCES `empresa` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
