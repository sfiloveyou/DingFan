<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.beans.factory.FactoryBean"%>
<%@page import="org.springframework.security.intercept.web.FilterSecurityInterceptor"%>
<%@page import="org.springframework.security.intercept.web.FilterInvocationDefinitionSource"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body onload="showOnLoadMessage()">
<div align="center">
<form action="/login.do" name="loginForm" method="post">
<table>
	<tr>
		<td>用户名:</td>
		<td><input name="userName" type="text" value="admin" /></td>
	</tr>
	<tr>
		<td>密码:</td>
		<td><input name="password" type="password" value="admin" /></td>
	</tr>
	<tr>
		<td><input type='checkbox' name='_spring_security_remember_me'/></td><td>Remember me on this computer.</td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit"	value="Submit" /></td>
	</tr>
</table>
</form>
</div>
</body>
</html>
