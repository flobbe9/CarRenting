-- Active: 1667982561949@@127.0.0.1@3306@carrenting

INSERT INTO car(id, brand, model, color, fuel_type, specifiaction_id, is_available) VALUES(1, "Volkswagen", "Caddy", "RED", "BENZINE", 1, true);
INSERT INTO car(id, brand, model, color, fuel_type, specifiaction_id, is_available) VALUES(2, "Volkswagen", "Golf VIII", "YELLOW", "DIESEL", 2, true);
INSERT INTO car(id, brand, model, color, fuel_type, specifiaction_id, is_available) VALUES(3, "Audi", "A1 Sportback", "SILVER", "BENZINE", 3, true);
INSERT INTO car(id, brand, model, color, fuel_type, specifiaction_id, is_available) VALUES(4, "Tesla", "Model X", "WHITE", "ELECTRIC", 4, true);
INSERT INTO car(id, brand, model, color, fuel_type, specifiaction_id, is_available) VALUES(5, "Lamborghini", "Aventador LP 780-4 Roadster", "BLUE", "BENZINE", 5, true);


INSERT INTO specification(id, num_seats, num_doors, horse_power, speed_max, weight_unladen, weight_max, car_id) VALUES(1, 5, 5, 122, 186, 1608, 2250, 1);
INSERT INTO specification(id, num_seats, num_doors, horse_power, speed_max, weight_unladen, weight_max, car_id) VALUES(2, 5, 5, 245, 250, 1388, 1950, 2);
INSERT INTO specification(id, num_seats, num_doors, horse_power, speed_max, weight_unladen, weight_max, car_id) VALUES(3, 5, 5, 150, 217, 1170, 1710, 3);
INSERT INTO specification(id, num_seats, num_doors, horse_power, speed_max, weight_unladen, weight_max, car_id) VALUES(4, 7, 5, 611, 250, 2562, 3120, 4);
INSERT INTO specification(id, num_seats, num_doors, horse_power, speed_max, weight_unladen, weight_max, car_id) VALUES(5, 2, 2, 780, 355, 1600, 2100, 5);