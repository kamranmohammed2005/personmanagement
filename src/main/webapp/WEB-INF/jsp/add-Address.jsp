<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
            .error {
                color: red
            }
        </style>
<title>Add Address</title>
</head>
<body>

<h1>Add New Address</h1>

<c:url var="saveUrl" value="/mars/main/address/add?id=${personId}" />
<form:form modelAttribute="addressAttribute" method="POST" action="${saveUrl}">
	<table>
	
		<tr>
			<td>Person Id:</td>
			<td><input type="text" value="${personId}" disabled="true"/>
		</tr>
		<tr>
			<td><form:label path="street_no">Street No:</form:label></td>
			<td><form:input path="street_no"  /></td>
			<td><form:errors path="street_no" cssClass="error" /></td>
		</tr>
		<tr>
			<td><form:label path="street_name">Street Name:</form:label></td>
			<td><form:input path="street_name"  /></td>
			<td><form:errors path="street_name" cssClass="error" /></td>
		</tr>
		
		<tr>
			<td><form:label path="city">City:</form:label></td>
			<td><form:input path="city"  /></td>
			<td><form:errors path="city" cssClass="error" /></td>
		</tr>


		<tr>
			<td><form:label path="country">Country:</form:label></td>
			<td><form:input path="country"  /></td>
			<td><form:errors path="country" cssClass="error" /></td>
		</tr>
	</table>
	
	<input type="submit" value="Save" />
</form:form>

</body>
</html>