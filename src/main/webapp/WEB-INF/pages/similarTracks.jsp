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
        <h2 class="text-center text-danger">Similar tracks according to your profile:</h2>
        <c:forEach items="${queryTracks}" var="track">
            <h4 class="text-center text-success">${track}</h4>
        </c:forEach>
    </div>
    <br/>

    <div class="row">
        <c:forEach items="${trackDTOs}" var="trackDTO">

            <div class="col-md-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4><a href="${trackDTO.track.url}" target="_blank"
                               style="color: white">${trackDTO.track.name}</a></h4>
                    </div>
                    <div class="panel-body">
                        <a href="${trackDTO.track.artist.image}" title="${trackDTO.track.artist.name}" target="_blank">
                            <img src="${trackDTO.track.artist.image}" alt="Image not available"/>
                        </a>
                    </div>
                    <div class=
                                 <h5>Artist:
                        <a href="${trackDTO.track.artist.url}" tt-success">${trackDTO.track.artist.name}</a>
                        </h5>
                    </div>
                </div>
            </div>

        </c:forEach>
    </div>
</div>
</body>
</html>
