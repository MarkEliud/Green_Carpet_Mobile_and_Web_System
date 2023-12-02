<?php
  include_once('includes/config.php');
  $choice = $_POST["choice"];
  $email = $_POST["email"];
$password = $_POST["psw"];


$isValidEMail = filter_var($email , FILTER_VALIDATE_EMAIL);
if($con){


if(strlen($password ) > 40 || strlen($password ) < 6){
echo "Password length must be more than 6 and less than 40";
}
else if($isValidEMail === false){
echo "This Email is not valid";
}
else{

if($choice=="Customer"){
$username = $_POST["name"];


$mobile = $_POST["mobile"];
$gender = $_POST["gender"];
$date = $_POST["date"];
$address = $_POST["address"];
$sqlCheckUname = "SELECT * FROM tblcustomer WHERE fullname LIKE '$username'";
$u_name_query = mysqli_query($con, $sqlCheckUname);
$sqlCheckEmail = "SELECT * FROM tblcustomer WHERE email LIKE '$email'";
$email_query = mysqli_query($con, $sqlCheckEmail);
$sql_register = "INSERT INTO tblcustomer (`fullname`, `phone`, `gender`, `address`, `password`, `email`) 
                                    VALUES ('$username','$mobile','$gender','$address','$password','$email')";
                                    if(mysqli_query($con,$sql_register)){
echo "You are registered successfully";
}else{
echo "Failed to register you account";
}
}
else if($choice=="Driver"){
$username = $_POST["username"];
$mobile = $_POST["mobile"];
$gender = $_POST["gender"];
$date = $_POST["date"];
$address = $_POST["address"];
$sqlCheckUname = "SELECT * FROM tbldriver WHERE fullname LIKE '$username'";
$u_name_query = mysqli_query($con, $sqlCheckUname);
$sqlCheckEmail = "SELECT * FROM tbldriver WHERE email LIKE '$email'";
$email_query = mysqli_query($con, $sqlCheckEmail);
$sql_register = "INSERT INTO tbldriver (`fullname`, `phone`,  `email`,`password`, `dlicence`, `gender`) 
                                    VALUES ('$username','$mobile','$email','$password','$address','$gender')";
                                    if(mysqli_query($con,$sql_register)){
echo "You are registered successfully";
}else{
echo "Failed to register you account";
}
}

else if($choice=="Finance"){
$username = $_POST["username"];
$mobile = $_POST["mobile"];
$gender = $_POST["gender"];
$date = $_POST["date"];
$address = $_POST["address"];
$sqlCheckUname = "SELECT * FROM tblfinance WHERE Name LIKE '$username'";
$u_name_query = mysqli_query($con, $sqlCheckUname);
$sqlCheckEmail = "SELECT * FROM tblfinance WHERE Email LIKE '$email'";
$email_query = mysqli_query($con, $sqlCheckEmail);
$sql_register = "INSERT INTO tblfinance (`Name`, `Email`,  `password`,`MobileNo`, `Address`, `RegDate`, `gender`) 
                                    VALUES ('$username','$email','$password','$mobile','$address','$date','$gender')";
                                    if(mysqli_query($con,$sql_register)){
echo "You are registered successfully";
}else{
echo "Failed to register you account";
}
}
else if($choice=="cleaner"){
$username = $_POST["username"];
$mobile = $_POST["mobile"];
$gender = $_POST["gender"];
$date = $_POST["date"];
$address = $_POST["address"];
$sqlCheckUname = "SELECT * FROM tblcleaner WHERE fullname LIKE '$username'";
$u_name_query = mysqli_query($con, $sqlCheckUname);
$sqlCheckEmail = "SELECT * FROM tblcleaner WHERE email LIKE '$email'";
$email_query = mysqli_query($con, $sqlCheckEmail);
$sql_register = "INSERT INTO tblcleaner (`fullname`, `address`,  `phone`, `email`, `password`, `gender`) 
                                    VALUES ('$username','$address','$mobile','$email','$password','$gender')";
                                    if(mysqli_query($con,$sql_register)){
echo "You are registered successfully";
}else{
echo "Failed to register you account";
}
}

else if($choice=="service"){
$username = $_POST["name"];
$mobile = $_POST["mobile"];
$gender = $_POST["gender"];
$date = $_POST["date"];
$address = $_POST["address"];
//$specia = $_POST["special"];
$sqlCheckUname = "SELECT * FROM tblservice WHERE name LIKE '$username'";
$u_name_query = mysqli_query($con, $sqlCheckUname);
$sqlCheckEmail = "SELECT * FROM tblservice WHERE email LIKE '$email'";
$email_query = mysqli_query($con, $sqlCheckEmail);
$sql_register = "INSERT INTO tblservice (`name`, `phone`,  `email`,`gender`, `address`, `password`) 
                                    VALUES ('$username','$mobile','$email','$gender','$address','$password')";
                                    if(mysqli_query($con,$sql_register)){
echo "You are registered successfully";
}else{
echo "Failed to register you account";
}
}
else if($choice=="supplier"){
$username = $_POST["username"];
$mobile = $_POST["mobile"];
$gender = $_POST["gender"];
$date = $_POST["date"];
$address = $_POST["address"];
//$specia = $_POST["special"];
$sqlCheckUname = "SELECT * FROM tblsupplier WHERE name LIKE '$username'";
$u_name_query = mysqli_query($con, $sqlCheckUname);
$sqlCheckEmail = "SELECT * FROM tblsupplier WHERE email LIKE '$email'";
$email_query = mysqli_query($con, $sqlCheckEmail);
$sql_register = "INSERT INTO tblsupplier (`name`, `email`,  `address`,`gender`, `password`, `phone`) 
                                    VALUES ('$username','$email','$address','$gender','$password','$mobile')";
                                    if(mysqli_query($con,$sql_register)){
echo "You are registered successfully";
}else{
echo "Failed to register you account";
}
}
else if($choice=="inventory"){
$username = $_POST["username"];
$mobile = $_POST["mobile"];
$gender = $_POST["gender"];
$date = $_POST["date"];
$address = $_POST["address"];
//$specia = $_POST["special"];
$sqlCheckUname = "SELECT * FROM tblinventory WHERE name LIKE '$username'";
$u_name_query = mysqli_query($con, $sqlCheckUname);
$sqlCheckEmail = "SELECT * FROM tblinventory WHERE email LIKE '$email'";
$email_query = mysqli_query($con, $sqlCheckEmail);
$sql_register = "INSERT INTO tblinventory (`name`, `email`,  `address`,`gender`, `password`, `phone`) 
                                    VALUES ('$username','$email','$address','$gender','$password','$mobile')";
                                    if(mysqli_query($con,$sql_register)){
echo "You are registered successfully";
}else{
echo "Failed to register you account";
}
}
else if($choice=="Supervisor"){
$username = $_POST["username"];
$mobile = $_POST["mobile"];
$gender = $_POST["gender"];
$date = $_POST["date"];
$address = $_POST["address"];
//$specia = $_POST["special"];
$sqlCheckUname = "SELECT * FROM tblsupervisor WHERE name LIKE '$username'";
$u_name_query = mysqli_query($con, $sqlCheckUname);
$sqlCheckEmail = "SELECT * FROM tblsupervisor WHERE email LIKE '$email'";
$email_query = mysqli_query($con, $sqlCheckEmail);
$sql_register = "INSERT INTO tblsupervisor (`name`, `email`,  `address`,`gender`, `password`, `phone`) 
                                    VALUES ('$username','$email','$address','$gender','$password','$mobile')";
                                    if(mysqli_query($con,$sql_register)){
echo "You are registered successfully";
}else{
echo "Failed to register you account";
}
}
}
}
else{
echo "Connection Error";
}
?>