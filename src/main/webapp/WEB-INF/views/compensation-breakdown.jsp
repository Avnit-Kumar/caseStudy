<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
    <title>Compensation Breakdown</title>

    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

    <!-- External CSS -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
</head>

<body>

<!-- Home Icon -->
<a href="${pageContext.request.contextPath}/" class="home-icon">
    <i class="fa-solid fa-house"></i>
</a>

<h2 style="text-align:center; margin-top:60px;">
    Compensation Breakdown - ${selectedMonth}
</h2>

<c:if test="${empty breakdown}">
    <p class="no-results">
        No compensation details found for this month.
    </p>
</c:if>

<c:if test="${not empty breakdown}">
    <table>
        <tr>
            <th>Type</th>
            <th>Amount</th>
            <th>Description</th>
            <th>Date</th>
            <th>Action</th>
        </tr>

        <c:forEach var="comp" items="${breakdown}">
            <tr>
                <td>${comp.type}</td>

                <td>${comp.amount}</td>

                <td>
                    <c:choose>
                        <c:when test="${not empty comp.description and fn:length(comp.description) > 30}">
                            ${fn:substring(comp.description,0,30)}...
                        </c:when>
                        <c:otherwise>
                            ${comp.description}
                        </c:otherwise>
                    </c:choose>
                </td>

                <td>${comp.paymentDate}</td>

                <td>
                    <a href="${pageContext.request.contextPath}/compensation/edit/${comp.id}">
                        Edit
                    </a>
                </td>
            </tr>
        </c:forEach>

        <!-- TOTAL ROW -->
        <tr style="font-weight:bold;">
            <td>Total</td>
            <td>${total}</td>
            <td colspan="3"></td>
        </tr>

    </table>
</c:if>

<!-- Buttons -->
<div style="text-align:center; margin-top:30px;">

    <button class="btn"
            onclick="window.history.back()">
        Back
    </button>

</div>

</body>
</html>
