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

<form:form action="${actionURI}" modelAttribute="administratorForm">

	<acme:textbox code ="administrator.userAccount.username"  path="username"/>
	<br />
	<acme:password code ="administrator.userAccount.password"  path="password"/>
	<br />
	<acme:password code ="administrator.userAccount.passwordVerificada"  path="passwordVerificada"/>
	<br />
		<acme:textbox code ="administrator.name"  path="name"/>
	<br />
		<acme:textbox code ="administrator.surname"  path="surname"/>
	<br />
		<acme:textbox code ="administrator.email"  path="email"/>
	<br />
		<acme:textbox code ="administrator.phone"  path="phone"/>
	<br />
		<acme:textbox code ="administrator.homePage"  path="homePage"/>
	<br />
	
	<acme:checkbox path="condition" url="privacy/lopd-lssi.do" code="administrator.laws" />
	<br />
		<acme:submit code="administrator.register" name="save" />
	&nbsp;
		<acme:cancel  url="'/'" code="administrator.cancel" />

	
</form:form>
