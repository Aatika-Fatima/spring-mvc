<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<spring:theme code='styleSheet'/>"
	type="text/css" />

<title>Insert title here</title>
</head>
<body bgcolor="<spring:theme code='background'/>">

	<h3>Spring MVC ThemeResolver Example</h3>
	theme:
	<a href="?theme=bright">bright</a> |
	<a href="?theme=dark">dark</a>
	<a href="?language=en_US">English</a> |
	<a href="?language=fr_FR">French</a> customer view
	<a href="excel">Customer List in excel</a>
	<a href="customer.json">Customer list in json format</a>

	<spring:message code="label.customer.name"></spring:message>

	<h1>Please upload a file</h1>
	<form method="post" action="uploadFile" enctype="multipart/form-data">
		<input type="text" name="tag" /> 
		<input type="file" name="file" /> <input
			type="submit" />
	</form>
    <spring:eval expression="customer.address" />
    <spring:eval expression="customer.dob" />  
    ${customer.dob}<br>
    ${customer.address}
  </body>
  
</html>