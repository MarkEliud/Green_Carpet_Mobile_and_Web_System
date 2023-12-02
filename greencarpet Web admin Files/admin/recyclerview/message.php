<?php 
  
  include_once('../includes/config.php');
    $status = $_GET['status'];

if($status=="Driver"){
  $stmt = $con->prepare("SELECT sender FROM messages WHERE user = 'Customer' AND receiver = 'Driver';");
$stmt->execute();
 $stmt->bind_result($sender);
 $products = array(); 
 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 //$temp['id'] = $id; 
 $temp['sender'] = $sender; 
 //$temp['email'] = $email; 

 array_push($products, $temp);
 }
 
 //displaying the result in json format 
 echo json_encode($products);
}
if($status=="Service Manager"){
  $stmt = $con->prepare("SELECT sender FROM messages WHERE user = 'Customer' AND receiver = 'Service Manager';");
$stmt->execute();
 $stmt->bind_result($sender);
 $products = array(); 
 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 //$temp['id'] = $id; 
 $temp['sender'] = $sender; 
 //$temp['email'] = $email; 

 array_push($products, $temp);
 }
 
 //displaying the result in json format 
 echo json_encode($products);
}
if($status=="Finance"){
  $stmt = $con->prepare("SELECT sender FROM messages WHERE user = 'Customer' AND receiver = 'Finance';");
$stmt->execute();
 $stmt->bind_result($sender);
 $products = array(); 
 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 //$temp['id'] = $id; 
 $temp['sender'] = $sender; 
 //$temp['email'] = $email; 

 array_push($products, $temp);
 }
 
 //displaying the result in json format 
 echo json_encode($products);
}
if($status=="Supervisor"){
  $stmt = $con->prepare("SELECT sender FROM messages WHERE user = 'Customer' AND receiver = 'Supervisor';");
$stmt->execute();
 $stmt->bind_result($sender);
 $products = array(); 
 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 //$temp['id'] = $id; 
 $temp['sender'] = $sender; 
 //$temp['email'] = $email; 

 array_push($products, $temp);
 }
 
 //displaying the result in json format 
 echo json_encode($products);
}
if($status=="Cleaner"){
  $stmt = $con->prepare("SELECT sender FROM messages WHERE user = 'Customer' AND receiver = 'Cleaner';");
$stmt->execute();
 $stmt->bind_result($sender);
 $products = array(); 
 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 //$temp['id'] = $id; 
 $temp['sender'] = $sender; 
 //$temp['email'] = $email; 

 array_push($products, $temp);
 }
 
 //displaying the result in json format 
 echo json_encode($products);
}
if($status=="Inventory"){
  $stmt = $con->prepare("SELECT sender FROM messages WHERE user = 'Customer' AND receiver = 'Inventory';");
$stmt->execute();
 $stmt->bind_result($sender);
 $products = array(); 
 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 //$temp['id'] = $id; 
 $temp['sender'] = $sender; 
 //$temp['email'] = $email; 

 array_push($products, $temp);
 }
 
 //displaying the result in json format 
 echo json_encode($products);
}
// if($status=="Pilot"){
//   $stmt = $con->prepare("SELECT sender FROM messages WHERE user = 'Customer' AND receiver = 'Pilot';");
// $stmt->execute();
//  $stmt->bind_result($sender);
//  $products = array(); 
 
//  //traversing through all the result 
//  while($stmt->fetch()){
//  $temp = array();
//  //$temp['id'] = $id; 
//  $temp['sender'] = $sender; 
//  //$temp['email'] = $email; 

//  array_push($products, $temp);
//  }
 
 //displaying the result in json format 
//  echo json_encode($products);
// }
