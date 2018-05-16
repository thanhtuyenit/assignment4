$(document).ready(function () {
    getMyBooks($('#pageSizeSelect').val(),1);
});

var properties_ = "Title";
var direction_ = "ASC";


function changePageAndSize() {
	var key = $('#keyword').val();
	var pageSize = $('#pageSizeSelect').val();
	if(key.length<1){
		window.history.pushState(null, null, '/');
		getMyBooks(pageSize,1);
	}else{
		searchMyBook(pageSize,1);
	}
	
}

function sortTable(typeSort) {
	if(typeSort == 0){
		//sort by title
		properties_ = "Title";
	}else{
		//sort by author
		properties_ = "Author";
	}
	if(direction_ == "ASC"){
		direction_ = "DESC";
	}else{
		direction_ = "ASC";
	}

	getMyBooks($('#pageSizeSelect').val(),1);
}

function getMyBooks(pageSize_,page_) {

	var url = "/myBook?pageSize="+pageSize_+"&page="+page_ ;
	if(page_ != 1){
		window.history.pushState(null, null, url);
	}
    $.ajax({
        contentType: "application/json",
        url: "/listMyBook",
        dataType: 'json',
        type : "GET",
        timeout: 100000,
        data : {
			pageSize : pageSize_,
			page: page_,
			properties:properties_, 
			direction: direction_,
		},
        success: function (data) {
			var colUser = "";
			var colAdmin = "";
			var rs = $('#checkUser').val();
			if(rs != "isUser"){
				colUser = 'class="hidden"';
			}else{
				colAdmin = 'class="hidden"';
			}
			var title = '<table class="table table-striped table-advance table-hover">'
					+ '<tbody>'
					+ '<tr>'
					+ '<th><i class=""></i>Index</th>'
					+ '<th><a onclick="sortTable(0)"><i class="glyphicon glyphicon-sort"></i>Title</a></th>'
					+ '<th><a onclick="sortTable(1)"><i class="glyphicon glyphicon-sort"></i>Author</a></th>'
					+ '<th><i class=""></i>Date created</th>'
					+ '<th><i class=""></i>Date updated</th>'
					+ '<th><i class=""></i>Status</th>'
					+ '<th><i class=""></i>Action</th>'
					+ ' </tr>';
			if(data.content.length > 0){
			for (var i = 0; i < data.content.length; i++) {
				var check = 'checked="checked"';
				var status = "Approved";
				if(data.content[i].enabled == 0){
					status = "Waiting";
					check = null;
				}
				var row = '<tr>' 
							+ '<td>'+i+'</td>'
							+ '<td>'+ data.content[i].title + '</td>'
							+ '<td>'+ data.content[i].author + '</td>'
							+ '<td>'+ data.content[i].createdAt	+ '</td>'
							+ '<td>'+ data.content[i].updatedAt	+ '</td>'
							+ '<td '+colUser+'>'+ status+ '</td>'
							+ '<td '+colAdmin+'>'+ '<input type="checkbox" '+ check
								+ ' onclick="changeStatusMyBook('+ data.content[i].idBook + ','+ data.content[i].enabled+ ')">'
							+ '</td>'
							+ '<td>'
								+ '<div class="btn-group">'
								+ '<a class="btn btn-warning"'
								+ ' href="/detailBook/'+data.content[i].idBook+'" title="Detail!"><i class="icon_camera_alt"></i></a>'
								+ '<a class="btn btn-success" onclick="showEditMyBook('+ data.content[i].idBook+ ')"' 
								+ 'href="#" title="Edit!"><i class="icon_pencil-edit"></i></a>'
								+ '<a class="btn btn-danger" onclick="deleteMyBook('+ data.content[i].idBook+ ')"'
								+ 'href="#" title="Delete!"><i class="icon_close_alt2"></i></a>'
								+ '</div>' 
							+ '</td>' 
							+ '</tr>';
				title = title + row;
			}

			title = title + '</tbody>' + '</table>';
			$('#result').html(title);
			var totalPages = data.totalPages;
			var pageSize = data.size;
			var currentPage = page_;
			pageMyBooks(totalPages,pageSize,currentPage);}
		else{
			$('#result').html("No data");
		}
        }
    });
}

function pageMyBooks(totalPages,pageSize,currentPage){
	var classFristPage = "";
	var classPrePage = "";
	var classLastPage = "";
	var classNextPage = "";
	if((currentPage - 1) == 0){
		classFristPage = 'class="disabled"';
		classPrePage = 'class="disabled"';
	}
	if(currentPage == totalPages){
		classLastPage = 'class="disabled"';
		classNextPage = 'class="disabled"';
	}
	var tmp = "";
	var title = "";
	var classTmp = "";
		for(var j = 1; j <= totalPages;j++){
			if(j == currentPage){
				classTmp = 'class="active pointer-disabled"';
			}else{
				classTmp = "";
			}
			tmp = '<li '+classTmp+'>'
	              	+' <a class="pageLink" href="#"'
	              	+' onclick = "getMyBooks('+pageSize+','+j+')">'+j+'</a>'
	               +' </li>';
	        title = title + tmp;      
		}

   var  valuePage = '<ul class="pagination">'
       + '<li '+classFristPage+' id="firstPage">'
       +' <a class="pageLink" href="#" '
       +' onclick="getMyBooks('+pageSize+',1)">&laquo;</a>'         
       +'        </li>'
       +'         <li '+classPrePage+' id="prePage">'
       +'             <a class="pageLink" '
       +'             onclick="getMyBooks('+pageSize+','+(currentPage - 1)+')" href="#">&larr;</a>'
       +'        </li>'
       +	title     
       +'        <li '+classNextPage+' id="nextPage">'
       +'            <a class="pageLink" href="#"'
       +'               onclick="getMyBooks('+pageSize+','+(currentPage + 1)+')">&rarr;</a>'
       +'        </li>'
       +'        <li '+classLastPage+' id="lastPage">'
       +'            <a class="pageLink" href="#"'
       +'            	onclick="getMyBooks('+pageSize+','+totalPages+')">&raquo;</a>'
       +'       </li>'
       +'   </ul>';

	if(totalPages != 1){
		$('#page').html(valuePage);
	}else{
		$('#page').html("");
	}
	
}


$("#formAddMyBook").submit(function(event) {
	event.preventDefault(); // no submit
	if ($("#formAddMyBook").valid()) {
		addMyBook();
	}
});

function addMyBook() {
	var url = "/addMyBook/" ;
	window.history.pushState(null, null, url);
	var newBook = {}
	newBook["title"] = $('#title').val();
	newBook["author"] = $('#author').val();
	newBook["description"] = $('#description').val();
	$
			.ajax({
				type : "post",
				contentType : "application/json",
				dataType : 'json',
				url : '/addBook',
				cache : false,
				data : JSON.stringify(newBook),
				success : function(data) {
					document.getElementById('viewFormAddMyBook').style.display = 'none';
					
					getMyBooks($('#pageSizeSelect').val(),1);
					$('#msgResult').html('Successfully added book!');
					setTimeout(function(){ $('#msgResult').html('')},2000);
				},
				error : function() {
					$('#msgResult').html('error');
					setTimeout(function(){ $('#msgResult').html('')},2000);
				}
			});
}

function showEditMyBook(idBook) {
	var url = "/showEditMyBook/"+idBook ;
	window.history.pushState(null, null, url);
	document.getElementById('viewFormEditMyBook').style.display = 'block';
	$.ajax({
		url : "/showEditMyBook/" + idBook,
		cache : false,
		type : "PUT",
		contentType : "application/json",
		dataType : 'json',
		success : function(data) {
			if (data != null) {
				$('#formEditMyBook').find('#titleEdit').val(data.title);
				$('#formEditMyBook').find('#authorEdit').val(data.author);
				$('#formEditMyBook').find('#descriptionEdit').val(
						data.description);
				$('#formEditMyBook').find('#idBookEdit').val(data.idBook);
				$('#formEditMyBook').find('#idUserBookEdit').val(data.idUser);
			} else {
				document.getElementById('viewFormEditMyBook').style.display = 'none';
				$('#msgResult').html('You do not have permission to edit!');
				setTimeout(function(){ $('#msgResult').html('')},2000);
			}
		},
		error : function() {
			document.getElementById('viewFormEditMyBook').style.display = 'none';
			$('#msgResult').html('You do not have permission to edit!');
			setTimeout(function(){ $('#msgResult').html('')},2000);
		}
	});
}

$("#formEditMyBook").submit(function(event) {
	event.preventDefault(); // no submit
	if ($("#formEditMyBook").valid()) {
		editMyBook();
	}
});

function editMyBook() {
	var url = "/editMyBook/";
	window.history.pushState(null, null, url);
	var newBook = {}
	newBook["title"] = $('#titleEdit').val();
	newBook["author"] = $('#authorEdit').val();
	newBook["description"] = $('#descriptionEdit').val();
	newBook["idBook"] = $('#idBookEdit').val();
	newBook["idUser"] = $('#idUserBookEdit').val();
	$
			.ajax({
				type : "PUT",
				contentType : "application/json",
				dataType : 'json',
				url : '/editMyBook',
				cache : false,
				data : JSON.stringify(newBook),
				success : function(data) {
					document.getElementById('viewFormEditMyBook').style.display = 'none';
					getMyBooks($('#pageSizeSelect').val(),1);
					$('#msgResult').html('Successfully edited book!');
					setTimeout(function(){ $('#msgResult').html('')},2000);
				},
				error : function() {
					$('#msgResult').html('You do not have permission to edit!');
					setTimeout(function(){ $('#msgResult').html('')},2000);
				}
			});
}

function deleteMyBook(idBook) {
	var url = "/deleteMybook/"+idBook ;
	window.history.pushState(null, null, url);
	if (confirm('Are you sure your want to delete?')) {
		$
				.ajax({
					url : "/deleteMyBook/" + idBook,
					type : "GET",
					contentType : "application/json",
					dataType : 'json',
					success : function(data) {
						if(data == 1){
							getMyBooks($('#pageSizeSelect').val(),1);
							$('#msgResult').html('Successfully deleted book!');
							setTimeout(function(){ $('#msgResult').html('')},2000);
						}else{
							$('#msgResult').html('Error');
							setTimeout(function(){ $('#msgResult').html('')},2000);
						}
						
					},
					error : function() {
						$('#msgResult').html('Error');
						setTimeout(function(){ $('#msgResult').html('')},2000);
					}
				});
	}
}

function changeStatusMyBook(idBook, st) {
	var url = "/admin/changeStatusMyBook/"+idBook ;
	window.history.pushState(null, null, url);
	$.ajax({
		url : "/admin/changeStatusMyBook/" + idBook,
		type : "GET",
		cache : false,
		contentType : "application/json",
		dataType : 'json',
		data : {
			enabled : st
		},
		success : function(data) {
			getMyBooks($('#pageSizeSelect').val(),1);
			$('#msgResult').html('Successfully!');
			setTimeout(function(){ $('#msgResult').html('')},2000);
			},
		error : function() {
			$('#msgResult').html('Error!');
			setTimeout(function(){ $('#msgResult').html('')},2000);
		}
	});
}
$("#formSearch").submit(function(event) {
	event.preventDefault(); // no submit
	if ($("#formSearch").valid()) {
		searchMyBook(5,1);
	}
});


function sortSearch(typeSort) {
	if(typeSort == 0){
		//sort by title
		properties_ = "Title";
	}else{
		//sort by author
		properties_ = "Author";
	}
	if(direction_ == "ASC"){
		direction_ = "DESC";
	}else{
		direction_ = "ASC";
	}

	searchMyBook($('#pageSizeSelect').val(),1);
}

function searchMyBook(pageSize_,page_){
	var key = $('#keyword').val();
	pageSize_ = $('#pageSizeSelect').val();
	var url = "/searchMyBook/?keyword="+key+"&pageSize="+pageSize_+"&page=1" ;
	window.history.pushState(null, null, url);
	$.ajax({
		url : "/searchMyBook/",
		type : "GET",
		cache : false,
		contentType : "application/json",
		dataType : 'json',
		data : {
			keyword : key,
			page : page_,
			pageSize : pageSize_,
			properties:properties_, 
			direction: direction_,
		},
		success : function(data) {
			if(data.content.length > 0){
			var colUser = "";
			var colAdmin = "";
			var rs = $('#checkUser').val();
			if(rs != "isUser"){
				colUser = 'class="hidden"';
			}else{
				colAdmin = 'class="hidden"';
			}
			var title = '<table class="table table-striped table-advance table-hover">'
					+ '<tbody>'
					+ '<tr>'
					+ '<th><i class=""></i>Index</th>'
					+ '<th><a onclick="sortSearch(0)"> <i class="glyphicon glyphicon-sort"></i>Title</a></th>'
					+ '<th><a onclick="sortSearch(1)"><i class="glyphicon glyphicon-sort"></i>Author</a></th>'
					+ '<th><i class=""></i>Date created</th>'
					+ '<th><i class=""></i>Date updated</th>'
					+ '<th><i class=""></i>Status</th>'
					+ '<th><i class=""></i>Action</th>'
					+ ' </tr>';
			for (var i = 0; i < data.content.length; i++) {
				var check = 'checked="checked"';
				var status = "Approved";
				if(data.content[i].enabled == 0){
					status = "Waiting";
					check = null;
				}
				var row = '<tr>' 
							+ '<td>'+i+'</td>'
							+ '<td>'+ data.content[i].title + '</td>'
							+ '<td>'+ data.content[i].author + '</td>'
							+ '<td>'+ data.content[i].createdAt	+ '</td>'
							+ '<td>'+ data.content[i].updatedAt	+ '</td>'
							+ '<td '+colUser+'>'+ status+ '</td>'
							+ '<td '+colAdmin+'>'+ '<input type="checkbox" '+ check
								+ ' onclick="changeStatusMyBook('+ data.content[i].idBook + ','+ data.content[i].enabled+ ')">'
							+ '</td>'
							+ '<td>'
								+ '<div class="btn-group">'
								+ '<a class="btn btn-warning"'
								+ ' href="/detailBook/'+data.content[i].idBook+'" title="Detail!"><i class="icon_camera_alt"></i></a>'
								+ '<a class="btn btn-success" onclick="showEditMyBook('+ data.content[i].idBook+ ')"' 
								+ 'href="#" title="Edit!"><i class="icon_pencil-edit"></i></a>'
								+ '<a class="btn btn-danger" onclick="deleteMyBook('+ data.content[i].idBook+ ')"'
								+ 'href="#" title="Delete!"><i class="icon_close_alt2"></i></a>'
								+ '</div>' 
							+ '</td>' 
							+ '</tr>';
				title = title + row;
			}

			title = title + '</tbody>' + '</table>';
			$('#msgResult').html('Have '+data.totalElements+' result with keyword = '+key);
			$('#result').html(title);
			$('#view').show();
			var totalPages = data.totalPages;
			var pageSize = data.size;
			var currentPage = data.number+1;
			pageMyBooks(totalPages,pageSize,currentPage);
        	
           }else{
        	   $('#result').html(""); 
        	   $('#msgResult').html('No result with keyword = '+key);
        	   $('#page').html("");
        	   $('#view').hide();
           }
		},
		error : function() {
			$('#msgResult').html('Error');
			setTimeout(function(){ $('#msgResult').html('')},2000);
		}
	});
	
}

function pageMySearch(totalPages,pageSize,currentPage){
	var classFristPage = "";
	var classPrePage = "";
	var classLastPage = "";
	var classNextPage = "";
	if((currentPage - 1) == 0){
		classFristPage = 'class="disabled"';
		classPrePage = 'class="disabled"';
	}
	if(currentPage == totalPages){
		classLastPage = 'class="disabled"';
		classNextPage = 'class="disabled"';
	}
	var tmp = "";
	var title = "";
	var classTmp = "";
		for(var j = 1; j <= totalPages;j++){
			if(j == currentPage){
				classTmp = 'class="active pointer-disabled"';
			}else{
				classTmp = "";
			}
			tmp = '<li '+classTmp+'>'
	              	+' <a class="pageLink" href="#"'
	              	+' onclick = "searchMyBook('+pageSize+','+j+')">'+j+'</a>'
	               +' </li>';
	        title = title + tmp;      
		}

   var  valuePage = '<ul class="pagination">'
       + '<li '+classFristPage+' id="firstPage">'
       +' <a class="pageLink" href="#" '
       +' onclick="searchMyBook('+pageSize+',1)">&laquo;</a>'         
       +'        </li>'
       +'         <li '+classPrePage+' id="prePage">'
       +'             <a class="pageLink" '
       +'             onclick="searchMyBook('+pageSize+','+(currentPage - 1)+')" href="#">&larr;</a>'
       +'        </li>'
       +	title     
       +'        <li '+classNextPage+' id="nextPage">'
       +'            <a class="pageLink" href="#"'
       +'               onclick="searchMyBook('+pageSize+','+(currentPage + 1)+')">&rarr;</a>'
       +'        </li>'
       +'        <li '+classLastPage+' id="lastPage">'
       +'            <a class="pageLink" href="#"'
       +'            	onclick="searchMyBook('+pageSize+','+totalPages+')">&raquo;</a>'
       +'       </li>'
       +'   </ul>';

	if(totalPages != 1){
		$('#page').html(valuePage);
	}else{
		$('#page').html("");
	}
	
}