<?php

  include_once('../includes/config.php');
// Create connection
//$con = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($con->connect_error) {
  die("Connection failed: " . $con->connect_error);
}

$status = $_POST["status"];
$size = $_POST["size"];
$image = $_POST["image"];
$price = $_POST["price"];
$desc = $_POST["desc"];
$category = $_POST["category"];
if($status=='add'){
// Decode the Base64 encoded image data into binary
$imageData = base64_decode($image);

// Generate a unique filename for the image
$imageName = uniqid() . '.jpg';

// Save the image file to the server's filesystem
$imagePath = '../uploads/' . $imageName;
file_put_contents($imagePath, $imageData);

// Insert data into the database
$sql = "INSERT INTO tblservices ( size, image, price,descr,category) VALUES ( '$size','$imageName', '$price', '$desc', '$category')";

if ($con->query($sql) === TRUE) {
  echo "Data uploaded successfully";
} else {
  echo "Error: " . $sql . "<br>" . $con->error;
}

$con->close();
}
if($status=='edit'){

// Decode the Base64 encoded image data into binary
$imageData = base64_decode($image);

// Generate a unique filename for the image
$imageName = uniqid() . '.jpg';

// Save the image file to the server's filesystem
$imagePath = '../uploads/' . $imageName;
file_put_contents($imagePath, $imageData);

// Insert data into the database
 $sql = "UPDATE tblservices SET  size='$size',image='$image', price='$price', descr='$desc' WHERE image LIKE '$imageName'";
//$sql = "INSERT INTO products (name, description, packages, image) VALUES ('$name',  '$description','$date', '$imageName')";

if ($con->query($sql) === TRUE) {
  echo "Data uploaded successfully";
} else {
  echo "Error: " . $sql . "<br>" . $con->error;
}

$con->close();
}
?>