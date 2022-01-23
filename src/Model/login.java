/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


public class login {
private String ID,MK;

   

    public login(String ID, String MK) {
        this.ID = ID;
        this.MK = MK;
       
    }

    public login() {
    }

   

    public String getID() {
        return ID;
    }

    public String getMK() {
        return MK;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setMK(String MK) {
        this.MK = MK;
    }

    @Override
    public String toString() {
        return "login{" + "ID=" + ID + ", MK=" + MK + '}';
    }

  

}
