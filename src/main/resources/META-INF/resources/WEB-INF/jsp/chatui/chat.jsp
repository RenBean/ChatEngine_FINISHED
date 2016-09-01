<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CHAT UI</title>
</head>
<body>
<h1>CHAT UI</h1>
<c:forEach var="chat" items="${allChats}">
    <c:out value="[${chat.sender}] ${chat.message} ${chat.whenSent}" /><br>
</c:forEach>
<br><br>
<form name="chatSubmit" action="/chatui/add" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="text" name="newChat" size="50"> <input type="submit" value="GO">
</form>
</body>
</html>