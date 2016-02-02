<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<table>
    <c:forEach items="${tracks}" var="track">
        <tr>
            <td>
                <h3>${track.artist.name}</h3>
                <a href="${track.artist.url}" title="${track.artist.name}">
                    <img src="${track.artist.image}" alt="Image not available"/>
                </a>
            </td>
            <td><a href="${track.url}">${track.name}</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
