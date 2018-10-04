<%@ page import="geral.Inquilino" %>



<div class="fieldcontain ${hasErrors(bean: inquilinoInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="inquilino.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" required="" value="${inquilinoInstance?.nome}"/>

</div>

