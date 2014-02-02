<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
   
<jsp:include page="layout/header.jsp">
	<jsp:param name="title" value="Office supplier"  />
	<jsp:param name="page" value="loan"  />
</jsp:include>

<div class="jumbotron">
	<div class="container">
		<div class="hero-unit">

			<h1>
				<span> Office supplier Demo app</span>
			</h1>
			<p>A simple application that helps to manage your suppliers!</p>

			<a class="btn btn-primary" href="apply"> Click to continue » </a> 
		</div>

	</div>
</div>

<jsp:include page="layout/footer.jsp" />