package usermanagement.databese;

import usermanagement.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUser {
    public boolean newUser(User u) {

        System.out.println(u);
        boolean isInserted = false;

            try {
                // 1. ma conectez la db
                final String URL = "jdbc:postgresql://newevent.cvezgsvjdeaf.eu-central-1.rds.amazonaws.com:5432/myevent";
                final String USERNAME = "eventadd";

                final String PASSWORD = System.getenv("PWDDB");

                System.out.println("parola:" + PASSWORD);

                Class.forName("org.postgresql.Driver");

                Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("next");

                // 2. creez un prepared ststement si il populez cu date
                PreparedStatement pSt = conn.prepareStatement("INSERT INTO memoapp (username, password,) VALUES(?,?)");
                pSt.setString(1, u.getEmail());
                pSt.setString(2, u.getPwd());

                System.out.println("bine");

                // 3. executie
                int insert = pSt.executeUpdate();
                if (insert != -1)
                    isInserted = true;

                System.out.println("da:" +isInserted);

                pSt.close();
                conn.close();

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                isInserted = false;
                System.out.println("aici da");

            }

        System.out.println("a intrat");
            return isInserted;

        }


        public User login (String username, String password){

            User u = null;
            // 1. ma conectez la db
            final String URL = "jdbc:postgresql:/newevent.cvezgsvjdeaf.eu-central-1.rds.amazonaws.com:5432/myevent";
            final String USERNAME = "eventadd";
            final String PASSWORD = System.getenv("PWDDB");
            int id = -1;
            try {
                Class.forName("org.postgresql.Driver");
                Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                System.out.println("s-a logat");
                // 2. fac un query pe o tabela , intai creez obiectul


                PreparedStatement pSt = conn.prepareStatement("select id, username from users where username=? and password=?");

                pSt.setString(1, username);
                pSt.setString(2, password);


                // 3. executie
                ResultSet rs = pSt.executeQuery();


                // atata timp cat am randuri
                while (rs.next()) {

                    u = new User();
                    u.setId(rs.getInt("id"));
                    u.setEmail(rs.getString("username"));

                    System.out.println("nxt");
                }

                rs.close();
                pSt.close();
                conn.close();


            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }


            return u;
        }


        public static void main (String[]args){
            DBUser dbuser = new DBUser();
            User u = new User("gfdghdf", "ererw", "wertewrt");
            boolean b = dbuser.newUser(u);
        }
    }


