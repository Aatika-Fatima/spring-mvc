<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>  

<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<link rel="stylesheet" href="<spring:theme code='styleSheet'/>"
type="text/css" />
	
	 <div class="card card-register mx-auto mt-5">
      <div class="card-header">Update</div>
      <div class="card-body">
 	  <form:errors path="customer.*"/>	
   <%--     <form action="/mvc/profile/name" method="get">
      	  <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                 <input class="form-control" type="text" placeholder="Enter first name" name="firstName" value="">
              </div>
              <div class="col-md-6">
                <input type="submit" class="btn btn-primary btn-block" value="Update"></input>
              </div>
            </div>
          </div> 
      	</form> --%>
        <form action="/mvc/profile" method="post">
          <input  type="hidden" name="id" value="${customer.id}">
          
          <div class="form-group">
          <div class="form-row">
          	 <div class="col-md-12"><label style="color:green; font-weight:bold">${requestScope.msg}</label></div>
          </div>
            <div class="form-row">
              <div class="col-md-6">
                <label>First name</label>
                <input class="form-control"  type="text"  placeholder="Enter first name" name="firstName" value="${customer.firstName}">
              </div>
              <div class="col-md-6">
                <label>Last name</label>
                <input class="form-control" type="text" placeholder="Enter last name" name="lastName" value="${customer.lastName}">
              </div>
            </div>
          </div>
          <div class="form-group">
          	<div class="form-row">
              <div class="col-md-6">
                <label>email</label>
                <input class="form-control" type="email"  placeholder="email" name="email" value="${customer.email}">
               </div>
              <div class="col-md-6">
                <label for="dob">dob</label>
                <input class="form-control" type="text" placeholder="yyyy-MM-dd" name="dob" value="<spring:eval expression="customer.dob"></spring:eval>"></input>
              </div>
            </div>
    	</div>
           
          <div class="form-group">
           <c:choose>
           		<c:when test="${customer.gender eq 'MALE'}">
           			<label class="radio-inline">
     					 <input type="radio" name="gender" value="FEMALE">&nbsp;FEMALE&nbsp;
  		     		</label>&nbsp;&nbsp;
   			 		<label class="radio-inline">
   			    		 <input type="radio" name="gender" value="MALE" checked>&nbsp;MALE
    		 		</label> 
           		</c:when>
           		<c:when test="${customer.gender eq 'FEMALE'}">
           			 <label class="radio-inline">
     					 <input type="radio" name="gender" value="FEMALE" checked="checked">&nbsp;FEMALE&nbsp;
  		     		</label>&nbsp;&nbsp;
   			 		<label class="radio-inline">
   			    		 <input type="radio" name="gender" value="MALE">&nbsp;MALE
    		 		</label> 
           		</c:when>
           		<c:otherwise>
           			 <label class="radio-inline">
     					 <input type="radio" name="gender" value="FEMALE">&nbsp;FEMALE&nbsp;
  		     		</label>&nbsp;&nbsp;
   			 		<label class="radio-inline">
   			    		 <input type="radio" name="gender" value="MALE">&nbsp;MALE
    		 		</label> 
           		</c:otherwise>
           </c:choose>
         </div>
    
          <div class="form-group">
            <label for="exampleInputEmail1">Address</label>
            <input class="form-control" id="exampleInputEmail1" type="text" aria-describedby="emailHelp" placeholder="Door no" name="address.doorNo" value="${customer.address.doorNo}">
          </div>
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <label for="exampleInputPassword1">Street</label>
                <input class="form-control" id="exampleInputPassword1" type="text" placeholder="street" name="address.street" value="${customer.address.street}">
              </div>
              <div class="col-md-6">
                <label for="exampleInputPassword1">city</label>
                <input class="form-control" id="exampleInputPassword1" type="text" placeholder="city" name="address.city" value="${customer.address.city}">
              </div>
            </div>
          </div>
          
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <label for="exampleInputPassword1">country</label>
                <form:select path="customer.country" class="form-control" style="display:none">
    				<form:options items="${countryCodes}" itemValue="code" itemLabel="name" />
				</form:select>
                <input class="form-control" id="exampleInputPassword1" type="text" placeholder="country" name="address.country" value="${customer.address.country}">
              </div>
              <div class="col-md-6">
                <label for="exampleInputPassword1">zipcode</label>
                <input class="form-control" id="exampleInputPassword1" type="text" placeholder="zipcode" name="address.zipCode" value="${customer.address.zipCode}">
              </div>
            </div>
          </div>
          <input type="submit" class="btn btn-primary btn-block" value="Update"></input>
        </form>
      
      </div>
    </div>
