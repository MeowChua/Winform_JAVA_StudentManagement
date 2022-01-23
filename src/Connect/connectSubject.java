/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;

/**
 *
 * @author WIN 10
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
import Model.SB;
public class connectSubject {
    private Connection conn;

    public connectSubject() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=DBQLSV;username=sa;password=Canhho1234");
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public boolean update(SB s) {
        String sql = "UPDATE Subject set  TenMH = ?, NXB = ?, Khoa = ? WHERE ID_MH = ?";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(4, s.getMaMH());
            ps.setString(1, s.getTenMH());
            ps.setString(2, s.getNXB());
            ps.setString(3,  s.getKhoa());
            return ps.executeUpdate() > 0;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }


    public boolean addTeacher(SB s) {
        String sql = "INSERT INTO Subject (ID_MH,TenMH,NXB,Khoa) VALUES(?,?,?,?)";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, s.getMaMH());
            ps.setString(2, s.getTenMH());
            ps.setString(3, s.getNXB());
             ps.setString(4,  s.getKhoa());
            return ps.executeUpdate() > 0;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }


  public ArrayList<SB> getListTeacher() {
        ArrayList<SB> list = new ArrayList<>();
        String sql = "SELECT * FROM Subject";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SB s = new SB();
                s.setMaMH(rs.getString("ID_MH"));
                s.setTenMH(rs.getString("TenMH"));
                s.setNXB(rs.getString("NXB"));
                s.setKhoa(rs.getString("Khoa"));
                
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
            String sql = "DELETE FROM Subject WHERE ID_MH = ?";
            statement = conn.prepareCall(sql);
            statement.setString(1, ID);
            statement.execute();
        } catch (SQLException var16) {
            Logger.getLogger(connectSubject.class.getName()).log(Level.SEVERE, (String) null, var16);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException var15) {
                    Logger.getLogger(connectSubject.class.getName()).log(Level.SEVERE, (String) null, var15);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException var14) {
                    Logger.getLogger(connectSubject.class.getName()).log(Level.SEVERE, (String) null, var14);
                }
            }

        }

    }

    public  List<SB> findByIDSubject(String MaMH) {
        List<SB> SPList = new ArrayList();

        CallableStatement statement = null;

        try {
          String sql = "select * from Subject where '%" + MaMH + "%' like ?";
            statement = conn.prepareCall(sql);
            statement.setString(1, "%" + MaMH + "%");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                SB sp = new SB(
                        resultSet.getString("ID_MH"),
                        resultSet.getString("TenMH"),
                        resultSet.getString("NXB"),
                        resultSet.getString("Khoa"));
                SPList.add(sp);
            }
        } catch (SQLException var19) {
            Logger.getLogger(connectSubject.class.getName()).log(Level.SEVERE, (String)null, var19);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException var18) {
                    Logger.getLogger(connectSubject.class.getName()).log(Level.SEVERE, (String)null, var18);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException var17) {
                    Logger.getLogger(connectSubject.class.getName()).log(Level.SEVERE, (String)null, var17);
                }
            }

        }

        return SPList;
    }
    public  void insert(SB sp) {
        CallableStatement statement = null;

        try {
          String sql = "INSERT INTO Teacher (ID_GV,Ten_GV,ID_MH,DOB,ADDRESS,SDT,EMAIL) VALUES(?,?,?,?,?,?,?)";
            statement = conn.prepareCall(sql);
             statement.setString(1, sp.getMaMH());
           statement.setString(2, sp.getTenMH());
            statement.setString(3, sp.getNXB());
            statement.setString(4,  sp.getKhoa());
            statement.execute();
        } catch (SQLException var16) {
            Logger.getLogger(connectSubject.class.getName()).log(Level.SEVERE, (String)null, var16);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException var15) {
                    Logger.getLogger(connectSubject.class.getName()).log(Level.SEVERE, (String)null, var15);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException var14) {
                    Logger.getLogger(connectSubject.class.getName()).log(Level.SEVERE, (String)null, var14);
                }
            }

        }

    }
    public static void main(String []args){
    new connectSubject();
    }


}