<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

<!-- Home Icon -->
<a href="${pageContext.request.contextPath}/" class="home-icon">
    <i class="fa-solid fa-house"></i>
</a>

<div class="container">

    <h2>Add Compensation</h2>

    <!-- Success Message -->
    <c:if test="${not empty success}">
        <div class="success">${success}</div>
    </c:if>

    <!-- Error Message -->
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form:form method="post"
               modelAttribute="compensation"
               action="${pageContext.request.contextPath}/compensation/add">

        <form:hidden path="employeeId"/>

        <!-- Type -->
        <label>Type*</label>
        <form:select path="type">
            <form:options items="${types}"/>
        </form:select>
        <form:errors path="type" cssClass="field-error"/>

        <!-- Amount -->
        <label>Amount*</label>
        <form:input path="amount"/>
        <form:errors path="amount" cssClass="field-error"/>

        <!-- Description -->
        <label>Description</label>
        <form:input path="description"/>
        <form:errors path="description" cssClass="field-error"/>

        <!-- Payment Date -->
        <label>Payment Date*</label>
        <form:input type="date" path="paymentDate"/>
        <form:errors path="paymentDate" cssClass="field-error"/>

        <button type="submit" class="btn">
            Add Compensation
        </button>

        <button type="button"
                class="clear-btn"
                onclick="window.history.back()">
            Cancel
        </button>

    </form:form>

</div>

</body>
</html>
