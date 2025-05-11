-- -----------------------------------------------------
-- Schema escape_room
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `escape_room` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `escape_room`;

-- -----------------------------------------------------
-- Table `difficulty`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `difficulty` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `display_name` VARCHAR(50) NOT NULL,
  `time_limit` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `rooms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rooms` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `difficulty_id` INT NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `theme` VARCHAR(100) NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_rooms_difficulty_idx` (`difficulty_id` ASC),
  CONSTRAINT `fk_rooms_difficulty`
    FOREIGN KEY (`difficulty_id`)
    REFERENCES `difficulty` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `puzzles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `puzzles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `room_id` INT NOT NULL,
  `description` TEXT NOT NULL,
  `solution` TEXT NOT NULL,
  `solved` BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (`id`),
  INDEX `fk_puzzles_rooms_idx` (`room_id` ASC),
  CONSTRAINT `fk_puzzles_rooms`
    FOREIGN KEY (`room_id`)
    REFERENCES `rooms` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `clues`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clues` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `description` TEXT NOT NULL,
  `theme` VARCHAR(100) NULL,
  `price` DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  `revealed` BOOLEAN NOT NULL DEFAULT FALSE,
  `visibility` BOOLEAN NOT NULL DEFAULT TRUE,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `puzzle_clues`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `puzzle_clues` (
  `puzzle_id` INT NOT NULL,
  `clue_id` INT NOT NULL,
  PRIMARY KEY (`puzzle_id`, `clue_id`),
  INDEX `fk_puzzle_clues_clues_idx` (`clue_id` ASC),
  CONSTRAINT `fk_puzzle_clues_puzzles`
    FOREIGN KEY (`puzzle_id`)
    REFERENCES `puzzles` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_puzzle_clues_clues`
    FOREIGN KEY (`clue_id`)
    REFERENCES `clues` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `room_clues`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `room_clues` (
  `room_id` INT NOT NULL,
  `clue_id` INT NOT NULL,
  PRIMARY KEY (`room_id`, `clue_id`),
  INDEX `fk_room_clues_clues_idx` (`clue_id` ASC),
  CONSTRAINT `fk_room_clues_rooms`
    FOREIGN KEY (`room_id`)
    REFERENCES `rooms` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_room_clues_clues`
    FOREIGN KEY (`clue_id`)
    REFERENCES `clues` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `materials`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `materials` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `display_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `decoration_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `decoration_items` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `material_id` INT NOT NULL,
  `price` DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  `is_interactive` BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (`id`),
  INDEX `fk_decoration_items_materials_idx` (`material_id` ASC),
  CONSTRAINT `fk_decoration_items_materials`
    FOREIGN KEY (`material_id`)
    REFERENCES `materials` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `room_decoration_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `room_decoration_items` (
  `room_id` INT NOT NULL,
  `decoration_item_id` INT NOT NULL,
  `quantity` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`room_id`, `decoration_item_id`),
  INDEX `fk_room_decoration_items_decoration_items_idx` (`decoration_item_id` ASC),
  CONSTRAINT `fk_room_decoration_items_rooms`
    FOREIGN KEY (`room_id`)
    REFERENCES `rooms` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_room_decoration_items_decoration_items`
    FOREIGN KEY (`decoration_item_id`)
    REFERENCES `decoration_items` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `players`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `players` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `room_progress` INT NOT NULL DEFAULT 0,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ticket_sales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ticket_sales` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `player_id` INT NOT NULL,
  `room_id` INT NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `sale_date` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ticket_sales_players_idx` (`player_id` ASC),
  INDEX `fk_ticket_sales_rooms_idx` (`room_id` ASC),
  CONSTRAINT `fk_ticket_sales_players`
    FOREIGN KEY (`player_id`)
    REFERENCES `players` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ticket_sales_rooms`
    FOREIGN KEY (`room_id`)
    REFERENCES `rooms` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `certificates`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `certificates` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `player_id` INT NOT NULL,
  `room_name` VARCHAR(100) NOT NULL,
  `certificate_date` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_certificates_players_idx` (`player_id` ASC),
  CONSTRAINT `fk_certificates_players`
    FOREIGN KEY (`player_id`)
    REFERENCES `players` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB;