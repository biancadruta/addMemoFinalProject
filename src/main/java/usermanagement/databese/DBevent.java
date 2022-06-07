package usermanagement.databese;

import usermanagement.ListMemo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBevent {


    public boolean newEvent(ListMemo u) throws SQLException {
        System.out.println("asa" +u);

        boolean isInserted = false;

        try {
            // ma conecte la db
            final String URL = "jdbc:postgresql:/newevent.cvezgsvjdeaf.eu-central-1.rds.amazonaws.com:5432/myevent";
            final String USERNAME = "eventadd";

            final String PASSWORD = System.getenv("PWDDB");

            System.out.println("parola:" + PASSWORD);

            Class.forName("org.postgresql.Driver");

            Connection  conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 2. creez un prepared ststement si il populez cu date

        PreparedStatement pSt = conn.prepareStatement("INSERT INTO myeventlist (eventname, eventdate, username) VALUES(?,?,?)");
        pSt.setString(1, u.getEventName());

        Date date = Date.valueOf(u.getEventDate());
        pSt.setDate(2, date);

        pSt.setInt(3, u.getIduser());

            // 3. executie
            int insert = pSt.executeUpdate();
            if(insert!=-1)
                isInserted=true;
            System.out.println(isInserted);
            pSt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            isInserted=false;
            System.out.println("aici executa");
        }


        return isInserted;

}
 public List<ListMemo> getListMemo (int idUser){
     ListMemo ml =null;
     List<ListMemo> list = new ArrayList<>();
     // 1. ma conectez la db
     final String URL = "jdbc:postgresql://newevent.cvezgsvjdeaf.eu-central-1.rds.amazonaws.com:5432/myevent";
     final String USERNAME = "eventadd";
     final String PASSWORD = System.getenv("PWDDB");
     int id =-1;
     try {
         Class.forName("org.postgresql.Driver");
         Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

// 2. fac un query pe o tabela , intai creez obiectul


         PreparedStatement pSt = conn.prepareStatement("select * from myeventlist where iduser=?");

         pSt.setInt(1, idUser);


         // 3. executie
         ResultSet rs = pSt.executeQuery();




         // atata timp cat am randuri
         while (rs.next()) {

             ml = new ListMemo();
             ml.setId(rs.getInt("id"));
             ml.setEventName(rs.getString("eventname"));

             Date dateFromDB = rs.getDate("eventdate");
             LocalDate localDate = dateFromDB.toLocalDate();
             ml.setEventDate(localDate);


             list.add(ml);

         }

         rs.close();
         pSt.close();
         conn.close();


     } catch (SQLException | ClassNotFoundException e) {
         e.printStackTrace();
     }


     return list;
 }


    public static void main(String[] args) {

        DBevent db = new DBevent();
        List<ListMemo> l = db.getListMemo(1);

        for(int i = 0;i<l.size();i++) {

            ListMemo ml = (ListMemo) l.get(i);

            System.out.println(ml.toString()); // just to test we get the right data from db
        }
     }

}
