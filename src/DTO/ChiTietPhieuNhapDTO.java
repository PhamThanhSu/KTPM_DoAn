package DTO;

public class ChiTietPhieuNhapDTO {

    private int maphieunhap;
    private int masp;
    private int soluong;
    private int gianhap;
    private int giaxuat; // Thêm trường giá xuất
    private int soluongconlai; // Thêm trường số lượng còn lại

    // Constructor đầy đủ
    public ChiTietPhieuNhapDTO(int maphieunhap, int masp, int soluong, int gianhap, int giaxuat, int soluongconlai) {
        this.maphieunhap = maphieunhap;
        this.masp = masp;
        this.soluong = soluong;
        this.gianhap = gianhap;
        this.giaxuat = giaxuat; // Khởi tạo giá xuất
        this.soluongconlai = soluongconlai; // Khởi tạo số lượng còn lại
    }

    // Constructor không có giá xuất và số lượng còn lại
    public ChiTietPhieuNhapDTO(int maphieunhap, int masp, int soluong, int gianhap, int giaxuat) {
        this.maphieunhap = maphieunhap;
        this.masp = masp;
        this.soluong = soluong;
        this.gianhap = gianhap;
        this.giaxuat = giaxuat; 
    }

    public ChiTietPhieuNhapDTO() {
    }

    // Getter và Setter cho giá xuất
    public int getGiaxuat() {
        return giaxuat;
    }

    public void setGiaxuat(int giaxuat) {
        this.giaxuat = giaxuat;
    }

    // Getter và Setter cho số lượng còn lại
    public int getSoluongconlai() {
        return soluongconlai;
    }

    public void setSoluongconlai(int soluongconlai) {
        this.soluongconlai = soluongconlai;
    }

    // Các Getter và Setter khác
    public void setMaphieunhap(int maphieunhap) {
        this.maphieunhap = maphieunhap;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public void setGianhap(int gianhap) {
        this.gianhap = gianhap;
    }

    public int getMaphieunhap() {
        return maphieunhap;
    }

    public int getMasp() {
        return masp;
    }

    public int getSoluong() {
        return soluong;
    }

    public int getGianhap() {
        return gianhap;
    }
}
