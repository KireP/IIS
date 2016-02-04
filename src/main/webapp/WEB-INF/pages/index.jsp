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
                                            value: track.id,
                                            count: track.userCount
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
                    $("#user_profile li").each(function () {
                        var trackId = $(this).attr('id');
                        if (trackId == selectedTrackId) {
                            shouldAppendSong = false;
                        }
                    });
                    var selectedTrackLi = $("<li class='list-group-item'>").text(ui.item.label);
                    if (shouldAppendSong) {
                        $("#user_profile").append(selectedTrackLi);

                        var inputHidden = $('<input />')
                                .attr('type', 'hidden')
                                .attr('name', 'trackId')
                                .attr('value', selectedTrackId);
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
<div class="container" style="padding-top: 10px">
    <div class="row">
        <div class="jumbotron">
            <h1 class="text-center">Track recommendation system</h1>
            <br/>

            <p>
                Enter your favourite tracks below and find their similar.
            </p>
        </div>
        <label class="col-md-4">
            Track:
            <input id="trackName" type="text" name="trackName" class="form-control" placeholder="Enter a track"/>
        </label>

        <div class="col-md-4"></div>
        <div id="preferences" class="col-md-4">
            <h2>Your profile</h2>
            <ul id="user_profile" class="list-group"></ul>

            <form method="post" action="findSimilarities" id="hidden_form">
                <input type="submit" value="Submit" class="btn btn-success">
            </form>
        </div>
    </div>
</div>
</body>
</html>
