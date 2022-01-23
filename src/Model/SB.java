/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author WIN 10
 */
public class SB {
    private String maMH,tenMH,NXB,Khoa;

    public SB(String maMH, String tenMH, String NXB, String Khoa) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.NXB = NXB;
        this.Khoa = Khoa;
    }

   

    public SB() {
    }

    public String getMaMH() {
        return maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public String getNXB() {
        return NXB;
    }

   
    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public void setNXB(String NXB) {
        this.NXB = NXB;
    }

    public String getKhoa() {
        return Khoa;
    }

    public void setKhoa(String Khoa) {
        this.Khoa = Khoa;
    }

    @Override
    public String toString() {
        return "SB{" + "maMH=" + maMH + ", tenMH=" + tenMH + ", NXB=" + NXB + ", Khoa=" + Khoa + '}';
    }

    
    
}
