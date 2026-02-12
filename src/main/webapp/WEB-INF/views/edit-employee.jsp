<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>View / Edit Employee</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #eef2f3, #d9e4f5);
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
            margin-top: 30px;
            color: #2c3e50;
        }

        .container {
            width: 55%;
            margin: 30px auto;
            background: #ffffff;
            padding: 30px 40px;
            border-radius: 10px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.1);
        }

        label {
            font-weight: bold;
            color: #34495e;
            display: block;
            margin-top: 15px;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-top: 6px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        input[readonly] {
            background-color: #f2f2f2;
        }

        .message-success {
            color: green;
            text-align: center;
            font-weight: bold;
        }

        .message-error {
            color: red;
            text-align: center;
            font-weight: bold;
        }

        .btn {
            padding: 10px 18px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 20px;
            margin-right: 10px;
            font-weight: bold;
        }

        .update-btn {
            background-color: #27ae60;
            color: white;
        }

        .back-btn {
            background-color: #2c3e50;
            color: white;
        }
    </style>
</head>

<body>

<h2>View / Edit Employee</h2>

<div class="container">

    <c:if test="${not empty success}">
        <p class="message-success">${success}</p>
    </c:if>

    <c:if test="${not empty error}">
        <p class="message-error">${error}</p>
    </c:if>

    <form method="post"
      action="${pageContext.request.contextPath}/employee/edit/${employee.id}">


        <label>ID:</label>
        <input type="text" name="id"
               value="${employee.id}" readonly />

        <label>First Name*:</label>
        <input type="text" name="firstName"
               value="${employee.firstName}" />

        <label>Middle Name:</label>
        <input type="text" name="middleName"
               value="${employee.middleName}" />

        <label>Last Name*:</label>
        <input type="text" name="lastName"
               value="${employee.lastName}" />

        <label>Birth Date*:</label>
        <input type="date" name="dateOfBirth"
               value="${employee.dateOfBirth}" />

        <label>Position*:</label>
        <input type="text" name="position"
               value="${employee.position}" />

        <br/>

        <button type="submit" class="btn update-btn">Update</button>

        <button type="button"
                class="btn back-btn"
                onclick="window.location.href='${pageContext.request.contextPath}/'">
            Back to Home
        </button>

    </form>

</div>

</body>
</html>
