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


<display:table name="contests" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="contest.name" var="name" />
	<display:column title="${name}" property="name" />

	<spring:message code="contest.description" var="description" />
	<display:column title="${description}" property="description" />
	
	<spring:message code="contest.holdingDate" var="holdingDate" />
	<display:column title="${holdingDate}" property="holdingDate" format="{0,date,dd/MM/yyyy}" sortable="true" />
	
	<spring:message code="contest.deadline" var="deadline" />
	<display:column title="${deadline}" property="deadline" format="{0,date,dd/MM/yyyy}" sortable="true" />
	
	<spring:message code="contest.results" var="results" />
	<display:column title="${results}" property="results"/>
	
	<display:column><a href="essay/list.do?contestId=${row.id}"><input type="button" name="Essays"
		value="<spring:message code="contest.essays"/>" /></a></display:column>
		
	<display:column titleKey="contest.picture" >
		<jstl:if test="${row.picture != null}">
			<img src="contest/organiser/show.do?contestId=${row.id}" style="height: 50px;" class="img-thumbnail">
		</jstl:if>
		</display:column>
	
	<security:authorize access="hasRole('AUTHOR')">
		<display:column><a href="essay/author/list.do?contestId=${row.id}"><input type="button" name="Essays"
			value="<spring:message code="contest.essays.author"/>" /></a></display:column>
			
		<display:column>
			<jstl:if test="${date.before(row.deadline)}">
				<a href="essay/author/create.do?contestId=${row.id}"><input type="button" name="Essays"
					value="<spring:message code="essay.create"/>" /></a>
				</jstl:if>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ORGANISER')">
		<jstl:if test="${myContests == true}">
			<display:column><a href="contest/organiser/edit.do?contestId=${row.id}"><input type="button" name="create"
			value="<spring:message code="contest.edit"/>" /></a></display:column>
			
			<display:column><a href="contest/organiser/add.do?contestId=${row.id}"><input type="button" name="create"
			value="<spring:message code="contest.add"/>" /></a></display:column>
			
			<display:column><a href="publicsession/organiser/list.do?contestId=${row.id}"><input type="button" name="create"
			value="<spring:message code="publicsession.list"/>" /></a></display:column>
			
			<display:column><a href="publicsession/organiser/create.do?contestId=${row.id}"><input type="button" name="create"
			value="<spring:message code="publicsession.edit"/>" /></a></display:column>
		</jstl:if>
	</security:authorize>
	
</display:table>


<br />

<security:authorize access="hasRole('ORGANISER')">
	<jstl:if test="${myContests == true}">
		<a href="contest/organiser/create.do"><input type="button" name="create"
		value="<spring:message code="contest.create"/>" /></a>
	</jstl:if>
</security:authorize>

		<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="contest.back"/>" /></a>
