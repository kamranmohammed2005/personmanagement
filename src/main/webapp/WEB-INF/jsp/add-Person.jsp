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
<title>Add Person</title>
</head>
<body>
<h1>Create New Person</h1>
<c:url var="saveUrl" value="/mars/main/record/add" />
<form:form modelAttribute="personAttribute" method="POST" action="${saveUrl}">
	<table>
		<tr>
			<td>First Name : </td>
			<td><form:input path="firstName"  /></td>
			<td><form:errors path="firstName" cssClass="error" /></td>
 		</tr>
		<tr>
			<td>Last Name : </td>
			<td><form:input path="lastName"/></td>
			<td><form:errors path="lastName" cssClass="error" /></td>
		</tr>
		
		
	</table>
	
	<input type="submit" value="Save" />
</form:form>

</body>
</html>