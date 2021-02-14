DROP SCHEMA banking;
CREATE SCHEMA banking;
USE banking;

-- Password: es password
INSERT INTO user(id, name, password, username) VALUES
('1', 'maria', '$2a$10$BE3p4yAl6JDeYhRuXmPUsOwFPn/2ZA8U7loYUoQ/.bk.OOgH10njW', 'username1'),
('2','pepe', '$2a$10$BE3p4yAl6JDeYhRuXmPUsOwFPn/2ZA8U7loYUoQ/.bk.OOgH10njW', 'username2'),
('3','marina', '$2a$10$BE3p4yAl6JDeYhRuXmPUsOwFPn/2ZA8U7loYUoQ/.bk.OOgH10njW', 'username3'),
('4','juan', '$2a$10$BE3p4yAl6JDeYhRuXmPUsOwFPn/2ZA8U7loYUoQ/.bk.OOgH10njW', 'username4');

INSERT INTO third_party (id, hashed_key, name) VALUES
(2, 12, 'Juan'),
(3, 13, 'Juana'),
(4, 14, 'Leo');

INSERT INTO roles(id, name, user_id) VALUES
('1','ACCOUNTHOLDER', '1'),
('2','ACCOUNTHOLDER', '2'),
('3','ADMIN', '3'),
('4', 'ADMIN', '4');

INSERT INTO admin (id) VALUES
('3'),
('4');

INSERT INTO account_holder(date_of_birth, mailing_city, mailing_country, mailing_street, mailing_zip, city, country,street, zip_code, id) VALUES
('1994-01-13 13:00:00', 'Sevilla', 'Spain', 'Av.America', '111', 'Madrid', 'Av.Diagonal', 'Spain', '122', '1'),
('1980-02-21 17:00:00', 'Cadiz', 'Spain', 'Calle Rosario', '112','Barcelona', 'Calle Joaquin Costa', 'Spain', '125', '2'),
('1986-04-01 16:00:00', 'Marid', 'Spain', 'Calle Callejas', '113', 'Bilbao', 'Calle Fuenteamarga', 'Spain', '124', '3'),
('2010-08-16 15:00:00', 'Barcelona', 'Spain', 'Carril Fuente', '114', 'Sevilla', 'Calle Olvido', 'Spain', '123', '4');

INSERT INTO account(id, balance_amount, balance_currency, penalty_fee_amount, penalty_fee_currency, primary_owner, secondary_owner) VALUES
(1, '1900', 'EUR', '40', 'EUR', '1', '3'),
(2, '1000', 'EUR', '40', 'EUR', '2', '1'),
(3, '900', 'EUR', '40', 'EUR', '1', '2'),
(4, '800', 'EUR', '40', 'EUR', '2', '4'),
(5, '700', 'EUR', '40', 'EUR', '3', '4'),
(6, '600', 'EUR', '40', 'EUR', '3', '1'),
(7, '500', 'EUR', '40', 'EUR', '4', '3'),
(8, '500', 'EUR', '40', 'EUR', '3', '1');

-- SecretKey es secretKey
INSERT INTO savings(interest_rate, last_interest_date, minimum_balance, secret_key, status, id) VALUES
('0.01', '2019-01-01 12:00:00', '203', '$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe', 'ACTIVE', '1'),
('0.11', '2017-01-01 12:00:00', '303', '$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe', 'ACTIVE', '2'),
('0.3', '2014-01-01 12:00:00', '400', '$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe', 'ACTIVE', '8');

-- SecretKey es secretKey
INSERT INTO checking (minimum_balance, last_maintenance_fee, monthly_maintenance_amount, monthly_maintenance_currency, secret_key, status, id) VALUES
('250','2021-02-01 00:00:00', '12', 'EUR', '$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe', 'ACTIVE', '3'),
('350','2020-02-01 00:00:00' ,'12', 'EUR', '$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe', 'ACTIVE', '4');

-- SecretKey es secretKey
INSERT INTO student_checking (secret_key, status, id) VALUES
('$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe', 'ACTIVE', 7);

INSERT INTO credit_card (credit_limit, interest_rate, last_interest_date, id) VALUES
('400', '0.11', '2019-01-01 12:00:00', 5),
('650', '0.2', '2015-01-01 12:00:00', 6);









