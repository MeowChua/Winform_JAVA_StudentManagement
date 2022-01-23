/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;

import Model.Mark;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WIN 10
 */
public class connectMark {
    private Connection conn;

    public connectMark() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=DBQLSV;username=sa;password=Canhho1234");
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }
    public boolean update(Mark s) {
        String sql = "UPDATE DIEMTHI set  ID_MH = ?, MARK = ? WHERE ID_SV = ?";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(3, s.getID_SV());
            ps.setString(1, s.getID_MH());
            ps.setFloat(2,  s.getMark());
           
            return ps.executeUpdate() > 0;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }


    public boolean addTeacher(Mark s) {
        String sql = "INSERT INTO DIEMTHI (ID_SV,ID_MH,MARK) VALUES(?,?,?)";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, s.getID_SV());
            ps.setString(2, s.getID_MH());
             ps.setFloat(3,  s.getMark());
           
            return ps.executeUpdate() > 0;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }


  public ArrayList<Mark> getListTeacher() {
        ArrayList<Mark> list = new ArrayList<>();
        String sql = "SELECT * FROM DIEMTHI";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Mark s = new Mark();
                s.setID_SV(rs.getString("ID_SV"));
                s.setID_MH(rs.getString("ID_MH"));
                s.setMark(rs.getFloat("MARK"));
               
                list.add(s);
               
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return list;
    }

    public void delete(String ID) {
        CallableStatement statement = null;

        try {
            String sql = "DELETE FROM DIEMTHI WHERE ID_SV = ?";
            statement = conn.prepareCall(sql);
            statement.setString(1, ID);
            statement.execute();
        } catch (SQLException var16) {
            Logger.getLogger(connectMark.class.getName()).log(Level.SEVERE, (String) null, var16);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException var15) {
                    Logger.getLogger(connectMark.class.getName()).log(Level.SEVERE, (String) null, var15);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException var14) {
                    Logger.getLogger(connectMark.class.getName()).log(Level.SEVERE, (String) null, var14);
                }
            }

        }

    }

    public  List<Mark> findByIDStudent(String MaMH) {
        List<Mark> SPList = new ArrayList();

        CallableStatement statement = null;

        try {
          String sql = "select * from DIEMTHI where '%" + MaMH + "%' like ?";
            statement = conn.prepareCall(sql);
            statement.setString(1, "%" + MaMH + "%");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Mark sp = new Mark(
                        resultSet.getString("ID_SV"),
                        resultSet.getString("ID_MH"),
                        resultSet.getFloat("MARK"));
                SPList.add(sp);
            }
        } catch (SQLException var19) {
            Logger.getLogger(connectMark.class.getName()).log(Level.SEVERE, (String)null, var19);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException var18) {
                    Logger.getLogger(connectMark.class.getName()).log(Level.SEVERE, (String)null, var18);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException var17) {
                    Logger.getLogger(connectMark.class.getName()).log(Level.SEVERE, (String)null, var17);
                }
            }

        }

        return SPList;
    }
   

    
}
