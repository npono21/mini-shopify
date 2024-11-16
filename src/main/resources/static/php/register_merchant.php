<?php
// Initialize variables
$error_message = "";
$success_message = "";

// Check if the form is submitted via POST
if ($_SERVER["REQUEST_METHOD"] === "POST") {
    // Fetch and sanitize user inputs
    $email = filter_input(INPUT_POST, 'email', FILTER_SANITIZE_EMAIL);
    $name = filter_input(INPUT_POST, 'name', FILTER_SANITIZE_STRING);
    $password = $_POST['password'];
    $confirm_password = $_POST['confirm_password'];
    $terms_accepted = isset($_POST['confirm_ts_and_cs']) ? true : false;

    // Validate inputs
    if (!$email || !$name || !$password || !$confirm_password || !$terms_accepted) {
        $error_message = "All fields are required, and Terms & Conditions must be accepted.";
    } elseif ($password !== $confirm_password) {
        $error_message = "Passwords do not match.";
    } else {
        try {
            // Connect to MSSQL database
            $dsn = "sqlsrv:server=tcp:minishopify.database.windows.net,1433;Database=mini_shopify_dev";
            $username = "admin_minishopify_sysc4806";
            $password_env = "j;*86XgQa^b2+CR5"; // Store securely in the environment for production!

            $conn = new PDO($dsn, $username, $password_env);
            $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

            // Hash the password
            $hashed_password = password_hash($password, PASSWORD_DEFAULT);

            // Insert data into the users table
            $sql = "INSERT INTO users (email, name, password) VALUES (:email, :name, :password)";
            $stmt = $conn->prepare($sql);
            $stmt->bindParam(':email', $email);
            $stmt->bindParam(':name', $name);
            $stmt->bindParam(':password', $hashed_password);
            $stmt->execute();

            // Redirect with success message
            header("Location: merchant_register.php?success=Account successfully created!");
            exit;
        } catch (PDOException $e) {
            $error_message = "Error saving to the database: " . htmlspecialchars($e->getMessage());
        }
    }

    // Redirect with error message if any
    if (!empty($error_message)) {
        header("Location: merchant_register.php?error=" . urlencode($error_message));
        exit;
    }
}
?>