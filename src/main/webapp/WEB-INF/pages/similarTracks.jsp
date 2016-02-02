<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Similar tracks</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="padding-top: 10px">
    <div class="row">
        <h1 class="text-center text-danger">Similar tracks for: ${queryTrack}.</h1>
    </div>
    <br/>

    <div class="row">
        <c:forEach items="${tracks}" var="track">

            <div class="col-md-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4><a href="${track.url}" target="_blank" style="color: white">${track.name}</a></h4>
                    </div>
                    <div class="panel-body">
                        <a href="${track.artist.image}" title="${track.artist.name}" target="_blank">
                            <img src="${track.artist.image}" alt="Image not available"/>
                        </a>
                    </div>
                    <div class="panel-footer">
                        <h4>Artist:
                            <a href="${track.artist.url}" target="_blank" class="text-success">${track.artist.name}</a>
                        </h4>
                    </div>
                </div>
            </div>

        </c:forEach>
    </div>
</div>
</body>
</html>
