package com.syl;

import java.sql.*;

public class DbConnect {
    private String url;
    private String username;
    private String password;
    private Connection connection;

    public String getUrl () {
        return url;
    }

    public void setUrl (String url) {
        this.url = url;
    }

    public String getUsername () {
        return username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public Boolean getConnect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println("No jdbc driver");
            e.printStackTrace();
        }
        try{
            Connection con = DriverManager.getConnection(url,username,password);
            if(con !=null){
                connection =con;
                return true;
            }
        }catch(SQLException e){
            System.out.println("Database connect failure!");
            e.printStackTrace();
            return false;
        }
       return false;
    }
    public boolean testConnect(){
        if(this.getConnect()){
            String sys ="";
            try{
                Statement stmt = this.connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT sysdate() as sys FROM dual");
                while(rs.next()){
                    sys=rs.getString("sys");
                }
            }catch(SQLException e){
                System.out.println("Statement create failure!");
                e.printStackTrace();
            }
            if(sys!=null && !"".equals(sys)){
                return true;
            }

         }
        return false;
    }

}
