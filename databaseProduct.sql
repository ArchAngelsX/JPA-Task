-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               5.1.72-community - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for item
CREATE DATABASE IF NOT EXISTS `item` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `item`;

-- Dumping structure for table item.order_item_tbl
CREATE TABLE IF NOT EXISTS `order_item_tbl` (
  `order_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_ordered` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `ordered_by` int(11) NOT NULL,
  PRIMARY KEY (`order_item_id`),
  KEY `pemesan` (`ordered_by`),
  KEY `product_ordered` (`product_ordered`),
  CONSTRAINT `barang yang dipesan` FOREIGN KEY (`product_ordered`) REFERENCES `product_tbl` (`product_id`),
  CONSTRAINT `pemesan` FOREIGN KEY (`ordered_by`) REFERENCES `order_tbl` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

-- Dumping data for table item.order_item_tbl: ~15 rows (approximately)
/*!40000 ALTER TABLE `order_item_tbl` DISABLE KEYS */;
INSERT INTO `order_item_tbl` (`order_item_id`, `product_ordered`, `quantity`, `ordered_by`) VALUES
	(1, 1, 1, 1),
	(2, 18, 2, 1),
	(3, 5, 7, 3),
	(4, 25, 7, 3),
	(5, 12, 10, 3),
	(6, 16, 10, 3),
	(7, 17, 2, 2),
	(8, 21, 1, 2),
	(9, 25, 8, 3),
	(10, 9, 1, 4),
	(11, 24, 2, 4),
	(12, 10, 1, 4),
	(13, 15, 1, 5),
	(14, 2, 1, 5),
	(15, 3, 2, 5);
/*!40000 ALTER TABLE `order_item_tbl` ENABLE KEYS */;

-- Dumping structure for table item.order_tbl
CREATE TABLE IF NOT EXISTS `order_tbl` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_name` varchar(30) NOT NULL,
  `order_date` date NOT NULL,
  `order_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `order_city` varchar(30) NOT NULL,
  `order_notes` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table item.order_tbl: ~6 rows (approximately)
/*!40000 ALTER TABLE `order_tbl` DISABLE KEYS */;
INSERT INTO `order_tbl` (`order_id`, `order_name`, `order_date`, `order_time`, `order_city`, `order_notes`) VALUES
	(1, 'Kinar', '2023-01-10', '0000-00-00 00:00:00', 'Tangerang Selatan', 'Self Reward'),
	(2, 'Jamal', '2023-01-18', '0000-00-00 00:00:00', 'Jakarta Selatan', NULL),
	(3, 'Minda', '2023-01-28', '0000-00-00 00:00:00', 'Jakarta Selatan', 'Hadiah untuk teman2ku'),
	(4, 'Harto', '2023-02-17', '0000-00-00 00:00:00', 'Bandung', 'Hadiah Bapak'),
	(5, 'Rizal', '2023-02-22', '2016-05-28 07:00:00', 'Bogor', 'Tolong tulisin "Here\'s Your Reward, Thank You"'),
	(6, 'Lina', '2023-01-30', '0000-00-00 00:00:00', 'Jakarta Pusat', 'Bonus Turun');
/*!40000 ALTER TABLE `order_tbl` ENABLE KEYS */;

-- Dumping structure for table item.product_tbl
CREATE TABLE IF NOT EXISTS `product_tbl` (
  `product_id` int(7) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(20) NOT NULL,
  `product_category` varchar(20) NOT NULL,
  `product_brand` varchar(20) NOT NULL,
  `product_description` varchar(100) DEFAULT NULL,
  `product_price` bigint(13) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

-- Dumping data for table item.product_tbl: ~28 rows (approximately)
/*!40000 ALTER TABLE `product_tbl` DISABLE KEYS */;
INSERT INTO `product_tbl` (`product_id`, `product_name`, `product_category`, `product_brand`, `product_description`, `product_price`) VALUES
	(1, 'HP Galaxy A11', 'Elektronik', 'Samsung', 'Size 256 GB Warna Putih', 7000000),
	(2, 'HP Galaxy A7', 'Elektronik', 'Samsung', 'Size 256 GB warna hitam', 6900000),
	(3, 'Indomie', 'Makanan', 'Indofood', 'Mie Goreng Rasa Kari Ayam', 3000),
	(4, 'Chitato', 'Makanan', 'Indofood', 'Sapi Panggang', 6000),
	(5, 'Chitato', 'Makanan', 'Indofood', 'Sapi Panggang Party Pack', 8000),
	(6, 'Ichi Ocha', 'Minuman', 'Indofood', '350 ml Original', 8000),
	(7, 'Teh Pucuk Harum', 'Minuman', 'Mayora', '500 ml original', 6000),
	(8, 'HP Iphone 13', 'Elektronik', 'Apple', '256 GB warna pink', 14599000),
	(9, 'Smart TV', 'Elektronik', 'LG', '53" LED warna silver', 9699000),
	(10, 'Blender', 'Elektronik', 'Samsung', '250 Watt warna putih', 950000),
	(11, 'Mie Sedap', 'Makanan', 'Wings', 'Mie Goreng Original', 3000),
	(12, 'Kopiko 78', 'Minuman', 'Mayora', '250 ml', 7100),
	(13, 'Naruto', 'Buku', 'Elex Media', 'Nomor 25', 30000),
	(14, 'Naruto', 'Buku', 'Elex Media', 'Paket Nomor 1-5', 120000),
	(15, 'Harry Potter', 'Buku', 'Gramedia', 'and The Goblet of Fire bahasa Indonesia', 250000),
	(16, 'Coca Cola', 'Minuman', 'CCA', '1,5 Liter', 13900),
	(17, 'Fanta', 'Minuman', 'CCA', '1,5 Liter rasa jeruk', 14900),
	(18, 'Jaket Jeans', 'Pakaian', 'Levi\'s', 'Denim Blue', 499000),
	(19, 'Jaket Jeans', 'Pakaian', 'Uniqlo', 'Denim Blue', 599000),
	(20, 'Celana Jeans', 'Pakaian', 'Levi\'s', 'Denim Blue Straight', 1299000),
	(21, 'Celana Jeans', 'Pakaian', 'MAX', 'Denim Black Skinny', 399000),
	(22, 'Majalah TEMPO', 'Buku', 'Tempo', 'Edisi 23 Februari 2023', 49900),
	(23, 'Polo Shirt', 'Pakaian', 'Giordano', 'Navy Blue XL', 399000),
	(24, 'Bohlam', 'Elektronik', 'Krisbow', 'isi 4 bohlam 10 Watt warna putih', 159000),
	(25, 'Chitato Lite', 'Makanan', 'Indofood', 'Rumput Laut', 13500),
	(27, 'Apple vision goggle', 'Elektronik', 'Apple', 'Warna putih', 52000000),
	(28, 'Nintendo Switch', 'Elektronik', 'Nintendo', 'ULED Red and Blue', 6499000),
	(30, 'Candy', 'Popping Gummy', 'Gavv', 'minion in form of candy', 75000);
/*!40000 ALTER TABLE `product_tbl` ENABLE KEYS */;

-- Dumping structure for table item.users_tbl
CREATE TABLE IF NOT EXISTS `users_tbl` (
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `admin` enum('Y','N') DEFAULT 'N'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table item.users_tbl: 2 rows
/*!40000 ALTER TABLE `users_tbl` DISABLE KEYS */;
INSERT INTO `users_tbl` (`username`, `password`, `admin`) VALUES
	('nat', '123', 'Y'),
	('member', '111', 'N');
/*!40000 ALTER TABLE `users_tbl` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
