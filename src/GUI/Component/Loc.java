/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Component;

import BUS.NhaCungCapBUS;
import BUS.PhieuNhapBUS;
import DTO.PhieuNhapDTO;
import GUI.PhieuNhap;
import java.util.ArrayList;

/**
 *
 * @author this pc
 */
public class Loc {
    private PhieuNhap phieuNhap;
    //PhieuNhap phieuNhap = new PhieuNhap();
    PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
    NhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();
    ArrayList<PhieuNhapDTO> selectedPNproducts;
    public Loc(){
        
    }
    
    
}
