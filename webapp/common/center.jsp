<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body onload="showOnLoadMessage()">
<a href="<c:url value="/loginAction.do?method=accessDenied"/>">1</a>
<a href="<c:url value="/system/header.jsp"/>">2</a>
<a href="<c:url value="/j_spring_security_logout"/>">退出登录</a>
<fmt:message key="errorPage.title"></fmt:message>
</body>
</html> 