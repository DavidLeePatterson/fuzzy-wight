<%@ page import="com.ascii.AsciiArt" %>



<div class="fieldcontain ${hasErrors(bean: asciiArtInstance, field: 'shape', 'error')} required">
	<label for="shape">
		<g:message code="asciiArt.shape.label" default="Shape" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="shape" from="${com.ascii.ShapeType?.values()}" keys="${com.ascii.ShapeType.values()*.name()}" required="" value="${asciiArtInstance?.shape?.name()}" />

</div>

<div class="fieldcontain ${hasErrors(bean: asciiArtInstance, field: 'height', 'error')} required">
	<label for="height">
		<g:message code="asciiArt.height.label" default="Shape Height" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="height" type="number" min="3" max="100" value="${asciiArtInstance.height}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: asciiArtInstance, field: 'label', 'error')} ">
	<label for="label">
		<g:message code="asciiArt.label.label" default="Art Label" />
		
	</label>
	<g:textArea name="label" cols="40" rows="5" maxlength="255" value="${asciiArtInstance?.label}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: asciiArtInstance, field: 'labelRow', 'error')} ">
	<label for="labelRow">
		<g:message code="asciiArt.labelRow.label" default="Label Row" />
		
	</label>
	<g:field name="labelRow" type="number" min="0" max="100" value="${asciiArtInstance.labelRow}"/>

</div>

