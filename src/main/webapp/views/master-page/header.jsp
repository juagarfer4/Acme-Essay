<%--
 * header.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme-Essay, Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMINISTRATOR')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/administrator/create.do"><spring:message code="master.page.register.administrator" /></a></li>
					<li><a href="author/administrator/list.do"><spring:message code="master.page.list.administrator.author" /></a></li>
					<li><a href="organiser/administrator/list.do"><spring:message code="master.page.list.administrator.organiser" /></a></li>
					<li><a href="administrator/dashboard.do"><spring:message code="master.page.list.administrator.dashboard" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('AUTHOR')">
			<li><a class="fNiv"><spring:message	code="master.page.author" /></a>
				<ul>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('ORGANISER')">
			<li><a class="fNiv"><spring:message	code="master.page.organiser" /></a>
				<ul>
					<li><a href="contest/organiser/list.do"><spring:message code="master.page.contest.organiser.list" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv"><spring:message	code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="customer/action-1.do"><spring:message code="master.page.customer.action.1" /></a></li>
					<li><a href="customer/action-2.do"><spring:message code="master.page.customer.action.2" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv" href="author/create.do"><spring:message code="master.page.register.author" /></a></li>
			<li><a class="fNiv" href="organiser/create.do"><spring:message code="master.page.register.organiser" /></a></li>
			<li><a class="fNiv" href="contest/list.do"><spring:message code="master.page.contest.list" /></a></li>
			<li><a class="fNiv"><spring:message	code="master.page.legal" /></a>
				<ul>
					<li><a href="privacy/lopd-lssi.do"><spring:message code="master.page.legal.lopd-lssi" /></a></li>
					<li><a href="privacy/cookies.do"><spring:message code="master.page.legal.transpositions" /></a></li>
					<li><a href="privacy/unsubscribe.do"><spring:message code="master.page.legal.unsubscribe" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
			<li><a class="fNiv" href="contest/list.do"><spring:message code="master.page.contest.list" /></a></li>
			<li><a class="fNiv"><spring:message	code="master.page.legal" /></a>
				<ul>
					<li><a href="privacy/lopd-lssi.do"><spring:message code="master.page.legal.lopd-lssi" /></a></li>
					<li><a href="privacy/cookies.do"><spring:message code="master.page.legal.transpositions" /></a></li>
					<li><a href="privacy/unsubscribe.do"><spring:message code="master.page.legal.unsubscribe" /></a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

