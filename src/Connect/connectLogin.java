/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;

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
import Model.login;
public class connectLogin {
    private Connection conn;

    public connectLogin() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=DBQLSV;username=sa;password=Canhho1234");
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }
     public boolean addStudent(login s) {
        String sql = "INSERT INTO Login(ID,MK) VALUES(?,?)";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, s.getID());
            ps.setString(2, s.getMK());
            return ps.executeUpdate() > 0;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }
      public boolean update(login s) {
        String sql = "UPDATE Login set  MK = ? WHERE ID = ?";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(2, s.getID());
            ps.setString(1, s.getMK());
            return ps.executeUpdate() > 0;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<login> getListID() {
        ArrayList<login> list = new ArrayList<>();
        String sql = "SELECT * FROM Login";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                login s = new login();
                s.setID(rs.getString("ID"));
                s.setMK(rs.getString("MK"));
                list.add(s);
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return list;
    }
    public  void insert(login sp) {
        CallableStatement statement = null;

        try {
          String sql = "INSERT INTO Login(ID,MK) VALUES(?,?)";
            statement = conn.prepareCall(sql);
             statement.setString(1, sp.getID());
             statement.setString(2, sp.getMK());
           
            statement.execute();
        } catch (SQLException var16) {
            Logger.getLogger(connectLogin.class.getName()).log(Level.SEVERE, (String)null, var16);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException var15) {
                    Logger.getLogger(connectLogin.class.getName()).log(Level.SEVERE, (String)null, var15);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException var14) {
                    Logger.getLogger(connectLogin.class.getName()).log(Level.SEVERE, (String)null, var14);
                }
            }

        }
    }
    
    public void delete(String ID) {
        CallableStatement statement = null;

        try {
            String sql = "DELETE FROM Login WHERE ID = ?";
            statement = conn.prepareCall(sql);
            statement.setString(1, ID);
            statement.execute();
        } catch (SQLException var16) {
            Logger.getLogger(connectLogin.class.getName()).log(Level.SEVERE, (String) null, var16);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException var15) {
                    Logger.getLogger(connectLogin.class.getName()).log(Level.SEVERE, (String) null, var15);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException var14) {
                    Logger.getLogger(connectLogin.class.getName()).log(Level.SEVERE, (String) null, var14);
                }
            }

        }

    }
    public boolean checkLogin(String tk,String mk)
    {
        String query;
        String dbUsername, dbPassword,rMK;
        boolean login = false;

        try {
            Statement stmt = (Statement) conn.createStatement();
            query = "SELECT ID, PASSWORD FROM ID;";
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();

            while(rs.next()){
					dbUsername = rs.getString("ID");
					dbPassword = rs.getString("MK");
                                       

					if(dbUsername.equals(tk) && dbPassword.equals(mk)){
                    login = true;
					}
				}
			}
			catch (Exception e) {
            e.printStackTrace();
			}
        return login;
    }
    
    public boolean checkLog(String tk,String mk)
    {
        String query;
        String dbUsername, dbPassword;
        boolean login = false;

        try {
            Statement stmt = (Statement) conn.createStatement();
            query = "SELECT ID, MK FROM Login;";
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();

            while(rs.next()){
					dbUsername = rs.getString("ID");
					dbPassword = rs.getString("MK");

					if(dbUsername.equals(tk) && dbPassword.equals(mk)){
                    login = true;
					}
				}
			}
			catch (Exception e) {
            e.printStackTrace();
			}
        return login;
    }
    
    
    public  List<login> findByTenSp(login TenSp) {
        List<login> SPList = new ArrayList();

        CallableStatement statement = null;

        try {
          String sql = "select * from Login where ID like ?";
            statement = conn.prepareCall(sql);
            statement.setString(1, "%" + TenSp + "%");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                login sp = new login(
                        resultSet.getString("ID"),
                        resultSet.getString("PASSWORD")
                );
                SPList.add(sp);
            }
        } catch (SQLException var19) {
            Logger.getLogger(connectLogin.class.getName()).log(Level.SEVERE, (String)null, var19);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException var18) {
                    Logger.getLogger(connectLogin.class.getName()).log(Level.SEVERE, (String)null, var18);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException var17) {
                    Logger.getLogger(connectLogin.class.getName()).log(Level.SEVERE, (String)null, var17);
                }
            }

        }

        return SPList;
    }
    
  
    public static void main(String []args){
        connectLogin d = new connectLogin();
}

}
