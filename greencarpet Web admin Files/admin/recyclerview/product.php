<?php

  include_once('../includes/config.php');
// Create connection
//$con = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($con->connect_error) {
  die("Connection failed: " . $con->connect_error);
}




$image = $_POST["image"];
$status = $_POST["status"];

if($status=='add'){
  $description = $_POST["description"];
  $date = $_POST["date"];
  $name = $_POST["name"];
// Decode the Base64 encoded image data into binary
$imageData = base64_decode($image);

// Generate a unique filename for the image
$imageName = uniqid() . '.jpg';

// Save the image file to the server's filesystem
$imagePath = '../uploads/' . $imageName;
file_put_contents($imagePath, $imageData);

// Insert data into the database
$sql = "INSERT INTO products (name, description, packages, image) VALUES ('$name',  '$description','$date', '$imageName')";

if ($con->query($sql) === TRUE) {
  echo "Data uploaded successfully";
} else {
  echo "Error: " . $sql . "<br>" . $con->error;
}

$con->close();
}
if($status=='edit'){
  $description = $_POST["description"];
  $date = $_POST["date"];
  $name = $_POST["name"];
  $oimage = $_POST["oimage"];
// Decode the Base64 encoded image data into binary
$imageData = base64_decode($image);

// Generate a unique filename for the image
$imageName = uniqid() . '.jpg';

// Save the image file to the server's filesystem
$imagePath = '../uploads/' . $imageName;
file_put_contents($imagePath, $imageData);

// Insert data into the database
 $sql = "UPDATE products SET name='$name', description='$description',packages='$date', image='$imageName' WHERE id LIKE '$oimage'";
//$sql = "INSERT INTO products (name, description, packages, image) VALUES ('$name',  '$description','$date', '$imageName')";

if ($con->query($sql) === TRUE) {
  echo "Data uploaded successfully";
} else {
  echo "Error: " . $sql . "<br>" . $con->error;
}

$con->close();
}
if($status=='editserv'){
  $name = $_POST["size"];
  $description = $_POST["description"];
$date = $_POST["price"];
$oimage = $_POST["oimage"];
// Decode the Base64 encoded image data into binary
$imageData = base64_decode($image);

// Generate a unique filename for the image
$imageName = uniqid() . '.jpg';

// Save the image file to the server's filesystem
$imagePath = '../uploads/' . $imageName;
file_put_contents($imagePath, $imageData);

// Insert data into the database
 $sql = "UPDATE tblservices SET size='$name', descr='$description',price='$date', image='$imageName' WHERE id LIKE '$oimage'";
//$sql = "INSERT INTO products (name, description, packages, image) VALUES ('$name',  '$description','$date', '$imageName')";

if ($con->query($sql) === TRUE) {
  //echo "Data uploaded successfully";
  echo $name.$date.$description;
} else {
  echo "Error: " . $sql . "<br>" . $con->error;
}

$con->close();
}
?>