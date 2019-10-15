<%--
  Created by IntelliJ IDEA.
  User: m_lac
  Date: 15.10.2019
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<form method="post" action="${pageContext.request.contextPath}/login">
    <fieldset>
        <legend>Enter Your login and password</legend>
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
        <input type="submit" value="Login" />

    </fieldset>
</form>

<%
    String login = request.getQueryString();
    String name = request.getParameter("login");

    if (!(login == null)) {
        out.println("Wrong password");
    }

%>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
