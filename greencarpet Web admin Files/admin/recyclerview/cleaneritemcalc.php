<?php
 include_once('../includes/config.php');

// Get the name and quantity from the request
$name = $_POST['name'];
$quantity = $_POST['quantity'];

// // Database credentials
// $servername = "localhost";
// $username = "your-username";
// $password = "your-password";
// $dbname = "your-database";

// // Create a connection
// $conn = new mysqli($servername, $username, $password, $dbname);

// Check the connection
if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
}

// Fetch the current packages value for the specified name
$sql = "SELECT packages FROM products WHERE name = '$name'";
$result = $con->query($sql);

if ($result->num_rows > 0) {
    $row = $result->fetch_assoc();
    $currentPackages = $row['packages'];

    // Perform the subtraction operation in PHP
    $updatedPackages = $currentPackages + $quantity;

    // Update the products table with the updated packages value
    $updateSql = "UPDATE products SET packages = '$updatedPackages' WHERE name = '$name'";
    if ($con->query($updateSql) === TRUE) {
        // Successful update
        echo "Product table updated successfully";
    } else {
        // Failed update
        echo "Error updating product table: " . $con->error;
    }
} else {
    // No matching record found
    echo "No record found for the specified name";
}

// Close the connection
$con->close();
?>
