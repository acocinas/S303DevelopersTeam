-- -----------------------------------------------------
-- Sample Data for escape_room Schema (Themes: Hospital & Hotel)
-- -----------------------------------------------------

USE `escape_room`;

-- -----------------------------
-- Table: difficulty
-- -----------------------------
INSERT INTO difficulty (id, name, display_name, time_limit) VALUES
(1, 'EASY', 'Easy', 60),
(2, 'MEDIUM', 'Medium', 50),
(3, 'HARD', 'Hard', 45);

-- -----------------------------
-- Table: materials
-- -----------------------------
INSERT INTO materials (id, name, display_name) VALUES
(1, 'WOOD', 'Wood'),
(2, 'METAL', 'Metal'),
(3, 'PLASTIC', 'Plastic'),
(4, 'PAPER', 'Paper'),
(5, 'GLASS', 'Glass'),
(6, 'FABRIC', 'Fabric'),
(7, 'STONE', 'Stone'),
(8, 'ELECTRONIC', 'Electronic');

-- -----------------------------
-- Table: rooms
-- -----------------------------
INSERT INTO rooms (id, name, difficulty_id, price, theme, created_at) VALUES
-- Hospital rooms
(1, 'ICU', 3, 150.00, 'Hospital', NOW()),
(2, 'Emergency', 2, 120.00, 'Hospital', NOW()),
(3, 'Surgery', 2, 130.00, 'Hospital', NOW()),
-- Hotel rooms
(4, 'Suite', 3, 200.00, 'Hotel', NOW()),
(5, 'Deluxe', 2, 160.00, 'Hotel', NOW()),
(6, 'Standard', 1, 100.00, 'Hotel', NOW());

-- -----------------------------
-- Table: puzzles
-- -----------------------------
INSERT INTO puzzles (id, room_id, description, solution, solved) VALUES
-- Hospital puzzles
(1, 1, 'Find the code to unlock the ICU medicine cabinet.', 'ICU123', FALSE),
(2, 2, 'Diagnose the patient in the emergency room.', 'APPENDICITIS', FALSE),
(3, 3, 'Assemble the surgical tools in the correct order.', 'SCALPEL', FALSE),
-- Hotel puzzles
(4, 4, 'Unlock the safe in the suite.', 'SAFE2023', FALSE),
(5, 5, 'Find the hidden key in the deluxe room.', 'KEYDELUXE', FALSE),
(6, 6, 'Solve the riddle left by the previous guest.', 'SUNRISE', FALSE);

-- -----------------------------
-- Table: clues
-- -----------------------------
INSERT INTO clues (id, name, description, theme, price, revealed, visibility) VALUES
-- Hospital clues
(1, 'Doctor Note', 'A note with a code for the ICU.', 'Hospital', 10.00, FALSE, TRUE),
(2, 'X-ray', 'An X-ray showing a hidden object.', 'Hospital', 12.00, FALSE, TRUE),
(3, 'Surgical Chart', 'A chart listing surgical tools.', 'Hospital', 8.00, FALSE, TRUE),
-- Hotel clues
(4, 'Room Service Receipt', 'A receipt with a safe code.', 'Hotel', 15.00, FALSE, TRUE),
(5, 'Key Tag', 'A tag with a room number.', 'Hotel', 9.00, FALSE, TRUE),
(6, 'Guest Diary', 'A diary with a riddle.', 'Hotel', 11.00, FALSE, TRUE);

-- -----------------------------
-- Table: puzzle_clues
-- -----------------------------
INSERT INTO puzzle_clues (puzzle_id, clue_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6);

-- -----------------------------
-- Table: room_clues
-- -----------------------------
INSERT INTO room_clues (room_id, clue_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6);

-- -----------------------------
-- Table: decoration_items
-- -----------------------------
INSERT INTO decoration_items (id, name, material_id, price, is_interactive) VALUES
-- Hospital decorations
(1, 'Hospital Bed', 6, 50.00, TRUE),
(2, 'IV Stand', 3, 30.00, FALSE),
(3, 'Surgical Lamp', 8, 40.00, TRUE),
-- Hotel decorations
(4, 'Mini Bar', 1, 60.00, TRUE),
(5, 'Chandelier', 5, 80.00, FALSE),
(6, 'Do Not Disturb Sign', 4, 5.00, TRUE);

-- -----------------------------
-- Table: room_decoration_items
-- -----------------------------
INSERT INTO room_decoration_items (room_id, decoration_item_id, quantity) VALUES
(1, 1, 2),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1),
(6, 6, 2);

-- -----------------------------
-- Table: players
-- -----------------------------
INSERT INTO players (id, name, email, room_progress, created_at) VALUES
(1, 'Jordan Smith', 'jordan.smith@example.com', 1, NOW()),
(2, 'Taylor Lee', 'taylor.lee@example.com', 2, NOW()),
(3, 'Morgan Brown', 'morgan.brown@example.com', 3, NOW());

-- -----------------------------
-- Table: ticket_sales
-- -----------------------------
INSERT INTO ticket_sales (id, player_id, room_id, price, sale_date) VALUES
(1, 1, 1, 150.00, '2023-08-01 14:00:00'),
(2, 2, 4, 200.00, '2023-08-02 15:30:00'),
(3, 3, 2, 120.00, '2023-08-03 16:45:00');

-- -----------------------------
-- Table: certificates
-- -----------------------------
INSERT INTO certificates (id, player_id, room_name, certificate_date) VALUES
(1, 1, 'ICU', '2023-08-10 18:00:00'),
(2, 2, 'Suite', '2023-08-11 19:00:00'),
(3, 3, 'Emergency', '2023-08-12 20:00:00');

