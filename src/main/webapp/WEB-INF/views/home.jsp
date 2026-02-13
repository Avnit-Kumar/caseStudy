
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Employment System</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
</head>

<body>

<h1 class="page-title">Employment System</h1>

<div class="container">

    <h2>Functions</h2>

    <div class="button-group">
        <a href="${pageContext.request.contextPath}/employee/add"
           class="btn">
            Add Employee
        </a>

        <a href="${pageContext.request.contextPath}/employee/search"
           class="btn">
            Search Employee
        </a>
    </div>

    <h2 class="section-title">Default Views</h2>

    <p class="default-text">
        Reserved area for default views
    </p>

</div>

</body>
</html>
