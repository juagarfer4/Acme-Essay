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

<form:form action="${actionURI}" modelAttribute="organiserForm">

	<acme:textbox code ="organiser.userAccount.username"  path="username"/>
	<br />
	<acme:password code ="organiser.userAccount.password"  path="password"/>
	<br />
	<acme:password code ="organiser.userAccount.passwordVerificada"  path="passwordVerificada"/>
	<br />
		<acme:textbox code ="organiser.name"  path="name"/>
	<br />
		<acme:textbox code ="organiser.surname"  path="surname"/>
	<br />
		<acme:textbox code ="organiser.email"  path="email"/>
	<br />
		<acme:textbox code ="organiser.phone"  path="phone"/>
	<br />
		<acme:textbox code ="organiser.homePage"  path="homePage"/>
	<br />
		<acme:textbox code ="organiser.birthDate"  path="birthDate"/>
	<br />
		<acme:textbox code ="organiser.nationality"  path="nationality"/>
	<br />
	
	<h3><spring:message code="organiser.creditCard.title" /></h3>
		<acme:textbox code ="organiser.creditCard.holderName"  path="creditCard.holderName"/>
	<br />
		<acme:textbox code ="organiser.creditCard.brandName"  path="creditCard.brandName"/>
	<br />
		<acme:textbox code ="organiser.creditCard.expirationMonth"  path="creditCard.expirationMonth"/>
	<br />
		<acme:textbox code ="organiser.creditCard.expirationYear"  path="creditCard.expirationYear"/>
	<br />
		<acme:textbox code ="organiser.creditCard.cVVCode"  path="creditCard.cVVCode"/>
	<br />
		<acme:textbox code ="organiser.creditCard.cardNumber"  path="creditCard.cardNumber"/>
	<br />
	
	<acme:checkbox path="condition" url="privacy/lopd-lssi.do" code="organiser.laws" />
	<br />
		<acme:submit code="organiser.register" name="save" />
	&nbsp;
	
	<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="organiser.cancel"/>" /></a>





	
	
</form:form>
