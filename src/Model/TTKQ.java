/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


public class TTKQ {
 private String ID,TENSV,IDMH,TENMH,MARK;

    public TTKQ(String ID, String TENSV, String IDMH, String TENMH, String MARK) {
        this.ID = ID;
        this.TENSV = TENSV;
        this.IDMH = IDMH;
        this.TENMH = TENMH;
        this.MARK = MARK;
    }

    public TTKQ(String ID, String TENSV) {
        this.ID = ID;
        this.TENSV = TENSV;
    }

    public TTKQ(String IDMH, String TENMH, String MARK) {
        this.IDMH = IDMH;
        this.TENMH = TENMH;
        this.MARK = MARK;
    }

    public String getID() {
        return ID;
    }

    public String getTENSV() {
        return TENSV;
    }

    public String getIDMH() {
        return IDMH;
    }

    public String getTENMH() {
        return TENMH;
    }

    public String getMARK() {
        return MARK;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setTENSV(String TENSV) {
        this.TENSV = TENSV;
    }

    public void setIDMH(String IDMH) {
        this.IDMH = IDMH;
    }

    public void setTENMH(String TENMH) {
        this.TENMH = TENMH;
    }

    public void setMARK(String MARK) {
        this.MARK = MARK;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TTKQ{ID=").append(ID);
        sb.append(", TENSV=").append(TENSV);
        sb.append(", IDMH=").append(IDMH);
        sb.append(", TENMH=").append(TENMH);
        sb.append(", MARK=").append(MARK);
        sb.append('}');
        return sb.toString();
    }





    
}
