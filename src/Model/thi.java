/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


public class thi {
    private String maMH,maSV;
    private float mark;

    public thi() {
    }

    public thi(String maMH, String maSV, float mark) {
        this.maMH = maMH;
        this.maSV = maSV;
        this.mark = mark;
    }

    public String getMaMH() {
        return maMH;
    }

    public String getMaSV() {
        return maSV;
    }

    public float getMark() {
        return mark;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "thi{" + "maMH=" + maMH + ", maSV=" + maSV + ", mark=" + mark + '}';
    }
    
}
