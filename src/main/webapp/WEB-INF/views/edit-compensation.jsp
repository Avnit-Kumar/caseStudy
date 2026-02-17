<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Compensation</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
</head>

<body>

<h2 style="text-align:center; margin-top:60px;">
    Edit Compensation
</h2>

<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>

<div class="container">

<form method="post"
      action="${pageContext.request.contextPath}/compensation/edit/${comp.id}">

    <label>Type</label>
    <input type="text" value="${comp.type}" readonly disabled=True/>

    <label>Payment Date</label>
    <input type="text" value="${comp.paymentDate}" readonly/>

    <label>Amount*</label>
    <input type="number" step="0.01"
           name="amount"
           value="${comp.amount}"
           required/>

    <label>Description</label>
    <input type="text"
           name="description"
           value="${comp.description}"/>

    <button type="submit" class="btn">Update</button>

    <button type="button"
            class="clear-btn"
            onclick="window.history.back()">
        Cancel
    </button>

</form>

</div>

</body>
</html>