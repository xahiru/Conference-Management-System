SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `confmng` ;
CREATE SCHEMA IF NOT EXISTS `confmng` DEFAULT CHARACTER SET utf8 ;
USE `confmng` ;

-- -----------------------------------------------------
-- Table `confmng`.`tblAdvertisement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`tblAdvertisement` ;

CREATE TABLE IF NOT EXISTS `confmng`.`tblAdvertisement` (
  `adId` INT(11) NOT NULL AUTO_INCREMENT,
  `photo` BLOB NULL DEFAULT NULL,
  PRIMARY KEY (`adId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `confmng`.`tblUser`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`tblUser` ;

CREATE TABLE IF NOT EXISTS `confmng`.`tblUser` (
  `userId` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL DEFAULT '123',
  PRIMARY KEY (`userId`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `confmng`.`tblBooking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`tblBooking` ;

CREATE TABLE IF NOT EXISTS `confmng`.`tblBooking` (
  `bookingId` INT(11) NOT NULL AUTO_INCREMENT,
  `start_time` DATETIME NOT NULL,
  `end_time` DATETIME NOT NULL,
  `tblUser_userId` INT(11) NOT NULL,
  PRIMARY KEY (`bookingId`),
  UNIQUE INDEX `booking_ref_UNIQUE` (`bookingId` ASC),
  INDEX `fk_tblBooking_tblUser1_idx` (`tblUser_userId` ASC),
  CONSTRAINT `fk_tblBooking_tblUser1`
    FOREIGN KEY (`tblUser_userId`)
    REFERENCES `confmng`.`tblUser` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `confmng`.`tblOrganizer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`tblOrganizer` ;

CREATE TABLE IF NOT EXISTS `confmng`.`tblOrganizer` (
  `organizerId` INT(11) NOT NULL AUTO_INCREMENT,
  `company_name` VARCHAR(45) NULL DEFAULT NULL,
  `contact_person_name` VARCHAR(45) NULL DEFAULT NULL,
  `contact_number` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`organizerId`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `confmng`.`tblRoom`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`tblRoom` ;

CREATE TABLE IF NOT EXISTS `confmng`.`tblRoom` (
  `roomId` INT(11) NOT NULL AUTO_INCREMENT,
  `number` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `area` INT(11) NULL DEFAULT NULL,
  `capacity` INT(11) NULL DEFAULT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `furniture_mobility` VARCHAR(45) NULL DEFAULT NULL,
  `furniture_type` VARCHAR(45) NULL DEFAULT NULL,
  `orientation` VARCHAR(45) NULL DEFAULT NULL,
  `floor_plan` MEDIUMBLOB NULL,
  PRIMARY KEY (`roomId`),
  UNIQUE INDEX `number_UNIQUE` (`number` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3;


-- -----------------------------------------------------
-- Table `confmng`.`tblEvent`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`tblEvent` ;

CREATE TABLE IF NOT EXISTS `confmng`.`tblEvent` (
  `eventId` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  `number_of_participants` INT(11) NULL DEFAULT NULL,
  `booking_booking_ref` INT(11) NOT NULL,
  `tblOrganizer_organizerId` INT(11) NOT NULL,
  `tblRoom_roomId` INT(11) NOT NULL,
  `openresgistration` TINYINT(1) NOT NULL DEFAULT TRUE,
  PRIMARY KEY (`eventId`),
  UNIQUE INDEX `idevent_UNIQUE` (`eventId` ASC),
  INDEX `fk_event_booking1_idx` (`booking_booking_ref` ASC),
  INDEX `fk_tblEvent_tblOrganizer1_idx` (`tblOrganizer_organizerId` ASC),
  INDEX `fk_tblEvent_tblRoom1_idx` (`tblRoom_roomId` ASC),
  CONSTRAINT `fk_event_booking1`
    FOREIGN KEY (`booking_booking_ref`)
    REFERENCES `confmng`.`tblBooking` (`bookingId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblEvent_tblOrganizer1`
    FOREIGN KEY (`tblOrganizer_organizerId`)
    REFERENCES `confmng`.`tblOrganizer` (`organizerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblEvent_tblRoom1`
    FOREIGN KEY (`tblRoom_roomId`)
    REFERENCES `confmng`.`tblRoom` (`roomId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `confmng`.`tblContent`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`tblContent` ;

CREATE TABLE IF NOT EXISTS `confmng`.`tblContent` (
  `contentsId` INT(11) NOT NULL AUTO_INCREMENT,
  `paticipant_notes` LONGBLOB NULL DEFAULT NULL,
  `tblEvent_eventId` INT(11) NOT NULL,
  PRIMARY KEY (`contentsId`),
  INDEX `fk_tblContent_tblEvent1_idx` (`tblEvent_eventId` ASC),
  CONSTRAINT `fk_tblContent_tblEvent1`
    FOREIGN KEY (`tblEvent_eventId`)
    REFERENCES `confmng`.`tblEvent` (`eventId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `confmng`.`tblEquipment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`tblEquipment` ;

CREATE TABLE IF NOT EXISTS `confmng`.`tblEquipment` (
  `equipmentId` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`equipmentId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `confmng`.`tblLayout`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`tblLayout` ;

CREATE TABLE IF NOT EXISTS `confmng`.`tblLayout` (
  `layoutId` INT(11) NOT NULL AUTO_INCREMENT,
  `photo` MEDIUMBLOB NULL DEFAULT NULL,
  `room_roomId` INT(11) NOT NULL,
  PRIMARY KEY (`layoutId`),
  INDEX `fk_layout_room1_idx` (`room_roomId` ASC),
  CONSTRAINT `fk_layout_room1`
    FOREIGN KEY (`room_roomId`)
    REFERENCES `confmng`.`tblRoom` (`roomId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `confmng`.`tblRoomcard`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`tblRoomcard` ;

CREATE TABLE IF NOT EXISTS `confmng`.`tblRoomcard` (
  `roomcardId` INT(11) NOT NULL AUTO_INCREMENT,
  `room_number` VARCHAR(45) NOT NULL,
  `card_number` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`roomcardId`),
  UNIQUE INDEX `card_number_UNIQUE` (`card_number` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `confmng`.`tblParticipant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`tblParticipant` ;

CREATE TABLE IF NOT EXISTS `confmng`.`tblParticipant` (
  `name` VARCHAR(45) NOT NULL,
  `photo` MEDIUMBLOB NULL DEFAULT NULL,
  `registrationstatus` TINYINT(1) NOT NULL,
  `tblEvent_eventId` INT(11) NOT NULL,
  `participantId` INT(11) NOT NULL AUTO_INCREMENT,
  `tblRoomcard_roomcardId` INT(11) NULL,
  INDEX `fk_tblParticipant_tblEvent1_idx` USING BTREE (`tblEvent_eventId` ASC),
  UNIQUE INDEX `participantId_UNIQUE` (`participantId` ASC),
  PRIMARY KEY (`participantId`),
  INDEX `fk_tblParticipant_tblRoomcard1_idx` (`tblRoomcard_roomcardId` ASC),
  CONSTRAINT `fk_tblParticipant_tblEvent1`
    FOREIGN KEY (`tblEvent_eventId`)
    REFERENCES `confmng`.`tblEvent` (`eventId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblParticipant_tblRoomcard1`
    FOREIGN KEY (`tblRoomcard_roomcardId`)
    REFERENCES `confmng`.`tblRoomcard` (`roomcardId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `confmng`.`tblRentalRequest`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`tblRentalRequest` ;

CREATE TABLE IF NOT EXISTS `confmng`.`tblRentalRequest` (
  `rentalrequestId` INT(11) NOT NULL AUTO_INCREMENT,
  `request_type` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  `qauntity` INT(11) NULL DEFAULT NULL,
  `tblEvent_eventId` INT(11) NOT NULL,
  PRIMARY KEY (`rentalrequestId`),
  INDEX `fk_tblRentalRequest_tblEvent1_idx` (`tblEvent_eventId` ASC),
  CONSTRAINT `fk_tblRentalRequest_tblEvent1`
    FOREIGN KEY (`tblEvent_eventId`)
    REFERENCES `confmng`.`tblEvent` (`eventId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `confmng`.`tblGroup`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`tblGroup` ;

CREATE TABLE IF NOT EXISTS `confmng`.`tblGroup` (
  `groupId` INT(11) NOT NULL AUTO_INCREMENT,
  `groupname` VARCHAR(255) NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`groupId`),
  INDEX `fk_role_group_users1_idx` (`username` ASC),
  CONSTRAINT `fk_role_group_users1`
    FOREIGN KEY (`username`)
    REFERENCES `confmng`.`tblUser` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `confmng`.`tblRentalRequest_has_tblEquipment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `confmng`.`tblRentalRequest_has_tblEquipment` ;

CREATE TABLE IF NOT EXISTS `confmng`.`tblRentalRequest_has_tblEquipment` (
  `tblRentalRequest_rentalrequestId` INT(11) NOT NULL,
  `tblEquipment_equipmentId` INT(11) NOT NULL,
  PRIMARY KEY (`tblRentalRequest_rentalrequestId`, `tblEquipment_equipmentId`),
  INDEX `fk_tblRentalRequest_has_tblEquipment_tblEquipment1_idx` (`tblEquipment_equipmentId` ASC),
  INDEX `fk_tblRentalRequest_has_tblEquipment_tblRentalRequest1_idx` (`tblRentalRequest_rentalrequestId` ASC),
  CONSTRAINT `fk_tblRentalRequest_has_tblEquipment_tblRentalRequest1`
    FOREIGN KEY (`tblRentalRequest_rentalrequestId`)
    REFERENCES `confmng`.`tblRentalRequest` (`rentalrequestId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblRentalRequest_has_tblEquipment_tblEquipment1`
    FOREIGN KEY (`tblEquipment_equipmentId`)
    REFERENCES `confmng`.`tblEquipment` (`equipmentId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `confmng`.`tblUser`
-- -----------------------------------------------------
START TRANSACTION;
USE `confmng`;
INSERT INTO `confmng`.`tblUser` (`userId`, `username`, `password`) VALUES (NULL, 'admin', 'ungWv48Bz+pBQUDeXa4iI7ADYaOWF3qctBD/YfIAFa0=');
INSERT INTO `confmng`.`tblUser` (`userId`, `username`, `password`) VALUES (NULL, 'zahir', 'ungWv48Bz+pBQUDeXa4iI7ADYaOWF3qctBD/YfIAFa0=');
INSERT INTO `confmng`.`tblUser` (`userId`, `username`, `password`) VALUES (NULL, 'admin2', 'ungWv48Bz+pBQUDeXa4iI7ADYaOWF3qctBD/YfIAFa0=');

COMMIT;


-- -----------------------------------------------------
-- Data for table `confmng`.`tblGroup`
-- -----------------------------------------------------
START TRANSACTION;
USE `confmng`;
INSERT INTO `confmng`.`tblGroup` (`groupId`, `groupname`, `username`) VALUES (NULL, 'admin', 'admin');
INSERT INTO `confmng`.`tblGroup` (`groupId`, `groupname`, `username`) VALUES (NULL, 'admin', 'zahir');
INSERT INTO `confmng`.`tblGroup` (`groupId`, `groupname`, `username`) VALUES (NULL, 'admin', 'admin2');
INSERT INTO `confmng`.`tblGroup` (`groupId`, `groupname`, `username`) VALUES (NULL, 'authusers', 'zahir');
INSERT INTO `confmng`.`tblGroup` (`groupId`, `groupname`, `username`) VALUES (NULL, 'coordinator', 'zahir');

COMMIT;
