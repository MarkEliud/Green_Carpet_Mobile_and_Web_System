<?php
  include_once('../includes/config.php');


 $stmt = $con->prepare("SELECT id,name, email,address,gender,password,status, phone FROM tblsupplier;");
 
 //executing the query 
 $stmt->execute();
 
 //binding results to the query 
 $stmt->bind_result( $id,$name, $email,$address,$gender,$password,$status, $phone);
 
 $products = array(); 
 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();

 $temp['fullname'] = $name; 
 //$temp['phone'] = $MobileNo; 
 $temp['email'] = $email; 
 //$temp['specialization'] = $specialization; 

 array_push($products, $temp);
 }
 
 //displaying the result in json format 
 echo json_encode($products);
$con->close();
?>