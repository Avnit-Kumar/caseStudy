<<<<<<< HEAD

<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Add Employee</title>

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

    <h2>Add Employee</h2>

    <!-- Success Message -->
    <c:if test="${not empty success}">
        <div class="success">${success}</div>
    </c:if>

    <!-- Global Error Message -->
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <!-- Spring Form -->
    <form:form method="post"
               action="${pageContext.request.contextPath}/employee/add"
               modelAttribute="employee">

        <!-- First Name -->
        <label>First Name*</label>
        <form:input path="firstName" cssClass="input-field"/>
        <form:errors path="firstName" cssClass="field-error" element="div"/>

        <!-- Middle Name -->
        <label>Middle Name</label>
        <form:input path="middleName" cssClass="input-field"/>
        <form:errors path="middleName" cssClass="field-error" element="div"/>

        <!-- Last Name -->
        <label>Last Name*</label>
        <form:input path="lastName" cssClass="input-field"/>
        <form:errors path="lastName" cssClass="field-error" element="div"/>

        <!-- Date of Birth -->
        <label>Date of Birth*</label>
        <form:input type="date" path="dateOfBirth" cssClass="input-field"/>
        <form:errors path="dateOfBirth" cssClass="field-error" element="div"/>

        <!-- Position -->
        <label>Position*</label>
        <form:input path="position" cssClass="input-field"/>
        <form:errors path="position" cssClass="field-error" element="div"/>

        <!-- Buttons -->
        <button type="submit" class="btn">Add Employee</button>
<!--         <button type="reset" class="clear-btn">Clear</button> -->
<button type="button"
        class="clear-btn"
        onclick="window.location.href='${pageContext.request.contextPath}/employee/add'">
    Clear
</button>

    </form:form>

</div>

</body>
</html>
=======
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Add Employee</h2>

<c:if test="${not empty success}">
    <p style="color:green">${success}</p>
</c:if>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<form:form method="post"
           action="${pageContext.request.contextPath}/employee/add"
           modelAttribute="employee">

    First Name*: <form:input path="firstName"/><br/>
    Middle Name: <form:input path="middleName"/><br/>
    Last Name*: <form:input path="lastName"/><br/>
    Birth Date*: <form:input path="birthDate" type="date"/><br/>
    Position*: <form:input path="position"/><br/><br/>

    <button type="submit">Add Employee</button>

</form:form>
>>>>>>> 072566eca27de27e69135cd93125643ded88f466
