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


<display:table name="essays" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="essay.title" var="title" />
	<display:column title="${title}" property="title" />

	<spring:message code="essay.optionalAbstract" var="optionalAbstract" />
	<display:column title="${optionalAbstract}" property="optionalAbstract" />
	
	<spring:message code="essay.contents" var="contents" />
	<display:column title="${contents}" property="contents"/>
	
	<spring:message code="essay.submissionDate" var="submissionDate" />
	<display:column title="${submissionDate}" property="submissionDate" format="{0,date,dd/MM/yyyy}" sortable="true" />
	
	<spring:message code="essay.published" var="published" />
	<display:column title="${published}" property="published"/>
	
	<jstl:if test="${myEssays != true}">
		<spring:message code="essay.author.name" var="author" />
		<display:column title="${author}" property="author.name"/>
	</jstl:if>
	
	<security:authorize access="hasRole('AUTHOR')">
		<jstl:if test="${myEssays == true}">
			<jstl:if test="${row.publicSession != null}">
			<spring:message code="essay.publicSession.startMoment" var="startMoment" />
			<display:column title="${startMoment}" property="publicSession.startMoment" format="{0,date,dd/MM/yyyy}" sortable="true" />
			
			<spring:message code="essay.publicSession.endMoment" var="endMoment" />
			<display:column title="${endMoment}" property="publicSession.endMoment" format="{0,date,dd/MM/yyyy}" sortable="true" />
			
			<spring:message code="essay.publicSession.capacity" var="capacity" />
			<display:column title="${capacity}" property="publicSession.capacity"/>
			</jstl:if>
			
			<display:column><a href="essay/author/edit.do?essayId=${row.id}"><input type="button" name="Edit"
			value="<spring:message code="essay.edit"/>" /></a></display:column>
		</jstl:if>
	</security:authorize>
	
	<security:authorize access="hasRole('ORGANISER')">
		<display:column>
		<jstl:if test="${row.published == false}">
			<a href="essay/organiser/edit.do?essayId=${row.id}"><input type="button" name="Edit"
			value="<spring:message code="essay.publish"/>" /></a>
		</jstl:if>
		</display:column>
	</security:authorize>
	
</display:table>


<br />

		<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="essay.back"/>" /></a>
