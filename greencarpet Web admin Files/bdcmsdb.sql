-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 24, 2022 at 09:59 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bdcmsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbladmin`
--

CREATE TABLE `tbladmin` (
  `ID` int(11) NOT NULL,
  `AdminName` varchar(120) DEFAULT NULL,
  `AdminuserName` varchar(20) DEFAULT NULL,
  `MobileNumber` bigint(10) DEFAULT NULL,
  `Email` varchar(120) DEFAULT NULL,
  `Password` varchar(120) DEFAULT NULL,
  `AdminRegdate` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbladmin`
--

INSERT INTO `tbladmin` (`ID`, `AdminName`, `AdminuserName`, `MobileNumber`, `Email`, `Password`, `AdminRegdate`) VALUES
(2, 'Admin test', 'admin', 1234596321, 'admin@gmail.com', '0192023a7bbd73250516f069df18b500', '2021-04-19 18:30:00');

-- --------------------------------------------------------

--
-- Table structure for table `tblbabysitter`
--

CREATE TABLE `tblbabysitter` (
  `id` int(11) NOT NULL,
  `Name` varchar(200) DEFAULT NULL,
  `Email` varchar(200) DEFAULT NULL,
  `password` varchar(111) NOT NULL,
  `MobileNo` bigint(12) DEFAULT NULL,
  `Address` mediumtext DEFAULT NULL,
  `RegDate` timestamp NULL DEFAULT current_timestamp(),
  `gender` varchar(10) NOT NULL,
  `status` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblbabysitter`
--

INSERT INTO `tblbabysitter` (`id`, `Name`, `Email`, `password`, `MobileNo`, `Address`, `RegDate`, `gender`, `status`) VALUES
(2, 'Mayanka', 'may@gmail.com', '', 7896541238, 'maaketano', '2021-10-28 05:30:33', 'male', 'Accepted'),
(3, 'dona', 'mah@gmail.com', '', 7896541236, 'u-789, goyal apartment', '2021-10-28 05:30:33', 'female', 'Accepted'),
(4, 'Jannifer Ronald', 'jannifer@gmail.com', '', 9896541236, '', '2021-10-28 05:30:33', 'female', 'Accepted'),
(5, 'gt Devi', 'komal@gmail.com', '', 7894561236, '', '2021-10-28 05:30:33', 'male', NULL),
(6, 'timo', 'gyaytri@gmail.com', '', 5698231445, '', '2021-10-28 05:30:33', 'male', NULL),
(7, 'petr', 'sudha@gmail.com', '', 7894561236, 'H-908 Gautam Vihar', '2021-10-28 05:37:42', 'male', NULL),
(9, 'eee', 'eee', '', 1111111, 'e33', '2022-10-24 18:10:02', 'male', NULL),
(10, 'ty 6yyhh', 't@gmail.com', '', 6666666888, 'tujhji98', '2022-11-12 21:07:55', 'Female', NULL),
(11, 'rty hjjj', 'rt@gmail.com', 'qwerty', 1234569870, 'meru', '2022-11-24 07:41:38', 'Female', 'Accepted');

-- --------------------------------------------------------

--
-- Table structure for table `tblchild`
--

CREATE TABLE `tblchild` (
  `id` int(111) NOT NULL,
  `name` varchar(111) NOT NULL,
  `dob` varchar(111) NOT NULL,
  `disability` varchar(111) NOT NULL,
  `gender` varchar(111) NOT NULL,
  `email` varchar(111) NOT NULL,
  `status` varchar(111) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblchild`
--

INSERT INTO `tblchild` (`id`, `name`, `dob`, `disability`, `gender`, `email`, `status`) VALUES
(1, 'kelvin', '2009', 'legs', 'male', 'mrk2@gmail.com', '1'),
(2, 'maryanne', '2010', 'hands', 'female', 'mary2@gmail.com', '1'),
(3, 'tyu', '568', 'legs', 'male', 'rag@gmail.com', 'Pending'),
(4, 'mercy', '2010', 'hearing ', 'female', 'tom@gmail.com', 'Pending'),
(5, 'mercy', '2010', 'hearing ', 'female', 'tom@gmail.com', 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `tblcontact`
--

CREATE TABLE `tblcontact` (
  `ID` int(10) NOT NULL,
  `FirstName` varchar(200) DEFAULT NULL,
  `LastName` varchar(200) DEFAULT NULL,
  `Email` varchar(200) DEFAULT NULL,
  `Phone` bigint(10) DEFAULT NULL,
  `Message` mediumtext DEFAULT NULL,
  `EnquiryDate` timestamp NOT NULL DEFAULT current_timestamp(),
  `IsRead` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblcontact`
--

INSERT INTO `tblcontact` (`ID`, `FirstName`, `LastName`, `Email`, `Phone`, `Message`, `EnquiryDate`, `IsRead`) VALUES
(1, 'Manu', 'Sharma', 'manu@gmail.com', 9879879879, 'Tell me fee of play school', '2021-11-01 04:53:55', 1),
(2, 'Anuj', 'Kumar', 'ak@gmail.com', 1234567890, 'test', '2021-11-09 17:42:29', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tblenrollment`
--

CREATE TABLE `tblenrollment` (
  `ID` int(10) NOT NULL,
  `YourName` varchar(200) DEFAULT NULL,
  `PhoneNumber` bigint(10) DEFAULT NULL,
  `Email` varchar(200) DEFAULT NULL,
  `password` varchar(40) NOT NULL,
  `Address` mediumtext DEFAULT NULL,
  `EnrollDate` timestamp NOT NULL DEFAULT current_timestamp(),
  `Remark` varchar(200) DEFAULT NULL,
  `Status` varchar(200) DEFAULT NULL,
  `RemarkDate` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblenrollment`
--

INSERT INTO `tblenrollment` (`ID`, `YourName`, `PhoneNumber`, `Email`, `password`, `Address`, `EnrollDate`, `Remark`, `Status`, `RemarkDate`) VALUES
(1, 'Rag ngwara', 8956565656, 'rag@gmail.com', 'qwerty', 'H-890, Diwan Apartment', '2021-11-01 09:23:54', '', 'Accepted', '2022-11-16 10:07:19'),
(2, 'George jn', 2356898654, 'gyan@gmail.com', '', 'K-789, Kamal Apartment', '2021-11-01 09:27:14', '', 'Accepted', '2022-10-25 09:35:09'),
(3, 'kkjhkjhjk', 5465454654, 'njk@kj', '', 'jhkjh', '2021-11-01 09:31:52', '', 'Rejected', '2021-11-03 05:19:25'),
(4, 'vivian jg', 4646446465, 'vir@gmail.com', '', 'kjhjkhkjhkjhkmmbjgjh', '2021-11-01 09:39:14', '', 'Accepted', '2022-10-17 05:26:59'),
(5, 'kjhjk', 7899879879, 'h@gmail.com', '', 'hkjhkjhkj', '2021-11-01 09:41:20', '', 'Rejected', '2021-11-03 05:19:45'),
(6, 'Tina', 9879787979, 'tina@gmail.com', '', 'hkjhkjhjkhkjhkhkh', '2021-11-01 09:42:57', '', 'Onhold', '2022-10-17 05:26:35'),
(7, 'Test', 7987977978, 'test@gmail.com', '', 'test test test', '2021-11-07 05:49:27', '', 'Accepted', '2022-10-17 16:03:27'),
(8, 'Ram ', 9879798797, 'ram@gmail.com', '', 'XDTTD', '2021-11-08 13:40:11', '', 'Accepted', '2022-10-18 05:28:10'),
(9, 'khkhkj', 5455465464, 'kjhkj@gnoa.com', '', 'bmnbm', '2021-11-08 13:44:41', '', 'Rejected', '2022-10-18 05:28:57'),
(11, 'Anat', 1234567890, 'anujk@gmail.com', '', 'A 346 ABC Street', '2021-11-09 17:41:20', '', 'Accepted', '2022-10-17 05:26:25'),
(12, 'eliud mark', 759867556, 'eliudmrk2@gmail.com', '', 'kisii ogem', '2022-11-12 19:55:36', NULL, NULL, '2022-11-12 20:03:45'),
(13, 'elud mark', 1234567890, 'eliudmrk2@gmail.com', '', 'kisi', '2022-11-24 06:23:05', NULL, NULL, '2022-11-24 06:23:38'),
(14, 'tom kin', 706895652, 'tom@gmail.com', '333333', 'meru', '2022-11-24 08:00:54', '', 'Accepted', '2022-11-24 08:05:15');

-- --------------------------------------------------------

--
-- Table structure for table `tblfinance`
--

CREATE TABLE `tblfinance` (
  `id` int(100) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Email` varchar(111) NOT NULL,
  `password` varchar(111) NOT NULL,
  `MobileNo` varchar(100) NOT NULL,
  `Address` varchar(111) DEFAULT NULL,
  `RegDate` timestamp NULL DEFAULT NULL,
  `gender` varchar(122) NOT NULL,
  `status` varchar(111) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblfinance`
--

INSERT INTO `tblfinance` (`id`, `Name`, `Email`, `password`, `MobileNo`, `Address`, `RegDate`, `gender`, `status`) VALUES
(1, 'ttt 55tt', 'eliudmrk2@gmail.com', '', '5633333333', '', '2022-11-12 21:07:17', 'Male', NULL),
(2, 'derrick osa', 'derr@gmail.com', '12345678', '1234567890', 'meru', '2022-11-24 07:36:05', 'Male', 'Accepted');

-- --------------------------------------------------------

--
-- Table structure for table `tblpage`
--

CREATE TABLE `tblpage` (
  `ID` int(10) NOT NULL,
  `PageType` varchar(200) DEFAULT NULL,
  `PageTitle` varchar(200) DEFAULT NULL,
  `PageDescription` mediumtext DEFAULT NULL,
  `Email` varchar(200) DEFAULT NULL,
  `MobileNumber` bigint(10) DEFAULT NULL,
  `UpdationDate` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblpage`
--

INSERT INTO `tblpage` (`ID`, `PageType`, `PageTitle`, `PageDescription`, `Email`, `MobileNumber`, `UpdationDate`) VALUES
(1, 'aboutus', 'About us', 'this is metropolitan\r\n', NULL, NULL, '2022-10-18 05:32:37'),
(2, 'contactus', 'Contact Us', '#890 kemu,meru, Makutano town.', 'kemu@test.com', 1234567899, '2022-10-17 05:08:03');

-- --------------------------------------------------------

--
-- Table structure for table `tblphysician`
--

CREATE TABLE `tblphysician` (
  `id` int(111) NOT NULL,
  `Name` varchar(111) NOT NULL,
  `Email` varchar(111) NOT NULL,
  `password` varchar(111) NOT NULL,
  `MobileNo` varchar(111) NOT NULL,
  `Address` varchar(111) NOT NULL,
  `RegDate` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `gender` varchar(111) NOT NULL,
  `status` varchar(111) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tblservices`
--

CREATE TABLE `tblservices` (
  `id` int(11) NOT NULL,
  `ServiceName` varchar(200) DEFAULT NULL,
  `ServiceDetail` mediumtext DEFAULT NULL,
  `CreationDate` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblservices`
--

INSERT INTO `tblservices` (`id`, `ServiceName`, `ServiceDetail`, `CreationDate`) VALUES
(1, 'Healthy Food', 'Food hkjh;l;kfvmotiunoiuotuy4igyory3biy4ityivy4riytiuer\r\nrgjhgecegiutyut', '2021-10-25 06:45:23'),
(2, 'Home Services', 'Suspendisse tincidunt velit velit sagittis vehicula. Duis posuere mollis iaculis.Lorem ipsum dolor sit amet, consectetur adipiscing', '2021-10-25 06:45:42'),
(3, 'Baby Care', 'Suspendisse tincidunt velit velit sagittis vehicula. Duis posuere mollis iaculis.Lorem ipsum dolor sit amet, consectetur adipiscing', '2021-10-25 06:45:58'),
(4, 'Special Care', 'Suspendisse tincidunt velit velit sagittis vehicula. Duis posuere mollis iaculis.Lorem ipsum dolor sit amet, consectetur adipiscing', '2021-10-25 06:46:13'),
(5, 'Nutrition', 'Suspendisse tincidunt velit velit sagittis vehicula. Duis posuere mollis iaculis.Lorem ipsum dolor sit amet, consectetur adipiscing', '2021-10-25 06:46:29'),
(6, 'Child Care', 'Suspendisse tincidunt velit velit sagittis vehicula. Duis posuere mollis iaculis.Lorem ipsum dolor sit amet, consectetur adipiscing', '2021-10-25 06:46:42'),
(7, 'Activity Skill', 'Suspendisse tincidunt velit velit sagittis vehicula. Duis posuere mollis iaculis.Lorem ipsum dolor sit amet, consectetur adipiscing', '2021-10-25 06:47:01'),
(8, 'Security', 'Suspendisse tincidunt velit velit sagittis vehicula. Duis posuere mollis iaculis.Lorem ipsum dolor sit amet, consectetur adipiscing', '2021-10-25 06:47:17'),
(11, 'leg disability exercises', 'works for 5 weeks', '2022-10-18 05:31:55');

-- --------------------------------------------------------

--
-- Table structure for table `tblsubscriber`
--

CREATE TABLE `tblsubscriber` (
  `ID` int(5) NOT NULL,
  `Email` varchar(200) DEFAULT NULL,
  `DateofSub` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblsubscriber`
--

INSERT INTO `tblsubscriber` (`ID`, `Email`, `DateofSub`) VALUES
(1, 'ani@gmail.com', '2021-07-16 07:32:33'),
(2, 'rahul@gmail.com', '2021-07-16 07:32:33'),
(6, 'j@gmail.com', '2021-08-16 15:00:59'),
(7, 'cfsdf@gmail.com', '2021-08-25 16:57:34'),
(8, 'raj@gmail.com', '2021-11-01 05:03:51'),
(9, 'test@gmail.com', '2021-11-09 17:45:41');

-- --------------------------------------------------------

--
-- Table structure for table `tblsupervisor`
--

CREATE TABLE `tblsupervisor` (
  `id` int(111) NOT NULL,
  `Name` varchar(111) NOT NULL,
  `Email` varchar(111) NOT NULL,
  `password` varchar(111) NOT NULL,
  `MobileNo` varchar(111) NOT NULL,
  `Address` varchar(111) DEFAULT NULL,
  `RegDate` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `gender` varchar(111) NOT NULL,
  `status` varchar(111) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblsupervisor`
--

INSERT INTO `tblsupervisor` (`id`, `Name`, `Email`, `password`, `MobileNo`, `Address`, `RegDate`, `gender`, `status`) VALUES
(1, 'tttt ttttyy', '5tt@gmail.com', '', '6665555555', 'tyytyft', '2022-11-12 21:16:53', 'Male', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_physician`
--

CREATE TABLE `tbl_physician` (
  `id` int(11) NOT NULL,
  `fullname` varchar(111) NOT NULL,
  `email` varchar(111) NOT NULL,
  `password` varchar(111) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `gender` varchar(100) NOT NULL,
  `specialization` varchar(100) NOT NULL,
  `regdate` timestamp NULL DEFAULT NULL,
  `status` varchar(111) DEFAULT NULL,
  `address` varchar(111) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_physician`
--

INSERT INTO `tbl_physician` (`id`, `fullname`, `email`, `password`, `phone`, `gender`, `specialization`, `regdate`, `status`, `address`) VALUES
(1, 'mark eliud', 'eliud@gmail.com', '', '112345565', 'male', 'blindness', '0000-00-00 00:00:00', '', ''),
(2, 'fgsg', 'sshs', '', 'hshshs', 'hsrh', 'hrshsh', '0000-00-00 00:00:00', 'Accepted', ''),
(3, 'ttyy james', 'erty@gmail.com', '', '9985555555', 'Female', 'teeth, leg', '2022-11-12 21:13:29', NULL, 'ogembo'),
(4, 'ttyy james', 'erty@gmail.com', '', '9985555555', 'Female', 'teeth, leg', '2022-11-12 21:15:29', NULL, 'ogembo'),
(5, 'ff rrr', 'eliudmrk2@gmail.com', 'ddr222', '555', 'Female', 'legs', '2022-11-24 06:50:48', NULL, 'drrr'),
(6, 'fgg rrr', 'eliudmrk2@gmail.com', 'qwerty', '0789654123', 'Female', 'legs', '2022-11-24 06:53:16', NULL, 'ttttg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbladmin`
--
ALTER TABLE `tbladmin`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `tblbabysitter`
--
ALTER TABLE `tblbabysitter`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tblchild`
--
ALTER TABLE `tblchild`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tblcontact`
--
ALTER TABLE `tblcontact`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `tblenrollment`
--
ALTER TABLE `tblenrollment`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `tblfinance`
--
ALTER TABLE `tblfinance`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tblpage`
--
ALTER TABLE `tblpage`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `tblservices`
--
ALTER TABLE `tblservices`
  ADD PRIMARY KEY (`id`),
  ADD KEY `PracticeArea` (`ServiceName`);

--
-- Indexes for table `tblsubscriber`
--
ALTER TABLE `tblsubscriber`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `tblsupervisor`
--
ALTER TABLE `tblsupervisor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_physician`
--
ALTER TABLE `tbl_physician`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbladmin`
--
ALTER TABLE `tbladmin`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tblbabysitter`
--
ALTER TABLE `tblbabysitter`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `tblchild`
--
ALTER TABLE `tblchild`
  MODIFY `id` int(111) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tblcontact`
--
ALTER TABLE `tblcontact`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tblenrollment`
--
ALTER TABLE `tblenrollment`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `tblfinance`
--
ALTER TABLE `tblfinance`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tblpage`
--
ALTER TABLE `tblpage`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tblservices`
--
ALTER TABLE `tblservices`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `tblsubscriber`
--
ALTER TABLE `tblsubscriber`
  MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `tblsupervisor`
--
ALTER TABLE `tblsupervisor`
  MODIFY `id` int(111) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_physician`
--
ALTER TABLE `tbl_physician`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
