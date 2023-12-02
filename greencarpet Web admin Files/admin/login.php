<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $connect = mysqli_connect('localhost', 'blupayin_panafrica', 'AweSome2030!', 'blupayin_greencarpet');

    $choice = $_POST['choice'];
    $email = $_POST['email'];
    $password = $_POST['psw'];

    $table = '';

    switch ($choice) {
        case 'Driver':
            $table = 'tbldriver';
            break;
        case 'Customer':
            $table = 'tblcustomer';
            break;
        case 'cleaner':
            $table = 'tblcleaner';
            break;
        case 'supplier':
            $table = 'tblsupplier';
            break;
        case 'Inventory':
            $table = 'tblinventory';
            break;
        case 'Supervisor':
            $table = 'tblsupervisor';
            break;
        case 'Finance':
            $table = 'tblfinance';
            break;
        case 'service':
            $table = 'tblservice';
            break;
    }

    if (!empty($table)) {
        $Sql_Query = "SELECT * FROM $table WHERE email = '$email' AND password = '$password'";
        $result = mysqli_query($connect, $Sql_Query);
        $row = mysqli_fetch_array($result);

        if ($row) {
            if ($row['status'] == 'Accepted') {
                echo "Login";
            } else {
                echo "Not approved yet!";
            }
        } else {
            if ($choice == 'Driver' || $choice == 'Customer' || $choice == 'cleaner' || $choice == 'supplier' || $choice == 'Inventory' || $choice == 'Supervisor' || $choice == 'Finance' || $choice == 'service') {
                echo "Wrong email and/or password";
            } else {
                echo "Invalid choice";
            }
        }
    } else {
        echo "Invalid choice";
    }
} else {
    echo "Check Again";
}
?>



<?php
 
//   if($_SERVER['REQUEST_METHOD']=='POST'){

// 	 $connect = mysqli_connect('localhost', 'blupayin_panafrica', 'AweSome2030!','blupayin_greencarpet');


//  $choice = $_POST['choice'];
//  $email = $_POST['email'];
//  $password = $_POST['psw'];



// if( $choice=="Driver")
// {

// $Sql_Query = "select * from   tbldriver where email = '$email' and password = '$password' and status='Accepted'";
// $check = mysqli_fetch_array(mysqli_query($connect,$Sql_Query));
// if(isset($check)){
// echo "Login";
//  }
//  else{
//  echo "Not approved yet!";
//  }
//  }
// if( $choice=="Customer")
// {

// $Sql_Query = "select * from tblcustomer where email = '$email' and password = '$password' and status='Accepted'";
// $check = mysqli_fetch_array(mysqli_query($connect,$Sql_Query));
// if(isset($check)){
// echo "Login";
//  }
//  else{
//  echo "Not approved yet!";
//  }
//  }
//  if( $choice=="cleaner")
// {

// $Sql_Query = "select * from tblcleaner where email = '$email' and password = '$password' and status='Accepted'";
// $check = mysqli_fetch_array(mysqli_query($connect,$Sql_Query));
// if(isset($check)){
// echo "Login";
//  }
//  else{
//  echo "Not approved yet!";
//  }
//  }
//   if( $choice=="supplier")
// {

// $Sql_Query = "select * from tblsupplier where email = '$email' and password = '$password' and status='Accepted'";
// $check = mysqli_fetch_array(mysqli_query($connect,$Sql_Query));
// if(isset($check)){
// echo "Login";
//  }
//  else{
//  echo "Not approved yet!";
//  }
//  }
//    if( $choice=="Inventory")
// {

// $Sql_Query = "select * from tblinventory where email = '$email' and password = '$password' and status='Accepted'";
// $check = mysqli_fetch_array(mysqli_query($connect,$Sql_Query));
// if(isset($check)){
// echo "Login";
//  }
//  else{
//  echo "Not approved yet!";
//  }
//  }
//     if( $choice=="Supervisor")
// {

// $Sql_Query = "select * from tblsupervisor where email = '$email' and password = '$password' and status='Accepted'";
// $check = mysqli_fetch_array(mysqli_query($connect,$Sql_Query));
// if(isset($check)){
// echo "Login";
//  }
//  else{
//  echo "Not approved yet!";
//  }
//  }
// if( $choice=="Finance")
// {

// $Sql_Query = "select * from tblfinance where email = '$email' and password = '$password' and status='Accepted'";
// $check = mysqli_fetch_array(mysqli_query($connect,$Sql_Query));
// if(isset($check)){
// echo "Login";
//  }
//  else{
//  echo "Not approved yet!";
//  }
//  }
//  if( $choice=="service")
// {

// $Sql_Query = "select * from tblservice where email = '$email' and password = '$password' and status='Accepted'";
// $check = mysqli_fetch_array(mysqli_query($connect,$Sql_Query));
// if(isset($check)){
// echo "Login";
//  }
//  else{
//  echo "Not approved yet!";
//  }
//  }
//  }else{
//  echo "Check Again";
//  }


?>