<?php 
 
  include_once('../includes/config.php');
 

  $email = $_GET['email'];
 //creating a query
 $stmt = $con->prepare("SELECT id, name, caretype, dob,disability,gender FROM tblchild WHERE email='$email';");
 
 //executing the query 
 $stmt->execute();
 
 //binding results to the query 
 $stmt->bind_result($id, $name, $caretype, $dob,$disability, $gender);
 
 $products = array(); 
 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 $temp['disability'] = $disability; 
 $temp['name'] = $name; 
 $temp['caretype'] = $caretype; 
 $temp['dob'] = $dob; 
  $temp['gender'] = $gender; 
 array_push($products, $temp);
 }
 
 //displaying the result in json format 
 echo json_encode($products);