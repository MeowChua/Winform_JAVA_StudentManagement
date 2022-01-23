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
import Model.Gv;
import javax.swing.JOptionPane;
public class connectTeacher {
    private Connection conn;

    public connectTeacher() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=DBQLSV;username=sa;password=Canhho1234");
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public boolean update(Gv s) {
        String sql = "UPDATE Teacher set  Ten_GV = ?, ID_MH = ?, DOB = ?, ADDRESS = ?, SDT = ?, EMAIL =? WHERE ID_GV = ?";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(7, s.getMaGv());
            ps.setString(1, s.getTenGv());
            ps.setString(2, s.getMaMH());
            ps.setString(3,  s.getDOB());
            ps.setString(4, s.getAddress());
            ps.setString(5,s.getSdt());
            ps.setString(6, s.getEmail());
            return ps.executeUpdate() > 0;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }


    public boolean addTeacher(Gv s) {
        String sql = "INSERT INTO Teacher (ID_GV,Ten_GV,ID_MH,DOB,ADDRESS,SDT,EMAIL) VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, s.getMaGv());
            ps.setString(2, s.getTenGv());
            ps.setString(3, s.getMaMH());
             ps.setString(4,  s.getDOB());
            ps.setString(5, s.getAddress());
            ps.setString(6,s.getSdt());
            ps.setString(7, s.getEmail());
            return ps.executeUpdate() > 0;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }


  public ArrayList<Gv> getListTeacher() {
        ArrayList<Gv> list = new ArrayList<>();
        String sql = "SELECT * FROM Teacher";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Gv s = new Gv();
                s.setMaGv(rs.getString("ID_GV"));
                s.setTenGv(rs.getString("Ten_GV"));
                s.setMaMH(rs.getString("ID_MH"));
                s.setDOB(rs.getString("DOB"));
                s.setAddress(rs.getString("ADDRESS"));
                s.setSdt(rs.getString("SDT"));
                s.setEmail(rs.getString("EMAIL"));
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
            String sql = "DELETE FROM Teacher WHERE ID_GV = ?";
            statement = conn.prepareCall(sql);
            statement.setString(1, ID);
            statement.execute();
        } catch (SQLException var16) {
            Logger.getLogger(connectTeacher.class.getName()).log(Level.SEVERE, (String) null, var16);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException var15) {
                    Logger.getLogger(connectTeacher.class.getName()).log(Level.SEVERE, (String) null, var15);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException var14) {
                    Logger.getLogger(connectTeacher.class.getName()).log(Level.SEVERE, (String) null, var14);
                }
            }

        }

    }

    
              
    public  void insert(Gv sp) {
        CallableStatement statement = null;

        try {
          String sql = "INSERT INTO Teacher (ID_GV,Ten_GV,ID_MH,DOB,ADDRESS,SDT,EMAIL) VALUES(?,?,?,?,?,?,?)";
            statement = conn.prepareCall(sql);
             statement.setString(1, sp.getMaGv());
           statement.setString(2, sp.getTenGv());
            statement.setString(3, sp.getMaMH());
            statement.setString(4,  sp.getDOB());
            statement.setString(5, sp.getAddress());
            statement.setString(6, sp.getSdt());
            statement.setString(7, sp.getEmail());
            statement.execute();
        } catch (SQLException var16) {
            Logger.getLogger(connectTeacher.class.getName()).log(Level.SEVERE, (String)null, var16);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException var15) {
                    Logger.getLogger(connectTeacher.class.getName()).log(Level.SEVERE, (String)null, var15);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException var14) {
                    Logger.getLogger(connectTeacher.class.getName()).log(Level.SEVERE, (String)null, var14);
                }
            }

        }

    }
    
    public  List<Gv> findByIDTeacher(String MaMH) {
        List<Gv> SPList = new ArrayList();

        CallableStatement statement = null;

        try {
          String sql = "select * from Teacher where '%" + MaMH + "%' like ?";
            statement = conn.prepareCall(sql);
            statement.setString(1, "%" + MaMH + "%");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Gv sp = new Gv(
                        resultSet.getString("ID_GV"),
                        resultSet.getString("Ten_GV"),
                        resultSet.getString("ID_MH"),
                        resultSet.getString("DOB"),
                        resultSet.getString("ADDRESS"),
                        resultSet.getString("SDT"),
                        resultSet.getString("EMAIL"));
                SPList.add(sp);
            }
        } catch (SQLException var19) {
            Logger.getLogger(connectTeacher.class.getName()).log(Level.SEVERE, (String)null, var19);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException var18) {
                    Logger.getLogger(connectTeacher.class.getName()).log(Level.SEVERE, (String)null, var18);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException var17) {
                    Logger.getLogger(connectTeacher.class.getName()).log(Level.SEVERE, (String)null, var17);
                }
            }

        }

        return SPList;
    }
    
    public static void main(String []args){
    new connectTeacher();
    }


}