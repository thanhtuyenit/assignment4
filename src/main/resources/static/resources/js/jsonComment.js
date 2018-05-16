$(document).ready(function () {
    getAllComments();
});

function addCommentLine(data) {
    var comment = $('<div style="color:#000">');
    var commentOwner = $("<div>");
    var dateComment = $('<div style="font-size: 10px;">');
    var commentContent = $('<div style="font-size:  20px; color: #3e5f64;">');

    commentOwner.html(data.user.lastName);
    dateComment.html(data.createdComment);
    commentContent.html(data.message);

    commentOwner.addClass("user");
    commentContent.addClass("comment-content");

    comment.append(commentOwner);
    comment.append(dateComment);
    comment.append(commentContent);
    comment.addClass("col col-md-12 comment");
    
    $(".comment").first().before(comment);
}
function getAllComments() {
	var idBook = $("#idBook").val();
    $.ajax({
        contentType: "application/json",
        url: "/allComments/" + idBook,
        dataType: 'json',
        type : "GET",
        timeout: 100000,
        success: function (data) {
            if (data.length > 0) {
            	for (var i = 0; i < data.length; i++ ) {
                    addCommentLine(data[i]);
                }
            }
        }
    });
}

$("#formComment").submit(function(event) {
	event.preventDefault(); // no submit
	if ($("#formComment").valid()) {
		addNewComment();
	}
});

function addNewComment() {
    var newComment = {};
    newComment["message"] = $("#message").val();
    newComment["idBook"] = $("#idBook").val();
  $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/addNewComment",
        data: JSON.stringify(newComment),
        dataType: 'json',
        timeout: 100000,
        success: function (data) {
            if (data.length > 0) {
            	document.getElementById("formComment").reset();
            	addCommentLine(data[data.length -1]);
            }
        }
    });
}