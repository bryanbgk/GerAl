
<%@ page import="geral.Relogio" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'relogio.label', default: 'Relogio')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-relogio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-relogio" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="numero" title="${message(code: 'relogio.numero.label', default: 'Numero')}" />
					
						<g:sortableColumn property="posicaoAtual" title="${message(code: 'relogio.posicaoAtual.label', default: 'Posicao Atual')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${relogioInstanceList}" status="i" var="relogioInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${relogioInstance.id}">${fieldValue(bean: relogioInstance, field: "numero")}</g:link></td>
					
						<td>${fieldValue(bean: relogioInstance, field: "posicaoAtual")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${relogioInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
