<%--
 * action-1.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${actionURI}" modelAttribute="essay">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="author" />
	<form:hidden path="contest" />
	<form:hidden path="submissionDate" />
	<form:hidden path="published" />
	
	<security:authorize access="hasRole('AUTHOR')">
		<jstl:if test="${essay.id == 0}">
			<acme:textbox code="essay.title" path="title" />
				<br />
		</jstl:if>
		<acme:textbox code="essay.optionalAbstract" path="optionalAbstract" />
			<br />
		<acme:textbox code="essay.contents" path="contents" />
			<br />
	</security:authorize>
	<security:authorize access="hasRole('ORGANISER')">
	<form:hidden path="title" />
	<form:hidden path="optionalAbstract" />
	<form:hidden path="contents" />
	</security:authorize>

	<jstl:if test="${essay.id != 0}">
			<form:hidden path="title" />
	</jstl:if>

	<acme:submit code="essay.save" name="save" />
	&nbsp;
	
	<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="essay.cancel"/>" /></a>



</form:form>


