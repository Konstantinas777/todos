<%-- 
    Document   : editTodo
    Created on : Jul 5, 2019, 7:06:38 PM
    Author     : Kostia
--%>

<%@page import="javax.persistence.EntityManager"%>
<%@page import="lt.bit.todos.db.Todos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action="save">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <%
                Todos todo = (Todos) request.getAttribute("todos");
            %>
            <% if (todo != null)%>
            Id: <input name="id"  value="<%=todo.getId()%>">
            User Id: <input name="UserId"  value="<%=todo.getUserId().getId()%>">
            Text: <input name="Text" value="<%=(todo == null) ? "" : todo.getTodoText()%>">

            Created Date: <input name="date" placeholder="YYYY-MM-DD" required pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" 
                                 value="<%=(todo == null) ? "" : todo.getCreatedDate()%>"> 
            Finished Date: <input name="findate" placeholder="YYYY-MM-DD" required pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" 
                                  value="<%=(todo == null) ? "" : todo.getFinishedDate()%>"> 
            <input type="submit" value="Save">
        </form>
        <a href="./">Cancel<a/>
    </body>
</html>
