<?
//estes são os detalhes do servidor
//o nome de usuário é root por padrão no caso de xampp
//senha não é nada por padrão
//e por último temos o banco de dados chamado android. se o nome do seu banco de dados for diferente, você terá que alterá-lo
$servername = "localhost";
$username = "root";
$password = "123456";
$database = "meusdireitos";
 
 
//criando um novo objeto de conexão usando mysqli 
$conn = new mysqli($servername, $username, $password, $database);
 
//se houver algum erro na conexão com o banco de dados
//com die vamos parar a execução posterior exibindo uma mensagem causando o erro
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}