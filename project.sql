-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 26, 2015 at 12:58 AM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `project`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE IF NOT EXISTS `books` (
  `isbn` char(10) NOT NULL DEFAULT '',
  `author` varchar(100) NOT NULL,
  `title` varchar(128) NOT NULL,
  `price` decimal(7,2) NOT NULL,
  `subject` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE IF NOT EXISTS `cart` (
  `userid` varchar(20) NOT NULL DEFAULT '',
  `isbn` char(10) NOT NULL DEFAULT '',
  `qty` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE IF NOT EXISTS `members` (
  `fname` varchar(20) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL,
  `city` varchar(30) NOT NULL,
  `state` varchar(20) NOT NULL,
  `zip` int(5) NOT NULL,
  `phone` varchar(12) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `userid` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(20) DEFAULT NULL,
  `creditcardtype` varchar(10) DEFAULT NULL,
  `creditcardnumber` char(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`fname`, `lname`, `address`, `city`, `state`, `zip`, `phone`, `email`, `userid`, `password`, `creditcardtype`, `creditcardnumber`) VALUES
('Ali', 'Korayem', '374 Heliopolis', 'Cairo', 'Cairo', 88739, '10283495', 'ali.korayem@gmail.com', 'alikorayem', '12341234', '', ''),
('Karim', 'Ahmed', '775 Heliopolis', 'Cairo', 'Cairo', 7785, '11427386', 'karim.asu92@gmail.com', 'karimahmed', '12341234', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `odetails`
--

CREATE TABLE IF NOT EXISTS `odetails` (
  `ono` int(5) NOT NULL DEFAULT '0',
  `isbn` char(10) NOT NULL DEFAULT '',
  `qty` int(5) NOT NULL,
  `price` decimal(7,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `userid` varchar(20) NOT NULL,
  `ono` int(5) NOT NULL DEFAULT '0',
  `received` date NOT NULL,
  `shipped` date DEFAULT NULL,
  `shipAddress` varchar(50) DEFAULT NULL,
  `shipCity` varchar(30) DEFAULT NULL,
  `shipState` varchar(20) DEFAULT NULL,
  `shipZip` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `books`
--
ALTER TABLE `books`
 ADD PRIMARY KEY (`isbn`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
 ADD PRIMARY KEY (`userid`,`isbn`), ADD KEY `isbn` (`isbn`);

--
-- Indexes for table `members`
--
ALTER TABLE `members`
 ADD PRIMARY KEY (`userid`);

--
-- Indexes for table `odetails`
--
ALTER TABLE `odetails`
 ADD PRIMARY KEY (`ono`,`isbn`), ADD KEY `isbn` (`isbn`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
 ADD PRIMARY KEY (`ono`), ADD KEY `userid` (`userid`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `members` (`userid`),
ADD CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`isbn`) REFERENCES `books` (`isbn`);

--
-- Constraints for table `odetails`
--
ALTER TABLE `odetails`
ADD CONSTRAINT `odetails_ibfk_1` FOREIGN KEY (`ono`) REFERENCES `orders` (`ono`),
ADD CONSTRAINT `odetails_ibfk_2` FOREIGN KEY (`isbn`) REFERENCES `books` (`isbn`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `members` (`userid`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
