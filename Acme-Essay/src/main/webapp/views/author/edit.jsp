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

<form:form action="${actionURI}" modelAttribute="authorForm">

	<acme:textbox code ="author.userAccount.username"  path="username"/>
	<br />
	<acme:password code ="author.userAccount.password"  path="password"/>
	<br />
	<acme:password code ="author.userAccount.passwordVerificada"  path="passwordVerificada"/>
	<br />
		<acme:textbox code ="author.name"  path="name"/>
	<br />
		<acme:textbox code ="author.surname"  path="surname"/>
	<br />
		<acme:textbox code ="author.email"  path="email"/>
	<br />
		<acme:textbox code ="author.phone"  path="phone"/>
	<br />
		<acme:textbox code ="author.homePage"  path="homePage"/>
	<br />
		<acme:textbox code ="author.birthDate"  path="birthDate"/>
	<br />
		<acme:textbox code ="author.nationality"  path="nationality"/>
	<br />
	
	<h3><spring:message code="author.creditCard.title" /></h3>
		<acme:textbox code ="author.creditCard.holderName"  path="creditCard.holderName"/>
	<br />
		<acme:textbox code ="author.creditCard.brandName"  path="creditCard.brandName"/>
	<br />
		<acme:textbox code ="author.creditCard.expirationMonth"  path="creditCard.expirationMonth"/>
	<br />
		<acme:textbox code ="author.creditCard.expirationYear"  path="creditCard.expirationYear"/>
	<br />
		<acme:textbox code ="author.creditCard.cVVCode"  path="creditCard.cVVCode"/>
	<br />
		<acme:textbox code ="author.creditCard.cardNumber"  path="creditCard.cardNumber"/>
	<br />
	
	<acme:checkbox path="condition" url="privacy/lopd-lssi.do" code="author.laws" />
	<br />
		<acme:submit code="author.register" name="save" />
	&nbsp;
	
	<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="author.cancel"/>" /></a>





	
	
</form:form>
