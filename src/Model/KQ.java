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
public class KQ {
    private String maSV,maMH;
    private float mark;
    public KQ(){};
    public KQ(String maSV, String maMH, float mark) {
        this.maSV = maSV;
        this.maMH = maMH;
        this.mark = mark;
    }

    public String getMaSV() {
        return maSV;
    }

    public String getMaMH() {
        return maMH;
    }

    public float getMark() {
        return mark;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "KQ{" + "maSV=" + maSV + ", maMH=" + maMH + ", mark=" + mark + '}';
    }
}
