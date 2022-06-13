<?php

    header('Content-Type: text/json; charset=utf-8');

    include 'DbConnect.php';

    $conn = new mysqli($HostNome, $HostUsuario, $HostSenha, $Database);

    mysqli_set_charset($conn, "utf8");

    if ($conn->connect_error) {
        # code...
    }

    $sql = "SELECT * FROM usuario";

    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        echo "Com alguns registros...";
    } else {
        echo "Sem dados no momento...";
    }

    $conn->close();

?>