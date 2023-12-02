<?php
  include_once('includes/config.php');

$name = $_POST["name"];
$dob = $_POST["dob"];
$disability = $_POST["disability"];
$gender = $_POST["gender"];
$email= $_POST["email"];
$status = $_POST["status"];

if($con){


   if($status=='Pending'){
    $sql = "INSERT INTO tblchild (`name`, `dob`, `disability`, `gender`, `email`, `status`) VALUES ('$name','$dob','$disability','female','$email','$status')";
    
  if ($con->query($sql) === TRUE) {
  echo "Records updated successfully";
   } else {
  echo "Error updating record: " . $con->error;
    }
  }
  else{
    echo $status;
  }

}
else{
echo "Connection Error";
}
?>