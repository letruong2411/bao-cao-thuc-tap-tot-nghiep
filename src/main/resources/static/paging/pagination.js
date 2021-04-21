	
var url;
var parameter;
var totalPage;

function setPagination(paginationInfo) {
		
		url = paginationInfo.url; // url is value of a tag.
		parameter = paginationInfo.parameter; // parameter is query of pagination like '?pageNo=11'
		totalPage = paginationInfo.totalPage; // total page is maximum page.

		// if pagination size is even number, we make it odd number for place current page number to mid of pagination.
		var paginationSize = paginationInfo.paginationSize % 2 == 0 ? paginationInfo.paginationSize + 1 : paginationInfo.paginationSize;
		var currentPage = getCurrentPage(parameter);

		//we place previous page
    	addPage({pageNo : currentPage - 1 , ariaLabel : "Previous" , text : "<span aria-hidden='true'>&laquo;</span>"});

    	// in below for block , we place page of before current page.
    	for(var prev = (currentPage) - (paginationSize  - 3) / 2; (prev <= (currentPage)  -  1); prev++){
    		if(prev >= 1) {
    			addPage({pageNo : prev});
    		}
    	}
    	
    	// we place the current page
    	addPage({pageNo : currentPage , class : "active"});

    	// in below for block , we place page of after current page.
    	for(var next = currentPage + 1; next <= (currentPage + ((paginationSize - 3 ) / 2)) && next <= totalPage ; next++) {
    		addPage({pageNo : next});
    	}

		//we place next page
    	addPage({pageNo : currentPage + 1 , ariaLabel : "next" , text : "<span aria-hidden='true'>&raquo;</span>"});
}

// in following function , we add page(ul) to pagination.
function addPage(pageInfo) {
	pageInfo.text = pageInfo.text == null ? pageInfo.pageNo : pageInfo.text;
	$(".pagination").append("<li class='page-item "+pageInfo.class+"'> <a href='"+url+"?"+parameter+"="+  pageInfo.pageNo  +"' class='page-link' aria-label='"+pageInfo.ariaLabel+"'> "+pageInfo.text+" </a> </li>");
}

// in following function , we get current page no from location query.
function getCurrentPage(parameter) {
	var location = getLocation();
	var currentPage = location.lastIndexOf(parameter) == -1 ?  1 : location.substring(location.lastIndexOf(parameter) + (parameter.length + 1)) * 1;
	return currentPage <= totalPage ? currentPage : 1;
}

function getLocation(){
	return window.location.toString();
}




