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

<display:table name="authors" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

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
	
	<spring:message code="author.creditCard.holderName" var="holderName" />
	<display:column title="${holderName}" property="creditCard.holderName"/>
	
	<spring:message code="author.creditCard.brandName" var="brandName" />
	<display:column title="${brandName}" property="creditCard.brandName"/>
	
	<spring:message code="author.creditCard.expirationMonth" var="expirationMonth" />
	<display:column title="${expirationMonth}" property="creditCard.expirationMonth"/>
	
	<spring:message code="author.creditCard.expirationYear" var="expirationYear" />
	<display:column title="${expirationYear}" property="creditCard.expirationYear"/>
	
	<spring:message code="author.creditCard.cVVCode" var="cVVCode" />
	<display:column title="${cVVCode}" property="creditCard.cVVCode"/>
	
	<spring:message code="author.creditCard.cardNumber" var="cardNumber" />
	<display:column title="${cardNumber}" property="creditCard.cardNumber"/>
	
	<spring:message code="author.birthDate" var="birthDate" />
	<display:column title="${birthDate}" property="birthDate" format="{0,date,dd/MM/yyyy}" sortable="true" />
	
	<spring:message code="author.nationality" var="nationality" />
	<display:column title="${nationality}" property="nationality"/>
	
</display:table>


<br />

		<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="author.back"/>" /></a>
