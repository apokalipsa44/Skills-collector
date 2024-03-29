<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>

<head>
    <title>Rejestracja użytkowanika</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>


<form method="post" action="${pageContext.request.contextPath}/register">
    <fieldset>
        <legend>Dane rejestracyjne</legend>
        <div>
            <label for="login">Login</label>
            <input type="text" required value="${user.login}" id="login" name="login"/>
        </div>
        <div>
            <label for="password">Password</label>
            <input type="text" required value="${user.password}" id="password" name="password"/>
        </div>

    </fieldset>
    <fieldset>
        <legend>Dane osobowe</legend>
        <div>
            <label for="firstName">Imię</label>
            <input type="text" required value="${user.firstName}" id="firstName" name="firstName"/>
        </div>
        <div>
            <label for="lastName">Nazwisko</label>
            <input type="text" required value="${user.lastName}" id="lastName" name="lastName"/>
        </div>
    </fieldset>
    <fieldset>
        <input type="submit" value="Zapisz"/> <input type="reset" value="Wyczyść"/>
    </fieldset>
</form>


<%--<c:if test="${empty request.getParameter('login')}">--%>
<%--    <h3>Login <%= request.getParameter("login") %> is already taken.</h3>--%>
<%--</c:if>--%>

<%
    String perametrized = request.getQueryString();
    String name = request.getParameter("login");
    String userNotFound = request.getParameter("userNotFound");

    if (!(perametrized==null)&&userNotFound==null) {
        out.println("Login " + name + " is  already taken.");
    }

    if (!(perametrized==null)&&userNotFound.equalsIgnoreCase("yes")) {
        out.println("Login " + name + " not found in database. Please register!");
    }
%>

<%--<c:if test="${param.login == null}">--%>
<%--    <h1>nie ma parametru</h1>--%>
<%--</c:if>--%>


<jsp:include page="fragments/footer.jsp"/>
</body>
</html>