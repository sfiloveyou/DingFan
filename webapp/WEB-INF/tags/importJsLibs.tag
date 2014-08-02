<%@tag description="Import Libary" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="ext" type="java.lang.String" description="true|false" %>
<%@attribute name="jquery" type="java.lang.String" description="true|false" %>
<%@attribute name="jqueryUI" type="java.lang.String" description="true|false" %>
<%@attribute name="jqueryUI_i18n" type="java.lang.String" description="例如：zh-CN" %>
<%@attribute name="jqueryValidate" type="java.lang.String" description="true|false" %>
<%@attribute name="jqueryValidate_i18n" type="java.lang.String" description="例如：cn" %>
<%@attribute name="dwr" type="java.lang.String" description="true|false" %>
<%
	if (jqueryUI_i18n == null) {
		jspContext.setAttribute("jqueryUI_i18n", "zh-CN");
	}
	if (jqueryValidate_i18n == null) {
		jspContext.setAttribute("jqueryValidate_i18n", "cn");
	}	
%> 
<c:if test="${dwr eq 'true' && dwr_includeScriptOnce ne true}" >
	<script type="text/javascript" src="dwr/engine.js" ></script>
	<script type="text/javascript" src="dwr/util.js"></script>
	<c:set var="dwr_includeScriptOnce" value="true" scope="request" />
</c:if>
<c:if test="${ext eq 'true' && ext_includeScriptOnce ne true}" >
	<link rel="stylesheet" type="text/css" href="<c:url value='/js/ext-2.2.1/resources/css/ext-all.css'/>" />
	<script type="text/javascript" src="<c:url value='/js/ext-2.2.1/adapter/ext/ext-base.js'/>" ></script>
	<script type="text/javascript" src="<c:url value='/js/ext-2.2.1/ext-all.js'/>"></script> 
	<script language="javascript">
	    Ext.BLANK_IMAGE_URL = "<c:url value='/js/ext-2.2.1/resources/images/default/s.gif'/>";
	</script>
	<c:set var="ext_includeScriptOnce" value="true" scope="request" />
</c:if>       
<c:if test="${jquery eq 'true' && jquery_includeScriptOnce ne true}" >
    <script type="text/javascript" src="<c:url value='/js/jquery-ui-1.8.2.custom/js/jquery-1.4.2.min.js'/>"></script>
	<script type="text/javascript">
	 $(document).ready(function(){
		 if('${msg}'!='')
		 	alert('${msg}');
	 })
	</script>            
	<c:set var="jquery_includeScriptOnce" value="true" scope="request" />
</c:if>
<c:if test="${jqueryUI eq 'true' && jqueryUI_includeScriptOnce ne true}" >
 	<link type="text/css" href="<c:url value='/js/jquery-ui-1.8.2.custom/development-bundle/themes/base/jquery.ui.all.css'/>" rel="stylesheet" />
    <script type="text/javascript" src="<c:url value='/js/jquery-ui-1.8.2.custom/development-bundle/ui/jquery-ui-1.8.2.custom.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/jquery-ui-1.8.2.custom/development-bundle/ui/i18n'/>/jquery.ui.datepicker-${jqueryUI_i18n}.js"></script>
    <c:set var="jqueryUI_includeScriptOnce" value="true" scope="request" />
</c:if>
<c:if test="${jqueryValidate eq 'true' && jqueryValidate_includeScriptOnce ne true}" >
    <script type="text/javascript" src="<c:url value='/js/jquery-validate/jquery.validate.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/jquery-validate/localization'/>/messages_${jqueryValidate_i18n}.js"></script>
	<c:set var="jqueryValidate_includeScriptOnce" value="true" scope="request" />
</c:if>
