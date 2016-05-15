-- phpMyAdmin SQL Dump
-- version 4.4.13.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 13, 2016 at 02:44 PM
-- Server version: 5.6.30-0ubuntu0.15.10.1
-- PHP Version: 5.6.11-1ubuntu3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `TheVenue_test`
--

-- --------------------------------------------------------

--
-- Table structure for table `Author`
--

CREATE TABLE IF NOT EXISTS `Author` (
  `AuthorId` int(11) NOT NULL,
  `AuthorName` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Author`
--

INSERT INTO `Author` (`AuthorId`, `AuthorName`) VALUES
(1, 'Ryan'),
(2, 'Pankaj');

-- --------------------------------------------------------

--
-- Table structure for table `Authority`
--

CREATE TABLE IF NOT EXISTS `Authority` (
  `UserName` varchar(20) NOT NULL,
  `Authority` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Authority`
--

INSERT INTO `Authority` (`UserName`, `Authority`) VALUES
('admin', 'ROLE_ADMIN'),
('admin', 'ROLE_USER'),
('employee', 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `Category`
--

CREATE TABLE IF NOT EXISTS `Category` (
  `CategoryId` int(11) NOT NULL,
  `CategoryName` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=621 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Category`
--

INSERT INTO `Category` (`CategoryId`, `CategoryName`) VALUES
(620, 'Music');

-- --------------------------------------------------------

--
-- Table structure for table `NavBarLocation`
--

CREATE TABLE IF NOT EXISTS `NavBarLocation` (
  `NavBarLocationId` int(11) NOT NULL,
  `PageFk` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `NavBarLocation`
--

INSERT INTO `NavBarLocation` (`NavBarLocationId`, `PageFk`) VALUES
(3, NULL),
(4, NULL),
(5, NULL),
(6, NULL),
(7, NULL),
(8, NULL),
(9, NULL),
(10, NULL),
(11, NULL),
(12, NULL),
(13, NULL),
(14, NULL),
(15, NULL),
(16, NULL),
(17, NULL),
(18, NULL),
(19, NULL),
(20, NULL),
(1, 934),
(2, 935);

-- --------------------------------------------------------

--
-- Table structure for table `Page`
--

CREATE TABLE IF NOT EXISTS `Page` (
  `PageId` int(11) NOT NULL,
  `URLTitle` varchar(60) NOT NULL,
  `Title` varchar(60) NOT NULL,
  `NavName` varchar(20) NOT NULL,
  `Heading` varchar(200) DEFAULT NULL,
  `Body` longtext NOT NULL,
  `ParentPageFk` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=936 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Page`
--

INSERT INTO `Page` (`PageId`, `URLTitle`, `Title`, `NavName`, `Heading`, `Body`, `ParentPageFk`) VALUES
(934, 'This+is+title+1', 'This is title 1', 'NavName', NULL, 'This is body for the title 1', NULL),
(935, 'This+is+title+2+1', 'This is title 2', 'NavName2', NULL, 'This is body for the title 2', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `Post`
--

CREATE TABLE IF NOT EXISTS `Post` (
  `PostId` int(11) NOT NULL,
  `Title` varchar(200) NOT NULL,
  `UrlTitle` varchar(200) NOT NULL,
  `AuthorFk` int(11) NOT NULL,
  `Body` longtext NOT NULL,
  `PublishDate` date NOT NULL,
  `StartDate` datetime DEFAULT NULL,
  `EndDate` datetime DEFAULT NULL,
  `StatusFk` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=529 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Post`
--

INSERT INTO `Post` (`PostId`, `Title`, `UrlTitle`, `AuthorFk`, `Body`, `PublishDate`, `StartDate`, `EndDate`, `StatusFk`) VALUES
(528, 'And This is the Title', 'And+This+is+the+Title', 1, 'And this can be the body', '2016-05-13', '2016-05-04 00:00:00', '2016-05-04 00:00:00', 1);

-- --------------------------------------------------------

--
-- Table structure for table `PostCategory`
--

CREATE TABLE IF NOT EXISTS `PostCategory` (
  `PostFk` int(11) NOT NULL,
  `CategoryFk` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `PostCategory`
--

INSERT INTO `PostCategory` (`PostFk`, `CategoryFk`) VALUES
(528, 620);

-- --------------------------------------------------------

--
-- Table structure for table `PostTag`
--

CREATE TABLE IF NOT EXISTS `PostTag` (
  `PostFk` int(11) NOT NULL,
  `TagFk` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `PostTag`
--

INSERT INTO `PostTag` (`PostFk`, `TagFk`) VALUES
(528, 859),
(528, 860);

-- --------------------------------------------------------

--
-- Table structure for table `Status`
--

CREATE TABLE IF NOT EXISTS `Status` (
  `StatusId` int(11) NOT NULL,
  `StatusName` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Status`
--

INSERT INTO `Status` (`StatusId`, `StatusName`) VALUES
(1, 'Published'),
(2, 'Archived'),
(3, 'Draft'),
(4, 'Unapproved');

-- --------------------------------------------------------

--
-- Table structure for table `Tag`
--

CREATE TABLE IF NOT EXISTS `Tag` (
  `TagId` int(11) NOT NULL,
  `TagName` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=861 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Tag`
--

INSERT INTO `Tag` (`TagId`, `TagName`) VALUES
(860, '#Jazz'),
(859, '#Rock');

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `UserId` int(11) NOT NULL,
  `UserName` varchar(20) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Enabled` tinyint(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`UserId`, `UserName`, `Email`, `Password`, `Enabled`) VALUES
(1, 'admin', '', 'password', 1),
(2, 'employee', '', 'password', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Author`
--
ALTER TABLE `Author`
  ADD PRIMARY KEY (`AuthorId`);

--
-- Indexes for table `Authority`
--
ALTER TABLE `Authority`
  ADD KEY `UserName` (`UserName`);

--
-- Indexes for table `Category`
--
ALTER TABLE `Category`
  ADD PRIMARY KEY (`CategoryId`);

--
-- Indexes for table `NavBarLocation`
--
ALTER TABLE `NavBarLocation`
  ADD PRIMARY KEY (`NavBarLocationId`),
  ADD KEY `PageFk` (`PageFk`);

--
-- Indexes for table `Page`
--
ALTER TABLE `Page`
  ADD PRIMARY KEY (`PageId`),
  ADD UNIQUE KEY `Title` (`Title`),
  ADD UNIQUE KEY `URLTitle` (`URLTitle`),
  ADD KEY `ParentPageFk` (`ParentPageFk`),
  ADD KEY `ParentPageFk_2` (`ParentPageFk`);

--
-- Indexes for table `Post`
--
ALTER TABLE `Post`
  ADD PRIMARY KEY (`PostId`),
  ADD KEY `authorFk` (`AuthorFk`),
  ADD KEY `statusFk` (`StatusFk`);

--
-- Indexes for table `PostCategory`
--
ALTER TABLE `PostCategory`
  ADD KEY `blogFk` (`PostFk`),
  ADD KEY `CategoryFk` (`CategoryFk`);

--
-- Indexes for table `PostTag`
--
ALTER TABLE `PostTag`
  ADD KEY `blogFk` (`PostFk`),
  ADD KEY `tagFk` (`TagFk`);

--
-- Indexes for table `Status`
--
ALTER TABLE `Status`
  ADD PRIMARY KEY (`StatusId`);

--
-- Indexes for table `Tag`
--
ALTER TABLE `Tag`
  ADD PRIMARY KEY (`TagId`),
  ADD UNIQUE KEY `name` (`TagName`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`UserId`),
  ADD KEY `UserName` (`UserName`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Author`
--
ALTER TABLE `Author`
  MODIFY `AuthorId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `Category`
--
ALTER TABLE `Category`
  MODIFY `CategoryId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=621;
--
-- AUTO_INCREMENT for table `NavBarLocation`
--
ALTER TABLE `NavBarLocation`
  MODIFY `NavBarLocationId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `Page`
--
ALTER TABLE `Page`
  MODIFY `PageId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=936;
--
-- AUTO_INCREMENT for table `Post`
--
ALTER TABLE `Post`
  MODIFY `PostId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=529;
--
-- AUTO_INCREMENT for table `Status`
--
ALTER TABLE `Status`
  MODIFY `StatusId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `Tag`
--
ALTER TABLE `Tag`
  MODIFY `TagId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=861;
--
-- AUTO_INCREMENT for table `User`
--
ALTER TABLE `User`
  MODIFY `UserId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `Authority`
--
ALTER TABLE `Authority`
  ADD CONSTRAINT `Authority_ibfk_1` FOREIGN KEY (`UserName`) REFERENCES `User` (`UserName`);

--
-- Constraints for table `Post`
--
ALTER TABLE `Post`
  ADD CONSTRAINT `Post_ibfk_1` FOREIGN KEY (`AuthorFk`) REFERENCES `Author` (`AuthorId`),
  ADD CONSTRAINT `Post_ibfk_2` FOREIGN KEY (`StatusFk`) REFERENCES `Status` (`StatusId`);

--
-- Constraints for table `PostCategory`
--
ALTER TABLE `PostCategory`
  ADD CONSTRAINT `PostCategory_ibfk_1` FOREIGN KEY (`PostFk`) REFERENCES `Post` (`PostId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `PostCategory_ibfk_2` FOREIGN KEY (`CategoryFk`) REFERENCES `Category` (`CategoryId`);

--
-- Constraints for table `PostTag`
--
ALTER TABLE `PostTag`
  ADD CONSTRAINT `PostTag_ibfk_1` FOREIGN KEY (`PostFk`) REFERENCES `Post` (`PostId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `PostTag_ibfk_2` FOREIGN KEY (`TagFk`) REFERENCES `Tag` (`TagId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
