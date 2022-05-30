<?php 
        //getting the database connection
 require_once 'DbConnect.php';
 
 //an array to display response
 $response = array();
 
 //if it is an api call 
 //that means a get parameter named api call is set in the URL 
 //and with this parameter we are concluding that it is an api call 
 if(isset($_GET['apicall'])){
 
 switch($_GET['apicall']){
 
 case 'signup':
 //checking the parameters required are available or not 
 if(isTheseParametersAvailable(array('username','email','idade','cidade','estado','password','numero_oab','telefone_cel'))){
 
    //getting the values 
    $username = $_POST['username']; 
    $email = $_POST['email']; 
    $idade = $_POST['idade'];
    $cidade = $_POST['cidade'];
    $estado = $_POST['estado'];
    $password = md5($_POST['password']);
    $numero_oab = $_POST['numero_oab'];
    $telefone_cel = $_POST['telefone_cel']; 
    
    //checking if the user is already exist with this username or email
    //as the email and username should be unique for every user 
    $stmt = $conn->prepare("SELECT id FROM users WHERE username = ? OR email = ?");
    $stmt->bind_param("ss", $username, $email);
    $stmt->execute();
    $stmt->store_result();
    
    //if the user already exist in the database 
    if($stmt->num_rows > 0){
    $response['error'] = true;
    $response['message'] = 'Usuario já está cadastrado';
    $stmt->close();
    }else{
    
    //if user is new creating an insert query 
    $stmt = $conn->prepare("INSERT INTO users (username, email, idade, cidade, estado, password, numero_oab, telefone_cel) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
    $stmt->bind_param("ssisssss", $username, $email, $idade, $cidade, $estado, $password, $numero_oab, $telefone_cel);
    
    //if the user is successfully added to the database 
    if($stmt->execute()){
    
    //fetching the user back 
    $stmt = $conn->prepare("SELECT id, id, username, email, idade, cidade, estado, numero_oab, telefone_cel FROM users WHERE username = ?"); 
    $stmt->bind_param("s",$username);
    $stmt->execute();
    $stmt->bind_result($userid, $id, $username, $email, $cidade, $estado, $numero_oab, $telefone_cel);
    $stmt->fetch();
    
    $user = array(
    'id'=>$id, 
    'username'=>$username, 
    'email'=>$email,
    'idade'=>$idade,
    'cidade'=>$cidade,
    'estado'=>$estado,
    'numero_oab'=>$numero_oab,
    'telefone_cel'=>$telefone_cel
    );
    
    $stmt->close();
    
    //adding the user data in response 
    $response['error'] = false; 
    $response['message'] = 'Usuario cadastrado com sucesso'; 
    $response['user'] = $user; 
    }
    }
    
    }else{
    $response['error'] = true; 
    $response['message'] = 'os parâmetros necessários não estão disponíveis'; 
    }
 
 break; 
 
 case 'login':
 //for login we need the username and password 
 if(isTheseParametersAvailable(array('email', 'password'))){
    //getting values 
    $email = $_POST['email'];
    $password = md5($_POST['password']); 
    
    //creating the query 
    $stmt = $conn->prepare("SELECT id, username, email, idade, cidade, estado, numero_oab, telefone_cel FROM users WHERE email = ? AND password = ?");
    $stmt->bind_param("ss",$email, $password);
    
    $stmt->execute();
    
    $stmt->store_result();
    
    //if the user exist with given credentials 
    if($stmt->num_rows > 0){
    
    $stmt->bind_result($id, $username, $email, $cidade, $estado, $numero_oab, $telefone_cel);
    $stmt->fetch();
    
    $user = array(
    'id'=>$id, 
    'username'=>$username, 
    'email'=>$email,
    'idade'=>$idade,
    'cidade'=>$cidade,
    'estado'=>$estado,
    'numero_oab'=>$numero_oab,
    'telefone_cel'=>$telefone_cel
    );
    
    $response['error'] = false; 
    $response['message'] = 'Logado com sucesso'; 
    $response['user'] = $user; 
    }else{
    //if the user not found 
    $response['error'] = false; 
    $response['message'] = 'Nome ou Senha inválidos';
    }
    } 
 
 break; 
 
 default: 
 $response['error'] = true; 
 $response['message'] = 'Invalid Operation Called';
 }
 
 }else{
 //if it is not api call 
 //pushing appropriate values to response array 
 $response['error'] = true; 
 $response['message'] = 'Invalid API Call';
 }
 
 //displaying the response in json structure 
 echo json_encode($response);

 //function validating all the paramters are available
 //we will pass the required parameters to this function 
 function isTheseParametersAvailable($params){
 
    //traversing through all the parameters 
    foreach($params as $param){
    //if the paramter is not available
    if(!isset($_POST[$param])){
    //return false 
    return false; 
    }
    }
    //return true if every param is available 
    return true; 
    }