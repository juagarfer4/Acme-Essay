<%--
 * list.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<form:form action="${actionURI}" modelAttribute="repo">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="publicSession" />

	<jstl:if test="${repo.id == 0}">
		<acme:textbox code ="repo.name"  path="name"/>
	<br />
		<acme:textarea code ="repo.remarks"  path="remarks"/>
	<br />
	
	<input type="submit" name="save" value="<spring:message code="repo.save"/>" />
	
	&nbsp;
	</jstl:if>
	<jstl:if test="${repo.id != 0}">
		<form:hidden path="name" />
		<form:hidden path="remarks" />
		<input type="submit" name="delete" value="<spring:message code="repo.delete"/>" />
	</jstl:if>
	
	&nbsp;
	
	 <a href="contest/organiser/list.do"><input type="button" name="cancel"
		value="<spring:message code="repo.cancel"/>" /></a>
	
</form:form>
