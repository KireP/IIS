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
                        dataType: "json",
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
                minLength: 4,
                select: function(event, ui) {
                    event.preventDefault();
                    $("#trackName").val(ui.item.label);
                    var selectedTrackId = ui.item.value;
                    var requestUrl = 'http://localhost:8080/tagSimilarTracks?trackID=' + selectedTrackId;
                    $.ajax({
                        type : 'GET',
                        url : requestUrl,
                        dataType : 'html',
                        success : function(data) {
                            document.open();
                            document.write(data);
                            document.close();
                        }
                    });
                }
            });
        });
    </script>

    <style>
        .ui-autocomplete-loading {
            background: white url("http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.2/themes/smoothness/images/ui-anim_basic_16x16.gif") right center no-repeat;
        }
    </style>

    <title></title>
</head>
<body>
<form>
    <label>
        Track name:
        <input id="trackName" type="text" name="trackName"/>
    </label>
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
