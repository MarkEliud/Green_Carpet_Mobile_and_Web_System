<?php
  include_once('../includes/config.php');

 $stmt = $con->prepare("SELECT id,fullname, phone,email,password,dlicence,gender, status FROM tbldriver;");
 
 //executing the query 
 $stmt->execute();
 
 //binding results to the query 
 $stmt->bind_result( $id,$fullname, $phone,$email,$password,$dlicence,$gender, $status);
 
 $products = array(); 
 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();

 $temp['fullname'] = $fullname; 
 //$temp['phone'] = $MobileNo; 
 $temp['email'] = $email; 
 //$temp['specialization'] = $specialization; 

 array_push($products, $temp);
 }
 
 //displaying the result in json format 
 echo json_encode($products);
$con->close();
?>