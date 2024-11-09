# DoAnJAVA 
**# Kiểm Thử Phần Mềm** and **# Công Nghệ Phần Mềm**

## Getting Started
```bash
*Lưu ý 1: Tạo file db.properties (KTPM_DoAn/db.properties), đuôi phải là .properties 

nội dung file ghi vào: 
url=jdbc:mysql://localhost:port tại máy của bạn/ tên file sql
user= tên user
password= password

-------------------------
*Lưu ý 2: Hướng dẫn tạo file
```bash
Bước 1:
Mở NetBeans và điều hướng đến dự án của bạn (ví dụ: KTPM_DoAn).
Nhấp chuột phải vào project (hoặc thư mục mà bạn muốn tạo file).
Chọn New > Other
Categories kéo xuống dưới chọn Other, File Types chọn Poperties File
Đặt tên là db
Nhấp Finish để tạo file.
Nhập Nội Dung vào File:
Mở file db.properties vừa tạo và nhập nội dung như sau:
url=jdbc:mysql://localhost:port tại máy của bạn/ tên file sql
user= tên user của bạn 
password= password của bạn
Chú ý: Đảm bảo rằng bạn đã thay thế localhost, port, và tên file sql bằng giá trị tương ứng của bạn.
Bước 2: Đổi Đuôi File (Nếu Cần)
Nếu NetBeans hoặc hệ điều hành của bạn không cho phép bạn đổi đuôi file từ .txt sang .properties, bạn có thể thực hiện theo các bước sau:
Di Chuyển File:
Nếu file db.properties hiện đang là file .txt, bạn có thể di chuyển nó ra ngoài thư mục config vào thư mục gốc của dự án (KTPM_DoAn).
Đổi Tên File:
Nhấp chuột phải vào file và chọn Rename.
Xóa phần .txt trong tên file để chỉ còn lại db.properties.
Bước 3: Xác Minh File db.properties
Kiểm Tra File:
Đảm bảo rằng file db.properties hiện có trong thư mục KTPM_DoAn.
Nếu bạn mở thư mục dự án trong file explorer (Windows Explorer hoặc Finder trên macOS), bạn sẽ thấy file db.properties đã được tạo.

-----------------------------------------
```
```bash
git clone https://github.com/PhamThanhSu/KTPM_DoAn.git
Mở XAMPP và vào trang http://localhost/phpmyadmin/.
Tạo một database mới có tên là quanlycuahanggiay.
Import cơ sở dữ liệu trong folder database.
Giao diện được tham khảo và thêm một số chức năng.

Sử dụng NetBeans với JDK 21 để chạy source code.

Tài khoản Admin
Username: thanhsu
Password: 123123
