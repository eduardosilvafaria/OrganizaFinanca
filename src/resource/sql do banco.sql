-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`001usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`001usuario` (
  `id` INT NOT NULL,
  `nome` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `data_nascimento` DATE NULL,
  `senha` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`002movimentacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`002movimentacao` (
  `idMovimentacao` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(30) NULL,
  `valor` DECIMAL NULL,
  `tipo` CHAR(1) NULL,
  `categoria` VARCHAR(15) NULL,
  `data_vencimento` DATE NULL,
  `data_alteracao` DATE NULL,
  `001usuario_id` INT NOT NULL,
  PRIMARY KEY (`idMovimentacao`),
  INDEX `fk_002movimentacao_001usuario_idx` (`001usuario_id` ASC) ,
  CONSTRAINT `fk_002movimentacao_001usuario`
    FOREIGN KEY (`001usuario_id`)
    REFERENCES `mydb`.`001usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
