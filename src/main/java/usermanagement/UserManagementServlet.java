package usermanagement;


import usermanagement.databese.DBUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;

@WebServlet("/userManagement")

public class UserManagementServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action"); // name as in the html form
        System.out.println("action is:" + action);
        boolean succes = false;
        if (action != null && action.equalsIgnoreCase("NEW")) {

            succes = newUser(req, resp);

            if (succes) {

                RequestDispatcher rd = req.getRequestDispatcher("login.html");
                try {
                    rd.forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();

                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            } else {
                error(resp, "there is an error while trying to create this user, pls try again");

            }
        }else if (action != null && action.equalsIgnoreCase("LOGIN")) {
                //afisare
                succes = loginUser(req, resp);
                if (succes) // in
                {
                    RequestDispatcher rd = req.getRequestDispatcher("listMyStuff.jsp");
                    try {
                        rd.forward(req, resp);
                    } catch (ServletException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    RequestDispatcher rd = req.getRequestDispatcher("login.html");
                    try {
                        rd.forward(req, resp);
                    } catch (ServletException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        else if (action != null && action.equalsIgnoreCase("OUT")) {
                HttpSession s = req.getSession();
                s.invalidate();
                try {
                    resp.sendRedirect("login.html");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        else {
                System.out.println("nu a venit action, deci nu fac nimic ");
                error(resp, "error on ui side");
            }

        }

    private boolean newUser(HttpServletRequest req, HttpServletResponse resp){
        // citesc date de la  browser email , parola, confimare parola

        String email = req.getParameter("email");
        String pwd = req.getParameter("pwd");
        String confirmPwd = req.getParameter("confirmPwd");

        System.out.println(pwd + confirmPwd);
        // validari
        if (!pwd.equals(confirmPwd)) {
            error(resp, "pwd is not the same as confirm password");
            return false;
        }
        DBUser dbUser = new DBUser();
        User u = new User(email,pwd, confirmPwd);
        boolean inserted = dbUser.newUser(u);


        return inserted;

    }



    private boolean loginUser(HttpServletRequest req, HttpServletResponse resp) {
        User u = null;
        String email = req.getParameter("email");
        String pwd = req.getParameter("pwd");
        boolean isLoggedIn=false;

        DBUser dbUser = new DBUser();
        u = dbUser.login(email, pwd);
        if (u != null) // i am in
        {
            HttpSession s = req.getSession();
            s.setAttribute("id", u.getId());
            s.setAttribute("email", u.getEmail());
            isLoggedIn=true;
        }
        return isLoggedIn;
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

}
