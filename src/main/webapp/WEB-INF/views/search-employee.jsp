<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Search Employees</title>

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
            width: 65%;
            margin: 30px auto;
            background: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.1);
        }

        label {
            font-weight: bold;
            color: #34495e;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin: 8px 0 15px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            transition: 0.3s ease;
        }

        input[type="text"]:focus {
            border-color: #3498db;
            box-shadow: 0 0 5px #3498db;
            outline: none;
        }

        .btn {
            padding: 10px 18px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-right: 10px;
            transition: 0.3s ease;
            font-weight: bold;
        }

        .search-btn {
            background-color: #3498db;
            color: white;
        }

        .search-btn:hover {
            background-color: #2980b9;
        }

        .clear-btn {
            background-color: #e67e22;
            color: white;
        }

        .clear-btn:hover {
            background-color: #ca6f1e;
        }

        .back-btn {
            background-color: #2c3e50;
            color: white;
        }

        .back-btn:hover {
            background-color: #1a252f;
        }

        hr {
            margin: 40px auto;
            width: 80%;
        }

        table {
            width: 85%;
            margin: 20px auto;
            border-collapse: collapse;
            background: white;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }

        th {
            background-color: #3498db;
            color: white;
            padding: 12px;
        }

        td {
            padding: 10px;
            text-align: center;
        }

        tr:nth-child(even) {
            background-color: #f2f6fc;
        }

        tr:hover {
            background-color: #e6f2ff;
            transition: 0.3s ease;
        }

        a {
            text-decoration: none;
            color: #3498db;
            font-weight: bold;
        }

        a:hover {
            color: #1d6fa5;
            text-decoration: underline;
        }

        .no-results {
            text-align: center;
            font-weight: bold;
            color: #c0392b;
            margin-top: 20px;
        }
    </style>
</head>

<body>

<h2>Search Employees</h2>

<div class="container">
    <form action="${pageContext.request.contextPath}/employee/results" method="get">

        <label>First Name:</label>
        <input type="text" name="firstName" value="${firstName}"/>

        <label>Last Name:</label>
        <input type="text" name="lastName" value="${lastName}"/>

        <label>Position:</label>
        <input type="text" name="position" value="${position}"/>

        <br/>

        <button type="submit" class="btn search-btn">Search</button>

        <a href="${pageContext.request.contextPath}/employee/search">
            <button type="button" class="btn clear-btn">Clear</button>
        </a>

        <button type="button"
                class="btn back-btn"
                onclick="window.location.href='${pageContext.request.contextPath}/'">
            Back to Home
        </button>

    </form>
</div>

<hr/>

<c:if test="${not empty employees}">
    <h3 style="text-align:center; color:#2c3e50;">Results:</h3>

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
                <td>
                    <a href="${pageContext.request.contextPath}/employee/edit/${emp.id}">
                        View/Edit
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<c:if test="${empty employees}">
    <p class="no-results">0 results found</p>
</c:if>

</body>
</html>