<%--
 * action-1.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${actionURI}" modelAttribute="contest" enctype="multipart/form-data">

	<form:hidden path="id" />
    <form:hidden path="version" />
    <form:hidden path="essays" />
    <form:hidden path="organisers" />
    <form:hidden path="publicSessions" />
	
		<acme:textbox code ="contest.name"  path="name"/>
	<br />
		<acme:textbox code ="contest.description"  path="description"/>
	<br />
		<acme:textbox code ="contest.holdingDate"  path="holdingDate"/>
	<br />
		<acme:textbox code ="contest.deadline"  path="deadline"/>
	<br />
	<jstl:if test="${contest.id != 0}">
			<acme:textbox code ="contest.results"  path="results"/>
		<br />
		
	<form:label path="picture">
		<spring:message code="contest.picture" />
	</form:label>
	<form:input path="picture" id="picture" type="file" />
	<form:errors path="picture" cssClass="error" />
		<br />
	</jstl:if>
	
		<acme:submit code="contest.register" name="save" />
	&nbsp;
	
	<jstl:if test="${contest.id != 0}">
		<input type="submit" name="delete" value="<spring:message code="contest.delete"/>" />
	
		&nbsp;
	</jstl:if>
	
	<a href="<spring:url value='contest/organiser/list.do' />"><input type="button" name="Back"
		value="<spring:message code="contest.cancel"/>" /></a>



	
</form:form>
