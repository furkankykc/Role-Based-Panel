<%--
  Created by IntelliJ IDEA.
  User: furkankykc
  Date: 03.02.2018
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

</head>
<body>
<form:form commandName="groupBean" method="post">
    <label>Grup Adı : </label> <input type='text' name='groupname' />
   <%--<form:select path="permission_id">--%>
       <%--<c:forEach items="${permissions}" var="permission">--%>
           <%--<form:option name="permission_id" value="${permission.getId()}" label="${permission.getName() }" />--%>
       <%--</c:forEach>--%>
   <%--</form:select>--%>
    <input name="add" action="add"  type="submit" value="Insert" class="btn">

</form:form>
<%--
<form:form commandName="group_perms" method="post">
    <form:select path="groupId">
        <c:forEach items="${groups}" var="group">
            <form:option name="groupId" value="${group.getId()}" label="${group.getName() }" />
        </c:forEach>
    </form:select>
    <form:select path="permId">
        <c:forEach items="${permissions}" var="permission">
            <form:option name="permId" value="${permission.getId()}" label="${permission.getName() }" />
        </c:forEach>
    </form:select>
    <input name="update" action="update"  type="submit" value="Insert" class="btn">

</form:form>
--%>
<%--
<form:form commandName="group_perms" method="post">
    <form:select path="groupId">
        <c:forEach items="${groups}" var="group">
            <form:option name="groupId" value="${group.getId()}" label="${group.getName() }" />
        </c:forEach>
    </form:select>
    <form:select path="permId">
        <c:forEach items="${permissions}" var="permission">
            <form:option name="permId" value="${permission.getId()}" label="${permission.getName() }" />
        </c:forEach>
    </form:select>
    <input name="update" action="update"  type="submit" value="update" class="btn">

</form:form>
--%>


<table border="1">
<tr>
    <td>tablo</td>
    <c:forEach items="${permissions}" var="permission">
        <td>${permission.getName()}</td>

    </c:forEach>
</tr>


<c:forEach items="${groups}" var="group">
    <tr>

    <td>${group.getName()} </td>
        <form:form method="post">
    <c:forEach items="${permissions}" var="permission">
        <td>
            <input type="checkbox" name="chckVals" value="${permission.getId()}" />
            <input type="hidden" name="permId" value="${permission.getId()}" />
            <input type="hidden" name="groupId" value="${group.getId()}" />
    </td>

    </c:forEach>
       <td>

           <input name="update" action="update"  type="submit" value="update" class="btn">
           </form:form>


       </td>
    </tr>

    </c:forEach>

</table>
<table>
    <td>gruplar</td>
    <td>${groups}</td>

</table>

</body>
</html>
