
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List my stuff</title>
    <script src="actiuni.js" type="text/javascript" ></script>
    <script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
</head>
<body>
<%

HttpSession s = request.getSession(); // citesc sesiunea curenta
Object o = s.getAttribute("id"); // daca pe sesiune exista obiectul numit id sau nu exista voi lua diferite decizii
Object email = s.getAttribute("email");
if(o==null)
{
response.sendRedirect("login.html"); // il trimit la login, nici nu se executa ce e mai jos
}
%>

Hello <b><%=email%></b>

<input type="text" id="name" placeholder="Add my food" />
<input type="button" id="add" value="New" onClick="newToDo()" />
<%--<input type="button" id="delete" value="Delete all" onClick="deleteAll()" />--%>

<div id="listOfToDo">

</div>

<script>
    loadToDo();
</script>

</p>
<a href ="logout.jsp">Logout</a>


</body>
</html>
