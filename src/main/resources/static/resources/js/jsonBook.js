$(document).ready(function () {
    getAllBooks($('#pageSizeSelect').val(),1);
});
var properties_ = "Title";
var direction_ = "ASC";
function changePageAndSize() {
	var key = $('#keyword').val();
	var pageSize = $('#pageSizeSelect').val();
	if(key.length<1){
		window.history.pushState(null, null, '/');
		getAllBooks(pageSize,1);
	}else{
		searchBook(pageSize,1);
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

	getAllBooks($('#pageSizeSelect').val(),1);
}
function getAllBooks(pageSize_,page_) {
	var url = "/?pageSize="+pageSize_+"&page="+page_ ;
	if(page_ != 1){
		window.history.pushState(null, null, url);
	}
    $.ajax({
        contentType: "application/json",
        url: "/listBook",
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
        	var idRole = $("#idRole").val();
        	var tmp = 'class="hidden"';
        	if(idRole == 1){
        		tmp = "";
        	}
           if (data.content.length > 0) {
        	   var iconTitle = "";
        	   if(direction_ == "DESC"){
        		   iconTitle = 'class="glyphicon glyphicon-sort-by-alphabet-alt"';
        	   }else{
				iconTitle = 'class="glyphicon glyphicon-sort-by-alphabet"';
        	   }
        	  // alert(iconTitle);
   			var title = '<table class="table table-striped table-advance table-hover">'
				+ '<thead>'
					+ '<tr>'
						+ '<th><i class=""></i>Index</th>'
						+ '<th ><a onclick="sortTable(0)"><i id="test" class="glyphicon glyphicon-sort"></i>Title</a></th>'
						+ '<th><a onclick="sortTable(1)"> <i class="glyphicon glyphicon-sort"></i>Author</a></th>'
						+ '<th><i class=""></i>Date created</th>'
						+ '<th><i class=""></i>Date updated</th>'
						+ '<th '+tmp+'><i class=""></i>Status</th>'
						+ '<th><i class=""></i>Action</th>'
					+ '</tr>'
				+ '</thead>'
				+ '<tbody>';
   			var j = 0;
			$.each(data.content, function(i,book){
				var check = null;
				if(book.enabled == 1){
					check = 'checked="checked"';
				}
				var row = '<tr>' 
						+ '<td>'+(++j)+'</td>'
						+ '<td>'+ book.title + '</td>'
						+ '<td>'+ book.author + '</td>'
						+ '<td>'+ book.createdAt + '</td>'
						+ '<td>'+ book.updatedAt + '</td>'
						+ '<td '+tmp+'>'
							+ '<input type="checkbox" '	+ check
							+ ' onclick="changeStatusBook('+ book.idBook+ ','+ book.enabled	+ ')">'
						+ '</td>'
						+ '<td>'
							+ '<div class="btn-group">'
								+ '<a class="btn btn-warning"'
								+ ' href="/detailBook/'+book.idBook+'" title="Detail!"><i class="icon_camera_alt"></i></a>'
								+ '<div '+tmp+' style="float:left">'
									+ '<a class="btn btn-success" '
										+'onclick="showEditBook('+ book.idBook + ')" href="#" title="Edit!"><i class="icon_pencil-edit"></i></a>'
									+ '<a class="btn btn-danger" '
										+'onclick="deleteBook('+ book.idBook+ ')" href="#" title="Delete!"><i class="icon_close_alt2"></i></a>'
								+ '</div>'		
							+ '</div>' 
						+ '</td>' 
					+ '</tr>';
				title = title + row;
			});
			title = title + '</tbody>' + '</table>';
			$('#result').html(title);
			var totalPages = data.totalPages;
			var pageSize = data.size;
			var currentPage = page_;
			page(totalPages,pageSize,currentPage);
        }
      },
    });
}

function page(totalPages,pageSize,currentPage){
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
	              	+' onclick = "getAllBooks('+pageSize+','+j+')">'+j+'</a>'
	               +' </li>';
	        title = title + tmp;      
		}

   var  valuePage = '<ul class="pagination">'
       + '<li '+classFristPage+' id="firstPage">'
       +' <a class="pageLink" href="#" '
       +' onclick="getAllBooks('+pageSize+',1)">&laquo;</a>'         
       +'        </li>'
       +'         <li '+classPrePage+' id="prePage">'
       +'             <a class="pageLink" '
       +'             onclick="getAllBooks('+pageSize+','+(currentPage - 1)+')" href="#">&larr;</a>'
       +'        </li>'
       +	title     
       +'        <li '+classNextPage+' id="nextPage">'
       +'            <a class="pageLink" href="#"'
       +'               onclick="getAllBooks('+pageSize+','+(currentPage + 1)+')">&rarr;</a>'
       +'        </li>'
       +'        <li '+classLastPage+' id="lastPage">'
       +'            <a class="pageLink" href="#"'
       +'            	onclick="getAllBooks('+pageSize+','+totalPages+')">&raquo;</a>'
       +'       </li>'
       +'   </ul>';

	if(totalPages != 1){
		$('#page').html(valuePage);
	}else{
		$('#page').html("");
	}
	
}

//admin login with index
function showEditBook(idBook) {
	var url = '/admin/showEditBook/'+idBook;
	window.history.pushState(null, null, url);
	document.getElementById('viewFormEditBook').style.display = 'block';
	$.ajax({
		url : "/admin/showEditBook/" + idBook,
		cache : false,
		type : "PUT",
		contentType : "application/json",
		dataType : 'json',
		success : function(data) {
			if (data != null) {
				$('#formEditBook').find('#titleEdit').val(data.title);
				$('#formEditBook').find('#authorEdit').val(data.author);
				$('#formEditBook').find('#descriptionEdit').val(
						data.description);
				$('#formEditBook').find('#idBookEdit').val(data.idBook);
				$('#formEditBook').find('#idUserBookEdit').val(data.idUser);
			} else {
				document.getElementById('viewFormEditBook').style.display = 'none';
				$('#msgResult').html('Error!');
				setTimeout(function(){ $('#msgResult').html('')},2000);
			}
		},
		error : function() {
			$('#msgResult').html('Error!');
			setTimeout(function(){ $('#msgResult').html('')},2000);
		}
	});
}

$("#formEditBook").submit(function(event) {
	event.preventDefault(); // no submit
	if ($("#formEditBook").valid()) {
		editBook();
	}
});

function editBook() {
	var url = "/admin/editBook/" ;
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
				url : '/admin/editBook',
				cache : false,
				data : JSON.stringify(newBook),
				success : function(data) {
					document.getElementById('viewFormEditBook').style.display = 'none';
					getAllBooks($('#pageSizeSelect').val(),1);
					$('#msgResult').html('Successfully edited book!');
					setTimeout(function(){ $('#msgResult').html('')},2000);
					},
				error : function() {
					$('#msgResult').html('Erorr editing book!');
					setTimeout(function(){ $('#msgResult').html('')},2000);
				}
			});
}

function deleteBook(idBook) {
	var url = "/admin/deleteBook/"+idBook ;
	window.history.pushState(null, null, url);
	if (confirm('Are you sure your want to delete?')) {
		$
				.ajax({
					url : "/admin/deleteBook/" + idBook,
					type : "GET",
					contentType : "application/json",
					dataType : 'json',
					success : function(data) {
						if(data == 1){
							getAllBooks($('#pageSizeSelect').val(),1);
							$('#msgResult').html('Successfully deleted book!');
							setTimeout(function(){ $('#msgResult').html('')},2000);
						}else{
							$('#msgResult').html('Error!');
							setTimeout(function(){ $('#msgResult').html('')},2000);
						}
						
						},
					error : function() {
						$('#msgResult').html('Error!');
						setTimeout(function(){ $('#msgResult').html('')},2000);
					}
				});
	}
}

function changeStatusBook(idBook, st) {
	var url = "/admin/changeStatusBook/"+idBook ;
	window.history.pushState(null, null, url);
	$.ajax({
		url : "/admin/changeStatusBook/" + idBook,
		type : "GET",
		cache : false,
		contentType : "application/json",
		dataType : 'json',
		data : {
			enabled : st
		},
		success : function(data) {
			getAllBooks($('#pageSizeSelect').val(),1);
			$('#msgResult').html('Successfully changed status book!');
			setTimeout(function(){ $('#msgResult').html('')},2000);
			},
		error : function() {
			$('#msgResult').html('Error');
			setTimeout(function(){ $('#msgResult').html('')},2000);
		}
	});
}

$("#formSearch").submit(function(event) {
	event.preventDefault(); // no submit
	if ($("#formSearch").valid()) {
		searchBook(5,1);
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

	searchBook($('#pageSizeSelect').val(),1);
}

function searchBook(pageSize_,page_){
	var key = $('#keyword').val();
	pageSize_ = $('#pageSizeSelect').val();
	var url = "/searchBook/?keyword="+key+"&pageSize="+pageSize_+"&page=1" ;
	window.history.pushState(null, null, url);
	$.ajax({
		url : "/searchBook/",
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
        	var idRole = $("#idRole").val();
        	var tmp = 'class="hidden"';
        	if(idRole == 1){
        		tmp = "";
        	}
        	var totalPages ;
			var pageSize;
			var currentPage;
           if (data.content.length > 0) {
   			var title = '<table class="table table-striped table-advance table-hover">'
				+ '<thead>'
					+ '<tr>'
						+ '<th><i class=""></i>Index</th>'
						+ '<th><a onclick="sortSearch(0)"><i class="glyphicon glyphicon-sort"></i>Title</a></th>'
						+ '<th><a onclick="sortSearch(1)"><i class="glyphicon glyphicon-sort"></i>Author</a></th>'
						+ '<th><i class=""></i>Date created</th>'
						+ '<th><i class=""></i>Date updated</th>'
						+ '<th '+tmp+'><i class=""></i>Status</th>'
						+ '<th><i class=""></i>Action</th>'
					+ '</tr>'
				+ '</thead>'
				+ '<tbody>';
   			var j = 0;
			$.each(data.content, function(i,book){
				var check = null;
				if(book.enabled == 1){
					check = 'checked="checked"';
				}
				var row = '<tr>' 
						+ '<td>'+(++j)+'</td>'
						+ '<td>'+ book.title + '</td>'
						+ '<td>'+ book.author + '</td>'
						+ '<td>'+ book.createdAt + '</td>'
						+ '<td>'+ book.updatedAt + '</td>'
						+ '<td '+tmp+'>'
							+ '<input type="checkbox" '	+ check
							+ ' onclick="changeStatusBook('+ book.idBook+ ','+ book.enabled	+ ')">'
						+ '</td>'
						+ '<td>'
							+ '<div class="btn-group">'
								+ '<a class="btn btn-warning"'
								+ ' href="/detailBook/'+book.idBook+'" title="Detail!"><i class="icon_camera_alt"></i></a>'
								+ '<div '+tmp+' style="float:left">'
									+ '<a class="btn btn-success" '
										+'onclick="showEditBook('+ book.idBook + ')" href="#" title="Edit!"><i class="icon_pencil-edit"></i></a>'
									+ '<a class="btn btn-danger" '
										+'onclick="deleteBook('+ book.idBook+ ')" href="#" title="Delete!"><i class="icon_close_alt2"></i></a>'
								+ '</div>'		
							+ '</div>' 
						+ '</td>' 
					+ '</tr>';
				title = title + row;
			});
			title = title + '</tbody>' + '</table>';
			$('#result').html(title);
			$('#view').show();
			$('#msgResult').html('Have '+data.totalElements+' result with keyword = '+key);
			 totalPages = data.totalPages;
			 pageSize = data.size;
			 currentPage = data.number+1;
			 pageSearch(totalPages,pageSize,currentPage);
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

function pageSearch(totalPages,pageSize,currentPage){
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
	              	+' onclick = "searchBook('+pageSize+','+j+')">'+j+'</a>'
	               +' </li>';
	        title = title + tmp;      
		}

   var  valuePage = '<ul class="pagination">'
       + '<li '+classFristPage+' id="firstPage">'
       +' <a class="pageLink" href="#" '
       +' onclick="searchBook('+pageSize+',1)">&laquo;</a>'         
       +'        </li>'
       +'         <li '+classPrePage+' id="prePage">'
       +'             <a class="pageLink" '
       +'             onclick="searchBook('+pageSize+','+(currentPage - 1)+')" href="#">&larr;</a>'
       +'        </li>'
       +	title     
       +'        <li '+classNextPage+' id="nextPage">'
       +'            <a class="pageLink" href="#"'
       +'               onclick="searchBook('+pageSize+','+(currentPage + 1)+')">&rarr;</a>'
       +'        </li>'
       +'        <li '+classLastPage+' id="lastPage">'
       +'            <a class="pageLink" href="#"'
       +'            	onclick="searchBook('+pageSize+','+totalPages+')">&raquo;</a>'
       +'       </li>'
       +'   </ul>';

	if(totalPages != 1){
		$('#page').html(valuePage);
	}else{
		$('#page').html("");
	}
}

