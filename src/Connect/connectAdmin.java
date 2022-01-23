/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.admin;
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
public class connectAdmin {
    private Connection conn;

    public connectAdmin() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=DBQLSV;username=sa;password=Canhho1234");
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }
    public boolean update(admin s) {
        String sql = "UPDATE ADMIN set  TenAD = ?, DOB = ?, ADDRESS = ?, Email = ?, SDT = ? WHERE ID = ?";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(6, s.getID());
            ps.setString(1, s.getTenAD());
            ps.setString(2, s.getDOB());
            ps.setString(3, s.getADDRESS());
            ps.setString(4, s.getEMAIL());
            ps.setString(5, s.getSDT());
           
            return ps.executeUpdate() > 0;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }


    public boolean addTeacher(admin s) {
        String sql = "INSERT INTO ADMIN (ID,TenAD,DOB,ADDRESS,EMAIL,SDT) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, s.getID());
            ps.setString(2, s.getTenAD());
            ps.setString(3, s.getDOB());
            ps.setString(4, s.getADDRESS());
            ps.setString(5, s.getEMAIL());
            ps.setString(6, s.getSDT());
            return ps.executeUpdate() > 0;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }


  public ArrayList<admin> getListTeacher() {
        ArrayList<admin> list = new ArrayList<>();
        String sql = "SELECT * FROM ADMIN";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                admin s = new admin();
                s.setID(rs.getString("ID"));
                s.setTenAD(rs.getString("TenAD"));
                s.setDOB(rs.getString("DOB"));
                s.setADDRESS(rs.getString("ADDRESS"));
                s.setEMAIL(rs.getString("EMAIL"));
                s.setSDT(rs.getString("SDT"));
                
               
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
            String sql = "DELETE FROM ADMIN WHERE ID = ?";
            statement = conn.prepareCall(sql);
            statement.setString(1, ID);
            statement.execute();
        } catch (SQLException var16) {
            Logger.getLogger(connectAdmin.class.getName()).log(Level.SEVERE, (String) null, var16);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException var15) {
                    Logger.getLogger(connectAdmin.class.getName()).log(Level.SEVERE, (String) null, var15);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException var14) {
                    Logger.getLogger(connectAdmin.class.getName()).log(Level.SEVERE, (String) null, var14);
                }
            }

        }

    }

    public  List<admin> findByIDStudent(String MaMH) {
        List<admin> SPList = new ArrayList();

        CallableStatement statement = null;

        try {
          String sql = "select * from ADMIN where '%" + MaMH + "%' like ?";
            statement = conn.prepareCall(sql);
            statement.setString(1, "%" + MaMH + "%");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                admin sp = new admin(
                        resultSet.getString("ID"),
                        resultSet.getString("TenAD"),
                        resultSet.getString("DOB"),
                        resultSet.getString("ADDRESS"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("SDT"));
                SPList.add(sp);
            }
        } catch (SQLException var19) {
            Logger.getLogger(connectAdmin.class.getName()).log(Level.SEVERE, (String)null, var19);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException var18) {
                    Logger.getLogger(connectAdmin.class.getName()).log(Level.SEVERE, (String)null, var18);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException var17) {
                    Logger.getLogger(connectAdmin.class.getName()).log(Level.SEVERE, (String)null, var17);
                }
            }

        }

        return SPList;
    }
   

    
}
