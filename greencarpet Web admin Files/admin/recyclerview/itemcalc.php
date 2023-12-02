<?php
// Get the product name and quantity from the request
 include_once('../includes/config.php');
$name = $_POST['name'];
$quantity = $_POST['quantity'];

// TODO: Implement your database connection and update logic here

// Assuming you have connected to the database and have a valid connection

// Query to update the product table
$query = "UPDATE products SET packages = packages - $quantity WHERE name = '$name'";

// Execute the query
$result = mysqli_query($con, $query);

if ($result) {
    // Update successful
    $response['success'] = true;
    $response['message'] = "Product quantity updated successfully";
} else {
    // Update failed
    $response['success'] = false;
    $response['message'] = "Failed to update product quantity";
}

// Send the JSON response
echo json_encode($response);

// Close the database connection
mysqli_close($con);
?>
