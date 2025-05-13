-- 🔄 Eliminar tabla antigua si existe
DROP TABLE IF EXISTS difficulties;

-- 🔹 Insertar niveles de dificultad
INSERT INTO difficulty (id, name, display_name, time_limit)
SELECT 1, 'EASY', 'Easy Mode', 30
WHERE NOT EXISTS (SELECT 1 FROM difficulty WHERE id = 1);

INSERT INTO difficulty (id, name, display_name, time_limit)
SELECT 2, 'MEDIUM', 'Medium Mode', 45
WHERE NOT EXISTS (SELECT 1 FROM difficulty WHERE id = 2);

INSERT INTO difficulty (id, name, display_name, time_limit)
SELECT 3, 'HARD', 'Hard Mode', 60
WHERE NOT EXISTS (SELECT 1 FROM difficulty WHERE id = 3);

-- 🔹 Insertar jugadores
INSERT INTO players (id, name, email)
SELECT 1, 'Alice', 'alice@example.com'
WHERE NOT EXISTS (SELECT 1 FROM players WHERE id = 1);

INSERT INTO players (id, name, email)
SELECT 2, 'Bob', 'bob@example.com'
WHERE NOT EXISTS (SELECT 1 FROM players WHERE id = 2);

INSERT INTO players (id, name, email)
SELECT 3, 'Charlie', 'charlie@example.com'
WHERE NOT EXISTS (SELECT 1 FROM players WHERE id = 3);

-- 🔹 Insertar salas
INSERT INTO rooms (id, name, difficulty_id, price)
SELECT 1, 'Haunted Mansion', 1, 29.99
WHERE NOT EXISTS (SELECT 1 FROM rooms WHERE id = 1);

INSERT INTO rooms (id, name, difficulty_id, price)
SELECT 2, 'Secret Lab', 2, 34.99
WHERE NOT EXISTS (SELECT 1 FROM rooms WHERE id = 2);

INSERT INTO rooms (id, name, difficulty_id, price)
SELECT 3, 'Ancient Temple', 3, 39.99
WHERE NOT EXISTS (SELECT 1 FROM rooms WHERE id = 3);

-- 🔹 Insertar puzzles (1 por sala)
INSERT INTO puzzles (id, room_id, description, solution)
SELECT 1, 1, 'Solve the riddle on the mirror', 'Reflection'
WHERE NOT EXISTS (SELECT 1 FROM puzzles WHERE id = 1);

INSERT INTO puzzles (id, room_id, description, solution)
SELECT 2, 2, 'Decrypt the chemical formula', 'H2O'
WHERE NOT EXISTS (SELECT 1 FROM puzzles WHERE id = 2);

INSERT INTO puzzles (id, room_id, description, solution)
SELECT 3, 3, 'Align the statues with the constellations', 'Orion'
WHERE NOT EXISTS (SELECT 1 FROM puzzles WHERE id = 3);

-- 🔹 Insertar clues (1 por sala)
INSERT INTO clues (id, name, description, theme, price, revealed, visibility)
SELECT 1, 'Mirror Clue', 'Look closely into the cracked mirror', 'Gothic', 5.00, FALSE, TRUE
WHERE NOT EXISTS (SELECT 1 FROM clues WHERE id = 1);

INSERT INTO clues (id, name, description, theme, price, revealed, visibility)
SELECT 2, 'Chemical Hint', 'One compound glows in the dark', 'Science', 7.50, FALSE, TRUE
WHERE NOT EXISTS (SELECT 1 FROM clues WHERE id = 2);

INSERT INTO clues (id, name, description, theme, price, revealed, visibility)
SELECT 3, 'Temple Symbol', 'Symbols align at specific times of day', 'Ancient', 6.00, FALSE, TRUE
WHERE NOT EXISTS (SELECT 1 FROM clues WHERE id = 3);

-- 🔹 Relacionar clues con rooms (room_clues)
INSERT INTO room_clues (room_id, clue_id)
SELECT 1, 1 WHERE NOT EXISTS (SELECT 1 FROM room_clues WHERE room_id = 1 AND clue_id = 1);

INSERT INTO room_clues (room_id, clue_id)
SELECT 2, 2 WHERE NOT EXISTS (SELECT 1 FROM room_clues WHERE room_id = 2 AND clue_id = 2);

INSERT INTO room_clues (room_id, clue_id)
SELECT 3, 3 WHERE NOT EXISTS (SELECT 1 FROM room_clues WHERE room_id = 3 AND clue_id = 3);

-- 🔹 Insertar materiales base para decoración
INSERT INTO materials (id, name, display_name)
SELECT 1, 'wood', 'Wood'
WHERE NOT EXISTS (SELECT 1 FROM materials WHERE id = 1);

INSERT INTO materials (id, name, display_name)
SELECT 2, 'glass', 'Glass'
WHERE NOT EXISTS (SELECT 1 FROM materials WHERE id = 2);

INSERT INTO materials (id, name, display_name)
SELECT 3, 'stone', 'Stone'
WHERE NOT EXISTS (SELECT 1 FROM materials WHERE id = 3);

-- 🔹 Insertar decoration_items (1 por sala)
INSERT INTO decoration_items (id, name, material_id, price, is_interactive)
SELECT 1, 'Dusty Mirror', 2, 15.00, TRUE
WHERE NOT EXISTS (SELECT 1 FROM decoration_items WHERE id = 1);

INSERT INTO decoration_items (id, name, material_id, price, is_interactive)
SELECT 2, 'Broken Flask', 2, 12.50, FALSE
WHERE NOT EXISTS (SELECT 1 FROM decoration_items WHERE id = 2);

INSERT INTO decoration_items (id, name, material_id, price, is_interactive)
SELECT 3, 'Ancient Statue', 3, 20.00, TRUE
WHERE NOT EXISTS (SELECT 1 FROM decoration_items WHERE id = 3);

-- 🔹 Relacionar decoration_items con rooms (room_decoration_items)
INSERT INTO room_decoration_items (room_id, decoration_item_id, quantity)
SELECT 1, 1, 1 WHERE NOT EXISTS (SELECT 1 FROM room_decoration_items WHERE room_id = 1 AND decoration_item_id = 1);

INSERT INTO room_decoration_items (room_id, decoration_item_id, quantity)
SELECT 2, 2, 1 WHERE NOT EXISTS (SELECT 1 FROM room_decoration_items WHERE room_id = 2 AND decoration_item_id = 2);

INSERT INTO room_decoration_items (room_id, decoration_item_id, quantity)
SELECT 3, 3, 1 WHERE NOT EXISTS (SELECT 1 FROM room_decoration_items WHERE room_id = 3 AND decoration_item_id = 3);
