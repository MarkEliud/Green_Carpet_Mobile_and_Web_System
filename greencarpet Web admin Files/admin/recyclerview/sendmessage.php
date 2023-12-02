<?php
 include_once('../includes/config.php');
 // include_once('includes/config.php');
$sender_id = $_POST['sender_id'];
$receiver_id = $_POST['receiver_id'];
$message = $_POST['message'];
$user = $_POST['user'];
//$mysqli = new mysqli('localhost', 'username', 'password', 'database_name');
if ($con->connect_errno) {
    echo 'Failed to connect to MySQL: (' . $con->connect_errno . ') ' . $con->connect_error;
    exit;
} 
 $sql = "INSERT INTO messages (`sender`, `receiver`, `message`, `user`) VALUES ('$sender_id','$receiver_id','$message','$user')";
    
  if ($con->query($sql) === TRUE) {
  echo "Records updated successfully";
   } else {
  echo "Error updating record: " . $con->error;
    }

// $stmt = $con->prepare("INSERT INTO messages (sender, receiver, message) VALUES (?, ?, ?)");
// $stmt->bind_param('iis', $sender_id, $receiver_id, $message);
// $stmt->execute();

// $stmt->close();
// $con->close();
?>
