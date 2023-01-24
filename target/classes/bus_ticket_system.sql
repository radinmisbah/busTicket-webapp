-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 24, 2023 at 06:21 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bus_ticket_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `bus`
--

CREATE TABLE `bus` (
  `bus_id` bigint(20) NOT NULL,
  `arrival` varchar(255) DEFAULT NULL,
  `bus_name` varchar(255) DEFAULT NULL,
  `departure` varchar(255) DEFAULT NULL,
  `departure_time` time DEFAULT NULL,
  `max_seat` int(11) DEFAULT NULL,
  `price` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `bus`
--

INSERT INTO `bus` (`bus_id`, `arrival`, `bus_name`, `departure`, `departure_time`, `max_seat`, `price`) VALUES
(3, 'Negeri Sembilan', 'Melur Interline', 'Selangor', '00:30:00', 50, 10),
(4, 'Pahang', 'KKKL', 'Kuala Lumpur', '12:07:00', 34, 0),
(5, 'Perak', 'try Bus', 'Klang', '14:30:00', 23, 9.87);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customer_id` bigint(20) NOT NULL,
  `date_of_register` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sold_ticket`
--

CREATE TABLE `sold_ticket` (
  `ticket_id` bigint(20) NOT NULL,
  `departure_date` datetime DEFAULT NULL,
  `purchased_time` datetime DEFAULT NULL,
  `seat_number` int(11) DEFAULT NULL,
  `purchased_by` bigint(20) DEFAULT NULL,
  `bus_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `date_of_register` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `date_of_register`, `email`, `first_name`, `last_name`, `mobile_number`, `password`, `role`) VALUES
(1, '2023-01-24 15:45:30', 'admintest@gmail.com', 'admin', 'test', '03920832793', '$2a$10$9cah4NDkopBvGs7FHWY6EeXC6W6zBi9wFdm8ocSQFTO2o4xFuxgLy', 'ROLE_ADMIN'),
(2, '2023-01-24 16:46:16', 'customertest@gmail.com', 'customer', 'test', '4683628392', '$2a$10$FH26gZYcmMbEp8MKJe0OUObY9NBXuCslKViWd9MxAI8QJ7.fE9hpG', 'ROLE_USER');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bus`
--
ALTER TABLE `bus`
  ADD PRIMARY KEY (`bus_id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sold_ticket`
--
ALTER TABLE `sold_ticket`
  ADD PRIMARY KEY (`ticket_id`),
  ADD KEY `FK8dfvfw3taoi7v2h4jjfnlkc1k` (`purchased_by`),
  ADD KEY `FKj877bl44nb1mf97dw025ukq9n` (`bus_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bus`
--
ALTER TABLE `bus`
  MODIFY `bus_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sold_ticket`
--
ALTER TABLE `sold_ticket`
  MODIFY `ticket_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `sold_ticket`
--
ALTER TABLE `sold_ticket`
  ADD CONSTRAINT `FK8dfvfw3taoi7v2h4jjfnlkc1k` FOREIGN KEY (`purchased_by`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKj877bl44nb1mf97dw025ukq9n` FOREIGN KEY (`bus_id`) REFERENCES `bus` (`bus_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
