<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employment System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background-color: #f4f4f4;
        }

        header {
            background-color: #004080;
            color: white;
            padding: 20px;
            text-align: center;
        }

        header h1 {
            margin: 0;
            font-size: 36px;
        }

        .container {
            display: flex;
            justify-content: space-around;
            margin: 40px auto;
            max-width: 1000px;
            flex-wrap: wrap;
        }

        .box {
            background-color: white;
            padding: 20px;
            margin: 10px;
            width: 45%;
            min-width: 300px;
            border: 2px solid #ccc;
            border-radius: 10px;
            box-shadow: 2px 2px 10px rgba(0,0,0,0.1);
        }

        .box h2 {
            margin-top: 0;
            color: #004080;
        }

        .add-button {
            display: inline-block;
            padding: 10px 20px;
            margin-top: 15px;
            text-decoration: none;
            color: white;
            background-color: #004080;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .add-button:hover {
            background-color: #0066cc;
        }

        p.placeholder {
            color: #888;
            font-style: italic;
        }
    </style>
</head>
<body>

<header>
    <h1>Employment System</h1>
</header>

<div class="container">
    <div class="box">
        <h2>Functions</h2>
<!--         <p class="placeholder">Placeholders for future functions</p> -->
        <a class="add-button" href="${pageContext.request.contextPath}/employee/add">Add Employee</a>
          <br/><br/>

    <a class="add-button" 
       href="${pageContext.request.contextPath}/employee/search">
        Search Employee
    </a>

    <br/><br/>

    <a class="add-button" 
       href="${pageContext.request.contextPath}/employee/search">
        Update Employee
    </a>
    </div>

    <div class="box">
        <h2>Default Views</h2>
        <p class="placeholder">Reserved area for default views</p>
    </div>
</div>

</body>
</html>