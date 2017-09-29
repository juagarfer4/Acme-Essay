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

<form:form action="${actionURI}" modelAttribute="contestForm">

	<form:hidden path="contestId" />
	
	<jstl:if test="${organisers.size()==0}">
			<spring:message code="contest.noorganisers" />
		<br />
	</jstl:if>
	<jstl:if test="${organisers.size()!=0}">
			<acme:select items="${organisers}" itemLabel="name" code="contest.organiser" path="organiser"/>
		<br />
		
		<acme:submit code="contest.add" name="save2" />
	&nbsp;
	</jstl:if>

	<a href="<spring:url value='contest/organiser/list.do' />"><input type="button" name="Back"
		value="<spring:message code="contest.cancel"/>" /></a>



	
</form:form>
