-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 15, 2020 at 02:20 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `prak_pbo`
--

-- --------------------------------------------------------

--
-- Table structure for table `anggota`
--

CREATE TABLE `anggota` (
  `NIS` int(20) NOT NULL,
  `Nama` varchar(20) NOT NULL,
  `Tanggal` date NOT NULL,
  `Jk` varchar(20) NOT NULL,
  `Agama` varchar(20) NOT NULL,
  `Kelas` varchar(20) NOT NULL,
  `Daftar` date NOT NULL,
  `Exp` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `anggota`
--

INSERT INTO `anggota` (`NIS`, `Nama`, `Tanggal`, `Jk`, `Agama`, `Kelas`, `Daftar`, `Exp`) VALUES
(123, 'Canni', '2000-01-29', 'Laki laki', 'Islam', 'A', '2020-04-04', '2120-04-04'),
(141, 'Simon', '1994-04-04', 'Laki laki', 'Islam', 'C', '2020-03-25', '2022-03-25'),
(190, 'John', '1996-04-04', 'Laki laki', 'Islam', 'B', '2020-04-15', '2021-04-15'),
(200, 'Price', '1989-04-04', 'Laki laki', 'Islam', 'C', '2020-02-12', '2022-02-12');

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `Kode` int(10) NOT NULL,
  `Nama` varchar(20) NOT NULL,
  `Pengarang` varchar(20) NOT NULL,
  `Penerbit` varchar(20) NOT NULL,
  `Tahun` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`Kode`, `Nama`, `Pengarang`, `Penerbit`, `Tahun`) VALUES
(1, 'The Hobbit', 'JK Tolkies', 'Tidak Tau', '2000'),
(2, 'Starwars', 'Gorge Lucas', 'Lucasfilm', '1987');

-- --------------------------------------------------------

--
-- Table structure for table `karyawan`
--

CREATE TABLE `karyawan` (
  `NIK` int(20) NOT NULL,
  `Nama` varchar(20) NOT NULL,
  `Jk` varchar(20) NOT NULL,
  `Golongan` varchar(5) NOT NULL,
  `Jabatan` varchar(20) NOT NULL,
  `Tunjangan` int(255) NOT NULL,
  `Gaji` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `karyawan`
--

INSERT INTO `karyawan` (`NIK`, `Nama`, `Jk`, `Golongan`, `Jabatan`, `Tunjangan`, `Gaji`) VALUES
(122, 'Obama', 'Laki laki', '2', 'null', 0, 0),
(124, 'Jojo', 'null', '1', 'null', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `peminjam`
--

CREATE TABLE `peminjam` (
  `No` int(20) NOT NULL,
  `NIS` int(20) NOT NULL,
  `Kode` int(10) NOT NULL,
  `Pinjam` date NOT NULL,
  `Kembali` date NOT NULL,
  `Lama` bigint(255) NOT NULL,
  `Denda` bigint(255) NOT NULL,
  `NIK` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `peminjam`
--

INSERT INTO `peminjam` (`No`, `NIS`, `Kode`, `Pinjam`, `Kembali`, `Lama`, `Denda`, `NIK`) VALUES
(1, 123, 1, '2020-04-12', '2020-04-15', 259200000, 2591999930000, 124),
(123, 123, 1, '2020-04-15', '2020-04-20', 432000000, 4319999930000, 124),
(4, 123, 1, '2020-04-15', '2020-04-17', 172800000, 1727999930000, 124),
(6, 123, 1, '2020-04-15', '2020-04-17', 172800000, 1727999930000, 124);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `anggota`
--
ALTER TABLE `anggota`
  ADD PRIMARY KEY (`NIS`);

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`Kode`);

--
-- Indexes for table `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`NIK`);

--
-- Indexes for table `peminjam`
--
ALTER TABLE `peminjam`
  ADD KEY `NIS` (`NIS`),
  ADD KEY `NIK` (`NIK`),
  ADD KEY `Kode` (`Kode`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `peminjam`
--
ALTER TABLE `peminjam`
  ADD CONSTRAINT `peminjam_ibfk_1` FOREIGN KEY (`NIS`) REFERENCES `anggota` (`NIS`),
  ADD CONSTRAINT `peminjam_ibfk_2` FOREIGN KEY (`NIK`) REFERENCES `karyawan` (`NIK`),
  ADD CONSTRAINT `peminjam_ibfk_3` FOREIGN KEY (`Kode`) REFERENCES `buku` (`Kode`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
