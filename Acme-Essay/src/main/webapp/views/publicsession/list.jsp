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

<display:table name="publicSessions" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="publicSession.startMoment" var="startMoment" />
	<display:column title="${startMoment}" property="startMoment" format="{0,date,dd/MM/yyyy}" sortable="true" />
	
	<spring:message code="publicSession.endMoment" var="endMoment" />
	<display:column title="${endMoment}" property="endMoment" format="{0,date,dd/MM/yyyy}" sortable="true" />

	<spring:message code="publicSession.capacity" var="capacity" />
	<display:column title="${capacity}" property="capacity" />
	
	<spring:message code="publicSession.limitOfEssays" var="limitOfEssays" />
	<display:column title="${limitOfEssays}" property="limitOfEssays" />
	
	<spring:message code="publicSession.chairman" var="chairman" />
	<display:column title="${chairman}" property="chairman.name" />
	
	<display:column><a href="publicsession/organiser/edit.do?publicSessionId=${row.id}"><input type="button" name="add"
			value="<spring:message code="publicSession.edit"/>" /></a></display:column>
	
	<display:column><a href="publicsession/organiser/add.do?publicSessionId=${row.id}"><input type="button" name="add"
			value="<spring:message code="publicSession.addEssay"/>" /></a></display:column>
	
	<display:column><a href="repo/organiser/list.do?publicSessionId=${row.id}"><input type="button" name="list"
			value="<spring:message code="repo.list"/>" /></a></display:column>
	
	<jstl:if test="${date.before(row.startMoment)}">
	<display:column><a href="repo/organiser/create.do?publicSessionId=${row.id}"><input type="button" name="create"
			value="<spring:message code="repo.create"/>" /></a></display:column>
	</jstl:if>
	
</display:table>


<br />

		<a href="<spring:url value='contest/organiser/list.do' />"><input type="button" name="Back"
		value="<spring:message code="publicsession.back"/>" /></a>
