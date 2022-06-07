package usermanagement;

import org.json.JSONObject;
import usermanagement.databese.DBevent;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/listmemo")
public class ListMemoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp){

        HttpSession s = req.getSession();
        Object o = s.getAttribute("id");
        if(o!=null)
        {
            int iduser = (int)o;


            DBevent db = new DBevent();
            List<ListMemo> l = db.getListMemo(iduser);
            List<String> listOfEvent= new ArrayList<>();

            for(int i = 0;i<l.size();i++) {

                ListMemo ml = (ListMemo) l.get(i);
                listOfEvent.add(ml.getEventName());


        }
            JSONObject json = new JSONObject();
            json.put("listFromBackend", listOfEvent); // only event name
            String result = json.toString();
            returnJsonResponse(resp, result);
    }
        else
        {
            error(resp, "operation forbidden. user is not logged in.");
        }
}
private  void returnJsonResponse(HttpServletResponse response, String jsonResponse) {
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

