/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

public class hoc {
   private String maSV,maMH;

    public hoc() {
    }

    public hoc(String maSV, String maMH) {
        this.maSV = maSV;
        this.maMH = maMH;
    }

    public String getMaSV() {
        return maSV;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    @Override
    public String toString() {
        return "hoc{" + "maSV=" + maSV + ", maMH=" + maMH + '}';
    }
   
}
