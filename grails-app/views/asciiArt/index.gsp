
<%@ page import="com.ascii.AsciiArt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'asciiArt.label', default: 'AsciiArt')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-asciiArt" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-asciiArt" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>

						<g:sortableColumn property="ID" title="${message(code: 'asciiArt.shape.label', default: 'ID')}" />

						<g:sortableColumn property="shape" title="${message(code: 'asciiArt.shape.label', default: 'Shape')}" />
					
						<g:sortableColumn property="height" title="${message(code: 'asciiArt.height.label', default: 'Height')}" />
					
						<g:sortableColumn property="label" title="${message(code: 'asciiArt.label.label', default: 'Label')}" />
					
						<g:sortableColumn property="labelRow" title="${message(code: 'asciiArt.labelRow.label', default: 'Label Row')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${asciiArtInstanceList}" status="i" var="asciiArtInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="show" id="${asciiArtInstance.id}">${fieldValue(bean: asciiArtInstance, field: "id")}</g:link></td>

						<td>${fieldValue(bean: asciiArtInstance, field: "shape")}</td>
					
						<td>${fieldValue(bean: asciiArtInstance, field: "height")}</td>
					
						<td>${fieldValue(bean: asciiArtInstance, field: "label")}</td>
					
						<td>${fieldValue(bean: asciiArtInstance, field: "labelRow")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${asciiArtInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
