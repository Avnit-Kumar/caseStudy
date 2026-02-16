<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>Compensation History</title>

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
<a href="${pageContext.request.contextPath}/" class="home-icon">
    <i class="fa-solid fa-house"></i>
</a>

<div class="container">

    <h2>View Compensation History</h2>

    <!-- Error Message -->
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form method="post"
          action="${pageContext.request.contextPath}/compensation/history">

        <!-- Hidden Employee ID -->
        <input type="hidden" name="employeeId" value="${employeeId}" />

        <!-- Start Month -->
        <label>Start Month*</label>
        <input type="month" name="startDate" required />

        <!-- End Month -->
        <label>End Month*</label>
        <input type="month" name="endDate" required />

        <!-- Buttons -->
        <button type="submit" class="btn">View History</button>

        <button type="button"
                class="clear-btn"
                onclick="window.location.href='${pageContext.request.contextPath}/employee/search'">
            Back
        </button>

    </form>

</div>

</body>
</html>
