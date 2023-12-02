<?php

  include_once('../includes/config.php');
// Create connection
//$con = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($con->connect_error) {
  die("Connection failed: " . $con->connect_error);
}

$name = $_POST["name"];
$date = $_POST["date"];
$description = $_POST["description"];
$image = $_POST["image"];

$address = $_POST["address"];
$amount = $_POST["amount"];
$code = $_POST["code"];
$status = $_POST["status"];
$customer = $_POST["customer"];
$fprice = $_POST["fprice"];
$email = $_POST["email"];
$size = $_POST["size"];
// Decode the Base64 encoded image data into binary
$imageData = base64_decode($image);

// Generate a unique filename for the image
$imageName = uniqid() . '.jpg';

// Save the image file to the server's filesystem
$imagePath = '../uploads/' . $imageName;
file_put_contents($imagePath, $imageData);

// Insert data into the database
$sql = "INSERT INTO orders (name, date, description, image, address, amount,code,status,customerName,fprice,email,size) VALUES ('$name', '$date', '$description', '$imageName', '$address', '$amount', '$code', '$status', '$customer', '$fprice', '$email', '$size')";

if ($con->query($sql) === TRUE) {
  echo "Data uploaded successfully";
} else {
  echo "Error: " . $sql . "<br>" . $con->error;
}

$con->close();

?>