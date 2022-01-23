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
public class ThongTin {
    private String date;
    private String noidung;

    public ThongTin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getDate() {
        return date;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public ThongTin(String date, String noidung) {
        this.date = date;
        this.noidung = noidung;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ThongTin{date=").append(date);
        sb.append(", noidung=").append(noidung);
        sb.append('}');
        return sb.toString();
    }
    
}
