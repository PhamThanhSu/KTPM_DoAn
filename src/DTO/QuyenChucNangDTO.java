package DTO;

public class QuyenChucNangDTO {
    private String maChucNang;
    private String tenChucNang;
    private int trangThai;

    // Constructor
    public QuyenChucNangDTO(String maChucNang, String tenChucNang, int trangThai) {
        this.maChucNang = maChucNang;
        this.tenChucNang = tenChucNang;
        this.trangThai = trangThai;
    }

    // Getters and Setters
    public String getMaChucNang() {
        return maChucNang;
    }

    public void setMaChucNang(String maChucNang) {
        this.maChucNang = maChucNang;
    }

    public String getTenChucNang() {
        return tenChucNang;
    }

    public void setTenChucNang(String tenChucNang) {
        this.tenChucNang = tenChucNang;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "QuyenChucNangDTO{" +
                "maChucNang='" + maChucNang + '\'' +
                ", tenChucNang='" + tenChucNang + '\'' +
                ", trangThai=" + trangThai +
                '}';
    }
}
