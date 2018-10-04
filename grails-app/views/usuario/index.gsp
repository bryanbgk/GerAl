<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Gerenciamento de Usuarios</title>
		<g:javascript library="jquery"/>
	</head>
	<body>
		<a href="#">Adicionar</a>
			<div id="divLista"><g:render template="lista" model="[usuarios: usuarios]" /></div>
			<div id="divForm"><g:render template="form" /></div>
	</body>
</html>
