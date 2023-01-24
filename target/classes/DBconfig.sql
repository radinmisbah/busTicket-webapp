CREATE DATABASE bus_ticket_system;
CREATE USER 'bus_admin'@'localhost' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON bus_ticket_system.* TO 'bus_admin'@'localhost';
commit;

ALTER DATABASE bus_ticket_system CHARACTER SET utf8 COLLATE utf8_general_ci;