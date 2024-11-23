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

<!DOCTYPE html>
<html>

<head>
    <title>Mini-Shopify Merchant Register</title>
    <meta charset="UTF-8">
    <meta name="author" content="Nicholai Ponomarev">
    <link rel="stylesheet" href="/css/shopper_merchant_login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <script src="/js/go_back.js"></script>
</head>

<body>
    <div class="container">
        <div id="go-back-div" onclick="goBack('merchantLogin')">
            <i class="fa fa-arrow-left" id="go-back-arrow" aria-hidden="true"></i>
        </div>
        <p>Create your Merchant account!</p>
        <form class="registration-form" id="registerForm" action="SYSC4806Project/registerMerchant" method="post">
            <label class="required" for="email">Email</label>
            <input type="text" id="email" name="email" required>

            <label class="required" for="name">Name</label>
            <input type="text" id="name" name="name" required>

            <label class="required" for="password">Password</label>
            <input type="password" id="password" name="password" required>

            <label class="required" for="confirm_password">Confirm Password</label>
            <input type="password" id="confirm_password" name="confirm_password" required>

            <div class="confirm_t_and_cs">
                <input type="checkbox" id="confirm_ts_and_cs" name="confirm_ts_and_cs" value="true">
                <p>I have read and agree to the </p>
                <a href="">Terms and Conditions</a>
            </div>

            <input type="submit" value="Register">
        </form>
        <p>Already have an account? <a href="/merchantLogin">Login here</a></p>
    </div>

    <script>
    document.getElementById('registerForm').addEventListener('submit', async (e) => {
        e.preventDefault();

        // Client-side validation
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirm_password').value;
        const confirmTsAndCs = document.getElementById('confirm_ts_and_cs').checked;

        if (password !== confirmPassword) {
            alert('Passwords do not match!');
            return;
        }

        if (!confirmTsAndCs) {
            alert('Please agree to the Terms and Conditions.');
            return;
        }

        const formData = new FormData(e.target);
        const response = await fetch('/registerMerchant', {
            method: 'POST',
            body: formData,
        });
        const result = await response.text();
        alert(result);
    });
    </script>
</body>

</html>