<%-- 
    Document   : index
    Created on : Jul 16, 2019, 6:15:19 PM
    Author     : Kostia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TODO</title>
    </head>
    <body>
        <form method = "POST" action="savetodo">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input name="todoText">
            <input type="submit" value="add">
        </form>
        <ul>
            <c:forEach items="${todos}" var ="todo">

                <li>
                    <p>
      Hello <b><c:out value="${pageContext.request.remoteUser}"/></b>
    </p>
                    ${todo.todoText}
                    ${todo.createdDate}
                    ${todo.finishedDate}
                    
                    <c:if test = "${todo.finishedDate == null}">
                        <p> not completed</p>
                          <a href="complete?id=${todo.id}">Complete</a>
                    </c:if>
                    
                    
                    <a href="deletetodo?id=${todo.id}">Delete</a>
                    <a href="edittodo?id=${todo.id}">Edit</a>
                  
     
                  
                   
                </li>
            </c:forEach>
        </ul>
        <a>  <fmt:formatDate value="${todo.CreatedDate}" pattern="dd/MM/yyyy"/></a>
        <a>  <fmt:formatDate value="${todo.FinishedDate}" pattern="dd/MM/yyyy"/></a>
        <a href="logout">Logout</a>
    </body>
</html>
