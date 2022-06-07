<%--
  Created by IntelliJ IDEA.
  User: biancadruta
  Date: 06.06.2022
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>log out</title>
</head>
<body>
<%
    HttpSession s = request.getSession();
    s.invalidate();
    response.sendRedirect("login.html");
%>
</body>
</html>
