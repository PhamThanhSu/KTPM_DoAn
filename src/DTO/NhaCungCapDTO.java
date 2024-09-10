package DTO;

public class NhaCungCapDTO {
    private int mancc;
    private String tenncc;
    private String diachi;
    private String email;
    private String sdt;

    public NhaCungCapDTO() {
    }

    public NhaCungCapDTO(int mancc, String tenncc, String diachi, String email, String sdt) {
        this.mancc = mancc;
        this.tenncc = tenncc;
        this.diachi = diachi;
        this.email = email;
        this.sdt = sdt;
    }
    public NhaCungCapDTO( String tenncc, String diachi, String email, String sdt) {
        this.tenncc = tenncc;
        this.diachi = diachi;
        this.email = email;
        this.sdt = sdt;
    }
    public int getMancc() {
        return mancc;
    }

    public void setMancc(int mancc) {
        this.mancc = mancc;
    }

    public String getTenncc() {
        return tenncc;
    }

    public void setTenncc(String tenncc) {
        this.tenncc = tenncc;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }


    @Override
    public String toString() {
        return "NhaCungCap{" + "mancc=" + mancc + ", tenncc=" + tenncc + ", diachi=" + diachi + ", email=" + email + ", sdt=" + sdt + '}';
    }
    
}
