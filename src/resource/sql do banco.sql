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
-- Table `mydb`.`005grupoFinanceiro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`005grupoFinanceiro` (
  `idGrupo` INT NOT NULL AUTO_INCREMENT,
  `administradorGrupo` TINYINT NOT NULL,
  `visualizarTudo` TINYINT NOT NULL,
  PRIMARY KEY (`idGrupo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`001usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`001usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `data_nascimento` DATE NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `primeiroAcesso` TINYINT NOT NULL DEFAULT 0,
  `grupoFinanceiro_idGrupo` INT NOT NULL,
  PRIMARY KEY (`grupoFinanceiro_idGrupo`),
  INDEX `fk_usuario_grupoFinanceiro_idx` (`grupoFinanceiro_idGrupo` ASC) ,
  CONSTRAINT usuario_pkey PRIMARY KEY (id ),
  CONSTRAINT usuario_email_key UNIQUE (email ),
  CONSTRAINT `fk_usuario_grupoFinanceiro1`
    FOREIGN KEY (`grupoFinanceiro_idGrupo`)
    REFERENCES `mydb`.`005grupoFinanceiro` (`idGrupo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`003categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`003categoria` (
  `idCategoria` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`idCategoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`004subCategoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`004subCategoria` (
  `idSubCategoria` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `idCategoria` INT NOT NULL,
  PRIMARY KEY (`idSubCategoria`, `idCategoria`),
  INDEX `fk_subCategoria_categoria_idx` (`idCategoria` ASC) ,
  CONSTRAINT `fk_subCategoria_categoria1`
    FOREIGN KEY (`idCategoria`)
    REFERENCES `mydb`.`003categoria` (`idCategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`002movimentacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`002movimentacao` (
  `idMovimentacao` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(30) NOT NULL,
  `valor` DECIMAL NOT NULL,
  `tipo` CHAR(1) NOT NULL,
  `categoria` VARCHAR(15) NOT NULL,
  `data_vencimento` DATE NULL,
  `data_alteracao` DATE NULL DEFAULT CURRENT_TIMESTAMP,
  `parcelamento` INT NOT NULL,
  `situacao` CHAR(1) NOT NULL,
  `idUsuario` INT NOT NULL,
  `idGrupo` INT NOT NULL,
  `idSubCategoria` INT NOT NULL,
  PRIMARY KEY (`idMovimentacao`, `idUsuario`, `idGrupo`, `idSubCategoria`),
  INDEX `fk_movimentacao_usuario_idx` (`idUsuario` ASC) ,
  INDEX `fk_movimentacao_grupoFinanceiro_idx` (`idGrupo` ASC) ,
  INDEX `fk_movimentacao_subCategoria_idx` (`idSubCategoria` ASC) ,
  CONSTRAINT `fk_movimentacao_usuario`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `mydb`.`001usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_movimentacao_grupoFinanceiro`
    FOREIGN KEY (`idGrupo`)
    REFERENCES `mydb`.`005grupoFinanceiro` (`idGrupo`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_movimentacao_subCategoria1`
    FOREIGN KEY (`idSubCategoria`)
    REFERENCES `mydb`.`004subCategoria` (`idSubCategoria`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
