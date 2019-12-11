-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 06, 2019 lúc 06:40 AM
-- Phiên bản máy phục vụ: 10.4.6-MariaDB
-- Phiên bản PHP: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `kanta`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `element`
--

CREATE TABLE `element` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `atomic_number` int(11) NOT NULL,
  `symbol` varchar(255) DEFAULT NULL,
  `metal_group` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `element_sub`
--

CREATE TABLE `element_sub` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `atomic_number` int(11) NOT NULL,
  `symbol` varchar(255) DEFAULT NULL,
  `metal_group` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `periodic_elements`
--

CREATE TABLE `periodic_elements` (
  `ELEMENT` varchar(255) NOT NULL,
  `ATOMIC_NUMBER` int(11) NOT NULL,
  `SYMBOL` varchar(255) NOT NULL,
  `METAL_GROUP` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `periodic_elements`
--

INSERT INTO `periodic_elements` (`ELEMENT`, `ATOMIC_NUMBER`, `SYMBOL`, `METAL_GROUP`) VALUES
('Hydrogen', 1, 'H', 'diatomicnonmetal'),
('Helium', 2, 'He', 'noblegas'),
('Lithium', 3, 'Li', 'alkalimetal'),
('Beryllium', 4, 'Be', 'alkalineearthmetal'),
('Boron', 5, 'B', 'metalloid'),
('Carbon', 6, 'C', 'polyatomicnonmetal'),
('Nitrogen', 7, 'N', 'diatomicnonmetal'),
('Oxygen', 8, 'O', 'diatomicnonmetal'),
('Fluorine', 9, 'F', 'diatomicnonmetal'),
('Neon', 10, 'Ne', 'noblegas'),
('Sodium', 11, 'Na', 'alkalimetal'),
('Magnesium', 12, 'Mg', 'alkalineearthmetal'),
('Aluminum', 13, 'Al', 'othermetal'),
('Silicon', 14, 'Si', 'metalloid'),
('Phosphorus', 15, 'P', 'polyatomicnonmetal'),
('Sulfur', 16, 'S', 'polyatomicnonmetal'),
('Chlorine', 17, 'Cl', 'diatomicnonmetal'),
('Argon', 18, 'Ar', 'noblegas'),
('Potassium', 19, 'K', 'alkalimetal'),
('Calcium', 20, 'Ca', 'alkalineearthmetal'),
('Scandium', 21, 'Sc', 'transitionmetal'),
('Titanium', 22, 'Ti', 'transitionmetal'),
('Vanadium', 23, 'V', 'transitionmetal'),
('Chromium', 24, 'Cr', 'transitionmetal'),
('Manganese', 25, 'Mn', 'transitionmetal'),
('Iron', 26, 'Fe', 'transitionmetal'),
('Cobalt', 27, 'Co', 'transitionmetal'),
('Nickel', 28, 'Ni', 'transitionmetal'),
('Copper', 29, 'Cu', 'transitionmetal'),
('Zinc', 30, 'Zn', 'transitionmetal'),
('Gallium', 31, 'Ga', 'othermetal'),
('Germanium', 32, 'Ge', 'metalloid'),
('Arsenic', 33, 'As', 'metalloid'),
('Selenium', 34, 'Se', 'polyatomicnonmetal'),
('Bromine', 35, 'Br', 'diatomicnonmetal'),
('Krypton', 36, 'Kr', 'noblegas'),
('Rubidium', 37, 'Rb', 'alkalimetal'),
('Strontium', 38, 'Sr', 'alkalineearthmetal'),
('Yttrium', 39, 'Y', 'transitionmetal'),
('Zirconium', 40, 'Zr', 'transitionmetal'),
('Niobium', 41, 'Nb', 'transitionmetal'),
('Molybdenum', 42, 'Mo', 'transitionmetal'),
('Technetium', 43, 'Tc', 'transitionmetal'),
('Ruthenium', 44, 'Ru', 'transitionmetal'),
('Rhodium', 45, 'Rh', 'transitionmetal'),
('Palladium', 46, 'Pd', 'transitionmetal'),
('Silver', 47, 'Ag', 'transitionmetal'),
('Cadmium', 48, 'Cd', 'transitionmetal'),
('Indium', 49, 'In', 'othermetal'),
('Tin', 50, 'Sn', 'othermetal'),
('Antimony', 51, 'Sb', 'metalloid'),
('Tellurium', 52, 'Te', 'metalloid'),
('Iodine', 53, 'I', 'diatomicnonmetal'),
('Xenon', 54, 'Xe', 'noblegas'),
('Cesium', 55, 'Cs', 'alkalimetal'),
('Barium', 56, 'Ba', 'alkalineearthmetal'),
('Lanthanum', 57, 'La', 'lanthanide'),
('Cerium', 58, 'Ce', 'lanthanide'),
('Praseodymium', 59, 'Pr', 'lanthanide'),
('Neodymium', 60, 'Nd', 'lanthanide'),
('Promethium', 61, 'Pm', 'lanthanide'),
('Samarium', 62, 'Sm', 'lanthanide'),
('Europium', 63, 'Eu', 'lanthanide'),
('Gadolinium', 64, 'Gd', 'lanthanide'),
('Terbium', 65, 'Tb', 'lanthanide'),
('Dysprosium', 66, 'Dy', 'lanthanide'),
('Holmium', 67, 'Ho', 'lanthanide'),
('Erbium', 68, 'Er', 'lanthanide'),
('Thulium', 69, 'Tm', 'lanthanide'),
('Ytterbium', 70, 'Yb', 'lanthanide'),
('Lutetium', 71, 'Lu', 'lanthanide'),
('Hafnium', 72, 'Hf', 'transitionmetal'),
('Tantalum', 73, 'Ta', 'transitionmetal'),
('Wolfram', 74, 'W', 'transitionmetal'),
('Rhenium', 75, 'Re', 'transitionmetal'),
('Osmium', 76, 'Os', 'transitionmetal'),
('Iridium', 77, 'Ir', 'transitionmetal'),
('Platinum', 78, 'Pt', 'transitionmetal'),
('Gold', 79, 'Au', 'transitionmetal'),
('Mercury', 80, 'Hg', 'transitionmetal'),
('Thallium', 81, 'Tl', 'othermetal'),
('Lead', 82, 'Pb', 'othermetal'),
('Bismuth', 83, 'Bi', 'othermetal'),
('Polonium', 84, 'Po', 'othermetal'),
('Astatine', 85, 'At', 'metalloid'),
('Radon', 86, 'Rn', 'noblegas'),
('Francium', 87, 'Fr', 'alkalimetal'),
('Radium', 88, 'Ra', 'alkalineearthmetal'),
('Actinium', 89, 'Ac', 'actinide'),
('Thorium', 90, 'Th', 'actinide'),
('Protactinium', 91, 'Pa', 'actinide'),
('Uranium', 92, 'U', 'actinide'),
('Neptunium', 93, 'Np', 'actinide'),
('Plutonium', 94, 'Pu', 'actinide'),
('Americium', 95, 'Am', 'actinide'),
('Curium', 96, 'Cm', 'actinide'),
('Berkelium', 97, 'Bk', 'actinide'),
('Californium', 98, 'Cf', 'actinide'),
('Einsteinium', 99, 'Es', 'actinide'),
('Fermium', 100, 'Fm', 'actinide'),
('Mendelevium', 101, 'Md', 'actinide'),
('Nobelium', 102, 'No', 'actinide'),
('Lawrencium', 103, 'Lr', 'actinide'),
('Hydrogen', 1, 'H', 'diatomicnonmetal'),
('Helium', 2, 'He', 'noblegas'),
('Lithium', 3, 'Li', 'alkalimetal'),
('Beryllium', 4, 'Be', 'alkalineearthmetal'),
('Boron', 5, 'B', 'metalloid'),
('Carbon', 6, 'C', 'polyatomicnonmetal'),
('Nitrogen', 7, 'N', 'diatomicnonmetal'),
('Oxygen', 8, 'O', 'diatomicnonmetal'),
('Fluorine', 9, 'F', 'diatomicnonmetal'),
('Neon', 10, 'Ne', 'noblegas'),
('Sodium', 11, 'Na', 'alkalimetal'),
('Magnesium', 12, 'Mg', 'alkalineearthmetal'),
('Aluminum', 13, 'Al', 'othermetal'),
('Silicon', 14, 'Si', 'metalloid'),
('Phosphorus', 15, 'P', 'polyatomicnonmetal'),
('Sulfur', 16, 'S', 'polyatomicnonmetal'),
('Chlorine', 17, 'Cl', 'diatomicnonmetal'),
('Argon', 18, 'Ar', 'noblegas'),
('Potassium', 19, 'K', 'alkalimetal'),
('Calcium', 20, 'Ca', 'alkalineearthmetal'),
('Scandium', 21, 'Sc', 'transitionmetal'),
('Titanium', 22, 'Ti', 'transitionmetal'),
('Vanadium', 23, 'V', 'transitionmetal'),
('Chromium', 24, 'Cr', 'transitionmetal'),
('Manganese', 25, 'Mn', 'transitionmetal'),
('Iron', 26, 'Fe', 'transitionmetal'),
('Cobalt', 27, 'Co', 'transitionmetal'),
('Nickel', 28, 'Ni', 'transitionmetal'),
('Copper', 29, 'Cu', 'transitionmetal'),
('Zinc', 30, 'Zn', 'transitionmetal'),
('Gallium', 31, 'Ga', 'othermetal'),
('Germanium', 32, 'Ge', 'metalloid'),
('Arsenic', 33, 'As', 'metalloid'),
('Selenium', 34, 'Se', 'polyatomicnonmetal'),
('Bromine', 35, 'Br', 'diatomicnonmetal'),
('Krypton', 36, 'Kr', 'noblegas'),
('Rubidium', 37, 'Rb', 'alkalimetal'),
('Strontium', 38, 'Sr', 'alkalineearthmetal'),
('Yttrium', 39, 'Y', 'transitionmetal'),
('Zirconium', 40, 'Zr', 'transitionmetal'),
('Niobium', 41, 'Nb', 'transitionmetal'),
('Molybdenum', 42, 'Mo', 'transitionmetal'),
('Technetium', 43, 'Tc', 'transitionmetal'),
('Ruthenium', 44, 'Ru', 'transitionmetal'),
('Rhodium', 45, 'Rh', 'transitionmetal'),
('Palladium', 46, 'Pd', 'transitionmetal'),
('Silver', 47, 'Ag', 'transitionmetal'),
('Cadmium', 48, 'Cd', 'transitionmetal'),
('Indium', 49, 'In', 'othermetal'),
('Tin', 50, 'Sn', 'othermetal'),
('Antimony', 51, 'Sb', 'metalloid'),
('Tellurium', 52, 'Te', 'metalloid'),
('Iodine', 53, 'I', 'diatomicnonmetal'),
('Xenon', 54, 'Xe', 'noblegas'),
('Cesium', 55, 'Cs', 'alkalimetal'),
('Barium', 56, 'Ba', 'alkalineearthmetal'),
('Lanthanum', 57, 'La', 'lanthanide'),
('Cerium', 58, 'Ce', 'lanthanide'),
('Praseodymium', 59, 'Pr', 'lanthanide'),
('Neodymium', 60, 'Nd', 'lanthanide'),
('Promethium', 61, 'Pm', 'lanthanide'),
('Samarium', 62, 'Sm', 'lanthanide'),
('Europium', 63, 'Eu', 'lanthanide'),
('Gadolinium', 64, 'Gd', 'lanthanide'),
('Terbium', 65, 'Tb', 'lanthanide'),
('Dysprosium', 66, 'Dy', 'lanthanide'),
('Holmium', 67, 'Ho', 'lanthanide'),
('Erbium', 68, 'Er', 'lanthanide'),
('Thulium', 69, 'Tm', 'lanthanide'),
('Ytterbium', 70, 'Yb', 'lanthanide'),
('Lutetium', 71, 'Lu', 'lanthanide'),
('Hafnium', 72, 'Hf', 'transitionmetal'),
('Tantalum', 73, 'Ta', 'transitionmetal'),
('Wolfram', 74, 'W', 'transitionmetal'),
('Rhenium', 75, 'Re', 'transitionmetal'),
('Osmium', 76, 'Os', 'transitionmetal'),
('Iridium', 77, 'Ir', 'transitionmetal'),
('Platinum', 78, 'Pt', 'transitionmetal'),
('Gold', 79, 'Au', 'transitionmetal'),
('Mercury', 80, 'Hg', 'transitionmetal'),
('Thallium', 81, 'Tl', 'othermetal'),
('Lead', 82, 'Pb', 'othermetal'),
('Bismuth', 83, 'Bi', 'othermetal'),
('Polonium', 84, 'Po', 'othermetal'),
('Astatine', 85, 'At', 'metalloid'),
('Radon', 86, 'Rn', 'noblegas'),
('Francium', 87, 'Fr', 'alkalimetal'),
('Radium', 88, 'Ra', 'alkalineearthmetal'),
('Actinium', 89, 'Ac', 'actinide'),
('Thorium', 90, 'Th', 'actinide'),
('Protactinium', 91, 'Pa', 'actinide'),
('Uranium', 92, 'U', 'actinide'),
('Neptunium', 93, 'Np', 'actinide'),
('Plutonium', 94, 'Pu', 'actinide'),
('Americium', 95, 'Am', 'actinide'),
('Curium', 96, 'Cm', 'actinide'),
('Berkelium', 97, 'Bk', 'actinide'),
('Californium', 98, 'Cf', 'actinide'),
('Einsteinium', 99, 'Es', 'actinide'),
('Fermium', 100, 'Fm', 'actinide'),
('Mendelevium', 101, 'Md', 'actinide'),
('Nobelium', 102, 'No', 'actinide'),
('Lawrencium', 103, 'Lr', 'actinide');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `play_evolutions`
--

CREATE TABLE `play_evolutions` (
  `id` int(11) NOT NULL,
  `hash` varchar(255) NOT NULL,
  `applied_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `apply_script` mediumtext DEFAULT NULL,
  `revert_script` mediumtext DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `last_problem` mediumtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `element`
--
ALTER TABLE `element`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `element_sub`
--
ALTER TABLE `element_sub`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `play_evolutions`
--
ALTER TABLE `play_evolutions`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `element`
--
ALTER TABLE `element`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `element_sub`
--
ALTER TABLE `element_sub`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
