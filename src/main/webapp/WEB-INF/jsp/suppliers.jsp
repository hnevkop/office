<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>     

<jsp:include page="layout/header.jsp">
	<jsp:param name="title" value="Suppliers"  />
	<jsp:param name="page" value="suppliers"  />
</jsp:include>

<script type="text/javascript">
	$(document).ready(
		function() {
			searchAjax();
		});		
	
function searchAjax() {
	var filterStr = $("#searchFilter").serialize();
	$.ajax({
	    dataType : "json",
	    url : 'search',	    
	    type: 'POST',
	    data:filterStr,
	    success : function(response) {   
	    	feed_table(response);
	    },      
	    error : function(){
	        alert("Search encountered a problem");
	    }
	});
	
	function feed_table(tableobj) {
	    $('#suppliers_table').html('');
	    // header hardcoded ... :(
	    $('#suppliers_table').append('<tr><th>Id</th><th>Name</th><th>Address</th><th>Email</th><th>Phone</th><th>Type of services</th><th></th></tr>');
	    $.each(tableobj, function (index, row) {
	            var line = "";
	            var supplierId = "";
	            
	            $.each(row, function (key, value) {				            
	            	if (key=="id"){
	            		supplierId = value;
	            	}
	                if(jQuery.isArray(value)){
	                    // is the group array
	                    var groups = "";
	                    $.each(value, function (i,val) {	                        
	                        if(i>0){
	                        	groups += ',';	
	                        }
	                        var group = this.name;
	                        groups += '&nbsp;'+group
	                    });
	                    line += '<td>' + groups + '</td>';
	                }else if (key=="id"){
	                    line += '<td><a href="suppliers/'+value+'" title="Edit supplier" >'+value+'</a></td>';
	                }else {
	                    line += '<td>' + value + '</td>';
	                }                 
	            });
	            // delete button
	            line += '<td><form:form action="delete" method="post">';
		    	line += '<input type="hidden" name="id" value="'+supplierId +'" />';
		    	line += '<input type="image" src="resources/icons/delete.png" title="Remove supplier" alt="remove" >';
		    	line += '</form:form></td>';
	            line  = '<tr>' + line + '</tr>';
	            $('#suppliers_table').append(line);
	        });
	}
	
}
</script>

<div class="jumbotron">
	<div class="container">
			<h2>
				<span> Suppliers</span>
			</h2>
			
			<form:form  modelAttribute="searchFilter" id="searchFilter" cssClass="form-horizontal" method="post" >
			<form:select path="searchId" onchange="searchAjax()"  >
			   <form:option  value="0" label="--- All types ---"  />
			   <form:options  itemValue="id"   items="${allGroups}" />
			</form:select>						
			</form:form>

  		<table class="table table-striped table-bordered table-hover" id="suppliers_table">
		</table>

	</div>
</div>

<jsp:include page="layout/footer.jsp" />