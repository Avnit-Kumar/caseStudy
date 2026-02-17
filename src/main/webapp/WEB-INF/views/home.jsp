<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>Employee Management System</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">

    <!-- Added animations -->
    <style>
        @keyframes fadeIn {
            from { opacity:0; transform:translateY(15px); }
            to { opacity:1; transform:translateY(0); }
        }
        @keyframes bounce {
            0%, 100% { transform: translateY(0); }
            50% { transform: translateY(8px); }
        }
    </style>
</head>

<body>

 <!-- Page Title -->
    <h1 class="page-title">Employee Management System</h1>
    <!-- Decorative Background Shapes -->
    <div style="position:fixed; top:-60px; left:-60px; width:220px; height:220px; 
        background:#0d3b66; opacity:0.12; border-radius:50%; z-index:-1;">
    </div>

    <div style="position:fixed; bottom:-80px; right:-80px; width:260px; height:260px; 
        background:#0d3b66; opacity:0.10; border-radius:50%; z-index:-1;">
    </div>

    <!-- Welcome Floating Card -->
    <div style="
        background:white;
        padding:18px 26px;
        border-radius:10px;
        box-shadow:0 8px 18px rgba(0,0,0,0.10);
        margin-bottom:25px;
        margin-top:-20px;
        width:380px;
        text-align:center;
        animation: fadeIn 0.8s ease;
    ">
        <p style="font-size:17px; color:#0d3b66; font-weight:600;">
            Welcome to Employee Management System
        </p>
        <p style="font-size:14px; color:#607d8b;">
            Choose a function to get started
        </p>
    </div>

    <!-- Animated Down Arrow -->
            <div style="margin-top:-5px; animation: bounce 1.8s infinite;">
                <span style="font-size:28px; color:#0d3b66;">⬇</span>
            </div>
   
<br>
    <!-- Wrapper to reduce spacing and improve centering -->
    <div style="display:flex; justify-content:center; align-items:center; margin-top:-30px;">

        <!-- Home Card -->
        <div class="container" style="text-align:center; padding:45px 35px; animation:fadeIn 1s ease;">

            <!-- Small Subtitle -->
            <p style="
                font-size:16px; 
                color:#607d8b; 
                margin-top:-10px; 
                margin-bottom:25px;
            ">
                Manage employees quickly & efficiently
            </p>

            <!-- Decorative Line -->
            <div style="
                width:60px; 
                height:4px; 
                background:#0d3b66; 
                margin:0 auto 25px auto; 
                border-radius:2px;
            "></div>
            
            <!-- Section Heading -->
            <h2 style="margin-bottom:25px;">Functions</h2>

            <!-- Buttons -->
            <div class="button-group">
                <a href="${pageContext.request.contextPath}/employee/add"
                class="btn">
                    ➕ Add Employee
                </a>

                <a href="${pageContext.request.contextPath}/employee/search"
                class="btn">
                    🔍 Search Employee
                </a>
            </div>

           
        </div>

    </div>

    <!-- Button Hover Pop Animation Script -->
    <script>
        document.querySelectorAll('.btn').forEach(btn => {
            btn.addEventListener('mouseover', () => {
                btn.style.transform = 'scale(1.05)';
            });
            btn.addEventListener('mouseout', () => {
                btn.style.transform = 'scale(1)';
            });
        });
    </script>

</body>
</html>

