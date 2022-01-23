/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author WIN 10
 */
public class SV {
    private String maMH,tenMH,SDT,address,email,dob;

    public SV(String maMH, String tenMH, String SDT, String address, String email, String DOB) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.SDT = SDT;
        this.address = address;
        this.email = email;
        this.dob = DOB;
    }
    public SV() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  

    public String getMaMH() {
        return maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public String getSDT() {
        return SDT;
    }

    public String getAddress() {
        return address;
    }

   

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
    
   
   

    @Override
    public String toString() {
        return "SV{" + "maMH=" + maMH + ", tenMH=" + tenMH + ", SDT=" + SDT + ", address=" + address + ", email=" + email + ", DOB=" + dob + '}';
    }

   
}
