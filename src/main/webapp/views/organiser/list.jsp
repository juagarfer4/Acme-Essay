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
	
	<spring:message code="organiser.creditCard.holderName" var="holderName" />
	<display:column title="${holderName}" property="creditCard.holderName"/>
	
	<spring:message code="organiser.creditCard.brandName" var="brandName" />
	<display:column title="${brandName}" property="creditCard.brandName"/>
	
	<spring:message code="organiser.creditCard.expirationMonth" var="expirationMonth" />
	<display:column title="${expirationMonth}" property="creditCard.expirationMonth"/>
	
	<spring:message code="organiser.creditCard.expirationYear" var="expirationYear" />
	<display:column title="${expirationYear}" property="creditCard.expirationYear"/>
	
	<spring:message code="organiser.creditCard.cVVCode" var="cVVCode" />
	<display:column title="${cVVCode}" property="creditCard.cVVCode"/>
	
	<spring:message code="organiser.creditCard.cardNumber" var="cardNumber" />
	<display:column title="${cardNumber}" property="creditCard.cardNumber"/>
	
	<spring:message code="organiser.birthDate" var="birthDate" />
	<display:column title="${birthDate}" property="birthDate" format="{0,date,dd/MM/yyyy}" sortable="true" />
	
	<spring:message code="organiser.nationality" var="nationality" />
	<display:column title="${nationality}" property="nationality"/>
	
</display:table>


<br />

		<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="organiser.back"/>" /></a>
