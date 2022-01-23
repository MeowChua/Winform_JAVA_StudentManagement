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
public class admin {
    private String ID,TenAD,DOB,ADDRESS,SDT,EMAIL;

    public admin() {
    }

    public admin(String ID, String TenAD, String DOB, String ADDRESS, String SDT, String EMAIL) {
        this.ID = ID;
        this.TenAD = TenAD;
        this.DOB = DOB;
        this.ADDRESS = ADDRESS;
        this.SDT = SDT;
        this.EMAIL = EMAIL;
    }

    public String getID() {
        return ID;
    }

    public String getTenAD() {
        return TenAD;
    }

    public String getDOB() {
        return DOB;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public String getSDT() {
        return SDT;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setTenAD(String TenAD) {
        this.TenAD = TenAD;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    @Override
    public String toString() {
        return "admin{" + "ID=" + ID + ", TenAD=" + TenAD + ", DOB=" + DOB + ", ADDRESS=" + ADDRESS + ", SDT=" + SDT + ", EMAIL=" + EMAIL + '}';
    }
    
}
