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

<display:table name="repos" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="repo.name" var="name" />
	<display:column title="${name}" property="name" />

	<spring:message code="repo.remarks" var="remarks" />
	<display:column title="${remarks}" property="remarks" />
	
	<jstl:if test="${date.before(row.publicSession.startMoment)}">
		<display:column><a href="repo/organiser/edit.do?repoId=${row.id}"><input type="button" name="edit"
	value="<spring:message code="repo.delete"/>" /></a></display:column>
	</jstl:if>

</display:table>

<br />

		<a href="contest/organiser/list.do"><input type="button" name="cancel"
		value="<spring:message code="repo.back"/>" /></a>
