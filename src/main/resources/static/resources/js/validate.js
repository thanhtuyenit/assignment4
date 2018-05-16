//validate
$(document).ready(function() {
	$("#formAddUser").validate({
		rules : {
			"email" : {
				required : true,
				email : true,
			},

			"password" : {
				required : true,
				minlength : 3,
				maxlength : 40
			},

			"rePassword" : {
				required : true,
				equalTo : "#password",
			},
		},
		messages : {
			"email" : {
				required : "Please enter email!",
				email : "Enter the correct email format!",
			},
			"password" : {
				required : "Please enter password!",
				minlength : "Password is too short!",
				maxlength : "Password is too long!"
			},

			"rePassword" : {
				required : "Please enter a password confirmation!",
				equalTo : "Password must match the entered password!",
			},
		}
	});
	

});

// validate
$(document).ready(function() {
	// alert('validate edit!');
	$("#formEditUser").validate({
		rules : {
			"passwordEdit" : {
				minlength : 3,
				maxlength : 40
			},

			"rePasswordEdit" : {
				equalTo : "#passwordEdit",
			},
		},
		messages : {
			"passwordEdit" : {
				minlength : "Password is too short!",
				maxlength : "Password is too long!"
			},

			"rePasswordEdit" : {
				equalTo : "Password must match the entered password!",
			},
		}
	});

});

function validateEmail(email) {
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(email);
}


$(document).ready(function() {
	$("#formEditBook").validate({
		rules : {
			"titleEdit" : {
				required : true,
				minlength : 4,
				maxlength : 40
			},
			"authorEdit" : {
				required : true,
				minlength : 6,
				maxlength : 40
			},
			"descriptionEdit" : {
				maxlength : 200
			},

		},
		messages : {
			"titleEdit" : {
				required : "Please enter title!",
				minlength : "Title is too short!",
				maxlength : "Title is too long!"
			},
			"authorEdit" : {
				required : "Please enter author!",
				minlength : "Author is too short!",
				maxlength : "Author is too long!"
			},
			"descriptionEdit" : {
				maxlength : "Author is too long!"
			},

		}
	});
});

$(document).ready(function() {
	$("#formAddMyBook").validate({
		rules : {
			"title" : {
				required : true,
				minlength : 4,
				maxlength : 40
			},
			"author" : {
				required : true,
				minlength : 6,
				maxlength : 40
			},
			"description" : {
				maxlength : 200
			},

		},
		messages : {
			"title" : {
				required : "Please enter title!",
				minlength : "Title is too short!",
				maxlength : "Title is too long!"
			},
			"author" : {
				required : "Please enter author!",
				minlength : "Author is too short!",
				maxlength : "Author is too long!"
			},
			"description" : {
				maxlength : "Author is too long!"
			},
		}
	});
});

$(document).ready(function() {
	$("#formEditMyBook").validate({
		rules : {
			"titleEdit" : {
				required : true,
				minlength : 4,
				maxlength : 40
			},
			"authorEdit" : {
				required : true,
				minlength : 6,
				maxlength : 40
			},
			"descriptionEdit" : {
				maxlength : 200
			},

		},
		messages : {
			"titleEdit" : {
				required : "Please enter title!",
				minlength : "Title is too short!",
				maxlength : "Title is too long!"
			},
			"authorEdit" : {
				required : "Please enter author!",
				minlength : "Author is too short!",
				maxlength : "Author is too long!"
			},
			"descriptionEdit" : {
				maxlength : "Description is too long!"
			},

		}
	});
});


$(document).ready(function() {
	$("#formSignUp").validate({
		rules : {
			"emailSignUp" : {
				required : true,
				email : true,
			},

			"passwordSignUp" : {
				required : true,
				minlength : 3,
				maxlength : 40
			},

			"rePasswordSignUp" : {
				required : true,
				equalTo : "#passwordSignUp",
			},
		},
		messages : {
			"emailSignUp" : {
				required : "Please enter email!",
				email : "Enter the correct email format!",
			},
			"passwordSignUp" : {
				required : "Please enter password!",
				minlength : "Password is too short!",
				maxlength : "Password is too long!"
			},

			"rePasswordSignUp" : {
				required : "Please enter a password confirmation!",
				equalTo : "Password must match the entered password!",
			},
		}
	});
});

//validate
$(document).ready(function() {
	$("#formChangePasswordUserLogin").validate({
		rules : {
			"oldPassword" : {
				required : true,
				minlength : 3,
				maxlength : 40
			},
			"newPassword" : {
				required : true,
				minlength : 3,
				maxlength : 40
			},

			"rePassword" : {
				required : true,
				equalTo : "#newPassword",
			},
		},
		messages : {
			"oldPassword" : {
				required : "Please enter a old password!",
				minlength : "Password is too short!",
				maxlength : "Password is too long!"
			},
			"newPassword" : {
				required : "Please enter new password!",
				minlength : "Password is too short!",
				maxlength : "Password is too long!"
			},

			"rePassword" : {
				required : "Please enter a password confirmation!",
				equalTo : "Password must match the entered password!",
			},
		}
	});

});

$(document).ready(function() {
	$("#formComment").validate({
		rules : {
			"message" : {
				required : true,
				minlength : 3,
				maxlength : 500
			},

		},
		messages : {
			"passwordEdit" : {
				required : "Please enter comment!",
				minlength : "Comment is too short!",
				maxlength : "Comment is too long!"
			},

		}
	});

});