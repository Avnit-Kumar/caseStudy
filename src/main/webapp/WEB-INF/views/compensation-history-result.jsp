<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Compensation History Result</title>

<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

<!-- External CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<!-- External JS -->
<script defer
	src="${pageContext.request.contextPath}/resources/js/script.js"></script>
</head>

<body>

	<!-- Home Icon -->
	<a href="${pageContext.request.contextPath}/" class="home-icon"> <i
		class="fa-solid fa-house"></i>
	</a>

	<h2 style="text-align: center; margin-top: 60px;">Monthly
		Compensation Summary</h2>

	<c:if test="${empty history}">
		<p class="no-results">No compensation records found for the
			selected period.</p>
	</c:if>

	<c:if test="${not empty history}">
		<table>
			<tr>
				<th>Month</th>
				<th>Total Amount</th>
			</tr>

			<c:forEach var="item" items="${history}">
				<tr>
					<td><a
						href="${pageContext.request.contextPath}/compensation/breakdown/${employeeId}/${item.month}">
							${item.month} </a></td>
					<td>${item.total}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<div style="text-align: center; margin-top: 30px;">
		<button class="btn" onclick="window.history.back()">Back</button>
	</div>

</body>
</html>