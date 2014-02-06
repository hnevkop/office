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

<div class="jumbotron">
	<div class="container">
			<h2>
				<span> Suppliers</span>
			</h2>
  		<table class="table table-striped table-bordered table-hover">
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Address</th>
					<th>Email</th>
					<th>Telephone</th>
					<th>Type</th>
					<th></th>
				</tr>				
				<c:forEach items="${suppliers}" var="supplier">
					<tr>
						<td><a href="suppliers/${supplier.id}">${supplier.id}</a></td>
						<td><c:out value="${supplier.name}"/></td>
						<td><c:out value="${supplier.address}"/></td>
						<td><c:out value="${supplier.email}"/></td>
						<td><c:out value="${supplier.phone}"/></td>
						<td><c:out value="${supplier.groups}"/></td>
						<td>   
							<form:form action="deleteSupplier" method="post">
							<input type="hidden" name="id" value="${supplier.id}" />
							 <input type="image" src="resources/icons/delete.png" title="Remove supplier" alt="remove" onclick="return confirm('Really remove this supplier?');">
							</form:form>
						</td>
					</tr>
				</c:forEach>
		</table>

	</div>
</div>

<jsp:include page="layout/footer.jsp" />