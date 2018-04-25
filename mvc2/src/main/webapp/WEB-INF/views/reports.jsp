<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<link rel="stylesheet" href="<spring:theme code='styleSheet'/>"
	type="text/css" />

 <!-- Example DataTables Card-->
      <div class="card mb-3">
        <div class="card-header">
          <i class="fa fa-table"></i> Customers
          <a href="reports.pdf" class="btn btn-primary">PDF</a>
          <a href="reports.json" class="btn btn-primary">JSON</a>
          <a href="reports.xlsx" class="btn btn-primary">EXCEL</a>
          <span class="pull-right">
          <img src="resources/images/theme-icon.png" class="" width="32" height="32"/>
          <a href="?theme=table-red" class="">ORANGE</a> |
	      <a href="?theme=table-green" class="">GREEN</a>
	      </span>
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-striped" id="dataTable" width="100%" cellspacing="0">
              <thead>
                <tr>
                  <th>id</th>
                  <th>FirstName</th>
                  <th>LastName</th>
                  <th>Gender</th>
                  <th>Dob</th>
                  <th>Address</th>
                </tr>
              </thead>
           	  <tbody>
           	  	<c:forEach var="customer" items="${customers}">
           	  		${customer}
           	  		<tr>
           	  			
           	  			<td>${customer.id}</td>
           	  			<td>${customer.firstName}</td>
           	  			<td>${customer.lastName}</td>
           	  			<td>${customer.gender.description}</td>
           	  			<td><spring:eval expression="customer.dob"></spring:eval></td>
           	  			<td><spring:eval expression="customer.address"></spring:eval></td>
           	  		</tr>
           	  	</c:forEach>
           	  </tbody>	
            </table>
          </div>
        </div>
        <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
     </div>
 