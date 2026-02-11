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