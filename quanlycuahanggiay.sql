-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1:3307
-- Thời gian đã tạo: Th10 18, 2024 lúc 05:07 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlycuahanggiay`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctphieunhap`
--

CREATE TABLE `ctphieunhap` (
  `maphieunhap` int(11) NOT NULL,
  `masp` int(11) NOT NULL DEFAULT 0,
  `soluong` int(11) NOT NULL DEFAULT 0,
  `gianhap` int(11) NOT NULL DEFAULT 0,
  `giaxuat` int(11) DEFAULT NULL,
  `soluongconlai` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ctphieunhap`
--

INSERT INTO `ctphieunhap` (`maphieunhap`, `masp`, `soluong`, `gianhap`, `giaxuat`, `soluongconlai`) VALUES
(1, 1, 10, 2000000, 2500000, 0),
(1, 2, 10, 1500000, 1875000, 3),
(1, 3, 5, 2000000, 2500000, 5),
(1, 4, 10, 2000000, 2500000, 5),
(1, 5, 7, 1700000, 2125000, 5),
(2, 6, 10, 1500000, 1800000, 5),
(2, 7, 5, 1800000, 2160000, 3),
(2, 8, 10, 1000000, 1200000, 3),
(2, 9, 8, 2000000, 2400000, 5),
(3, 1, 10, 1000000, 1250000, 7);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctphieuxuat`
--

CREATE TABLE `ctphieuxuat` (
  `maphieuxuat` int(11) NOT NULL,
  `masp` int(11) NOT NULL DEFAULT 0,
  `soluong` int(11) NOT NULL DEFAULT 0,
  `gianhap` int(11) NOT NULL DEFAULT 0,
  `giaxuat` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ctphieuxuat`
--

INSERT INTO `ctphieuxuat` (`maphieuxuat`, `masp`, `soluong`, `gianhap`, `giaxuat`) VALUES
(1, 1, 5, 2000000, 2500000),
(1, 2, 5, 1500000, 1875000),
(2, 4, 5, 2000000, 2500000),
(2, 7, 2, 1800000, 2160000),
(2, 9, 3, 2000000, 2400000),
(3, 1, 5, 2000000, 2500000),
(3, 5, 2, 1700000, 2125000),
(3, 8, 3, 1000000, 1200000),
(4, 1, 3, 1000000, 1250000),
(4, 2, 2, 1500000, 1875000),
(5, 6, 5, 1500000, 1800000),
(5, 8, 4, 1000000, 1200000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctquyen`
--

CREATE TABLE `ctquyen` (
  `manhomquyen` int(11) NOT NULL,
  `machucnang` varchar(50) NOT NULL,
  `hanhdong` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ctquyen`
--

INSERT INTO `ctquyen` (`manhomquyen`, `machucnang`, `hanhdong`) VALUES
(1, 'nhanvien', 'view'),
(1, 'nhomquyen', 'create'),
(1, 'nhomquyen', 'view'),
(1, 'taikhoan', 'create'),
(1, 'taikhoan', 'delete'),
(1, 'taikhoan', 'update'),
(1, 'taikhoan', 'view'),
(2, 'khuvuckho', 'view'),
(2, 'nhaphang', 'view'),
(2, 'sanpham', 'view'),
(2, 'thongke', 'view'),
(2, 'xuathang', 'view'),
(3, 'khachhang', 'view'),
(3, 'khuvuckho', 'view'),
(3, 'nhacungcap', 'view'),
(3, 'nhaphang', 'view'),
(3, 'sanpham', 'view'),
(3, 'thongke', 'view'),
(3, 'thuoctinh', 'view'),
(3, 'xuathang', 'create'),
(3, 'xuathang', 'update'),
(3, 'xuathang', 'view'),
(4, 'khachhang', 'view'),
(4, 'khuvuckho', 'view'),
(4, 'nhacungcap', 'view'),
(4, 'nhaphang', 'create'),
(4, 'nhaphang', 'update'),
(4, 'nhaphang', 'view'),
(4, 'sanpham', 'view'),
(4, 'thongke', 'view'),
(4, 'thuoctinh', 'view'),
(4, 'xuathang', 'view'),
(5, 'khachhang', 'create'),
(5, 'khachhang', 'delete'),
(5, 'khachhang', 'update'),
(5, 'khachhang', 'view'),
(5, 'khuvuckho', 'create'),
(5, 'khuvuckho', 'delete'),
(5, 'khuvuckho', 'update'),
(5, 'khuvuckho', 'view'),
(5, 'nhacungcap', 'create'),
(5, 'nhacungcap', 'delete'),
(5, 'nhacungcap', 'update'),
(5, 'nhacungcap', 'view'),
(5, 'nhanvien', 'create'),
(5, 'nhanvien', 'delete'),
(5, 'nhanvien', 'update'),
(5, 'nhanvien', 'view'),
(5, 'nhaphang', 'create'),
(5, 'nhaphang', 'delete'),
(5, 'nhaphang', 'update'),
(5, 'nhaphang', 'view'),
(5, 'nhomquyen', 'create'),
(5, 'nhomquyen', 'delete'),
(5, 'nhomquyen', 'update'),
(5, 'nhomquyen', 'view'),
(5, 'sanpham', 'create'),
(5, 'sanpham', 'delete'),
(5, 'sanpham', 'update'),
(5, 'sanpham', 'view'),
(5, 'taikhoan', 'create'),
(5, 'taikhoan', 'delete'),
(5, 'taikhoan', 'update'),
(5, 'taikhoan', 'view'),
(5, 'thongke', 'create'),
(5, 'thongke', 'delete'),
(5, 'thongke', 'update'),
(5, 'thongke', 'view'),
(5, 'thuoctinh', 'create'),
(5, 'thuoctinh', 'delete'),
(5, 'thuoctinh', 'update'),
(5, 'thuoctinh', 'view'),
(5, 'xuathang', 'create'),
(5, 'xuathang', 'delete'),
(5, 'xuathang', 'update'),
(5, 'xuathang', 'view'),
(7, 'xuathang', 'view'),
(19, 'taikhoan', 'create'),
(19, 'taikhoan', 'view');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `giamgia`
--

CREATE TABLE `giamgia` (
  `magiamgia` int(11) NOT NULL,
  `tengiamgia` varchar(255) NOT NULL,
  `phantramgiam` int(11) NOT NULL,
  `giatrihoadon` bigint(20) NOT NULL,
  `ngaybatdau` date NOT NULL,
  `ngayketthuc` date NOT NULL,
  `trangthai` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `giamgia`
--

INSERT INTO `giamgia` (`magiamgia`, `tengiamgia`, `phantramgiam`, `giatrihoadon`, `ngaybatdau`, `ngayketthuc`, `trangthai`) VALUES
(3, 'giảm giá 1', 10, 0, '2024-11-18', '2024-11-21', 'Có hiệu lực'),
(4, 'giảm giá tháng 11', 30, 2500000, '2024-11-20', '2024-11-23', 'Có hiệu lực'),
(5, 'giảm giá ngày 18', 10, 0, '2024-11-18', '2024-11-19', 'Có hiệu lực'),
(6, 'giảm giá ngày 19', 20, 0, '2024-11-19', '2024-11-20', 'Có hiệu lực');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `giamgiasanpham`
--

CREATE TABLE `giamgiasanpham` (
  `magiamgia` int(11) NOT NULL,
  `masp` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `giamgiasanpham`
--

INSERT INTO `giamgiasanpham` (`magiamgia`, `masp`) VALUES
(4, 1),
(4, 2),
(4, 3),
(4, 4),
(4, 5);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
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
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`makh`, `tenkhachhang`, `diachi`, `sdt`, `trangthai`, `ngaythamgia`) VALUES
(1, 'Nguyễn Văn A', 'Gia Đức, Ân Đức, Hoài Ân, Bình Định', '0387913347', 1, '2024-03-19 09:52:29'),
(2, 'Trần Nhất Hai', '205 Trần Hưng Đạo, Phường 10, Quận 5, Thành phố Hồ Chí Minh', '0123456789', 1, '2024-03-19 09:52:29'),
(3, 'Hoàng Gia Nghĩa', 'Khoa Trường, Hoài Ân, Bình Định', '0987654321', 1, '2024-03-19 09:52:29'),
(4, 'Hồ Minh Hưng', 'Khoa Trường, Hoài Ân, Bình Định', '0867987456', 0, '2024-03-19 09:52:29'),
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
-- Cấu trúc bảng cho bảng `khuvuckho`
--

CREATE TABLE `khuvuckho` (
  `makhuvuc` int(11) NOT NULL,
  `tenkhuvuc` varchar(255) NOT NULL,
  `ghichu` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khuvuckho`
--

INSERT INTO `khuvuckho` (`makhuvuc`, `tenkhuvuc`, `ghichu`, `trangthai`) VALUES
(1, 'Khu vực A', 'Quận 3\r\n', 1),
(2, 'Khu vực B', 'Quận 4\r\n', 1),
(3, 'Khu vực C', 'Quận 2\r\n', 1),
(4, 'Khu vực D', 'Quận 1', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loai`
--

CREATE TABLE `loai` (
  `maloai` int(11) NOT NULL,
  `tenloai` varchar(255) NOT NULL,
  `trangthai` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `loai`
--

INSERT INTO `loai` (`maloai`, `tenloai`, `trangthai`) VALUES
(1, 'Thể thao', 1),
(2, 'Sneaker', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `manhacungcap` int(11) NOT NULL,
  `tennhacungcap` varchar(255) NOT NULL,
  `diachi` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `sdt` varchar(255) NOT NULL,
  `trangthai` int(11) NOT NULL DEFAULT 1,
  `phantramloinhuan` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`manhacungcap`, `tennhacungcap`, `diachi`, `email`, `sdt`, `trangthai`, `phantramloinhuan`) VALUES
(1, 'Công Ty Nike', ' Phòng 6.5, Tầng6, Tòa Nhà E. Town 2, 364 Cộng Hòa, P. 13, Q. Tân Bình, Tp. Hồ Chí Minh', 'lienhe@gmail.com', '02835100100', 1, 20),
(2, 'Công ty Puma', 'Số 79 đường số 6, Hưng Phước 4, Phú Mỹ Hưng, quận 7, TPHCM', 'contact@paviet.vn', '19009477', 1, 25),
(3, 'Công Ty Adidas', '8/38 Đinh Bô Lĩnh, P.24, Q. Bình Thạnh, Tp. Hồ Chí Minh', 'contact@baola.vn', '02835119060', 1, 15),
(4, 'Công Ty Thượng Đình', 'Phòng 703, Tầng7, Tòa Nhà Metropolitan, 235 Đồng Khởi, P. Bến Nghé, Q. 1, Tp. Hồ Chí Minh (TPHCM)', 'chau.nguyen@nokia.com', '02838236894', 1, 20),
(5, 'Hệ Thống Phân Phối Giày USA', '261 Lê Lợi, P. Lê Lợi, Q. Ngô Quyền, Tp. Hải Phòng', 'info@mihome.vn', '0365888866', 1, 25),
(6, 'Công Ty Giày Trung', 'Tòa nhà tài chính Bitexco, 2 Hải Triều, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh', 'contact@gmail.vn', '0988788456', 1, 15),
(7, 'Công ty GIAYVN', '27 Đ. Nguyễn Trung Trực, Phường Bến Thành, Quận 1, Thành phố Hồ Chí Minh', 'giayvn@oppo.vn', '0456345234', 1, 30);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
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
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`manv`, `hoten`, `gioitinh`, `ngaysinh`, `sdt`, `email`, `trangthai`) VALUES
(1, 'Lê Nguyễn Hoàng Phát', 1, '2004-03-16', '0338641606', 'phat@gmail.com', 1),
(2, 'Phạm Thiên Phúc', 1, '2004-04-17', '0932831928', 'phuc@gmail.com', 1),
(3, 'Phạm Thanh Sự', 1, '2004-03-20', '0932831928', 'su@gmail.com', 1),
(4, 'Dương Minh Trí', 1, '2004-04-17', '0932831928', 'tri@gmail.com', 1),
(5, 'Đoàn Ánh Dương', 1, '2004-03-16', '0338641606', 'duong@gmail.com', 1),
(10, 'test', 1, '2024-11-04', '0123456789', 'tht@gmail.com', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhomquyen`
--

CREATE TABLE `nhomquyen` (
  `manhomquyen` int(11) NOT NULL,
  `tennhomquyen` varchar(255) NOT NULL,
  `type` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhomquyen`
--

INSERT INTO `nhomquyen` (`manhomquyen`, `tennhomquyen`, `type`) VALUES
(1, 'Quản lý tài khoản', 1),
(2, 'Nhân viên thống kê', 2),
(3, 'Nhân viên xuất hàng', 3),
(4, 'Nhân viên nhập hàng', 4),
(5, 'Quản lý kho', 5),
(6, 'test', NULL),
(7, 'test lan 2', NULL),
(19, 'test lan 3', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhap`
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
-- Đang đổ dữ liệu cho bảng `phieunhap`
--

INSERT INTO `phieunhap` (`maphieunhap`, `thoigian`, `manhacungcap`, `manv`, `tongtien`, `trangthai`) VALUES
(1, '2024-09-15 00:45:27', 5, 3, 76900000, 1),
(2, '2024-09-16 00:51:47', 4, 3, 50000000, 1),
(3, '2024-09-17 00:55:34', 5, 3, 10000000, 1),
(4, '2024-11-05 10:37:58', 4, 3, 100000, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieuxuat`
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
-- Đang đổ dữ liệu cho bảng `phieuxuat`
--

INSERT INTO `phieuxuat` (`maphieuxuat`, `thoigian`, `tongtien`, `manv`, `makh`, `trangthai`) VALUES
(1, '2022-09-15 00:52:55', 21875000, 3, 15, 1),
(2, '2023-09-13 00:55:04', 24020000, 3, 17, 1),
(3, '2024-08-22 00:56:43', 20350000, 3, 11, 1),
(4, '2024-09-18 00:57:14', 7500000, 3, 5, 1),
(5, '2024-09-19 08:06:54', 13800000, 3, 16, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `quyenchucnang`
--

CREATE TABLE `quyenchucnang` (
  `machucnang` varchar(50) NOT NULL,
  `tenchucnang` varchar(255) NOT NULL,
  `trangthai` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `quyenchucnang`
--

INSERT INTO `quyenchucnang` (`machucnang`, `tenchucnang`, `trangthai`) VALUES
('khachhang', 'Quản lý khách hàng', 1),
('khuvuckho', 'Quản lý khu vực kho', 1),
('nhacungcap', 'Quản lý nhà cung cấp', 1),
('nhanvien', 'Quản lý nhân viên', 1),
('nhaphang', 'Quản lý nhập hàng', 1),
('nhomquyen', 'Quản lý nhóm quyền', 1),
('sanpham', 'Quản lý sản phẩm', 1),
('taikhoan', 'Quản lý tài khoản', 1),
('thongke', 'Quản lý thống kê', 1),
('thuoctinh', 'Quản lý thuộc tính', 1),
('xuathang', 'Quản lý xuất hàng', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
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
  `trangthai` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`masp`, `tensp`, `size`, `hinhanh`, `xuatxu`, `loai`, `thuonghieu`, `khuvuckho`, `soluongton`, `trangthai`) VALUES
(1, '1Nike Air Zoom Pegasus', 41, 'Nike-Air-Zoom-Pegasus.png', 3, 1, 2, 1, 7, 1),
(2, 'Nike Pegasus 40', 43, 'Nike-Pegasus-40.png', 3, 1, 2, 2, 3, 1),
(3, 'Puma Carina Street', 38, 'Puma-Carina-Street', 1, 2, 3, 3, 5, 1),
(4, 'Adidas Supernova Solution', 44, 'Adidas-Supernova-Solution.png\r\n', 3, 1, 1, 4, 5, 1),
(5, 'Nike Air Winflo', 40, 'Nike-Air-Winflo.png', 2, 1, 2, 1, 5, 1),
(6, 'Puma Suede', 41, 'Puma-Suede.png', 1, 2, 3, 2, 5, 1),
(7, 'Adidas SWITCH RUN', 42, 'adidas-SWITCH-RUN.png', 3, 1, 1, 4, 3, 1),
(8, 'Nike Air zoom', 38, 'Nike-Air-zoom.png', 3, 1, 2, 3, 3, 1),
(9, 'Nike Pegasus', 45, 'Nike-Pegasus.png', 1, 2, 2, 2, 5, 1),
(10, 'Puma RS-Z AS', 41, 'Puma-RS-Z-AS.png', 1, 2, 3, 2, 0, 1),
(11, 'Adidas ULTRABOOST LIGHT', 44, 'Adidas-ULTRABOOST-LIGHT.png', 3, 1, 1, 1, 0, 0),
(12, 'Puma Caven', 38, 'Puma-Caven.png', 2, 2, 3, 2, 0, 1),
(13, 'Adidas ADIZERO TAKUMI SEN 10', 45, 'adidas-ADIZERO-TAKUMI-SEN-10.png', 3, 1, 1, 4, 0, 1),
(14, 'Puma White Black', 40, 'Puma-White-Black.png', 1, 2, 3, 3, 0, 1),
(15, 'Air Max Pulse', 41, 'air-max-90-ltr-older-shoes-9xnt2B.png', 3, 2, 2, 2, 0, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `manv` int(11) NOT NULL,
  `matkhau` varchar(255) NOT NULL,
  `manhomquyen` int(11) NOT NULL,
  `tendangnhap` varchar(50) NOT NULL DEFAULT '',
  `trangthai` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`manv`, `matkhau`, `manhomquyen`, `tendangnhap`, `trangthai`) VALUES
(1, '123123', 3, 'hoangphat', 1),
(2, '123123', 2, 'thienphuc', 1),
(3, '123123', 5, 'thanhsu', 1),
(4, '123123', 4, 'minhtri', 1),
(5, '123123', 1, 'anhduong', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thuonghieu`
--

CREATE TABLE `thuonghieu` (
  `mathuonghieu` int(11) NOT NULL,
  `tenthuonghieu` varchar(255) NOT NULL,
  `trangthai` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `thuonghieu`
--

INSERT INTO `thuonghieu` (`mathuonghieu`, `tenthuonghieu`, `trangthai`) VALUES
(1, 'Adidas', 1),
(2, 'Nike', 1),
(3, 'Puma', 1),
(5, 'frr', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `xuatxu`
--

CREATE TABLE `xuatxu` (
  `maxuatxu` int(11) NOT NULL,
  `tenxuatxu` varchar(50) NOT NULL,
  `trangthai` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `xuatxu`
--

INSERT INTO `xuatxu` (`maxuatxu`, `tenxuatxu`, `trangthai`) VALUES
(1, 'Trung Quốc', 1),
(2, 'Việt Nam', 1),
(3, 'USA', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `ctphieunhap`
--
ALTER TABLE `ctphieunhap`
  ADD PRIMARY KEY (`maphieunhap`,`masp`) USING BTREE,
  ADD KEY `FK_sanpham_ctphieunhap` (`masp`);

--
-- Chỉ mục cho bảng `ctphieuxuat`
--
ALTER TABLE `ctphieuxuat`
  ADD PRIMARY KEY (`maphieuxuat`,`masp`) USING BTREE,
  ADD KEY `FK_sanpham_ctphieuxuat` (`masp`);

--
-- Chỉ mục cho bảng `ctquyen`
--
ALTER TABLE `ctquyen`
  ADD PRIMARY KEY (`manhomquyen`,`machucnang`,`hanhdong`),
  ADD KEY `fk_quyenchucnang` (`machucnang`);

--
-- Chỉ mục cho bảng `giamgia`
--
ALTER TABLE `giamgia`
  ADD PRIMARY KEY (`magiamgia`);

--
-- Chỉ mục cho bảng `giamgiasanpham`
--
ALTER TABLE `giamgiasanpham`
  ADD PRIMARY KEY (`magiamgia`,`masp`),
  ADD KEY `masp` (`masp`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`makh`);

--
-- Chỉ mục cho bảng `khuvuckho`
--
ALTER TABLE `khuvuckho`
  ADD PRIMARY KEY (`makhuvuc`);

--
-- Chỉ mục cho bảng `loai`
--
ALTER TABLE `loai`
  ADD PRIMARY KEY (`maloai`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`manhacungcap`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`manv`);

--
-- Chỉ mục cho bảng `nhomquyen`
--
ALTER TABLE `nhomquyen`
  ADD PRIMARY KEY (`manhomquyen`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`maphieunhap`),
  ADD KEY `FK_ncc_phieunhap` (`manhacungcap`),
  ADD KEY `FK_nv_phieunhap` (`manv`);

--
-- Chỉ mục cho bảng `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD PRIMARY KEY (`maphieuxuat`),
  ADD KEY `FK_nv_phieuxuat` (`manv`),
  ADD KEY `FK_kh_phieuxuat` (`makh`);

--
-- Chỉ mục cho bảng `quyenchucnang`
--
ALTER TABLE `quyenchucnang`
  ADD PRIMARY KEY (`machucnang`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`masp`),
  ADD KEY `FK_xuatxu_sanpham` (`xuatxu`),
  ADD KEY `FK_thuonghieu_sanpham` (`thuonghieu`),
  ADD KEY `FK_loai_sanpham` (`loai`),
  ADD KEY `FK_kho_sanpham` (`khuvuckho`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`manv`),
  ADD UNIQUE KEY `tendangnhap` (`tendangnhap`),
  ADD KEY `FK_taikhoan_nhomquyen` (`manhomquyen`);

--
-- Chỉ mục cho bảng `thuonghieu`
--
ALTER TABLE `thuonghieu`
  ADD PRIMARY KEY (`mathuonghieu`);

--
-- Chỉ mục cho bảng `xuatxu`
--
ALTER TABLE `xuatxu`
  ADD PRIMARY KEY (`maxuatxu`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `ctphieunhap`
--
ALTER TABLE `ctphieunhap`
  MODIFY `maphieunhap` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT cho bảng `ctphieuxuat`
--
ALTER TABLE `ctphieuxuat`
  MODIFY `maphieuxuat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT cho bảng `giamgia`
--
ALTER TABLE `giamgia`
  MODIFY `magiamgia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `makh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT cho bảng `khuvuckho`
--
ALTER TABLE `khuvuckho`
  MODIFY `makhuvuc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `loai`
--
ALTER TABLE `loai`
  MODIFY `maloai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `manhacungcap` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `manv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `nhomquyen`
--
ALTER TABLE `nhomquyen`
  MODIFY `manhomquyen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `maphieunhap` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT cho bảng `phieuxuat`
--
ALTER TABLE `phieuxuat`
  MODIFY `maphieuxuat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `masp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  MODIFY `manv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `thuonghieu`
--
ALTER TABLE `thuonghieu`
  MODIFY `mathuonghieu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `xuatxu`
--
ALTER TABLE `xuatxu`
  MODIFY `maxuatxu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `ctphieunhap`
--
ALTER TABLE `ctphieunhap`
  ADD CONSTRAINT `FK_phieunhap_ctphieunhap` FOREIGN KEY (`maphieunhap`) REFERENCES `phieunhap` (`maphieunhap`),
  ADD CONSTRAINT `FK_sanpham_ctphieunhap` FOREIGN KEY (`masp`) REFERENCES `sanpham` (`masp`);

--
-- Các ràng buộc cho bảng `ctphieuxuat`
--
ALTER TABLE `ctphieuxuat`
  ADD CONSTRAINT `FK_phieuxuat_ctphieuxuat` FOREIGN KEY (`maphieuxuat`) REFERENCES `phieuxuat` (`maphieuxuat`),
  ADD CONSTRAINT `FK_sanpham_ctphieuxuat` FOREIGN KEY (`masp`) REFERENCES `sanpham` (`masp`);

--
-- Các ràng buộc cho bảng `ctquyen`
--
ALTER TABLE `ctquyen`
  ADD CONSTRAINT `fk_nhomquyen` FOREIGN KEY (`manhomquyen`) REFERENCES `nhomquyen` (`manhomquyen`),
  ADD CONSTRAINT `fk_quyenchucnang` FOREIGN KEY (`machucnang`) REFERENCES `quyenchucnang` (`machucnang`);

--
-- Các ràng buộc cho bảng `giamgiasanpham`
--
ALTER TABLE `giamgiasanpham`
  ADD CONSTRAINT `giamgiasanpham_ibfk_1` FOREIGN KEY (`magiamgia`) REFERENCES `giamgia` (`magiamgia`) ON DELETE CASCADE,
  ADD CONSTRAINT `giamgiasanpham_ibfk_2` FOREIGN KEY (`masp`) REFERENCES `sanpham` (`masp`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `FK_ncc_phieunhap` FOREIGN KEY (`manhacungcap`) REFERENCES `nhacungcap` (`manhacungcap`),
  ADD CONSTRAINT `FK_nv_phieunhap` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`);

--
-- Các ràng buộc cho bảng `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD CONSTRAINT `FK_kh_phieuxuat` FOREIGN KEY (`makh`) REFERENCES `khachhang` (`makh`),
  ADD CONSTRAINT `FK_nv_phieuxuat` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`);

--
-- Các ràng buộc cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `FK_kho_sanpham` FOREIGN KEY (`khuvuckho`) REFERENCES `khuvuckho` (`makhuvuc`),
  ADD CONSTRAINT `FK_loai_sanpham` FOREIGN KEY (`loai`) REFERENCES `loai` (`maloai`),
  ADD CONSTRAINT `FK_thuonghieu_sanpham` FOREIGN KEY (`thuonghieu`) REFERENCES `thuonghieu` (`mathuonghieu`),
  ADD CONSTRAINT `FK_xuatxu_sanpham` FOREIGN KEY (`xuatxu`) REFERENCES `xuatxu` (`maxuatxu`);

--
-- Các ràng buộc cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `FK_taikhoan_nhanvien` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`),
  ADD CONSTRAINT `FK_taikhoan_nhomquyen` FOREIGN KEY (`manhomquyen`) REFERENCES `nhomquyen` (`manhomquyen`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
