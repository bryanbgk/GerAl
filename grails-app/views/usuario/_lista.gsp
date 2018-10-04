<table>
  <tr>
    <th>Usuario</th>
    <th>Senha</th>
    <th>E-mail</th>
  </tr>
  <g:each var="usuario" in="${usuarios}">
  <tr>
    <td>${usuario.user}</td>
    <td>${usuario.senha}</td>
    <td>${usuario.email}</td>
    <td>
      <a href="#">Alterar</a>
      <a href="#">Excluir</a>
    </td>
  </tr>
  </g:each>
</table>
