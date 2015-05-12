<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>

    <meta name="viewport" content="width=device-width,user-scalable=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="keywords" content="wdcy.cc"/>
    <meta name="description" content="wdcy.cc"/>
    <meta name="author" content="wdcy.cc"/>

    <title><decorator:title default=""/> - WDCY Oauth</title>
    <decorator:head/>

</head>
<body>
<div>
    <decorator:body/>
</div>
<div>
    <hr/>
    <p>
        <a href="mailto:monkeyk1987@gmail.com">monkeyk1987@gmail.com</a>, from <a href="http://wdcy.cc" target="_blank">WDCY</a>.
    </p>
</div>
</body>
</html>