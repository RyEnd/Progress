-- phpMyAdmin SQL Dump
-- version 4.4.13.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 13, 2016 at 02:43 PM
-- Server version: 5.6.30-0ubuntu0.15.10.1
-- PHP Version: 5.6.11-1ubuntu3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `TheVenue`
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Category`
--

INSERT INTO `Category` (`CategoryId`, `CategoryName`) VALUES
(1, 'New and Shiney'),
(2, 'Best Category');

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
(1, 0),
(4, 1),
(5, 2),
(6, 5),
(7, 35),
(3, 55),
(2, 56);

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
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Page`
--

INSERT INTO `Page` (`PageId`, `URLTitle`, `Title`, `NavName`, `Heading`, `Body`, `ParentPageFk`) VALUES
(-2, ' ', ' ', ' ', NULL, ' ', NULL),
(1, 'amet', 'Tiny MCE Introduction', 'TinyMCE', '', '<p style="text-align: center;"><iframe class="embed-responsive-item" src="https://www.youtube.com/embed/QZ_caGrH3k0?rel=0&amp;hd=0" width="640" height="385" frameborder="0" allowfullscreen="allowfullscreen"> </iframe>&nbsp;</p>\n<p>&nbsp;</p>\n<p style="text-align: center;">&nbsp;</p>', NULL),
(2, 'in', 'in', 'in', 'Nunc nisl.', 'Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh.\r\n\r\nIn quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.\r\n\r\nMaecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui.\r\n\r\nMaecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti.', NULL),
(5, 'adipiscing+lorem', 'adipiscing lorem', 'adipiscing lorem', 'Donec posuere metus vitae ipsum.', 'In sagittis dui vel nisl. Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.\r\n\r\nSuspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst.\r\n\r\nMaecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.\r\n\r\nCurabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.\r\n\r\nInteger tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.', NULL),
(13, 'pede+ac+diam', 'pede ac diam', 'pede ac diam', 'Mauris lacinia sapien quis libero. Nullam sit amet turpis elementum ligula vehicula consequat.', 'Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit.\r\n\r\nProin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.\r\n\r\nAenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum.\r\n\r\nCurabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est.', 2),
(15, 'diam+in', 'diam in', 'diam in', 'In congue. Etiam justo.', 'Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti.\r\n\r\nNullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris.\r\n\r\nMorbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.\r\n\r\nFusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem.', 2),
(34, 'orci+luctus', 'orci luctus', 'orci luctus', 'In hac habitasse platea dictumst. Maecenas ut massa quis augue luctus tincidunt.', 'Fusce consequat. Nulla nisl. Nunc nisl.\r\n\r\nDuis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum.', 2),
(35, 'potenti', 'potenti', 'potenti', 'Integer non velit. Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue.', 'Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti.\r\n\r\nNullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris.\r\n\r\nMorbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.', NULL),
(36, 'magnis', 'magnis', 'magnis', 'Vivamus vel nulla eget eros elementum pellentesque. Quisque porta volutpat erat.', 'Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti.\r\n\r\nNullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris.\r\n\r\nMorbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.', 13),
(39, 'luctus', 'luctus', 'luctus', 'Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.', 'Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem.\r\n\r\nFusce consequat. Nulla nisl. Nunc nisl.\r\n\r\nDuis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum.', 13),
(55, 'A+page+about+a+man', 'A page about a man', 'Old Man', 'This is a page about a man!', '<table style="height: 303px; width: 1062px;">\n<tbody>\n<tr style="height: 93.6736px;">\n<td style="width: 628.278px; height: 93.6736px; text-align: left;">\n<h3 style="text-align: left;">This is a simple page about a man who is old. As you can see, his picture is here to the right, and he is quite old. His hair has long since receded back beyond the middle of his skull, and any hint of color it once possessed has fled. To avoid&nbsp;the lack of color found both on top of his head and in his translucent skin, he has chosen a striking color for his tie, to accent his bland, tired, natural appearance.</h3>\n</td>\n<td style="width: 432.722px; height: 93.6736px;">\n<h3><img src="http://motherhoodlater.com/wp-content/uploads/2015/06/Artie-McFerrin-2013.jpg" alt="" width="302" height="453" /></h3>\n</td>\n</tr>\n</tbody>\n</table>\n<h3>Imagine with me, if you will, what kind of aspirations might a man such as this hold? Have his dreams become distant memories, so far and long out of reach that he cannot even recall if he once thought them possible at all? Or maybe he only dreams of peace in death, relieved from the struggles of his daily life.</h3>', NULL),
(56, 'Shinest+Page', 'Shinest Page', 'Diamonds', 'No wine, no shine', '<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<img style="display: block; margin-left: auto; margin-right: auto;" src="http://goldworkshmb.com/wp-content/uploads/2014/08/iStock_000006489660XLarge.jpg" alt="" width="555" height="222" /></p>\n<p style="text-align: center;">Damn, these are some shiney diamonds.</p>\n<p style="text-align: center;">&nbsp;</p>\n<p style="text-align: center;">You should check out our sweet sweet blog&nbsp;up on the nav bar ^^^^^^^^</p>', NULL),
(65, 'Child+of+No+Man', 'Child of No Man', 'Parent', '', '', NULL),
(66, 'Child1', 'Child1', 'Child', '', '', 65),
(67, 'Orphaned+Child', 'Orphaned Child', 'Child2', '', '', 65);

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Post`
--

INSERT INTO `Post` (`PostId`, `Title`, `UrlTitle`, `AuthorFk`, `Body`, `PublishDate`, `StartDate`, `EndDate`, `StatusFk`) VALUES
(1, 'This is a blog post.', 'This+is+a+blog+post', 2, 'This is a blog post''s body.', '2016-04-01', NULL, NULL, 1),
(3, 'This too, is a blog', 'This+too+is+a+blog', 2, 'so many blogs, so little time.', '2016-04-18', NULL, NULL, 1),
(4, 'More Blogs?', 'More+Blogs', 1, 'Would you like some more blogs?\r\nButcher echo park pour-over try-hard pabst. Fixie cronut raw denim chia, kombucha kogi vice next level brunch lumbersexual austin pickled taxidermy. Four loko tofu 90''s narwhal everyday carry, tousled pinterest helvetica mlkshk portland post-ironic polaroid. Cardigan tousled distillery, blue bottle migas venmo biodiesel man bun mustache heirloom freegan meh marfa sustainable. Kale chips lumbersexual brunch franzen, vice organic single-origin coffee try-hard quinoa tousled artisan butcher chillwave locavore umami. Pug pop-up bushwick four loko, paleo you probably haven''t heard of them disrupt schlitz pickled neutra wolf raw denim hoodie. Disrupt affogato blue bottle next level helvetica meh.', '2016-04-10', NULL, NULL, 1),
(5, 'TinyMCE', 'TinyMCE', 1, '<p>&nbsp;</p>\r\n<p style="text-align: center; font-size: 15px;"><img src="images/glyph-tinymce@2x.png" alt="TinyMCE" width="110" height="97" /></p>\r\n<p style="text-align: center; color: #7e7e7e; font-size: 15px; font-family: avenir; font-weight: 200;">TinyMCE is a platform independent web-based JavaScript HTML WYSIWYG<br /> editor control released as open source under LGPL.</p>\r\n<p style="text-align: center; color: #868686; font-size: 15px; font-family: avenir; font-weight: 200;"><em>TinyMCE enables you to convert HTML textarea fields or other HTML elements to editor instances.</em></p>\r\n<p>&nbsp;</p>', '2016-04-30', NULL, NULL, 2),
(6, 'This Is How We Do', 'This+Is+How+We+Do', 1, '<p>&nbsp;</p>\r\n<p style="text-align: center; font-size: 15px;"><img src="https://upload.wikimedia.org/wikipedia/commons/3/33/RedRocksAMP.png" alt="" width="542" height="360" /></p>\r\n<p style="text-align: center; color: #7e7e7e; font-size: 15px; font-family: avenir; font-weight: 200;">This is probably what The Venue would looke like.</p>', '2016-04-30', NULL, NULL, 2),
(7, 'Newest Shiniest Blogs', 'Newest+Shiniest+Blogs', 2, '', '2016-05-03', NULL, NULL, 4),
(8, 'This Should By Ryan''s Blog', 'This+Should+By+Ryan''s+Blog', 1, '<p><img src="http://whatyouth.com/wp-content/uploads/2015/06/nirvana-blurry-1400x943.jpg" alt="" width="1101" height="742" /></p>', '2016-05-04', NULL, NULL, 1),
(9, 'This should be Pankaj''s Blog Post', 'This+should+be+Pankaj''s+Blog+Post', 2, '<p><img src="http://wallpaperswa.com/thumbnails/detail/20120421/music%20front%20cover%20music%20bands%201680x1050%20wallpaper_wallpaperswa.com_83.jpg" alt="bandssss" width="801" height="502" /></p>', '2016-05-04', NULL, NULL, 4),
(10, 'asdfadf', 'asdfadf', 1, '<p>adfasfd</p>', '2016-05-04', NULL, NULL, 1);

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
(7, 1),
(9, 2),
(1, 2),
(3, 1),
(4, 2),
(5, 1),
(6, 1),
(10, 1),
(8, 2);

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
(1, 1),
(1, 2),
(1, 3),
(7, 6),
(7, 7),
(1, 1),
(3, 1),
(4, 1),
(5, 2),
(7, 2),
(9, 6),
(9, 7),
(10, 8),
(10, 9),
(10, 10),
(10, 11),
(10, 12);

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Tag`
--

INSERT INTO `Tag` (`TagId`, `TagName`) VALUES
(5, 'comedy'),
(2, 'EDM'),
(3, 'folk'),
(8, 'hi'),
(10, 'is'),
(4, 'rap'),
(1, 'rock'),
(11, 'some'),
(6, 'tag'),
(7, 'tag2'),
(12, 'tags'),
(9, 'this');

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
  MODIFY `CategoryId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `NavBarLocation`
--
ALTER TABLE `NavBarLocation`
  MODIFY `NavBarLocationId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `Page`
--
ALTER TABLE `Page`
  MODIFY `PageId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=68;
--
-- AUTO_INCREMENT for table `Post`
--
ALTER TABLE `Post`
  MODIFY `PostId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `Status`
--
ALTER TABLE `Status`
  MODIFY `StatusId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `Tag`
--
ALTER TABLE `Tag`
  MODIFY `TagId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
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
