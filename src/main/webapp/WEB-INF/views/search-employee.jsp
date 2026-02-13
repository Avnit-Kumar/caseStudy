
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Search Employees</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<script src="${pageContext.request.contextPath}/resources/js/script.js"></script>

</head>

<body>
	<a href="${pageContext.request.contextPath}/" class="home-icon"> <i
		class="fa-solid fa-house"></i>
	</a>
<br>
<br>
<br>
	

	<div class="container">
	<h2>Search Employees</h2>
		<form action="${pageContext.request.contextPath}/employee/results"
			method="get">

			<label>First Name:</label> <input type="text" name="firstName"
				value="${firstName}" /> <label>Last Name:</label> <input
				type="text" name="lastName" value="${lastName}" /> <label>Position:</label>
			<input type="text" name="position" value="${position}" /> <br />

			<button type="submit" class="btn search-btn">Search</button>

			<a href="${pageContext.request.contextPath}/employee/search">
				<button type="button" class="btn clear-btn">Clear</button>
			</a>

			<!-- 			<button type="button" class="btn back-btn" -->
			<%-- 				onclick="window.location.href='${pageContext.request.contextPath}/'"> --%>
			<!-- 				Back to Home</button> -->

		</form>
	</div>

	<hr />
	<hr />


	<c:if test="${not empty message}">
		<p class="no-results">${message}</p>
	</c:if>


	<c:if test="${searched and not empty employees}">
		<h3 style="text-align: center; color: #2c3e50;">Results:</h3>

		<table>
			<tr>
				<th>ID</th>
				<th>First Name</th>
				<th>Middle Name</th>
				<th>Last Name</th>
				<th>Birth Date</th>
				<th>Position</th>
				<th>Action</th>
			</tr>

			<c:forEach var="emp" items="${employees}">
				<tr>
					<td>${emp.id}</td>
					<td>${emp.firstName}</td>
					<td>${emp.middleName}</td>
					<td>${emp.lastName}</td>
					<td>${emp.dateOfBirth}</td>
					<td>${emp.position}</td>
					<td><a
						href="${pageContext.request.contextPath}/employee/edit/${emp.id}">
							View/Edit </a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${searched and empty employees and empty message}">
		<p class="no-results">0 results found</p>
	</c:if>


</body>
</html>
