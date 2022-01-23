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
public class Mark {
    private String ID_SV,ID_MH;
    private float Mark;

    public Mark() {
    }

    public Mark(String ID_SV, String ID_MH, float Mark) {
        this.ID_SV = ID_SV;
        this.ID_MH = ID_MH;
        this.Mark = Mark;
    }

    public String getID_SV() {
        return ID_SV;
    }

    public String getID_MH() {
        return ID_MH;
    }

    public float getMark() {
        return Mark;
    }

    public void setID_SV(String ID_SV) {
        this.ID_SV = ID_SV;
    }

    public void setID_MH(String ID_MH) {
        this.ID_MH = ID_MH;
    }

    public void setMark(float Mark) {
        this.Mark = Mark;
    }

    @Override
    public String toString() {
        return "Mark{" + "ID_SV=" + ID_SV + ", ID_MH=" + ID_MH + ", Mark=" + Mark + '}';
    }
    
    
}
