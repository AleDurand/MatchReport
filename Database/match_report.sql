-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema match_report
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema match_report
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `match_report` DEFAULT CHARACTER SET utf8 ;
USE `match_report` ;

-- -----------------------------------------------------
-- Table `match_report`.`club`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `match_report`.`club` ;

CREATE TABLE IF NOT EXISTS `match_report`.`club` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NULL,
  `escudo` CHAR(32) NULL,
  `pagina_web` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `match_report`.`jugador`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `match_report`.`jugador` ;

CREATE TABLE IF NOT EXISTS `match_report`.`jugador` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombres` VARCHAR(45) NULL,
  `apellido` VARCHAR(45) NULL,
  `fecha_nacimiento` DATE NULL,
  `status` TINYINT(1) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `match_report`.`juega_en`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `match_report`.`juega_en` ;

CREATE TABLE IF NOT EXISTS `match_report`.`juega_en` (
  `jugador_id` BIGINT NOT NULL,
  `club_id` BIGINT NOT NULL,
  INDEX `fk_juega_en_jugador_idx` (`jugador_id` ASC),
  INDEX `fk_juega_en_club1_idx` (`club_id` ASC),
  PRIMARY KEY (`jugador_id`, `club_id`),
  CONSTRAINT `fk_juega_en_jugador`
    FOREIGN KEY (`jugador_id`)
    REFERENCES `match_report`.`jugador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_juega_en_club1`
    FOREIGN KEY (`club_id`)
    REFERENCES `match_report`.`club` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
