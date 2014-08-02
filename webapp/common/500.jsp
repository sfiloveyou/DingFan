<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>500 - 系统内部错误</title>
<script type="text/javascript">
function onload(){
	window.parent.location.href='<c:url value="/index.jsp" />';
}
</script>
</head>

<body>
<div><h1>系统发生内部错误.</h1></div>
<div><a href="javascript:onload()">返回首页</a></div>
</body>
</html>
