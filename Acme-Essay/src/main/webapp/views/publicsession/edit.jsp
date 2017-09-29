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

<form:form action="${actionURI}" modelAttribute="publicSession">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="essays" />
	<form:hidden path="contest" />
	<form:hidden path="repos" />

	<acme:textbox code ="publicSession.startMoment"  path="startMoment"/>
	<br />
	<acme:textbox code ="publicSession.endMoment"  path="endMoment"/>
	<br />
	<acme:textbox code ="publicSession.capacity"  path="capacity"/>
	<br />
	<acme:textbox code ="publicSession.limitOfEssays"  path="limitOfEssays"/>
	<br />
	
	<acme:select items="${organisers}" itemLabel="name" code="publicSession.chairman" path="chairman"/>
	<br />
	
		<acme:submit code="publicSession.save" name="save" />
	&nbsp;
	
	<a href="<spring:url value='contest/organiser/list.do' />"><input type="button" name="Back"
		value="<spring:message code="publicSession.cancel"/>" /></a>





	
	
</form:form>
