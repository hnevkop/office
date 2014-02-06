<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="layout/header.jsp">
	<jsp:param name="title" value="${message}" />
	<jsp:param name="page" value="register" />
</jsp:include>


<div class="jumbotron">
	<div class="container">
	<p>${message}</p>

		<form:errors path="*" class="alert alert-danger" element="div" />		
		<form:form commandName="supplier" cssClass="form-horizontal" method="POST" >
			<table>
				<tr>
					<td>Name:</td>
					<td><form:input path="name" required="true" /></td>
				</tr>
				<tr>
					<td>Address:</td>
					<td><form:input path="address" required="true" /></td>
				</tr>
				<tr>
					<td>Phone number:</td>
					<td><form:input path="phone" required="true" /></td>
				</tr>
				<tr>
					<td>email:</td>
					<td><form:input path="email" required="true" /></td>
				</tr>
				<tr>
					<td>Group:</td>
					<td><form:select path="groups" required="true" multiple="true" items="${groups}" itemLabel="name" itemValue="id" /></td>
				</tr>
				
			</table>			
					<div class="form-group">
							<div class="col-lg-2"> 
								<input type="submit" class="btn btn-primary navbar-btn" title="Submit" name="Submit" />
							</div>
					</div>
		</form:form>
	</div>
</div>
<jsp:include page="layout/footer.jsp" />