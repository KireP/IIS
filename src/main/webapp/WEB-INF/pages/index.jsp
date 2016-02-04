<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Index</title>

    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script>
        $(function () {
            $("#trackName").autocomplete({
                source: function (request, response) {
                    $.ajax({
                        url: 'http://localhost:8080/tracksListJson?input=' + request.term,
                        dataType: "json",
                        success: function (data) {
                            response($.map(data, function (track) {
                                        return {
                                            label: track.artist.name + ' - ' + track.name,
                                            value: track.id
                                        }
                                    })
                            );
                        }

                    });
                },
                minLength: 4,
                select: function (event, ui) {
                    event.preventDefault();
                    $("#trackName").val("");
                    var selectedTrackId = ui.item.value;
                    var shouldAppendSong = true;
                    // window.location.href = 'http://localhost:8080/tagSimilarTracks?trackID=' + selectedTrackId;
                    $("#user_profile li").each(function () {
                        var trackId = $(this).attr('id');
                        if (trackId == selectedTrackId) {
                            shouldAppendSong = false;
                        }
                    });
                    var selectedTrackLi = $("<li>").text(ui.item.label).attr('id', selectedTrackId);
                    if (shouldAppendSong) {
                        $("#user_profile").append(selectedTrackLi);
                        var inputHidden = $('<input />')
                                .attr('type', 'hidden')
                                .attr('name', 'trackId')
                                .attr('value', selectedTrackId)
                        $("#hidden_form").append(inputHidden);
                    }
                },

                focus: function (event, ui) {
                    event.preventDefault();
                    $("#trackName").val(ui.item.label);
                }
            });
        });
    </script>

    <style>
        .ui-autocomplete-loading {
            background: white url("http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.2/themes/smoothness/images/ui-anim_basic_16x16.gif") right center no-repeat;
        }
    </style>
</head>
<body>
<label>
    Track name:
    <input id="trackName" type="text" name="trackName"/>
</label>

<div id="preferences" style="float:right">
    <h4>Your profile</h4>
    <ul id="user_profile"></ul>

    <form method="post" action="findSimilarities" id="hidden_form">
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
