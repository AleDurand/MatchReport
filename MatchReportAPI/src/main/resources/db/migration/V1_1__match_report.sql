-- MySQL Workbench Forward Engineering

-- -----------------------------------------------------
-- Schema match_report
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema match_report
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `match_report` ; --DEFAULT CHARACTER SET utf8 ;
USE `match_report` ;

-- -----------------------------------------------------
-- Table `match_report`.`authority`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `match_report`.`authority` ;

CREATE TABLE IF NOT EXISTS `match_report`.`authority` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
-- DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `match_report`.`cancha`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `match_report`.`cancha` ;

CREATE TABLE IF NOT EXISTS `match_report`.`cancha` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `foto` CHAR(250) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
-- DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `match_report`.`club`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `match_report`.`club` ;

CREATE TABLE IF NOT EXISTS `match_report`.`club` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `escudo` CHAR(250) NULL DEFAULT NULL,
  `pagina_web` VARCHAR(45) NULL DEFAULT NULL,
  `id_cancha` INT(11) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_club_cancha1_idx` (`id_cancha` ASC),
  CONSTRAINT `fk_club_cancha1`
    FOREIGN KEY (`id_cancha`)
    REFERENCES `match_report`.`cancha` (`id`)
    
    )
ENGINE = InnoDB;
-- DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `match_report`.`torneo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `match_report`.`torneo` ;

CREATE TABLE IF NOT EXISTS `match_report`.`torneo` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `fecha_inicio` DATE NOT NULL,
  `fecha_fin` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
-- DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `match_report`.`fecha`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `match_report`.`fecha` ;

CREATE TABLE IF NOT EXISTS `match_report`.`fecha` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `numero` INT(11) NOT NULL,
  `descripcion` VARCHAR(45) NULL DEFAULT NULL,
  `torneo_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_fecha_torneo1_idx` (`torneo_id` ASC),
  CONSTRAINT `fk_fecha_torneo1`
    FOREIGN KEY (`torneo_id`)
    REFERENCES `match_report`.`torneo` (`id`)
    
    )
ENGINE = InnoDB;
-- DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `match_report`.`jugador`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `match_report`.`jugador` ;

CREATE TABLE IF NOT EXISTS `match_report`.`jugador` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nombres` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `status` TINYINT(1) NULL DEFAULT NULL,
  `tipo_documento` VARCHAR(10) NOT NULL,
  `nro_documento` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
-- DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `match_report`.`juega_en`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `match_report`.`juega_en` ;

CREATE TABLE IF NOT EXISTS `match_report`.`juega_en` (
  `id_jugador` BIGINT(20) NOT NULL,
  `id_club` BIGINT(20) NOT NULL,
  `id_torneo` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id_jugador`, `id_club`, `id_torneo`),
  INDEX `fk_juega_en_jugador_idx` (`id_jugador` ASC),
  INDEX `fk_juega_en_club1_idx` (`id_club` ASC),
  INDEX `fk_juega_en_torneo1_idx` (`id_torneo` ASC),
  CONSTRAINT `fk_juega_en_club1`
    FOREIGN KEY (`id_club`)
    REFERENCES `match_report`.`club` (`id`)
    
    ,
  CONSTRAINT `fk_juega_en_jugador`
    FOREIGN KEY (`id_jugador`)
    REFERENCES `match_report`.`jugador` (`id`)
    
    ,
  CONSTRAINT `fk_juega_en_torneo1`
    FOREIGN KEY (`id_torneo`)
    REFERENCES `match_report`.`torneo` (`id`)
    
    )
ENGINE = InnoDB;
-- DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `match_report`.`partido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `match_report`.`partido` ;

CREATE TABLE IF NOT EXISTS `match_report`.`partido` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `id_cancha` INT(11) NOT NULL,
  `status` TINYINT NULL DEFAULT 0,
  `fecha_id` INT(11) NOT NULL,
  `equipo_local` BIGINT(20) NOT NULL,
  `equipo_visitante` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_partido_cancha1_idx` (`id_cancha` ASC),
  INDEX `fk_partido_fecha1_idx` (`fecha_id` ASC),
  INDEX `fk_partido_club1_idx` (`equipo_local` ASC),
  INDEX `fk_partido_club2_idx` (`equipo_visitante` ASC),
  CONSTRAINT `fk_partido_cancha1`
    FOREIGN KEY (`id_cancha`)
    REFERENCES `match_report`.`cancha` (`id`)
    
    ,
  CONSTRAINT `fk_partido_fecha1`
    FOREIGN KEY (`fecha_id`)
    REFERENCES `match_report`.`fecha` (`id`)
    
    ,
  CONSTRAINT `fk_partido_club1`
    FOREIGN KEY (`equipo_local`)
    REFERENCES `match_report`.`club` (`id`)
    
    ,
  CONSTRAINT `fk_partido_club2`
    FOREIGN KEY (`equipo_visitante`)
    REFERENCES `match_report`.`club` (`id`)
    
    )
ENGINE = InnoDB;
-- DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `match_report`.`jugo_partido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `match_report`.`jugo_partido` ;

CREATE TABLE IF NOT EXISTS `match_report`.`jugo_partido` (
  `partido_id` BIGINT(20) NOT NULL,
  `club_id` BIGINT(20) NOT NULL,
  `jugador_id` BIGINT(20) NOT NULL,
  `goles` INT(11) NOT NULL DEFAULT 0,
  `amarilla` TINYINT(1) NOT NULL DEFAULT 0,
  `descalificacion` TINYINT(1) NOT NULL DEFAULT 0,
  `exclusion` INT(11) NOT NULL DEFAULT 0,
  `tarjeta_azul` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`partido_id`, `club_id`, `jugador_id`),
  INDEX `fk_jugo_partido_partido1_idx` (`partido_id` ASC),
  INDEX `fk_jugo_partido_club1_idx` (`club_id` ASC),
  CONSTRAINT `fk_jugo_partido_jugador1`
    FOREIGN KEY (`jugador_id`)
    REFERENCES `match_report`.`jugador` (`id`)
    
    ,
  CONSTRAINT `fk_jugo_partido_partido1`
    FOREIGN KEY (`partido_id`)
    REFERENCES `match_report`.`partido` (`id`)
    
    ,
  CONSTRAINT `fk_jugo_partido_club1`
    FOREIGN KEY (`club_id`)
    REFERENCES `match_report`.`club` (`id`)
    
    )
ENGINE = InnoDB;
-- DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `match_report`.`resultado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `match_report`.`resultado` ;

CREATE TABLE IF NOT EXISTS `match_report`.`resultado` (
  `id_partido` BIGINT(20) NOT NULL,
  `goles_local` INT(11) NOT NULL DEFAULT '0',
  `goles_visitante` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_partido`),
  INDEX `fk_resultado_partido1_idx` (`id_partido` ASC),
  CONSTRAINT `fk_resultado_partido1`
    FOREIGN KEY (`id_partido`)
    REFERENCES `match_report`.`partido` (`id`)
    
    )
ENGINE = InnoDB;
-- DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `match_report`.`torneo_club`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `match_report`.`torneo_club` ;

CREATE TABLE IF NOT EXISTS `match_report`.`torneo_club` (
  `id_torneo` BIGINT(20) NOT NULL,
  `id_club` BIGINT(20) NOT NULL,
  `partidos_jugados` INT(11) NOT NULL DEFAULT '0',
  `partidos_ganados` INT(11) NOT NULL,
  `partidos_empatados` INT(11) NOT NULL,
  `partidos_perdidos` INT(11) NOT NULL,
  `goles_a_favor` INT(11) NOT NULL,
  `goles_en_contra` INT(11) NOT NULL,
  PRIMARY KEY (`id_club`, `id_torneo`),
  INDEX `fk_posiciones_torneo1_idx` (`id_torneo` ASC),
  INDEX `fk_posiciones_club1_idx` (`id_club` ASC),
  CONSTRAINT `fk_posiciones_club1`
    FOREIGN KEY (`id_club`)
    REFERENCES `match_report`.`club` (`id`)
    
    ,
  CONSTRAINT `fk_posiciones_torneo1`
    FOREIGN KEY (`id_torneo`)
    REFERENCES `match_report`.`torneo` (`id`)
    
    )
ENGINE = InnoDB;
-- DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `match_report`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `match_report`.`user` ;

CREATE TABLE IF NOT EXISTS `match_report`.`user` (
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `enabled` TINYINT(1) NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB;
-- DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `match_report`.`user_has_authority`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `match_report`.`user_has_authority` ;

CREATE TABLE IF NOT EXISTS `match_report`.`user_has_authority` (
  `user_id` VARCHAR(50) NOT NULL,
  `authority_id` INT(11) NOT NULL,
  PRIMARY KEY (`user_id`, `authority_id`),
  INDEX `fk_users_has_authorities_authorities1_idx` (`authority_id` ASC),
  INDEX `fk_users_has_authorities_users1_idx` (`user_id` ASC),
  CONSTRAINT `fk_users_has_authorities_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `match_report`.`user` (`username`)
    
    ,
  CONSTRAINT `fk_users_has_authorities_authorities1`
    FOREIGN KEY (`authority_id`)
    REFERENCES `match_report`.`authority` (`id`)
    
    )
ENGINE = InnoDB;
-- DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `match_report`.`partido_observacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `match_report`.`partido_observacion` ;

CREATE TABLE IF NOT EXISTS `match_report`.`partido_observacion` (
  `observacion` VARCHAR(500) NOT NULL,
  `partido_id` BIGINT(20) NOT NULL,
  INDEX `fk_partido_observacion_partido1_idx` (`partido_id` ASC),
  CONSTRAINT `fk_partido_observacion_partido1`
    FOREIGN KEY (`partido_id`)
    REFERENCES `match_report`.`partido` (`id`)
    
    )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `match_report`.`evento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `match_report`.`evento` ;

CREATE TABLE IF NOT EXISTS `match_report`.`evento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `discriminador` VARCHAR(45) NOT NULL,
  `partido_id` BIGINT(20) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `minuto` VARCHAR(45) NOT NULL,
  `minuto_extra` VARCHAR(45) NULL,
  `periodo` VARCHAR(45) NULL,
  `jugador_id` BIGINT(20) NULL,
  `club_id` BIGINT(20) NULL,
  `jugador_in_id` BIGINT(20) NULL,
  `jugador_out_id` BIGINT(20) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_evento_jugador1_idx` (`jugador_id` ASC),
  INDEX `fk_evento_partido1_idx` (`partido_id` ASC),
  INDEX `fk_evento_club1_idx` (`club_id` ASC),
  INDEX `fk_evento_jugador2_idx` (`jugador_in_id` ASC),
  INDEX `fk_evento_jugador3_idx` (`jugador_out_id` ASC),
  CONSTRAINT `fk_evento_jugador1`
    FOREIGN KEY (`jugador_id`)
    REFERENCES `match_report`.`jugador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_evento_partido1`
    FOREIGN KEY (`partido_id`)
    REFERENCES `match_report`.`partido` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_evento_club1`
    FOREIGN KEY (`club_id`)
    REFERENCES `match_report`.`club` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_evento_jugador2`
    FOREIGN KEY (`jugador_in_id`)
    REFERENCES `match_report`.`jugador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_evento_jugador3`
    FOREIGN KEY (`jugador_out_id`)
    REFERENCES `match_report`.`jugador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
