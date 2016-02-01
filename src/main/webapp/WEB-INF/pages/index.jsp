<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<form method="get" action="tracksList">
    <label>
        Track name:
        <input type="text" name="trackName"/>
    </label>
    <input type="submit" value="Submit"/>
</form>

<table>
    <c:forEach items="${tracks}" var="track">
        <tr>
            <td>${track}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
