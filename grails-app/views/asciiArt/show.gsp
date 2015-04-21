<%@ page import="com.ascii.AsciiArt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'asciiArt.label', default: 'AsciiArt')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-asciiArt" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-asciiArt" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list asciiArt">
			
				<g:if test="${asciiArtInstance?.shape}">
				<li class="fieldcontain">
					<span id="shape-label" class="property-label"><g:message code="asciiArt.shape.label" default="Shape" /></span>
					
						<span class="property-value" aria-labelledby="shape-label"><g:fieldValue bean="${asciiArtInstance}" field="shape"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${asciiArtInstance?.height}">
				<li class="fieldcontain">
					<span id="height-label" class="property-label"><g:message code="asciiArt.height.label" default="Shape Height" /></span>
					
						<span class="property-value" aria-labelledby="height-label"><g:fieldValue bean="${asciiArtInstance}" field="height"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${asciiArtInstance?.label}">
				<li class="fieldcontain">
					<span id="label-label" class="property-label"><g:message code="asciiArt.label.label" default="Art Label" /></span>
					
						<span class="property-value" aria-labelledby="label-label"><g:fieldValue bean="${asciiArtInstance}" field="label"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${asciiArtInstance?.labelRow}">
				<li class="fieldcontain">
					<span id="labelRow-label" class="property-label"><g:message code="asciiArt.labelRow.label" default="Label Row" /></span>
					
						<span class="property-value" aria-labelledby="labelRow-label"><g:fieldValue bean="${asciiArtInstance}" field="labelRow"/></span>
					
				</li>
				</g:if>

				<li class="fieldcontain">
					<span id="Drawing-label" class="property-label">Drawing</span>

					<span class="property-value" aria-labelledby="labelRow-label">
						<pre class="AsciiDrawing">${asciiArtInstance.outputShape()}</pre>
					</span>

				</li>
			
			</ol>
			<g:form url="[resource:asciiArtInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${asciiArtInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
