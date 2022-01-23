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
public class day {
    private String maMH,maGV;

    public String getMaMH() {
        return maMH;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public day() {
    }

    public day(String maMH, String maGV) {
        this.maMH = maMH;
        this.maGV = maGV;
    }

    @Override
    public String toString() {
        return "day{" + "maMH=" + maMH + ", maGV=" + maGV + '}';
    }
    
}
