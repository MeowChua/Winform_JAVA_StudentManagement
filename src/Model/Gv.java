/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author WIN 10
 */
public class Gv {
    private String maGv,tenGv,maMH,address,sdt,email,DOB;
    private String tenMH;
    public Gv(){};

    public Gv(String maGv, String tenGv, String maMH, String DOB, String address, String sdt,String Email) {
        this.maGv = maGv;
        this.tenGv = tenGv;
        this.maMH = maMH;
        this.address = address;
        this.sdt = sdt;
        this.DOB = DOB;
        this.email=Email;
    }

    public Gv(String maGv, String tenGv, String maMH, String tenMH) {
        this.maGv = maGv;
        this.tenGv = tenGv;
        this.maMH = maMH;
        this.tenMH = tenMH;
    }

    public Gv(String tenGv, String maMH) {
        this.tenGv = tenGv;
        this.maMH = maMH;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMaGv() {
        return maGv;
    }

    public String getTenGv() {
        return tenGv;
    }

    public String getMaMH() {
        return maMH;
    }

    public String getAddress() {
        return address;
    }

    public String getSdt() {
        return sdt;
    }

    public String getDOB() {
        return DOB;
    }

    public void setMaGv(String maGv) {
        this.maGv = maGv;
    }

    public void setTenGv(String tenGv) {
        this.tenGv = tenGv;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    @Override
    public String toString() {
        return "Gv{" + "maGv=" + maGv + ", tenGv=" + tenGv + ", maMH=" + maMH + ", address=" + address + ", sdt=" + sdt + ", DOB=" + DOB + '}';
    }
}
