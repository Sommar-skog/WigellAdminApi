
--Address
INSERT INTO address(street, postal_code, city) VALUES ('Storgatan 1', '123 45', 'Stockholm');
INSERT INTO address(street, postal_code, city) VALUES ('Björkvägen 7', '543 21', 'Göteborg');
INSERT INTO address(street, postal_code, city) VALUES ('Ekbacken 3', '111 22', 'Malmö');
INSERT INTO address(street, postal_code, city) VALUES ('Lindhagsvägen 10', '333 33', 'Uppsala');
INSERT INTO address(street, postal_code, city) VALUES ('Solrosgatan 15', '987 65', 'Örebro');

--Member
INSERT INTO member(first_name, last_name, address_id, email, phone, date_of_birth) VALUES ('Anna', 'Andersson',1, 'anna@example.com','0701234567','1990-01-01');
INSERT INTO member(first_name, last_name, address_id, email, phone, date_of_birth) VALUES ('Björn', 'Berg', 2, 'bjorn@example.com', '0702345678', '1985-05-15');
INSERT INTO member(first_name, last_name, address_id, email, phone, date_of_birth) VALUES ('Clara', 'Carlsson', 1, 'clara@example.com', NULL, '1993-03-20');
INSERT INTO member(first_name, last_name, address_id, email, phone, date_of_birth) VALUES ('David', 'Dahl', 3, 'david@example.com', '0703456789', '1980-12-10');
INSERT INTO member(first_name, last_name, address_id, email, phone, date_of_birth) VALUES ('Eva', 'Ek', 4, 'eva@example.com', '0704567891', '1989-07-06');



