package me.ershad;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DbCon {

    Connection con;
    PreparedStatement ps;

    private String url;
    private String driver;
    private String username;
    private String password;

    public DbCon() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties");
        Properties prop = new Properties();
        prop.load(is);

        setUrl(prop.getProperty("url"));
        setDriver(prop.getProperty("driver"));
        setUsername(prop.getProperty("username"));
        setPassword(prop.getProperty("password"));
    }

    public int runQuery(String query, String[] params){
        int id = 0;
        ResultSet rs = null;
        try {
            Class.forName(getDriver());
            con = DriverManager.getConnection(getUrl(), getUsername(), getPassword());
            ps = con.prepareStatement(query);
            for(int x = 0; x < params.length; x++){
                ps.setString(x+1, params[x]);
            }
            rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt("id");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(rs != null) {
                    rs.close();
                }
                if(ps != null){
                    ps.cancel();
                }
                if(con != null){
                    con.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return id;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getDriver() {
        return driver;
    }
    public void setDriver(String driver) {
        this.driver = driver;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
