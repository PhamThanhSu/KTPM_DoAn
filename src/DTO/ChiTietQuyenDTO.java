package DTO;

public class ChiTietQuyenDTO {
    private String maNhomQuyen;
    private String maChucNang;
    private String hanhDong;

    // Constructor
    public ChiTietQuyenDTO(String maNhomQuyen, String maChucNang, String hanhDong) {
        this.maNhomQuyen = maNhomQuyen;
        this.maChucNang = maChucNang;
        this.hanhDong = hanhDong;
    }

    // Getters and Setters
    public String getMaNhomQuyen() {
        return maNhomQuyen;
    }

    public void setMaNhomQuyen(String maNhomQuyen) {
        this.maNhomQuyen = maNhomQuyen;
    }

    public String getMaChucNang() {
        return maChucNang;
    }

    public void setMaChucNang(String maChucNang) {
        this.maChucNang = maChucNang;
    }

    public String getHanhDong() {
        return hanhDong;
    }

    public void setHanhDong(String hanhDong) {
        this.hanhDong = hanhDong;
    }
}
