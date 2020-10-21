<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Persons List</title>
</head>
<body>
	<h1>Persons Records List</h1>

	<c:url var="editImgUrl" value="/resources/img/edit.png" />
	<c:url var="deleteImgUrl" value="/resources/img/delete.png" />
	<c:url var="addUrl" value="/mars/main/record/add" />
	<table style="border: 1px solid; width: 100%; text-align: center">
		<thead style="background:#d3dce3">
			<tr>
				<th>Id</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th colspan="2"></th>
				<th>Stree No</th>
				<th>Street Name</th>
				<th>City</th>
				<th>Country</th>
				<th colspan="3"></th>
			</tr>
		</thead>
		<tbody style="background: #ccc">
			<c:forEach items="${persons}" var="person">
				<c:url var="editUrl" value="/mars/main/record/edit?id=${person.id}" />
				<c:url var="deleteUrl" value="/mars/main/record/delete?id=${person.id}" />

				<c:if test="${!empty person.addresses}">
					<c:forEach items="${person.addresses}" var="address">
						<tr>
							<td><c:out value="${person.id}" /></td>
							<td><c:out value="${person.firstName}" /></td>
							<td><c:out value="${person.lastName}" /></td>
							<td><a href="${editUrl}"><img src="${editImgUrl}"></img></a></td>
							<td><a href="${deleteUrl}"><img src="${deleteImgUrl}"></img></a></td>

							<td><c:out value="${address.street_no}" /></td>
							<td><c:out value="${address.street_name}" /></td>
							<td><c:out value="${address.city}" /></td>
							<td><c:out value="${address.country}" /></td>
							<c:url var="addAdUrl" value="/mars/main/address/add?id=${person.id}" />
							<c:url var="editAdUrl" value="/mars/main/address/edit?pid=${person.id}&aid=${address.id}" />
							<c:url var="deleteAdUrl" value="/mars/main/address/delete?id=${address.id}" />
							<td><a href="${addAdUrl}">+</a></td>
							<td><a href="${editAdUrl}"><img src="${editImgUrl}"></img></a></td>
							<td><a href="${deleteAdUrl}"><img src="${deleteImgUrl}"></img></a></td>
						</tr>
					</c:forEach>
				</c:if>

				<c:if test="${empty person.addresses}">
					<tr>
						<td><c:out value="${person.id}" /></td>
						<td><c:out value="${person.firstName}" /></td>
						<td><c:out value="${person.lastName}" /></td>
						<td><a href="${editUrl}"><img src="${editImgUrl}"></img></a></td>
						<td><a href="${deleteUrl}"><img src="${deleteImgUrl}"></img></a></td>

						<td>N/A</td>
						<td>N/A</td>
						<td>N/A</td>
						<td>N/A</td>
						<c:url var="addAdUrl"  value="/mars/main/address/add?id=${person.id}" />
						<td><a href="${addAdUrl}">+</a></td>
						<td></td>
						<td></td>
					</tr>
				</c:if>

			</c:forEach>
		</tbody>
	</table>

	<c:if test="${empty persons}">
 No records found. 
</c:if>

	<p>
		<a href="${addUrl}">Create new record</a>
	</p>

</body>
</html>