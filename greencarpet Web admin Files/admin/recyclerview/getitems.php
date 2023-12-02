<?php

include_once('../includes/config.php');
// Check connection
if ($con->connect_error) {
    die('Connection failed: ' . $con->connect_error);
}

// Fetch product names
$products = array();
$sql = 'SELECT name FROM products';
$result = $con->query($sql);
if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $products[] = $row['name'];
    }
}

// Fetch product description based on selected name
$name = isset($_GET['name']) ? $_GET['name'] : ''; // Check if 'name' parameter is set
$description = '';
if (!empty($name)) {
    $sql = "SELECT description FROM products WHERE name LIKE '%$name%'";

   // $sql = "SELECT description FROM products WHERE name LIKE '$name'";
    $result = $con->query($sql);
    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
        $description = $row['description'];
    }
}

$response = array(
    'products' => $products,
    'description' => $description
);

header('Content-Type: application/json');
echo json_encode($response);

$con->close();
?>
