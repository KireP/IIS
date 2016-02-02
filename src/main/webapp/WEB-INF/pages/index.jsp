<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script>
        $(function (){
            $("#trackName").autocomplete({

                source : function(request, response) {
                    $.ajax({

                        url: 'http://localhost:8080/tracksListJson?trackName=' + request.term,
                        datatype: "json",
                        success: function(data) {
                            response($.map(data, function(track) {
                              return {
                                  label: track.artist.name + ' - '+ track.name,
                                  value: track.id
                              }
                            })
                            );
                        }

                    });
                },
                minLength: 4
            });
        });
    </script>
    <title></title>
</head>
<body>
<form method="get" action="tracksList">
    <label>
        Track name:
        <input id="trackName" type="text" name="trackName"/>
    </label>
    <input type="submit" value="Submit"/>
</form>

<table>
    <c:forEach items="${tracks}" var="track">
        <tr>
            <td><a href="tagSimilarTracks?trackID=${track.id}">${track}</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
