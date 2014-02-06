<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Premysl Hnevkovsky">

    <title>${param.title}</title>

	<c:url value="/resources/css/bootstrap.min.css" var="bootstrapCssUrl" />
    <link href="${bootstrapCssUrl}" rel="stylesheet">

	<c:url value="/resources/js/jquery-2.0.3.js" var="jqueryUrl" />
    <script src="${jqueryUrl}"></script>
    
  	<c:url value="/resources/js/moment-with-langs.js" var="momentJsUrl" />
    <script src="${momentJsUrl}"></script>

	<c:url value="/resources/js/bootstrap.min.js" var="bootstrapJsUrl" />
    <script src="${bootstrapJsUrl}"></script>
  </head>

  <body>

    <!-- Static navbar -->
    <div class="navbar navbar-inverse navbar-static-top" role="navigation" >
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <c:url var="homeUrl" value="/"  />
          <a class="navbar-brand" href="${homeUrl}">Office</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <c:url var="suppliersUrl" value="/suppliers" />
            <c:url var="registerUrl" value="/supplier" />
            <li class="${param.page eq 'apply' ? 'active' : ''}"><a href="${registerUrl}">New supplier</a></li>
            <li class="${param.page eq 'loans' ? 'active' : ''}"><a href="${suppliersUrl}">All Suppliers</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

    <div class="container">
