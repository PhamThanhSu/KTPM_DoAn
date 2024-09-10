-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 05, 2024 at 08:37 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quanlykhogiay`
--

-- --------------------------------------------------------

--
-- Table structure for table `ctphieunhap`
--

CREATE TABLE `ctphieunhap` (
  `maphieunhap` int(11) NOT NULL,
  `masp` int(11) NOT NULL DEFAULT 0,
  `soluong` int(11) NOT NULL DEFAULT 0,
  `dongia` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ctphieunhap`
--

INSERT INTO `ctphieunhap` (`maphieunhap`, `masp`, `soluong`, `dongia`) VALUES
(1, 2, 2, 2600000),
(1, 3, 12, 1200000),
(2, 1, 10, 2500000),
(2, 4, 6, 3400000),
(3, 12, 5, 1800000),
(3, 13, 3, 4700000),
(3, 14, 4, 2300000),
(3, 15, 2, 3600000),
(4, 6, 3, 1700000),
(4, 7, 3, 2100000),
(4, 8, 6, 3300000),
(4, 9, 9, 3500000),
(5, 5, 4, 1700000),
(5, 10, 7, 1600000),
(5, 11, 8, 1800000),
(6, 1, 3, 2500000),
(6, 2, 3, 2600000),
(7, 15, 5, 3600000);

-- --------------------------------------------------------

--
-- Table structure for table `ctphieuxuat`
--

CREATE TABLE `ctphieuxuat` (
  `maphieuxuat` int(11) NOT NULL,
  `masp` int(11) NOT NULL DEFAULT 0,
  `soluong` int(11) NOT NULL DEFAULT 0,
  `dongia` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ctphieuxuat`
--

INSERT INTO `ctphieuxuat` (`maphieuxuat`, `masp`, `soluong`, `dongia`) VALUES
(1, 1, 2, 3000000),
(1, 2, 1, 3100000),
(2, 15, 1, 3800000),
(3, 14, 1, 2500000),
(3, 15, 1, 3800000),
(4, 3, 1, 1500000),
(4, 4, 1, 4000000),
(5, 5, 1, 2100000),
(5, 14, 1, 2500000),
(6, 4, 1, 4000000),
(6, 14, 1, 2500000),
(7, 1, 1, 3000000),
(7, 3, 1, 1500000),
(8, 1, 4, 3000000),
(8, 4, 2, 4000000),
(9, 13, 2, 5000000),
(9, 14, 1, 2500000),
(10, 12, 2, 1900000),
(10, 13, 1, 5000000),
(11, 4, 2, 4000000),
(11, 12, 1, 1900000),
(12, 15, 2, 3800000),
(13, 3, 2, 1500000),
(13, 5, 2, 2100000),
(14, 1, 3, 3000000),
(14, 2, 2, 3100000);

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `makh` int(11) NOT NULL,
  `tenkhachhang` varchar(255) NOT NULL,
  `diachi` varchar(255) NOT NULL,
  `sdt` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL DEFAULT 1,
  `ngaythamgia` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`makh`, `tenkhachhang`, `diachi`, `sdt`, `trangthai`, `ngaythamgia`) VALUES
(1, 'Nguyễn Văn A', 'Gia Đức, Ân Đức, Hoài Ân, Bình Định', '0387913347', 1, '2024-03-19 09:52:29'),
(2, 'Trần Nhất Nhất', '205 Trần Hưng Đạo, Phường 10, Quận 5, Thành phố Hồ Chí Minh', '0123456789', 1, '2024-03-19 09:52:29'),
(3, 'Hoàng Gia Bo', 'Khoa Trường, Hoài Ân, Bình Định', '0987654321', 1, '2024-03-19 09:52:29'),
(4, 'Hồ Minh Hưng', 'Khoa Trường, Hoài Ân, Bình Định', '0867987456', 1, '2024-03-19 09:52:29'),
(5, 'Nguyễn Thị Minh Anh', '123 Phố Huế, Quận Hai Bà Trưng, Hà Nội', '0935123456', 1, '2024-03-23 17:59:57'),
(6, 'Trần Đức Minh', '789 Đường Lê Hồng Phong, Thành phố Đà Nẵng', '0983456789', 1, '2024-03-23 18:08:12'),
(7, 'Lê Hải Yến', '456 Tôn Thất Thuyết, Quận 4, Thành phố Hồ Chí Minh', '0977234567', 1, '2024-03-23 18:08:47'),
(8, 'Phạm Thanh Hằng', '102 Lê Duẩn, Thành phố Hải Phòng', '0965876543', 1, '2024-03-23 18:12:59'),
(9, 'Hoàng Đức Anh', '321 Lý Thường Kiệt, Thành phố Cần Thơ', '0946789012', 1, '2024-03-23 18:13:47'),
(10, 'Ngô Thanh Tùng', '987 Trần Hưng Đạo, Quận 1, Thành phố Hồ Chí Minh', '0912345678', 1, '2024-03-23 18:14:12'),
(11, 'Võ Thị Kim Ngân', '555 Nguyễn Văn Linh, Quận Nam Từ Liêm, Hà Nội', '0916789123', 1, '2024-03-23 18:15:11'),
(12, 'Đỗ Văn Tú', '777 Hùng Vương, Thành phố Huế', '0982345678', 1, '2024-03-23 18:15:56'),
(13, 'Lý Thanh Trúc', '888 Nguyễn Thái Học, Quận Ba Đình, Hà Nội', '0982123456', 1, '2024-03-23 18:16:22'),
(14, 'Bùi Văn Hoàng', '222 Đường 2/4, Thành phố Nha Trang', '0933789012', 1, '2024-03-23 18:16:53'),
(15, 'Lê Văn Thành', '23 Đường 3 Tháng 2, Quận 10, TP. Hồ Chí Minh', '0933456789', 1, '2024-03-23 18:17:46'),
(16, 'Nguyễn Thị Lan Anh', '456 Lê Lợi, Quận 1, TP. Hà Nội', '0965123456', 1, '2024-03-23 18:18:10'),
(17, 'Phạm Thị Mai', '234 Lê Hồng Phong, Quận 5, TP. Hồ Chí Minh', '0946789012', 1, '2024-03-23 18:18:34'),
(18, 'Hoàng Văn Nam', ' 567 Phố Huế, Quận Hai Bà Trưng, Hà Nội', '0912345678', 1, '2024-03-23 18:19:16');

-- --------------------------------------------------------

--
-- Table structure for table `khuvuckho`
--

CREATE TABLE `khuvuckho` (
  `makhuvuc` int(11) NOT NULL,
  `tenkhuvuc` varchar(255) NOT NULL,
  `ghichu` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `khuvuckho`
--

INSERT INTO `khuvuckho` (`makhuvuc`, `tenkhuvuc`, `ghichu`, `trangthai`) VALUES
(1, 'Khu vực A', 'Quận 3\r\n', 1),
(2, 'Khu vực B', 'Quận 4\r\n', 1),
(3, 'Khu vực C', 'Quận 2\r\n', 1),
(4, 'Khu vực D', 'Quận 1', 1);

-- --------------------------------------------------------

--
-- Table structure for table `loai`
--

CREATE TABLE `loai` (
  `maloai` int(11) NOT NULL,
  `tenloai` varchar(255) NOT NULL,
  `trangthai` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `loai`
--

INSERT INTO `loai` (`maloai`, `tenloai`, `trangthai`) VALUES
(1, 'Thể thao', 1),
(2, 'Sneaker', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `manhacungcap` int(11) NOT NULL,
  `tennhacungcap` varchar(255) NOT NULL,
  `diachi` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `sdt` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhacungcap`
--

INSERT INTO `nhacungcap` (`manhacungcap`, `tennhacungcap`, `diachi`, `email`, `sdt`, `trangthai`) VALUES
(1, 'Công Ty Nike', ' Phòng 6.5, Tầng6, Tòa Nhà E. Town 2, 364 Cộng Hòa, P. 13, Q. Tân Bình, Tp. Hồ Chí Minh', 'lienhe@gmail.com', '02835100100', 1),
(2, 'Công ty Puma', 'Số 79 đường số 6, Hưng Phước 4, Phú Mỹ Hưng, quận 7, TPHCM', 'contact@paviet.vn', '19009477', 1),
(3, 'Công Ty Adidas', '8/38 Đinh Bô Lĩnh, P.24, Q. Bình Thạnh, Tp. Hồ Chí Minh', 'contact@baola.vn', '02835119060', 1),
(4, 'Công Ty Thượng Đình', 'Phòng 703, Tầng7, Tòa Nhà Metropolitan, 235 Đồng Khởi, P. Bến Nghé, Q. 1, Tp. Hồ Chí Minh (TPHCM)', 'chau.nguyen@nokia.com', '02838236894', 1),
(5, 'Hệ Thống Phân Phối Giày USA', '261 Lê Lợi, P. Lê Lợi, Q. Ngô Quyền, Tp. Hải Phòng', 'info@mihome.vn', '0365888866', 1),
(6, 'Công Ty Giày Trung', 'Tòa nhà tài chính Bitexco, 2 Hải Triều, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh', 'contact@gmail.vn', '0988788456', 1),
(7, 'Công ty GIAYVN', '27 Đ. Nguyễn Trung Trực, Phường Bến Thành, Quận 1, Thành phố Hồ Chí Minh', 'giayvn@oppo.vn', '0456345234', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `manv` int(11) NOT NULL,
  `hoten` varchar(255) NOT NULL,
  `gioitinh` int(11) NOT NULL,
  `ngaysinh` date NOT NULL,
  `sdt` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`manv`, `hoten`, `gioitinh`, `ngaysinh`, `sdt`, `email`, `trangthai`) VALUES
(1, 'Lê Nguyễn Hoàng Phát', 1, '2004-03-16', '0338641606', 'phat@gmail.com', 1),
(2, 'Phạm Thiên Phúc', 1, '2004-04-17', '0932831928', 'phuc@gmail.com', 1),
(3, 'Phạm Thanh Sự', 1, '2004-03-20', '0932831928', 'su@gmail.com', 1),
(4, 'Dương Minh Trí', 1, '2004-04-17', '0932831928', 'tri@gmail.com', 1),
(5, 'Đoàn Ánh Dương', 1, '2004-03-16', '0338641606', 'duong@gmail.com', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhomquyen`
--

CREATE TABLE `nhomquyen` (
  `manhomquyen` int(11) NOT NULL,
  `tennhomquyen` varchar(255) NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhomquyen`
--

INSERT INTO `nhomquyen` (`manhomquyen`, `tennhomquyen`, `type`) VALUES
(1, 'Quản lý tài khoản', 1),
(2, 'Nhân viên thống kê', 2),
(3, 'Nhân viên xuất hàng', 3),
(4, 'Nhân viên nhập hàng', 4),
(5, 'Quản lý kho', 5);

-- --------------------------------------------------------

--
-- Table structure for table `phieunhap`
--

CREATE TABLE `phieunhap` (
  `maphieunhap` int(11) NOT NULL,
  `thoigian` datetime DEFAULT current_timestamp(),
  `manhacungcap` int(11) NOT NULL,
  `manv` int(11) NOT NULL,
  `tongtien` bigint(20) NOT NULL DEFAULT 0,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phieunhap`
--

INSERT INTO `phieunhap` (`maphieunhap`, `thoigian`, `manhacungcap`, `manv`, `tongtien`, `trangthai`) VALUES
(1, '2023-12-05 12:38:33', 5, 1, 19600000, 1),
(2, '2023-12-05 12:38:40', 3, 4, 45400000, 1),
(3, '2023-12-05 12:45:03', 3, 1, 39500000, 1),
(4, '2023-12-05 12:45:03', 5, 4, 62700000, 1),
(5, '2023-12-05 12:46:03', 7, 4, 32400000, 1),
(6, '2024-05-05 13:24:47', 7, 1, 15300000, 1),
(7, '2024-05-05 13:24:58', 1, 1, 18000000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `phieuxuat`
--

CREATE TABLE `phieuxuat` (
  `maphieuxuat` int(11) NOT NULL,
  `thoigian` datetime NOT NULL DEFAULT current_timestamp(),
  `tongtien` bigint(20) DEFAULT NULL,
  `manv` int(11) DEFAULT NULL,
  `makh` int(11) DEFAULT NULL,
  `trangthai` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phieuxuat`
--

INSERT INTO `phieuxuat` (`maphieuxuat`, `thoigian`, `tongtien`, `manv`, `makh`, `trangthai`) VALUES
(1, '2023-12-28 12:46:57', 9100000, 1, 4, 1),
(2, '2023-12-29 12:47:35', 3800000, 1, 15, 1),
(3, '2023-12-30 12:49:05', 6300000, 1, 2, 1),
(4, '2023-12-01 12:49:17', 5500000, 3, 7, 1),
(5, '2023-12-02 12:50:23', 4600000, 1, 18, 1),
(6, '2023-12-03 12:50:38', 6500000, 1, 17, 1),
(7, '2023-12-04 12:50:52', 4500000, 3, 16, 1),
(8, '2024-04-28 13:25:29', 20000000, 3, 5, 1),
(9, '2024-04-29 13:25:45', 12500000, 1, 1, 1),
(10, '2024-04-30 13:26:00', 8800000, 3, 1, 1),
(11, '2024-05-01 13:26:41', 9900000, 1, 5, 1),
(12, '2024-05-02 13:26:53', 7600000, 3, 3, 1),
(13, '2024-05-03 13:27:03', 7200000, 1, 14, 1),
(14, '2024-05-04 13:27:17', 15200000, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `masp` int(11) NOT NULL,
  `tensp` varchar(255) DEFAULT NULL,
  `size` int(11) NOT NULL,
  `hinhanh` varchar(255) DEFAULT NULL,
  `xuatxu` int(11) DEFAULT NULL,
  `loai` int(11) DEFAULT NULL,
  `thuonghieu` int(11) DEFAULT NULL,
  `khuvuckho` int(11) DEFAULT NULL,
  `soluongton` int(11) DEFAULT 0,
  `trangthai` tinyint(1) DEFAULT 1,
  `gianhap` bigint(100) NOT NULL,
  `giaxuat` bigint(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`masp`, `tensp`, `size`, `hinhanh`, `xuatxu`, `loai`, `thuonghieu`, `khuvuckho`, `soluongton`, `trangthai`, `gianhap`, `giaxuat`) VALUES
(1, 'Nike Air Zoom Pegasus', 41, 'Nike-Air-Zoom-Pegasus.png', 3, 1, 2, 1, 3, 1, 2500000, 3000000),
(2, 'Nike Pegasus 40', 43, 'Nike-Pegasus-40.png', 3, 1, 2, 2, 2, 1, 2600000, 3100000),
(3, 'Puma Carina Street', 38, 'Puma-Carina-Street', 1, 2, 3, 3, 8, 1, 1200000, 1500000),
(4, 'Adidas Supernova Solution', 44, 'Adidas-Supernova-Solution.png\r\n', 3, 1, 1, 4, 0, 1, 3400000, 4000000),
(5, 'Nike Air Winflo', 40, 'Nike-Air-Winflo.png', 2, 1, 2, 1, 1, 1, 1700000, 2100000),
(6, 'Puma Suede', 41, 'Puma-Suede.png', 1, 2, 3, 2, 3, 1, 1700000, 2000000),
(7, 'Adidas SWITCH RUN', 42, 'adidas-SWITCH-RUN.png', 3, 1, 1, 4, 3, 1, 2100000, 2400000),
(8, 'Nike Air zoom', 38, 'Nike-Air-zoom.png', 3, 1, 2, 3, 6, 1, 3300000, 3500000),
(9, 'Nike Pegasus', 45, 'Nike-Pegasus.png', 1, 2, 2, 2, 9, 1, 3500000, 3800000),
(10, 'Puma RS-Z AS', 41, 'Puma-RS-Z-AS.png', 1, 2, 3, 2, 7, 1, 1600000, 1800000),
(11, 'Adidas ULTRABOOST LIGHT', 44, 'Adidas-ULTRABOOST-LIGHT.png', 3, 1, 1, 1, 8, 1, 1800000, 2000000),
(12, 'Puma Caven', 38, 'Puma-Caven.png', 2, 2, 3, 2, 2, 1, 1800000, 1900000),
(13, 'Adidas ADIZERO TAKUMI SEN 10', 45, 'adidas-ADIZERO-TAKUMI-SEN-10.png', 3, 1, 1, 4, 0, 1, 4700000, 5000000),
(14, 'Puma White Black', 40, 'Puma-White-Black.png', 1, 2, 3, 3, 0, 1, 2300000, 2500000),
(15, 'Air Max Pulse', 41, 'air-max-90-ltr-older-shoes-9xnt2B.png', 3, 2, 2, 2, 3, 1, 3600000, 3800000);

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `manv` int(11) NOT NULL,
  `matkhau` varchar(255) NOT NULL,
  `manhomquyen` int(11) NOT NULL,
  `tendangnhap` varchar(50) NOT NULL DEFAULT '',
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`manv`, `matkhau`, `manhomquyen`, `tendangnhap`, `trangthai`) VALUES
(1, '123123', 5, 'hoangphat', 1),
(2, '123123', 2, 'thienphuc', 1),
(3, '123123', 3, 'thanhsu', 1),
(4, '123123', 4, 'minhtri', 1),
(5, '123123', 1, 'anhduong', 1);

-- --------------------------------------------------------

--
-- Table structure for table `thuonghieu`
--

CREATE TABLE `thuonghieu` (
  `mathuonghieu` int(11) NOT NULL,
  `tenthuonghieu` varchar(255) NOT NULL,
  `trangthai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `thuonghieu`
--

INSERT INTO `thuonghieu` (`mathuonghieu`, `tenthuonghieu`, `trangthai`) VALUES
(1, 'Adidas', 1),
(2, 'Nike', 1),
(3, 'Puma', 1);

-- --------------------------------------------------------

--
-- Table structure for table `xuatxu`
--

CREATE TABLE `xuatxu` (
  `maxuatxu` int(11) NOT NULL,
  `tenxuatxu` varchar(50) NOT NULL,
  `trangthai` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `xuatxu`
--

INSERT INTO `xuatxu` (`maxuatxu`, `tenxuatxu`, `trangthai`) VALUES
(1, 'Trung Quốc', 1),
(2, 'Việt Nam', 1),
(3, 'USA', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ctphieunhap`
--
ALTER TABLE `ctphieunhap`
  ADD PRIMARY KEY (`maphieunhap`,`masp`) USING BTREE,
  ADD KEY `FK_sanpham_ctphieunhap` (`masp`);

--
-- Indexes for table `ctphieuxuat`
--
ALTER TABLE `ctphieuxuat`
  ADD PRIMARY KEY (`maphieuxuat`,`masp`) USING BTREE,
  ADD KEY `FK_sanpham_ctphieuxuat` (`masp`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`makh`);

--
-- Indexes for table `khuvuckho`
--
ALTER TABLE `khuvuckho`
  ADD PRIMARY KEY (`makhuvuc`);

--
-- Indexes for table `loai`
--
ALTER TABLE `loai`
  ADD PRIMARY KEY (`maloai`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`manhacungcap`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`manv`);

--
-- Indexes for table `nhomquyen`
--
ALTER TABLE `nhomquyen`
  ADD PRIMARY KEY (`manhomquyen`);

--
-- Indexes for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`maphieunhap`),
  ADD KEY `FK_ncc_phieunhap` (`manhacungcap`),
  ADD KEY `FK_nv_phieunhap` (`manv`);

--
-- Indexes for table `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD PRIMARY KEY (`maphieuxuat`),
  ADD KEY `FK_nv_phieuxuat` (`manv`),
  ADD KEY `FK_kh_phieuxuat` (`makh`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`masp`),
  ADD KEY `FK_xuatxu_sanpham` (`xuatxu`),
  ADD KEY `FK_thuonghieu_sanpham` (`thuonghieu`),
  ADD KEY `FK_loai_sanpham` (`loai`),
  ADD KEY `FK_kho_sanpham` (`khuvuckho`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`manv`),
  ADD UNIQUE KEY `tendangnhap` (`tendangnhap`),
  ADD KEY `FK_taikhoan_nhomquyen` (`manhomquyen`);

--
-- Indexes for table `thuonghieu`
--
ALTER TABLE `thuonghieu`
  ADD PRIMARY KEY (`mathuonghieu`);

--
-- Indexes for table `xuatxu`
--
ALTER TABLE `xuatxu`
  ADD PRIMARY KEY (`maxuatxu`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ctphieunhap`
--
ALTER TABLE `ctphieunhap`
  MODIFY `maphieunhap` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `ctphieuxuat`
--
ALTER TABLE `ctphieuxuat`
  MODIFY `maphieuxuat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `makh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `khuvuckho`
--
ALTER TABLE `khuvuckho`
  MODIFY `makhuvuc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `loai`
--
ALTER TABLE `loai`
  MODIFY `maloai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `manhacungcap` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `manv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `nhomquyen`
--
ALTER TABLE `nhomquyen`
  MODIFY `manhomquyen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `maphieunhap` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `phieuxuat`
--
ALTER TABLE `phieuxuat`
  MODIFY `maphieuxuat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `masp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `taikhoan`
--
ALTER TABLE `taikhoan`
  MODIFY `manv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `thuonghieu`
--
ALTER TABLE `thuonghieu`
  MODIFY `mathuonghieu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `xuatxu`
--
ALTER TABLE `xuatxu`
  MODIFY `maxuatxu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ctphieunhap`
--
ALTER TABLE `ctphieunhap`
  ADD CONSTRAINT `FK_phieunhap_ctphieunhap` FOREIGN KEY (`maphieunhap`) REFERENCES `phieunhap` (`maphieunhap`),
  ADD CONSTRAINT `FK_sanpham_ctphieunhap` FOREIGN KEY (`masp`) REFERENCES `sanpham` (`masp`);

--
-- Constraints for table `ctphieuxuat`
--
ALTER TABLE `ctphieuxuat`
  ADD CONSTRAINT `FK_phieuxuat_ctphieuxuat` FOREIGN KEY (`maphieuxuat`) REFERENCES `phieuxuat` (`maphieuxuat`),
  ADD CONSTRAINT `FK_sanpham_ctphieuxuat` FOREIGN KEY (`masp`) REFERENCES `sanpham` (`masp`);

--
-- Constraints for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `FK_ncc_phieunhap` FOREIGN KEY (`manhacungcap`) REFERENCES `nhacungcap` (`manhacungcap`),
  ADD CONSTRAINT `FK_nv_phieunhap` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`);

--
-- Constraints for table `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD CONSTRAINT `FK_kh_phieuxuat` FOREIGN KEY (`makh`) REFERENCES `khachhang` (`makh`),
  ADD CONSTRAINT `FK_nv_phieuxuat` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`);

--
-- Constraints for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `FK_kho_sanpham` FOREIGN KEY (`khuvuckho`) REFERENCES `khuvuckho` (`makhuvuc`),
  ADD CONSTRAINT `FK_loai_sanpham` FOREIGN KEY (`loai`) REFERENCES `loai` (`maloai`),
  ADD CONSTRAINT `FK_thuonghieu_sanpham` FOREIGN KEY (`thuonghieu`) REFERENCES `thuonghieu` (`mathuonghieu`),
  ADD CONSTRAINT `FK_xuatxu_sanpham` FOREIGN KEY (`xuatxu`) REFERENCES `xuatxu` (`maxuatxu`);

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `FK_taikhoan_nhanvien` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`),
  ADD CONSTRAINT `FK_taikhoan_nhomquyen` FOREIGN KEY (`manhomquyen`) REFERENCES `nhomquyen` (`manhomquyen`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
