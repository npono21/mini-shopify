<?php
// PHP Data Objects(PDO) Sample Code:
try {
    $conn = new PDO("sqlsrv:server = tcp:minishopify.database.windows.net,1433; Database = mini_shopify_dev", "admin_minishopify_sysc4806", "j;*86XgQa^b2+CR5");
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
}
catch (PDOException $e) {
    print("Error connecting to SQL Server.");
    die(print_r($e));
}

// SQL Server Extension Sample Code:
$connectionInfo = array("UID" => "admin_minishopify_sysc4806", "pwd" => "j;*86XgQa^b2+CR5", "Database" => "mini_shopify_dev", "LoginTimeout" => 30, "Encrypt" => 1, "TrustServerCertificate" => 0);
$serverName = "tcp:minishopify.database.windows.net,1433";
$conn = sqlsrv_connect($serverName, $connectionInfo);
?>