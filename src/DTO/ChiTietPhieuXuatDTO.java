package DTO;

public class ChiTietPhieuXuatDTO {
    private int maphieuxuat;
    private int masp;
    private int soluong;
    private int giaxuat;
    private int gianhap;  // Thêm trường gianhap

    public ChiTietPhieuXuatDTO() {}

    public ChiTietPhieuXuatDTO(int maphieuxuat, int masp, int soluong, int giaxuat, int gianhap) {
        this.maphieuxuat = maphieuxuat;
        this.masp = masp;
        this.soluong = soluong;
        this.giaxuat = giaxuat;
        this.gianhap = gianhap;  // Khởi tạo giá trị gianhap
    }
    
    public ChiTietPhieuXuatDTO(int maphieuxuat, int masp, int soluong, int giaxuat) {
        this.maphieuxuat = maphieuxuat;
        this.masp = masp;
        this.soluong = soluong;
        this.giaxuat = giaxuat;
    }

    public int getMaphieuxuat() {
        return maphieuxuat;
    }

    public void setMaphieuxuat(int maphieuxuat) {
        this.maphieuxuat = maphieuxuat;
    }

    public int getMaSp() {
        return masp;
    }

    public void setMaSp(int masp) {
        this.masp = masp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGiaxuat() {
        return giaxuat;
    }

    public void setGiaxuat(int giaxuat) {
        this.giaxuat = giaxuat;
    }

    public int getGianhap() {  // Getter cho gianhap
        return gianhap;
    }

    public void setGianhap(int gianhap) {  // Setter cho gianhap
        this.gianhap = gianhap;
    }

    @Override
    public String toString() {
        return "ChiTietPhieuXuatDTO{" +
                "maphieuxuat=" + maphieuxuat +
                ", masp=" + masp +
                ", soluong=" + soluong +
                ", giaxuat=" + giaxuat +
                ", gianhap=" + gianhap +  // Thêm gianhap vào toString
                '}';
    }
}
