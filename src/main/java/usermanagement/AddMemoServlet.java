package usermanagement;

import usermanagement.databese.DBevent;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/addmemo")
public class AddMemoServlet extends HttpServlet{

@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp){
    HttpSession s = req.getSession();
    Object o = s.getAttribute("id"); // daca pe sesiune exista obiectul numit id sau nu exista voi lua diferite decizii

if(o!=null) {

    String memoname = req.getParameter("eventname");
    LocalDate ld = LocalDate.now();
    int iduser = (int) o;

    ListMemo lm = new ListMemo(memoname, ld, iduser);
    DBevent db = new DBevent();
    try {
        db.newEvent(lm);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
else
{
    error(resp, "operation forbidden. user is not logged in.");
}
}

    private void returnJsonResponse(HttpServletResponse response, String jsonResponse) {
        response.setContentType("application/json");
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pr != null;
        pr.write(jsonResponse);
        pr.close();
    }

    private void error( HttpServletResponse resp, String mesaj) {

        try {
            PrintWriter pw = resp.getWriter();
            pw.println(mesaj);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



