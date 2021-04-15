package pl.coderslab.workshop_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private String dBUrl;
    private String dBUser;
    private String dBName;
    private String dBPassword;


    public DBUtil(String dBUrl, String dBUser, String dBPassword, String dBName) {
        this.dBUrl = dBUrl;
        this.dBUser = dBUser;
        this.dBPassword = dBPassword;
        this.dBName = dBName;
    }

    public /*static*/ Connection connect() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:" + dBUrl + '/' + dBName + "?useSSH=false&characterEncoding=utf8", dBUser, dBPassword);
            return conn;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
}


