package DTO;

public class GiamGiaSanPhamDTO {
    private int magiamgia;
    private int masp;

    // Constructors
    public GiamGiaSanPhamDTO() {}

    public GiamGiaSanPhamDTO(int magiamgia, int masp) {
        this.magiamgia = magiamgia;
        this.masp = masp;
    }

    // Getters and Setters
    public int getMagiamgia() {
        return magiamgia;
    }

    public void setMagiamgia(int magiamgia) {
        this.magiamgia = magiamgia;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }
}
