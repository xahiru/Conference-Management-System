
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `confmng` ;
CREATE SCHEMA IF NOT EXISTS `confmng` DEFAULT CHARACTER SET utf8 ;
DROP SCHEMA IF EXISTS `securitydb` ;
CREATE SCHEMA IF NOT EXISTS `securitydb` DEFAULT CHARACTER SET latin1 ;
USE `confmng` ;

-- -----------------------------------------------------
-- Table `confmng`.`contents`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`contents` ;

CREATE TABLE IF NOT EXISTS `confmng`.`contents` (
  `idcontents` INT(11) NOT NULL AUTO_INCREMENT,
  `paticipant_notes` BLOB NULL DEFAULT NULL,
  PRIMARY KEY (`idcontents`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `confmng`.`roomcard`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`roomcard` ;

CREATE TABLE IF NOT EXISTS `confmng`.`roomcard` (
  `idtable1` INT(11) NOT NULL,
  `room_number` VARCHAR(45) NOT NULL,
  `card_number` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idtable1`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `confmng`.`participant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`participant` ;

CREATE TABLE IF NOT EXISTS `confmng`.`participant` (
  `idparticipant` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `photo` BLOB NULL DEFAULT NULL,
  `roomcard_idtable1` INT(11) NOT NULL,
  PRIMARY KEY (`idparticipant`),
  INDEX `fk_participant_roomcard1_idx` (`roomcard_idtable1` ASC),
  CONSTRAINT `fk_participant_roomcard1`
    FOREIGN KEY (`roomcard_idtable1`)
    REFERENCES `confmng`.`roomcard` (`idtable1`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `confmng`.`equipment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`equipment` ;

CREATE TABLE IF NOT EXISTS `confmng`.`equipment` (
  `idequipment` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idequipment`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `confmng`.`rental_request`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`rental_request` ;

CREATE TABLE IF NOT EXISTS `confmng`.`rental_request` (
  `idrental_request` INT(11) NOT NULL AUTO_INCREMENT,
  `request_type` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  `qauntity` INT(11) NULL DEFAULT NULL,
  `equipment_idequipment` INT(11) NOT NULL,
  PRIMARY KEY (`idrental_request`),
  INDEX `fk_rental_request_equipment1_idx` (`equipment_idequipment` ASC),
  CONSTRAINT `fk_rental_request_equipment1`
    FOREIGN KEY (`equipment_idequipment`)
    REFERENCES `confmng`.`equipment` (`idequipment`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `confmng`.`room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`room` ;

CREATE TABLE IF NOT EXISTS `confmng`.`room` (
  `idroom` INT(11) NOT NULL AUTO_INCREMENT,
  `number` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `area` INT(11) NULL DEFAULT NULL,
  `capacity` INT(11) NULL DEFAULT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `furniture_mobility` VARCHAR(45) NULL DEFAULT NULL,
  `furniture_type` VARCHAR(45) NULL DEFAULT NULL,
  `orientation` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idroom`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `confmng`.`event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`event` ;

CREATE TABLE IF NOT EXISTS `confmng`.`event` (
  `idevent` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  `number_of_participants` INT(11) NULL DEFAULT NULL,
  `rental_request_idrental_request` INT(11) NOT NULL,
  `contents_idcontents` INT(11) NOT NULL,
  `participant_idparticipant` INT(11) NOT NULL,
  `room_idroom` INT(11) NOT NULL,
  PRIMARY KEY (`idevent`),
  UNIQUE INDEX `idevent_UNIQUE` (`idevent` ASC),
  INDEX `fk_event_rental_request1_idx` (`rental_request_idrental_request` ASC),
  INDEX `fk_event_contents1_idx` (`contents_idcontents` ASC),
  INDEX `fk_event_participant1_idx` (`participant_idparticipant` ASC),
  INDEX `fk_event_room1_idx` (`room_idroom` ASC),
  CONSTRAINT `fk_event_contents1`
    FOREIGN KEY (`contents_idcontents`)
    REFERENCES `confmng`.`contents` (`idcontents`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_participant1`
    FOREIGN KEY (`participant_idparticipant`)
    REFERENCES `confmng`.`participant` (`idparticipant`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_rental_request1`
    FOREIGN KEY (`rental_request_idrental_request`)
    REFERENCES `confmng`.`rental_request` (`idrental_request`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_room1`
    FOREIGN KEY (`room_idroom`)
    REFERENCES `confmng`.`room` (`idroom`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `confmng`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`users` ;

CREATE TABLE IF NOT EXISTS `confmng`.`users` (
  `iduser` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL DEFAULT '123',
  PRIMARY KEY (`iduser`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `confmng`.`booking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`booking` ;

CREATE TABLE IF NOT EXISTS `confmng`.`booking` (
  `booking_ref` INT(11) NOT NULL AUTO_INCREMENT,
  `start_time` DATETIME NOT NULL,
  `end_time` DATETIME NOT NULL,
  `event_idevent` INT(11) NOT NULL,
  `users_iduser` INT(11) NOT NULL,
  PRIMARY KEY (`booking_ref`),
  UNIQUE INDEX `booking_ref_UNIQUE` (`booking_ref` ASC),
  INDEX `fk_booking_event1_idx` (`event_idevent` ASC),
  INDEX `fk_booking_users1_idx` (`users_iduser` ASC),
  CONSTRAINT `fk_booking_event1`
    FOREIGN KEY (`event_idevent`)
    REFERENCES `confmng`.`event` (`idevent`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_booking_users1`
    FOREIGN KEY (`users_iduser`)
    REFERENCES `confmng`.`users` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `confmng`.`schedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`schedule` ;

CREATE TABLE IF NOT EXISTS `confmng`.`schedule` (
  `idtable1` INT(11) NOT NULL,
  `date` VARCHAR(45) NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  `booking_booking_ref` INT(11) NOT NULL,
  PRIMARY KEY (`idtable1`),
  INDEX `fk_schedule_booking1_idx` (`booking_booking_ref` ASC),
  CONSTRAINT `fk_schedule_booking1`
    FOREIGN KEY (`booking_booking_ref`)
    REFERENCES `confmng`.`booking` (`booking_ref`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `confmng`.`display`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`display` ;

CREATE TABLE IF NOT EXISTS `confmng`.`display` (
  `iddisplay` INT(11) NOT NULL,
  `schedule_idtable1` INT(11) NOT NULL,
  PRIMARY KEY (`iddisplay`),
  INDEX `fk_display_schedule1_idx` (`schedule_idtable1` ASC),
  CONSTRAINT `fk_display_schedule1`
    FOREIGN KEY (`schedule_idtable1`)
    REFERENCES `confmng`.`schedule` (`idtable1`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `confmng`.`ads`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`ads` ;

CREATE TABLE IF NOT EXISTS `confmng`.`ads` (
  `idads` INT(11) NOT NULL AUTO_INCREMENT,
  `photo` BLOB NULL DEFAULT NULL,
  `video` BLOB NULL DEFAULT NULL,
  `date` VARCHAR(45) NULL DEFAULT NULL,
  `adscol` VARCHAR(45) NULL DEFAULT NULL,
  `display_iddisplay` INT(11) NOT NULL,
  PRIMARY KEY (`idads`),
  INDEX `fk_ads_display1_idx` (`display_iddisplay` ASC),
  CONSTRAINT `fk_ads_display1`
    FOREIGN KEY (`display_iddisplay`)
    REFERENCES `confmng`.`display` (`iddisplay`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `confmng`.`layout`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`layout` ;

CREATE TABLE IF NOT EXISTS `confmng`.`layout` (
  `idlayout` INT(11) NOT NULL,
  `floor_plan` BLOB NULL DEFAULT NULL,
  `photo` BLOB NULL DEFAULT NULL,
  `room_idroom` INT(11) NOT NULL,
  PRIMARY KEY (`idlayout`),
  INDEX `fk_layout_room1_idx` (`room_idroom` ASC),
  CONSTRAINT `fk_layout_room1`
    FOREIGN KEY (`room_idroom`)
    REFERENCES `confmng`.`room` (`idroom`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `confmng`.`organizer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`organizer` ;

CREATE TABLE IF NOT EXISTS `confmng`.`organizer` (
  `idorganizer` INT(11) NOT NULL AUTO_INCREMENT,
  `company_name` VARCHAR(45) NULL DEFAULT NULL,
  `contact_person_name` VARCHAR(45) NULL DEFAULT NULL,
  `contact_number` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `event_idevent` INT(11) NOT NULL,
  PRIMARY KEY (`idorganizer`),
  INDEX `fk_organizer_event1_idx` (`event_idevent` ASC),
  CONSTRAINT `fk_organizer_event1`
    FOREIGN KEY (`event_idevent`)
    REFERENCES `confmng`.`event` (`idevent`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `confmng`.`user_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`user_group` ;

CREATE TABLE IF NOT EXISTS `confmng`.`user_group` (
  `idroles` INT(11) NOT NULL AUTO_INCREMENT,
  `groupname` VARCHAR(255) NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idroles`),
  INDEX `fk_user_group_users1_idx` (`username` ASC),
  CONSTRAINT `fk_user_group_users1`
    FOREIGN KEY (`username`)
    REFERENCES `confmng`.`users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;

USE `securitydb` ;

-- -----------------------------------------------------
-- Table `securitydb`.`user_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `securitydb`.`user_group` ;

CREATE TABLE IF NOT EXISTS `securitydb`.`user_group` (
  `ID` BIGINT(20) NOT NULL,
  `groupname` VARCHAR(255) NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `version` BIGINT(20) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `securitydb`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `securitydb`.`users` ;

CREATE TABLE IF NOT EXISTS `securitydb`.`users` (
  `ID` BIGINT(20) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `version` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `confmng`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `confmng`;
INSERT INTO `confmng`.`users` (`iduser`, `username`, `password`) VALUES (NULL, 'admin', 'ungWv48Bz+pBQUDeXa4iI7ADYaOWF3qctBD/YfIAFa0=');
INSERT INTO `confmng`.`users` (`iduser`, `username`, `password`) VALUES (NULL, 'zahir', 'ungWv48Bz+pBQUDeXa4iI7ADYaOWF3qctBD/YfIAFa0=');
INSERT INTO `confmng`.`users` (`iduser`, `username`, `password`) VALUES (NULL, 'niya', 'ungWv48Bz+pBQUDeXa4iI7ADYaOWF3qctBD/YfIAFa0=');

COMMIT;


-- -----------------------------------------------------
-- Data for table `confmng`.`user_group`
-- -----------------------------------------------------
START TRANSACTION;
USE `confmng`;
INSERT INTO `confmng`.`user_group` (`idroles`, `groupname`, `username`) VALUES (NULL, 'admin', 'admin');
INSERT INTO `confmng`.`user_group` (`idroles`, `groupname`, `username`) VALUES (NULL, 'admin', 'zahir');
INSERT INTO `confmng`.`user_group` (`idroles`, `groupname`, `username`) VALUES (NULL, 'admin', 'niya');

COMMIT;
