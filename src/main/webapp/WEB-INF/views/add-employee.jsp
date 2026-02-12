<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Add Employee</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #e6f0ff, #f4f4f4);
        }

        .container {
            width: 420px;
            margin: 60px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            color: #004080;
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
            margin-top: 10px;
            display: block;
        }

        input {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .btn {
            width: 100%;
            padding: 10px;
            margin-top: 20px;
            background: #004080;
            color: white;
            border: none;
            border-radius: 5px;
        }

        .btn:hover {
            background: #0066cc;
        }

        .success {
            background: #e6ffe6;
            color: green;
            padding: 8px;
            border-radius: 5px;
            margin-bottom: 10px;
            text-align: center;
        }

        .error {
            background: #ffe6e6;
            color: red;
            padding: 8px;
            border-radius: 5px;
            margin-bottom: 10px;
            text-align: center;
        }

        .field-error {
            color: red;
            font-size: 13px;
        }

        .back-btn {
            width: 100%;
            padding: 8px;
            margin-top: 10px;
            background: gray;
            color: white;
            border: none;
            border-radius: 5px;
        }
    </style>
</head>

<body>

<div class="container">

    <h2>Add Employee</h2>

    <c:if test="${not empty success}">
        <div class="success">${success}</div>
    </c:if>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form method="post"
          action="${pageContext.request.contextPath}/employee/add">

        <label>First Name*</label>
        <input type="text" name="firstName" value="${employee.firstName}"/>
        <span class="field-error">${firstNameError}</span>

        <label>Middle Name</label>
        <input type="text" name="middleName" value="${employee.middleName}"/>
        <span class="field-error">${middleNameError}</span>

        <label>Last Name*</label>
        <input type="text" name="lastName" value="${employee.lastName}"/>
        <span class="field-error">${lastNameError}</span>

        <label>Date of Birth*</label>
        <input type="date" name="dateOfBirth" value="${employee.dateOfBirth}"/>
        <span class="field-error">${dateOfBirthError}</span>

        <label>Position*</label>
        <input type="text" name="position" value="${employee.position}"/>
        <span class="field-error">${positionError}</span>

        <button type="submit" class="btn">Add Employee</button>

        <button type="button" class="back-btn"
                onclick="window.location.href='${pageContext.request.contextPath}/'">
            Back to Home
        </button>

    </form>

</div>

</body>
</html>
