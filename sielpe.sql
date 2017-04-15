# Host: localhost  (Version 5.7.16-log)
# Date: 2017-04-14 20:33:29
# Generator: MySQL-Front 5.4  (Build 2.11)
# Internet: http://www.mysqlfront.de/

/*!40101 SET NAMES utf8 */;

#
# Structure for table "candidato"
#

CREATE TABLE `candidato` (
  `idcandidato` varchar(45) NOT NULL DEFAULT '0',
  `id_eleccion` int(11) NOT NULL DEFAULT '0',
  `nombre` varchar(200) DEFAULT NULL,
  `genero` varchar(45) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `numero_lista` int(11) DEFAULT NULL,
  `imagen` blob,
  PRIMARY KEY (`idcandidato`),
  KEY `id_eleccion_idx` (`id_eleccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "candidato"
#

REPLACE INTO `candidato` VALUES ('1',0,'En Blanco',NULL,NULL,NULL,NULL),('123123',1,'Pedro Garcia','Masculino','1990-02-02',10,NULL),('2313',1,'Juan Jimenez','Masculino','1999-09-01',5,X'');

#
# Structure for table "eleccion"
#

CREATE TABLE `eleccion` (
  `ideleccion` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_eleccion` varchar(45) DEFAULT NULL,
  `fecha_inicio_inscripcion` date DEFAULT NULL,
  `fecha_fin_inscripcion` date DEFAULT NULL,
  `hora_inicio_votacion` time DEFAULT NULL,
  `hora_fin_votacion` time DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha_votacion` date DEFAULT NULL,
  PRIMARY KEY (`ideleccion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "eleccion"
#

REPLACE INTO `eleccion` VALUES (1,'eleccion asamblea','2017-04-14','2017-04-16','08:00:00','20:00:00','Votacion para la asamblea general','2017-04-14'),(2,'Eleccion padrinos','2017-04-13','2017-04-20','08:00:00','20:00:00','Eleccion de padrinos de area','2017-04-15');

#
# Structure for table "estado_usuarios"
#

CREATE TABLE `estado_usuarios` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_estado` varchar(45) DEFAULT NULL,
  `descripcion_estado` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "estado_usuarios"
#

REPLACE INTO `estado_usuarios` VALUES (1,'Activo',NULL),(2,'Inactivo',NULL);

#
# Structure for table "estado_votacion"
#

CREATE TABLE `estado_votacion` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `estado` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "estado_votacion"
#

REPLACE INTO `estado_votacion` VALUES (1,'Almacenado'),(2,'Cancelado');

#
# Structure for table "rol"
#

CREATE TABLE `rol` (
  `idrol` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_rol` varchar(45) NOT NULL,
  `descripcion_rol` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idrol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "rol"
#

REPLACE INTO `rol` VALUES (1,'Administrador','Usuario encargado de administrar el sistema de votaciones'),(2,'Usuario','Usuario basico que puede votar en las elecciones');

#
# Structure for table "usuario"
#

CREATE TABLE `usuario` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `nro_documento_user` varchar(45) DEFAULT NULL,
  `nombre_user` varchar(45) DEFAULT NULL,
  `fecha_nacimiento_user` date DEFAULT NULL,
  `id_rol` int(11) NOT NULL,
  `password_user` varchar(100) DEFAULT NULL,
  `genero` varchar(45) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `id_estado` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`iduser`),
  KEY `id_rol_idx` (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "usuario"
#

REPLACE INTO `usuario` VALUES (1,'1010','nicolasrg','1990-12-02',2,'1','Masculino','admin@mail.com','1'),(2,'777','admin','1990-02-09',1,'1','Masculino','nico@mail.com','1');

#
# Structure for table "votacion"
#

CREATE TABLE `votacion` (
  `id_eleccion` int(11) NOT NULL DEFAULT '0',
  `id_user` varchar(45) NOT NULL DEFAULT '0',
  `id_candidato` varchar(45) NOT NULL DEFAULT '0',
  `estado` varchar(100) DEFAULT NULL,
  KEY `id_user_idx` (`id_user`),
  KEY `id_candidato_idx` (`id_candidato`),
  KEY `id_eleccion_fk` (`id_eleccion`),
  CONSTRAINT `id_eleccion_fk` FOREIGN KEY (`id_eleccion`) REFERENCES `eleccion` (`ideleccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "votacion"
#

