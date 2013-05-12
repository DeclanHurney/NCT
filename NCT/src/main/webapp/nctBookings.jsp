<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
   <%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NCT Booking</title>
</head>
<body>
	<a href="j_spring_security-logout">Logout: 
		<security:authentication property="principal.username"/>
	</a>
	<h1>NCT Booking Application</h1>
	<form action="create.html" method="post">
		<fieldset>
			Enter your First Name: 
			<input name="customerFirstName">
			Enter your Last Name: 
			<input name="customerLastName">
    	</fieldset>
		<fieldset>
			Enter your Registration: 
			<input name="registration">
	        <label for="selectVehicleMake">Select Vehicle Make</label>
	        <select id="select" name="vehicleMake">
	            <option>Citroen</option>
	            <option>Ford</option>
	            <option>Peugeot</option>
	            <option>Renault</option>
	            <option>Toyota</option>
	            <option>Volkswagen</option>
	        </select>
			Enter your Vehicle Model:  
			<input name="vehicleModel">
    	</fieldset>
		<fieldset>
			Enter Date of test dd-Mon-yyyy (example 23-Jun-2013): 
			<input name="date">	
	        <label for="selectTestCentre">Select Test Centre</label>
	        <select id="select" name="location">
	            <option>Athlone</option>
	            <option>Cork</option>
	           	<option>Dublin</option>
	            <option>Galway</option>
	           	<option>Limerick</option>
	            <option>Waterford</option>	            
	        </select>
    	</fieldset>
		<input type="submit" value="Create Booking">
	</form>
	
	
	<table id="specificBooking" border="1">
		  <tr>				${thankYou}        </tr>
		  <tr>
		    <td>Booking Number</td>
		    <td>Your Name</td>
		    <td>Vehicle Registration</td>    
		    <td>Vehicle Make and Model</td>
		    <td>Date of Test</td>
		    <td>Test Centre</td> 
		  </tr>
		  <tr>
		    <td>${nctSpecificBooking.location}${nctSpecificBooking.bookingId}</td>
		    <td>${nctSpecificBooking.customerFirst} ${nctSpecificBooking.customerLast}</td>
		    <td>${nctSpecificBooking.registration}</td>    
		    <td>${nctSpecificBooking.vehicleMake} ${nctSpecificBooking.vehicleModel}</td>
		    <td>${nctSpecificBooking.date}</td>
		    <td>${nctSpecificBooking.location}</td> 
		  </tr>	
	</table>		
		<table id="myTable" border="1">
			  <tr>
			    <td>Booking Number</td>
			    <td>Customer Name</td>
			    <td>Registration</td>    
			    <td>Vehicle Make</td>
			    <td>Vehicle Model</td>
			    <td>Booking Date</td>
			    <td>Test Centre</td> 
			    <td>Booking Status</td> 
			    <td>Close</td> 
			    <td>Open</td> 		    
			  </tr>
			<c:forEach items="${nctBookings}" var="nctBooking" varStatus="row">
			  <tr>
			    <td>${nctBooking.location}${nctBooking.bookingId}</td>
			    <td>${nctBooking.customerFirst} ${nctBooking.customerLast}</td>
			    <td>${nctBooking.registration}</td>    
			    <td>${nctBooking.vehicleMake}</td>
			    <td>${nctBooking.vehicleModel}</td>
			    <td>${nctBooking.date}</td>
			    <td>${nctBooking.location}</td> 
			    <td>${nctBooking.status}</td> 
			    <td>
				<form action="cancel.html" method="post">
						<input name="bookingId" value="${nctBooking.bookingId}" type="hidden">
						<input type="submit" value="Cancel">
				</form> 
			    </td> 	
			    <td>
			    <form action="open.html" method="post">
					<input name="bookingId" value="${nctBooking.bookingId}" type="hidden">
					<input type="submit" value="Open">
				</form>
			    </td> 	
			  </tr>
			</c:forEach>		
		</table>
</body>
</html>
