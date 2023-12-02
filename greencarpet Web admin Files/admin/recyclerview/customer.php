<?php 
 $email = $_GET["email"];
  include_once('../includes/config.php');
//$sql = "SELECT * FROM tblcustomer WHERE email = $email";
if (!$con) {
    die("Connection failed: " . mysqli_connect_error());
}

$sql = "SELECT fullname, address FROM tblcustomer WHERE email = '$email'";
$result = mysqli_query($con, $sql);

if (mysqli_num_rows($result) > 0) {
    $customerData = array();
    while($row = mysqli_fetch_assoc($result)) {
        $customerData[] = $row;
    }
    echo json_encode($customerData);
} else {
    echo "0 results";
}

mysqli_close($con);
?>