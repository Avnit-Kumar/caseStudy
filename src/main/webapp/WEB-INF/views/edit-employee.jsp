<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
    <title>View / Edit Employee</title>

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">

    <script src="${pageContext.request.contextPath}/resources/js/script.js"></script>
</head>

<body>

<a href="${pageContext.request.contextPath}/" class="home-icon">
    <i class="fa-solid fa-house"></i>
</a>

<div class="container">

    <h2>View / Edit Employee</h2>

    <!-- Success Message -->
    <c:if test="${not empty success}">
        <p class="message-success">${success}</p>
    </c:if>

    <!-- Error Message -->
    <c:if test="${not empty error}">
        <p class="message-error">${error}</p>
    </c:if>

    <!-- Spring Form Starts -->
    <form:form method="post"
               modelAttribute="employee"
               action="${pageContext.request.contextPath}/employee/edit/${employee.id}">

        <!-- ID -->
        <label>ID:</label>
        <form:input path="id" readonly="true" />

        <!-- First Name -->
        <label>First Name*:</label>
        <form:input path="firstName" />
        <form:errors path="firstName" cssClass="field-error" />

        <!-- Middle Name -->
        <label>Middle Name:</label>
        <form:input path="middleName" />
        <form:errors path="middleName" cssClass="field-error" />

        <!-- Last Name -->
        <label>Last Name*:</label>
        <form:input path="lastName" />
        <form:errors path="lastName" cssClass="field-error" />

        <!-- Birth Date -->
        <label>Birth Date*:</label>
        <form:input path="dateOfBirth" type="date" />
        <form:errors path="dateOfBirth" cssClass="field-error" />

        <!-- Position -->
        <label>Position*:</label>
        <form:input path="position" />
        <form:errors path="position" cssClass="field-error" />

        <br/><br/>

        <button type="submit" class="btn update-btn">Update</button>
        <button type="reset" class="btn clear-btn">Clear</button>

        <button type="button"
                class="btn back-btn"
                onclick="window.location.href='${pageContext.request.contextPath}/employee/search'">
            Back
        </button>

    </form:form>

</div>

</body>
</html>
