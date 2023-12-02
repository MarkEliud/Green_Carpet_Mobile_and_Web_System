<?php 
 
  include_once('../includes/config.php');
 

 // $email = $_GET['email'];
 //creating a query
   // $stmt = $con->prepare("SELECT id, name, description, pck,fprice,amount,code,status FROM supply WHERE status like 'Pending' AND email like '$email';");
 $stmt = $con->prepare("SELECT id, name, description, pck,fprice,amount,code,status ,investatus FROM supply WHERE status like 'Delivered';");
 
 //executing the query 
 $stmt->execute();
 
 //binding results to the query 
 $stmt->bind_result($id, $name, $description, $pck,$fprice,$amount,$code,$status,$investatus);
 
 $products = array(); 
 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 $temp['description'] = $description; 
 $temp['name'] = $name; 
 $temp['pck'] = $pck; 
 $temp['fprice'] = $fprice; 
  $temp['amount'] = $amount; 
   $temp['code'] = $code; 
  $temp['status'] = $status; 
  $temp['id'] = $id; 
    $temp['investatus'] = $investatus;
  
 array_push($products, $temp);
 }
 
 //displaying the result in json format 
 echo json_encode($products);