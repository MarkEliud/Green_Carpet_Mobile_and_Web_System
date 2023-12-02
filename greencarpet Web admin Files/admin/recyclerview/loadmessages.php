<?php
 include_once('../includes/config.php');
    //include_once('includes/config.php');
$sender_id = $_GET['sender_id'];
$receiver_id = $_GET['receiver_id'];

//$mysqli = new mysqli('localhost', 'username', 'password', 'database_name');
if ($con->connect_errno) {
    echo 'Failed to connect to MySQL: (' . $con->connect_errno . ') ' . $con->connect_error;
    exit;
}

$result = $con->query("SELECT * FROM messages WHERE (sender='$sender_id' AND receiver='$receiver_id') OR (sender='$receiver_id' AND receiver='$sender_id')");

$rows = array();
while ($row = $result->fetch_assoc()) {
    $rows[] = $row;
}
echo json_encode($rows);

$result->free();
$con->close();
?>
