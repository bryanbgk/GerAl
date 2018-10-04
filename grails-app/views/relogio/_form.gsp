<%@ page import="geral.Relogio" %>



<div class="fieldcontain ${hasErrors(bean: relogioInstance, field: 'numero', 'error')} required">
	<label for="numero">
		<g:message code="relogio.numero.label" default="Numero" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numero" type="number" value="${relogioInstance.numero}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: relogioInstance, field: 'posicaoAtual', 'error')} required">
	<label for="posicaoAtual">
		<g:message code="relogio.posicaoAtual.label" default="Posicao Atual" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="posicaoAtual" type="number" value="${relogioInstance.posicaoAtual}" required=""/>

</div>

