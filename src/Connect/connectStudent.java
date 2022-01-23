/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import Model.SV;
import Model.admin;
public class connectStudent {
    private Connection conn;

    public connectStudent() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=DBQLSV;username=sa;password=Canhho1234");
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public boolean update(SV s) {
        String sql = "UPDATE Student set  Ten_SV = ?, DOB = ?, ADDRESS = ?, SDT = ?, EMAIL =? WHERE ID_SV = ?";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(6, s.getMaMH());
            ps.setString(1, s.getTenMH());
            ps.setString(2,  s.getDob());
            ps.setString(3, s.getAddress());
            ps.setString(4,s.getSDT());
            ps.setString(5, s.getEmail());
            return ps.executeUpdate() > 0;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }


    public boolean addTeacher(SV s) {
        String sql = "INSERT INTO Student (ID_SV,Ten_SV,DOB,ADDRESS,SDT,EMAIL) VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, s.getMaMH());
            ps.setString(2, s.getTenMH());
             ps.setString(3,  s.getDob());
            ps.setString(4, s.getAddress());
            ps.setString(5,s.getSDT());
            ps.setString(6, s.getEmail());
            return ps.executeUpdate() > 0;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }


  public ArrayList<SV> getListTeacher() {
        ArrayList<SV> list = new ArrayList<>();
        String sql = "SELECT * FROM Student";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SV s = new SV();
                s.setMaMH(rs.getString("ID_SV"));
                s.setTenMH(rs.getString("Ten_SV"));
                s.setDob(rs.getString("DOB"));
                s.setAddress(rs.getString("ADDRESS"));
                s.setSDT(rs.getString("SDT"));
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
            String sql = "DELETE FROM Student WHERE ID_SV = ?";
            statement = conn.prepareCall(sql);
            statement.setString(1, ID);
            statement.execute();
        } catch (SQLException var16) {
            Logger.getLogger(connectStudent.class.getName()).log(Level.SEVERE, (String) null, var16);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException var15) {
                    Logger.getLogger(connectStudent.class.getName()).log(Level.SEVERE, (String) null, var15);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException var14) {
                    Logger.getLogger(connectStudent.class.getName()).log(Level.SEVERE, (String) null, var14);
                }
            }

        }

    }

    public  List<SV> findByIDStudent(String MaMH) {
        List<SV> SPList = new ArrayList();

        CallableStatement statement = null;

        try {
          String sql = "select * from Student where '%" + MaMH + "%' like ?";
            statement = conn.prepareCall(sql);
            statement.setString(1, "%" + MaMH + "%");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                SV sp = new SV(
                        resultSet.getString("ID_SV"),
                        resultSet.getString("Ten_SV"),
                        resultSet.getString("DOB"),
                        resultSet.getString("ADDRESS"),
                        resultSet.getString("SDT"),
                        resultSet.getString("EMAIL"));
                SPList.add(sp);
            }
        } catch (SQLException var19) {
            Logger.getLogger(connectStudent.class.getName()).log(Level.SEVERE, (String)null, var19);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException var18) {
                    Logger.getLogger(connectStudent.class.getName()).log(Level.SEVERE, (String)null, var18);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException var17) {
                    Logger.getLogger(connectStudent.class.getName()).log(Level.SEVERE, (String)null, var17);
                }
            }

        }

        return SPList;
    }
    public  void insert(SV sp) {
        CallableStatement statement = null;

        try {
          String sql = "INSERT INTO Teacher (ID_GV,Ten_GV,ID_MH,DOB,ADDRESS,SDT,EMAIL) VALUES(?,?,?,?,?,?,?)";
            statement = conn.prepareCall(sql);
             statement.setString(1, sp.getMaMH());
           statement.setString(2, sp.getTenMH());
            statement.setString(3,  sp.getDob());
            statement.setString(4, sp.getAddress());
            statement.setString(5, sp.getSDT());
            statement.setString(6, sp.getEmail());
            statement.execute();
        } catch (SQLException var16) {
            Logger.getLogger(connectStudent.class.getName()).log(Level.SEVERE, (String)null, var16);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException var15) {
                    Logger.getLogger(connectStudent.class.getName()).log(Level.SEVERE, (String)null, var15);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException var14) {
                    Logger.getLogger(connectStudent.class.getName()).log(Level.SEVERE, (String)null, var14);
                }
            }

        }

    }
   
    public static void main(String []args){
    new connectStudent();
    }

   

}
