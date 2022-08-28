<html>
<body>
<h2>Bem vindo</h2>
Para acessar os m�todos, siga as instru��es abaixo:<br>
Inclua na URL acima os seguintes endpoints:<br>

GET todos os dados funcionarios<br>
https://itau-integration.herokuapp.com/rest/service/funcionarios<br>

GET um unico funcionario<br>
https://itau-integration.herokuapp.com/rest/service/funcionario/{id}<br>

GET todos os dados cargos<br>
https://sf-integration-dev.herokuapp.com/rest/service/cargos<br>

GET um unico cargo<br>
https://sf-integration-dev.herokuapp.com/rest/service/cargo/{id}<br>

GET todos os dados setores<br>
https://sf-integration-dev.herokuapp.com/rest/service/setores<br>

GET um unico setor<br>
https://sf-integration-dev.herokuapp.com/rest/service/setor/{id}<br>

POST cadastra um funcionario<br>
https://sf-integration-dev.herokuapp.com/rest/service/cadastrar_funcionario<br>

PUT atualiza um funcionario<br>
https://sf-integration-dev.herokuapp.com/rest/service/atualizar_funcionario<br>

DELETE um funcionario<br>
https://sf-integration-dev.herokuapp.com/rest/service/funcionario/{id}<br>

EXEMPLO de json POST e PUT<br>
{  
   "nome":"Teste",
   "idade":00,
   "nacionalidade":"Brasileiro",
   "cpf":"12345",
   "rg":"54321",
   "id_setor":1,
   "id_cargo":1
}

</body>
</html>
