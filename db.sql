CREATE DATABASE hotel_db;
USE hotel_db;

CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20)
);

CREATE TABLE rooms (
    room_number INT PRIMARY KEY,
    type VARCHAR(50),
    price DECIMAL(10,2),
    available BOOLEAN
);

CREATE TABLE reservations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    room_number INT,
    check_in DATE,
    check_out DATE,
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (room_number) REFERENCES rooms(room_number)
);

-- Insert sample rooms
INSERT INTO rooms VALUES (101, 'Single', 1500, TRUE);
INSERT INTO rooms VALUES (102, 'Double', 2500, TRUE);
INSERT INTO rooms VALUES (103, 'Suite', 5000, TRUE);
