-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`club`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`club` ;

CREATE TABLE IF NOT EXISTS `mydb`.`club` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NULL,
  `escudo` CHAR(32) NULL,
  `pagina_web` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`table1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`table1` ;

CREATE TABLE IF NOT EXISTS `mydb`.`table1` (
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`jugador`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`jugador` ;

CREATE TABLE IF NOT EXISTS `mydb`.`jugador` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombres` VARCHAR(45) NULL,
  `apellido` VARCHAR(45) NULL,
  `fecha_nacimiento` DATE NULL,
  `status` TINYINT(1) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`juega_en`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`juega_en` ;

CREATE TABLE IF NOT EXISTS `mydb`.`juega_en` (
  `jugador_id` BIGINT NOT NULL,
  `club_id` BIGINT NOT NULL,
  INDEX `fk_juega_en_jugador_idx` (`jugador_id` ASC),
  INDEX `fk_juega_en_club1_idx` (`club_id` ASC),
  PRIMARY KEY (`jugador_id`, `club_id`),
  CONSTRAINT `fk_juega_en_jugador`
    FOREIGN KEY (`jugador_id`)
    REFERENCES `mydb`.`jugador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_juega_en_club1`
    FOREIGN KEY (`club_id`)
    REFERENCES `mydb`.`club` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
