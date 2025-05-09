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
(3, 'HARD', 'Hard', 45),
(4, 'EXPERT', 'Expert', 40),
(5, 'NOVICE', 'Novice', 70);

-- -----------------------------
-- Table: materials
-- -----------------------------
INSERT INTO materials (id, name, display_name) VALUES
(1, 'WOOD', 'Wood'),
(2, 'METAL', 'Metal'),
(3, 'PLASTIC', 'Plastic'),
(4, 'GLASS', 'Glass'),
(5, 'FABRIC', 'Fabric'),
(6, 'CERAMIC', 'Ceramic'),
(7, 'LEATHER', 'Leather'),
(8, 'SILICONE', 'Silicone');

-- -----------------------------
-- Table: rooms
-- -----------------------------
INSERT INTO rooms (id, name, difficulty_id, price, theme, created_at) VALUES
-- Hospital themed rooms
(1, 'Abandoned Hospital Ward', 2, 110.00, 'Hospital', NOW()),
(2, 'Emergency Room', 1, 80.00, 'Hospital', NOW()),
(3, 'Surgical Theater', 4, 130.00, 'Hospital', NOW()),
(4, 'Psychiatric Ward', 3, 125.00, 'Hospital', NOW()),
(5, 'Medical Research Lab', 4, 135.00, 'Hospital', NOW()),
(6, 'Quarantine Zone', 3, 120.00, 'Hospital', NOW()),
(7, 'Pediatric Wing', 2, 95.00, 'Hospital', NOW()),
(8, 'Morgue', 4, 140.00, 'Hospital', NOW()),

-- Hotel themed rooms
(9, 'Luxury Hotel Suite', 3, 140.00, 'Hotel', NOW()),
(10, 'Haunted Hotel Lobby', 2, 120.00, 'Hotel', NOW()),
(11, 'Penthouse Suite', 4, 150.00, 'Hotel', NOW()),
(12, 'Hotel Kitchen', 2, 110.00, 'Hotel', NOW()),
(13, 'Maintenance Room', 3, 125.00, 'Hotel', NOW()),
(14, 'Abandoned 13th Floor', 4, 145.00, 'Hotel', NOW()),
(15, 'Bellhop's Service Room', 1, 85.00, 'Hotel', NOW()),
(16, 'Hotel Vault', 4, 155.00, 'Hotel', NOW());

-- -----------------------------
-- Table: puzzles
-- -----------------------------
INSERT INTO puzzles (id, room_id, description, solution, solved) VALUES
-- Hospital puzzles
(1, 1, 'Crack the code on the medicine cabinet to find the antidote.', 'RX2023', FALSE),
(2, 2, 'Diagnose the patient using the available charts.', 'INFLUENZA', FALSE),
(3, 3, 'Assemble the surgical instruments in the correct order.', 'SCALPEL', FALSE),
(4, 4, 'Decode the patient files to find the missing doctor.', 'DRHOUSE', FALSE),
(5, 5, 'Combine the correct chemical compounds to create a vaccine.', 'H2O+NaCl', FALSE),
(6, 6, 'Match the blood types to the correct patients.', 'O-NEGATIVE', FALSE),
(7, 7, 'Solve the children's puzzle to unlock the medication.', 'TEDDY', FALSE),
(8, 8, 'Determine the cause of death from the autopsy report.', 'POISON', FALSE),
(9, 1, 'Reconstruct the torn medical records.', 'PATIENT42', FALSE),
(10, 3, 'Calculate the correct anesthesia dosage.', '50MG', FALSE),
(11, 5, 'Identify the virus under the microscope.', 'COVID19', FALSE),
(12, 8, 'Find the toe tag that doesn't belong.', 'JOHNDOE', FALSE),

-- Hotel puzzles
(13, 9, 'Unlock the safe hidden behind the hotel painting.', 'GUEST123', FALSE),
(14, 10, 'Find the master key to access all hotel rooms.', 'KEYCARD', FALSE),
(15, 11, 'Solve the riddle to access the hidden penthouse safe.', 'SUNRISE', FALSE),
(16, 12, 'Prepare the correct recipe from the scattered ingredients.', 'SOUFFLÉ', FALSE),
(17, 13, 'Restore power to the elevator with the correct wire sequence.', 'RED-BLUE-GREEN', FALSE),
(18, 14, 'Discover what happened to the missing guest in room 1313.', 'MURDER', FALSE),
(19, 15, 'Sort the luggage tags to find the VIP guest.', 'GOLDMAN', FALSE),
(20, 16, 'Crack the combination to the hotel's main vault.', '19-24-37', FALSE),
(21, 9, 'Decode the mysterious phone message.', 'CHECKOUT', FALSE),
(22, 11, 'Align the stars on the ceiling to reveal a hidden message.', 'GEMINI', FALSE),
(23, 14, 'Identify which room key is actually a disguised tool.', 'ROOM237', FALSE),
(24, 16, 'Find the safety deposit box with contraband.', 'BOX42', FALSE);

-- -----------------------------
-- Table: clues
-- -----------------------------
INSERT INTO clues (id, name, description, theme, price, revealed, visibility) VALUES
-- Hospital clues
(1, 'Prescription Note', 'A doctor''s note with a suspicious code.', 'Hospital', 10.00, FALSE, TRUE),
(2, 'X-ray Image', 'An X-ray showing a hidden object.', 'Hospital', 8.00, FALSE, TRUE),
(3, 'Surgical Schedule', 'A schedule with a circled time.', 'Hospital', 18.00, FALSE, TRUE),
(4, 'Patient Bracelet', 'A hospital ID bracelet with a strange pattern.', 'Hospital', 12.00, FALSE, TRUE),
(5, 'Lab Results', 'Blood test results with highlighted anomalies.', 'Hospital', 15.00, FALSE, TRUE),
(6, 'Medical Dictionary', 'A dictionary with certain medical terms circled.', 'Hospital', 8.00, FALSE, TRUE),
(7, 'Nurse's Notes', 'Scribbled notes about patient behavior.', 'Hospital', 10.00, FALSE, TRUE),
(8, 'Medicine Vial', 'An empty vial with a partially removed label.', 'Hospital', 14.00, FALSE, TRUE),
(9, 'Doctor's ID Badge', 'A security badge with access codes.', 'Hospital', 20.00, FALSE, TRUE),
(10, 'Broken Stethoscope', 'A stethoscope with numbers scratched on it.', 'Hospital', 12.00, FALSE, TRUE),

-- Hotel clues
(11, 'Room Service Receipt', 'A receipt with a highlighted room number.', 'Hotel', 15.00, FALSE, TRUE),
(12, 'Bellhop''s List', 'A list of VIP guests and their room numbers.', 'Hotel', 12.00, FALSE, TRUE),
(13, 'Hotel Map', 'A map with a specific room circled.', 'Hotel', 8.00, FALSE, TRUE),
(14, 'Guest Complaint', 'A strange complaint about noises from an adjacent room.', 'Hotel', 10.00, FALSE, TRUE),
(15, 'Maintenance Request', 'A form detailing issues with the hotel infrastructure.', 'Hotel', 14.00, FALSE, TRUE),
(16, 'Lost & Found Log', 'A record of items left behind by guests.', 'Hotel', 9.00, FALSE, TRUE),
(17, 'Staff Roster', 'Employee schedule with suspicious time gaps.', 'Hotel', 16.00, FALSE, TRUE),
(18, 'Room Key Card', 'A key card programmed for a specific room.', 'Hotel', 20.00, FALSE, TRUE),
(19, 'Hotel Letterhead', 'A partially burned letter with visible clues.', 'Hotel', 11.00, FALSE, TRUE),
(20, 'Concierge Notes', 'Special requests from particular guests.', 'Hotel', 13.00, FALSE, TRUE);

-- -----------------------------
-- Table: puzzle_clues
-- -----------------------------
INSERT INTO puzzle_clues (puzzle_id, clue_id) VALUES
-- Hospital puzzle-clue connections
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 5),
(12, 8),

-- Hotel puzzle-clue connections
(13, 11),
(14, 12),
(15, 13),
(16, 14),
(17, 15),
(18, 16),
(19, 17),
(20, 18),
(21, 19),
(22, 20),
(23, 18),
(24, 16);

-- -----------------------------
-- Table: room_clues
-- -----------------------------
INSERT INTO room_clues (room_id, clue_id) VALUES
-- Hospital room-clue connections
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(1, 9),
(3, 10),
(5, 5),
(8, 8),

-- Hotel room-clue connections
(9, 11),
(10, 12),
(11, 13),
(12, 14),
(13, 15),
(14, 16),
(15, 17),
(16, 18),
(9, 19),
(11, 20),
(14, 16),
(16, 18);

-- -----------------------------
-- Table: decoration_items
-- -----------------------------
INSERT INTO decoration_items (id, name, material_id, price, is_interactive) VALUES
-- Hospital decorations
(1, 'Hospital Bed', 5, 45.00, TRUE),
(2, 'IV Stand', 2, 35.00, FALSE),
(3, 'Surgical Lamp', 4, 30.00, TRUE),
(4, 'Wheelchair', 2, 25.00, TRUE),
(5, 'Medical Chart', 3, 15.00, TRUE),
(6, 'Defibrillator', 3, 40.00, TRUE),
(7, 'Bio-hazard Container', 3, 20.00, FALSE),
(8, 'X-ray Viewer', 4, 28.00, TRUE),
(9, 'Specimen Jars', 4, 18.00, FALSE),
(10, 'Operating Table', 2, 50.00, TRUE),
(11, 'Medicine Cabinet', 1, 35.00, TRUE),
(12, 'Stretcher', 2, 30.00, FALSE),

-- Hotel decorations
(13, 'Hotel Bell', 2, 35.00, TRUE),
(14, 'Reception Desk', 1, 50.00, FALSE),
(15, 'Luggage Cart', 2, 25.00, TRUE),
(16, 'Mini Bar', 1, 45.00, TRUE),
(17, 'Hotel Safe', 2, 40.00, TRUE),
(18, 'Room Service Tray', 2, 15.00, FALSE),
(19, 'Hotel Phone', 3, 20.00, TRUE),
(20, 'Chandelier', 4, 60.00, FALSE),
(21, 'King Size Bed', 5, 55.00, FALSE),
(22, 'Bathroom Amenities', 8, 10.00, FALSE),
(23, 'Do Not Disturb Sign', 3, 5.00, TRUE),
(24, 'Housekeeping Cart', 3, 30.00, TRUE);

-- -----------------------------
-- Table: room_decoration_items
-- -----------------------------
INSERT INTO room_decoration_items (room_id, decoration_item_id, quantity) VALUES
-- Hospital room decorations
(1, 1, 2),
(1, 2, 1),
(1, 5, 3),
(2, 4, 1),
(2, 6, 1),
(2, 11, 1),
(3, 3, 2),
(3, 10, 1),
(3, 9, 4),
(4, 1, 3),
(4, 5, 2),
(4, 7, 1),
(5, 3, 1),
(5, 8, 1),
(5, 9, 6),
(6, 7, 3),
(6, 12, 2),
(6, 5, 4),
(7, 1, 2),
(7, 2, 2),
(7, 5, 1),
(8, 9, 8),
(8, 10, 1),
(8, 3, 1),

-- Hotel room decorations
(9, 16, 1),
(9, 17, 1),
(9, 21, 1),
(9, 22, 2),
(10, 13, 1),
(10, 14, 1),
(10, 20, 1),
(11, 16, 1),
(11, 17, 1),
(11, 21, 1),
(11, 20, 2),
(12, 18, 3),
(12, 14, 1),
(12, 19, 1),
(13, 15, 1),
(13, 23, 2),
(13, 24, 1),
(14, 21, 1),
(14, 19, 1),
(14, 23, 1),
(15, 15, 2),
(15, 23, 3),
(15, 24, 1),
(16, 13, 1),
(16, 17, 3),
(16, 14, 1);

-- -----------------------------
-- Table: players
-- -----------------------------
INSERT INTO players (id, name, email, room_progress, created_at) VALUES
(1, 'Casey Morgan', 'casey.morgan@example.com', 2, NOW()),
(2, 'Riley Adams', 'riley.adams@example.com', 1, NOW()),
(3, 'Jordan Blake', 'jordan.blake@example.com', 3, NOW()),
(4, 'Taylor Quinn', 'taylor.quinn@example.com', 0, NOW()),
(5, 'Alexis Lane', 'alexis.lane@example.com', 4, NOW()),
(6, 'Robin Shields', 'robin.shields@example.com', 5, NOW()),
(7, 'Dakota Reeves', 'dakota.reeves@example.com', 3, NOW()),
(8, 'Cameron Wells', 'cameron.wells@example.com', 2, NOW()),
(9, 'Avery Knight', 'avery.knight@example.com', 1, NOW()),
(10, 'Harper Stone', 'harper.stone@example.com', 4, NOW());

-- -----------------------------
-- Table: ticket_sales
-- -----------------------------
INSERT INTO ticket_sales (id, player_id, room_id, price, sale_date) VALUES
-- Hospital room ticket sales
(1, 1, 1, 110.00, '2023-08-01 14:00:00'),
(2, 2, 2, 80.00, '2023-08-02 15:30:00'),
(3, 3, 3, 130.00, '2023-08-03 16:45:00'),
(4, 4, 4, 125.00, '2023-08-04 13:20:00'),
(5, 5, 5, 135.00, '2023-08-05 17:10:00'),
(6, 6, 6, 120.00, '2023-08-06 11:30:00'),
(7, 7, 7, 95.00, '2023-08-07 14:15:00'),
(8, 8, 8, 140.00, '2023-08-08 18:00:00'),
(9, 9, 1, 110.00, '2023-08-09 12:45:00'),
(10, 10, 2, 80.00, '2023-08-10 16:20:00'),

-- Hotel room ticket sales
(11, 1, 9, 140.00, '2023-08-11 13:15:00'),
(12, 2, 10, 120.00, '2023-08-12 15:00:00'),
(13, 3, 11, 150.00, '2023-08-13 17:30:00'),
(14, 4, 12, 110.00, '2023-08-14 19:20:00'),
(15, 5, 13, 125.00, '2023-08-15 14:45:00'),
(16, 6, 14, 145.00, '2023-08-16 16:10:00'),
(17, 7, 15, 85.00, '2023-08-17 18:30:00'),
(18, 8, 16, 155.00, '2023-08-18 13:00:00'),
(19, 9, 9, 140.00, '2023-08-19 15:45:00'),
(20, 10, 10, 120.00, '2023-08-20 17:15:00');

-- -----------------------------
-- Table: certificates
-- -----------------------------
INSERT INTO certificates (id, player_id, room_name, certificate_date) VALUES
-- Hospital room certificates
(1, 1, 'Abandoned Hospital Ward', '2023-08-10 18:00:00'),
(2, 2, 'Emergency Room', '2023-08-11 19:00:00'),
(3, 3, 'Surgical Theater', '2023-08-12 20:00:00'),
(4, 4, 'Psychiatric Ward', '2023-08-13 21:00:00'),
(5, 5, 'Medical Research Lab', '2023-08-14 22:00:00'),
(6, 6, 'Quarantine Zone', '2023-08-15 17:30:00'),
(7, 7, 'Pediatric Wing', '2023-08-16 19:15:00'),
(8, 8, 'Morgue', '2023-08-17 20:45:00'),

-- Hotel room certificates
(9, 1, 'Luxury Hotel Suite', '2023-08-18 18:30:00'),
(10, 2, 'Haunted Hotel Lobby', '2023-08-19 19:45:00'),
(11, 3, 'Penthouse Suite', '2023-08-20 20:15:00'),
(12, 4, 'Hotel Kitchen', '2023-08-21 21:30:00'),
(13, 5, 'Maintenance Room', '2023-08-22 19:00:00'),
(14, 6, 'Abandoned 13th Floor', '2023-08-23 20:30:00'),
(15, 7, 'Bellhop's Service Room', '2023-08-24 18:45:00'),
(16, 8, 'Hotel Vault', '2023-08-25 21:15:00');

-- -----------------------------
-- Scenario Examples - Use these to expand your testing
-- -----------------------------

-- Hospital Scenario 1: Medical Mysteries
-- An escape room focused on diagnosing strange patients
-- Recommended puzzle sequence: 2, 5, 11, 3
-- Key clues: 2, 5, 3, 10
-- Themed decorations: 2, 5, 8, 10

-- Hospital Scenario 2: Quarantine Breach
-- Players must contain a virus outbreak before it spreads
-- Recommended puzzle sequence: 6, 5, 11, 4
-- Key clues: 6, 5, 7, 9
-- Themed decorations: 7, 6, 12, 5

-- Hospital Scenario 3: Night Shift Horror
-- Something strange is happening in the morgue
-- Recommended puzzle sequence: 8, 12, 4, 1
-- Key clues: 8, 1, 9, 10
-- Themed decorations: 9, 10, 3, 1

-- Hotel Scenario 1: The Missing Heiress
-- A wealthy guest has disappeared from the luxury suite
-- Recommended puzzle sequence: 13, 21, 15, 22
-- Key clues: 11, 19, 13, 20
-- Themed decorations: 16, 17, 21, 19

-- Hotel Scenario 2: Kitchen Catastrophe
-- Something deadly has been added to the hotel menu
-- Recommended puzzle sequence: 16, 14, 24, 17
-- Key clues: 14, 12, 16, 15
-- Themed decorations: 18, 14, 13, 24

-- Hotel Scenario 3: The 13th Floor
-- Explore a floor that shouldn't exist
-- Recommended puzzle sequence: 18, 23, 22, 20
-- Key clues: 16, 18, 19, 20
-- Themed decorations: 21, 19, 23, 17

-- -----------------------------
-- Tailor your data below
-- -----------------------------
-- Add or modify INSERT statements as needed for your use case 