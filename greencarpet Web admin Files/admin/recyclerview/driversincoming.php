<?php 
 
  include_once('../includes/config.php');
 

  $email = $_GET['email'];
 //creating a query
 $stmt = $con->prepare("SELECT id,date, name, description, image,customerName,address,amount,status,code,fprice,email,assignee, assigneeDriver,cleanername FROM orders WHERE assigneeDriver='$email' AND dstatus is null;");
 
 //executing the query 
 $stmt->execute();
 
 //binding results to the query 
 $stmt->bind_result($id,$date, $name, $description, $image,$customerName,$address,$amount,$status,$code,$fprice,$email,$assignee, $assigneeDriver,$cleanerName);
 
 $products = array(); 
 


 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 $temp['cleanerName'] = $cleanerName; 
 $temp['address'] = $address; 
 $temp['customerName'] = $customerName; 
 $temp['date'] = $date; 
  $temp['assignee'] = $assignee; 
    $temp['code'] = $code; 
    $temp['imageUrl'] = $image; 
 array_push($products, $temp);
 }
 
 //displaying the result in json format 
 echo json_encode($products);