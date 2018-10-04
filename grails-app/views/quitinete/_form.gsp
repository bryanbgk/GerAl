<%@ page import="geral.Quitinete" %>



<div class="fieldcontain ${hasErrors(bean: quitineteInstance, field: 'numero', 'error')} required">
	<label for="numero">
		<g:message code="quitinete.numero.label" default="Numero" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numero" type="number" value="${quitineteInstance.numero}" required=""/>

</div>

