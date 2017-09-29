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



<!-- The list of contests in descending order of number submitted essays. -->
<h3>
	<spring:message code="administrator.dashboard.req1" />
</h3>

<display:table name="contests1" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

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
	
</display:table>


<br />

<!-- The list of authors who have submitted more essays. -->
<h3>
	<spring:message code="administrator.dashboard.req2" />
</h3>

<display:table name="authors1" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="author.name" var="name" />
	<display:column title="${name}" property="name" />

	<spring:message code="author.surname" var="surname" />
	<display:column title="${surname}" property="surname" />
	
	<spring:message code="author.email" var="email" />
	<display:column title="${email}" property="email"/>
	
	<spring:message code="author.phone" var="phone" />
	<display:column title="${phone}" property="phone"/>
	
	<spring:message code="author.homePage" var="homePage" />
	<display:column title="${homePage}" property="homePage"/>
	
	<spring:message code="author.birthDate" var="birthDate" />
	<display:column title="${birthDate}" property="birthDate" format="{0,date,dd/MM/yyyy}" sortable="true" />
	
	<spring:message code="author.nationality" var="nationality" />
	<display:column title="${nationality}" property="nationality"/>
	
</display:table>



<br />
<!-- The list of authors who have got more essays published. -->
<h3>
	<spring:message code="administrator.dashboard.req3" />
</h3>

<display:table name="authors2" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="author.name" var="name" />
	<display:column title="${name}" property="name" />

	<spring:message code="author.surname" var="surname" />
	<display:column title="${surname}" property="surname" />
	
	<spring:message code="author.email" var="email" />
	<display:column title="${email}" property="email"/>
	
	<spring:message code="author.phone" var="phone" />
	<display:column title="${phone}" property="phone"/>
	
	<spring:message code="author.homePage" var="homePage" />
	<display:column title="${homePage}" property="homePage"/>
	
	<spring:message code="author.birthDate" var="birthDate" />
	<display:column title="${birthDate}" property="birthDate" format="{0,date,dd/MM/yyyy}" sortable="true" />
	
	<spring:message code="author.nationality" var="nationality" />
	<display:column title="${nationality}" property="nationality"/>
	
</display:table>

<br />
<!-- The list of authors who have got less essays published. -->
<h3>
	<spring:message code="administrator.dashboard.req4" />
</h3>

<display:table name="authors3" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="author.name" var="name" />
	<display:column title="${name}" property="name" />

	<spring:message code="author.surname" var="surname" />
	<display:column title="${surname}" property="surname" />
	
	<spring:message code="author.email" var="email" />
	<display:column title="${email}" property="email"/>
	
	<spring:message code="author.phone" var="phone" />
	<display:column title="${phone}" property="phone"/>
	
	<spring:message code="author.homePage" var="homePage" />
	<display:column title="${homePage}" property="homePage"/>
	
	<spring:message code="author.birthDate" var="birthDate" />
	<display:column title="${birthDate}" property="birthDate" format="{0,date,dd/MM/yyyy}" sortable="true" />
	
	<spring:message code="author.nationality" var="nationality" />
	<display:column title="${nationality}" property="nationality" format="{0,date,dd/MM/yyyy}" sortable="true" />
	
</display:table>


<br />
<!-- The average number of essays submitted by an author. -->
<h3>
	<spring:message code="administrator.dashboard.req5" />
</h3>

	<jstl:out value="${averageEssays}"></jstl:out>

<br />
<!-- The average number of contests organised by an organiser. -->
<h3>
	<spring:message code="administrator.dashboard.req6" />
</h3>

	<jstl:out value="${averageContests}"></jstl:out>


<br />
<!-- The list of contests that were held during the last month.  -->
<h3>
	<spring:message code="administrator.dashboard.req7" />
</h3>
<display:table name="contests2" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

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
	
	
</display:table>



<br />
<!-- Show the public sessions with an assigned chairman in descending order of
the session capacity.   -->
<h3>
	<spring:message code="administrator.dashboard.req8" />
</h3>

<display:table name="contests3" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

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
	
	
</display:table>

<display:table name="publicSessions1" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

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
	
	<spring:message code="publicSession.contest.name" var="contest" />
	<display:column title="${contest}" property="contest.name" />
	
	
</display:table>


<br />
<!-- Show the public sessions with an assigned chairman in descending order of
the session capacity.   -->
<h3>
	<spring:message code="administrator.dashboard.req9" />
</h3>
<display:table name="organisers" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="organiser.name" var="name" />
	<display:column title="${name}" property="name" />

	<spring:message code="organiser.surname" var="surname" />
	<display:column title="${surname}" property="surname" />
	
	<spring:message code="organiser.email" var="email" />
	<display:column title="${email}" property="email"/>
	
	<spring:message code="organiser.phone" var="phone" />
	<display:column title="${phone}" property="phone"/>
	
	<spring:message code="organiser.homePage" var="homePage" />
	<display:column title="${homePage}" property="homePage"/>
	
	<spring:message code="organiser.birthDate" var="birthDate" />
	<display:column title="${birthDate}" property="birthDate" format="{0,date,dd/MM/yyyy}" sortable="true" />
	
	<spring:message code="organiser.nationality" var="nationality" />
	<display:column title="${nationality}" property="nationality"/>
	
	<spring:message code="organiser.publicSessions" var="publicSessions" />
	<display:column title="${publicSessions}" >
	<jstl:out value="${row.publicSessions.size()}"></jstl:out>
	</display:column>
	
	
</display:table>



<br />
<!-- Show the public sessions with an assigned chairman in descending order of
the session capacity.   -->
<h3>
	<spring:message code="administrator.dashboard.req10" />
</h3>

<display:table name="publicSessions2" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

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
	
	
</display:table>

<br /><br />

<a href="<spring:url value='/' />"><input type="button" name="Back"
	value="<spring:message code="administrator.back"/>" /></a>
