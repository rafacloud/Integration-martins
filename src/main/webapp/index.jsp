<html>
<body>
<h2>Bem vindo</h2>

Inclua na URL acima os seguintes endpoints:<br>

GET todos os dados funcionarios<br>
https://sf-integration-dev.herokuapp.com/rest/service/funcionarios<br>

GET um unico funcionario<br>
https://sf-integration-dev.herokuapp.com/rest/service/funcionario/{id}<br>

GET todos os dados cargos<br>
https://sf-integration-dev.herokuapp.com/rest/service/cargos<br>

GET um unico cargo<br>
https://sf-integration-dev.herokuapp.com/rest/service/cargo/{id}<br><br><br>

GET todos os dados setores<br>
https://sf-integration-dev.herokuapp.com/rest/service/setores<br><br><br>

GET um unico setor<br>
https://sf-integration-dev.herokuapp.com/rest/service/setor/{id}<br><br><br>

POST cadastra um funcionario<br>
https://sf-integration-dev.herokuapp.com/rest/service/cadastrar_funcionario<br><br><br>

PUT atualiza um funcionario<br>
https://sf-integration-dev.herokuapp.com/rest/service/atualizar_funcionario<br><br><br>

POST cadastra um cargo<br>
https://sf-integration-dev.herokuapp.com/rest/service/cadastrar_cargo<br><br><br>

PUT atualiza um cargo<br>
https://sf-integration-dev.herokuapp.com/rest/service/atualizar_cargo<br><br><br>

POST cadastra um setor<br>
https://sf-integration-dev.herokuapp.com/rest/service/cadastrar_setor<br><br><br>

PUT atualiza um setor<br>
https://sf-integration-dev.herokuapp.com/rest/service/atualizar_setor<br><br><br>

DELETE um funcionario<br>
https://sf-integration-dev.herokuapp.com/rest/service/funcionario/{id}<br><br><br>

EXEMPLO de json POST e PUT cadastro de Funcionario<br>
Insert <br>
{ <br>
   "nome":"Teste",<br>
   "idade":00,<br>
   "nacionalidade":"Brasileiro",<br>
   "cpf":"12345",<br>
   "rg":"54321",<br>
   "id_setor":1,<br>
   "id_cargo":1<br>
}<br>

Update <br>
{  <br>
   "id":1<br>
   "nome":"Teste",<br>
   "idade":00,<br>
   "nacionalidade":"Brasileiro",<br>
   "cpf":"12345",<br>
   "rg":"54321",<br>
   "id_setor":1,<br>
   "id_cargo":1<br>
}

<br><br><br>

EXEMPLO de json POST e PUT cadastro de setor<br>
Insert <br>
{<br>
   "setor":"RH", <br>
   "coordenador":"John", <br>
   "gerente":"Bob"<br>
}<br><br>

Update <br>
{<br>
   "id":1, <br>
   "setor":"RH", <br>
   "coordenador":"John", <br>
   "gerente":"Bob"<br>
}<br><br>


EXEMPLO de json POST e PUT cadastro de cargo<br>

Insert <br>
{<br>
   "cargo":"Headhunter",<br>
   "salario":2500.0<br>
}<br>
<br>
Update
<br>
{<br>
   "id":1,<br>
   "cargo":"Headhunter",<br>
   "salario":2500.0<br>
}<br>
</body>
</html>
