
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Add Compensation</title>

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

	<a href="${pageContext.request.contextPath}/" class="home-icon"> <i
		class="fa-solid fa-house"></i>
	</a>

	<div class="container">

		<h2>Add Compensation</h2>

		<c:if test="${not empty success}">
			<div class="success">${success}</div>
		</c:if>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>

		<form:form method="post" modelAttribute="compensation"
			action="${pageContext.request.contextPath}/compensation/add">

			<form:hidden path="employeeId" />

			<!-- TYPE -->
			<label class="required">Type </label>
			<form:select path="type" id="typeSelect">
				<form:options items="${types}" />
			</form:select>

			<!-- AMOUNT -->
			<label class="required">Amount </label>
			<form:input path="amount" />
			<form:errors path="amount" cssClass="field-error" />

			<!-- DESCRIPTION -->
			<label> Description <span id="descStar"
				style="color: red; display: none;">*</span>
			</label>
			<form:input path="description" />
			<form:errors path="description" cssClass="field-error" />



			<!-- PAYMENT DATE -->
			<label class="required">Payment Date </label>
			<form:input type="date" path="paymentDate" />
			<form:errors path="paymentDate" cssClass="field-error" />

			<button type="submit" class="btn">Add Compensation</button>

			<button type="button" class="clear-btn"
				onclick="window.history.back()">Back</button>

			<button type="reset" class="clear-btn">Clear</button>

		</form:form>

	</div>


	<script>
		document.addEventListener("DOMContentLoaded", function() {

			const typeSelect = document.getElementById("typeSelect");
			const descStar = document.getElementById("descStar");

			function toggleDescriptionStar() {
				if (!typeSelect)
					return;

				if (typeSelect.value === "SALARY") {
					descStar.style.display = "none";
				} else {
					descStar.style.display = "inline";
				}
			}

			toggleDescriptionStar(); // initial check
			typeSelect.addEventListener("change", toggleDescriptionStar);
		});
	</script>

</body>


</html>