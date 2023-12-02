<?php
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);
//error_log("Received POST data: " .);
$status = $_POST["status"];
  include_once('../includes/config.php');
 // error_log("Received POST data: " . print_r($_POST, true));
//echo $status;


//$status="quoteamount";


// $isValidEMail = filter_var($email , FILTER_VALIDATE_EMAIL);
if($con){

  if($status=='updateamount'){
    $amount = $_POST["amount"];
$description = $_POST["description"];
    $code = $_POST["code"];
    $sql = "UPDATE orders SET amount='$amount', code='$code' WHERE description LIKE '$description'";
  if ($con->query($sql) === TRUE) {
  echo "Record updated successfully";
   } else {
  echo "Error updating record: " . $connect->error;
    }
  }
  //C:\Users\PC\AppData\Roaming\Code\User\globalStorage\humy2833.ftp-simple\
  //remote-workspace-temp\3a697ea070e38c49961afd42cf893b4a\greencarpet\admin\recyclerview\update.php
if($status=='quoteamount'){
    $fprice = $_POST["fprice"];
     $desc = $_POST["desc"];
     $sql = "UPDATE orders SET fprice='$fprice' WHERE description LIKE '$desc'";
  if ($con->query($sql) === TRUE) {
  echo "Record updated successfully";
   } else {
  echo "Error updating record: " . $connect->error;
    }
  }
  if($status=='entermpesa'){
    $fprice = $_POST["fprice"];
     $desc = $_POST["desc"];
     
     $sql = "UPDATE orders SET code='$fprice' WHERE description LIKE '$desc'";
  if ($con->query($sql) === TRUE) {
  echo "Record updated successfully";
   } else {
  echo "Error updating record: " . $connect->error;
    }
  }
  if($status=='Rejected'){
  //  $code = $_POST["code"];
     $desc = $_POST["desc"];
    $sql = "UPDATE orders SET status='Rejected' WHERE description LIKE '$desc'";
  if ($con->query($sql) === TRUE) {
  echo "Record updated successfully";
   } else {
  echo "Error updating record: " . $connect->error;
    }
  }
  if($status=='Approved'){
  //  $code = $_POST["code"];
     $desc = $_POST["desc"];
    $sql = "UPDATE orders SET status='Approved' WHERE description LIKE '$desc'";
  if ($con->query($sql) === TRUE) {
  echo "Record updated successfully";
   } else {
  echo "Error updating record: " . $connect->error;
    }
  }
    if($status=='Allocated'){
  //  $code = $_POST["code"];
     $desc = $_POST["desc"];
    $sql = "UPDATE orders SET cleanerName='Allocated' WHERE description LIKE '$desc'";
  if ($con->query($sql) === TRUE) {
  echo "Record updated successfully";
   } else {
  echo "Error updating record: " . $connect->error;
    }
  }
    if($status=='Cancelled'){
  //  $code = $_POST["code"];
     $desc = $_POST["desc"];
    $sql = "UPDATE orders SET cleanerName='Cancelled' WHERE description LIKE '$desc'";
  if ($con->query($sql) === TRUE) {
  echo "Record updated successfully";
   } else {
  echo "Error updating record: " . $connect->error;
    }
  }
   if($status=='activity'){
    $time = $_POST["time"];
    $activity = $_POST["activity"];
    $name = $_POST["name"];
    $sqlq = "INSERT INTO tblactivity (`name`, `time`, `activity`) VALUES ('$name','$time','$activity')";
  if ($con->query($sqlq) === TRUE) {
  echo "Record updated successfully";
   } else {
  echo "Error updating record: " . $connect->error;
    }
  }
   if($status=='activityUpdate_'){
    $time = $_POST["time"];
    $activity = $_POST["activity"];
    $name = $_POST["name"];

    $sqlq = "UPDATE tblactivity SET activity='$activity' WHERE time LIKE '$time'";

    //$sqlq = "INSERT INTO tblactivity (`name`, `time`, `activity`) VALUES ('$name','$time','$activity')";
  if ($con->query($sqlq) === TRUE) {
  echo "Record updated successfully";
   } else {
  echo "Error updating record: " . $connect->error;
    }
  }
   if($status=='activityUpdate'){
    $time = $_POST["time"];
    $activity = $_POST["activity"];
    $name = $_POST["name"];

    $sqlq = "UPDATE tblactivity SET activity='$activity' WHERE name LIKE '$name'";

    //$sqlq = "INSERT INTO tblactivity (`name`, `time`, `activity`) VALUES ('$name','$time','$activity')";
  if ($con->query($sqlq) === TRUE) {
  echo "Record updated successfully";
   } else {
  echo "Error updating record: " . $connect->error;
    }
  }
    if($status=='CleanerAssign'){
    $code = $_POST["code"];
    $email = $_POST["email"];
     $cleaner = $_POST["fname"];

    $sqlq = "UPDATE orders SET assignee='$email', cleanerName='$cleaner' WHERE code LIKE '$code'";

    //$sqlq = "INSERT INTO tblactivity (`name`, `time`, `activity`) VALUES ('$name','$time','$activity')";
  if ($con->query($sqlq) === TRUE) {
  echo "Record updated successfully";
   } else {
  echo "Error updating record: " . $connect->error;
    }
  }
   if($status=='DriverAssign'){
    $code = $_POST["code"];
    $email = $_POST["email"];
  

    $sqlq = "UPDATE orders SET assigneeDriver='$email' WHERE code LIKE '$code'";

    //$sqlq = "INSERT INTO tblactivity (`name`, `time`, `activity`) VALUES ('$name','$time','$activity')";
  if ($con->query($sqlq) === TRUE) {
  echo "Record updated successfully";
   } else {
  echo "Error updating record: " . $connect->error;
    }
  }
   if($status=='DriverComplete'){
    $code = $_POST["code"];
    //$email = $_POST["email"];
  

    $sqlq = "UPDATE orders SET dstatus='Completed' WHERE code LIKE '$code'";

    //$sqlq = "INSERT INTO tblactivity (`name`, `time`, `activity`) VALUES ('$name','$time','$activity')";
  if ($con->query($sqlq) === TRUE) {
  echo "Record updated successfully";
   } else {
  echo "Error updating record: " . $connect->error;
    }
  }
  if($status=='cleanerUpdate'){
    $code = $_POST["code"];
   // $fprice = $_POST["fprice"];
  //$desc = $_POST["desc"];

    $sqlq = "UPDATE orders SET status='Completed' WHERE code LIKE '$code'";

    //$sqlq = "INSERT INTO tblactivity (`name`, `time`, `activity`) VALUES ('$name','$time','$activity')";
  if ($con->query($sqlq) === TRUE) {
  echo "Record updated successfully";
   } else {
  echo "Error updating record: " . $connect->error;
    }
  }
  // if($status=='supplyApproved'){
  //   $code = $_POST["code"];
  //  // $fprice = $_POST["fprice"];
  // //$desc = $_POST["desc"];

  //   $sqlq = "UPDATE supply SET investatus='Approved' WHERE code LIKE '$code'";

  //   //$sqlq = "INSERT INTO tblactivity (`name`, `time`, `activity`) VALUES ('$name','$time','$activity')";
  // if ($con->query($sqlq) === TRUE) {
  // echo "Record updated successfully";
  //  } else {
  // echo "Error updating record: " . $connect->error;
  //   }
  // }
  if($status=='supplyApproved'){
    $code = $_POST["code"];
    $pck = $_POST["pck"]; //6
    //$desc = $_POST["desc"];
    $name = $_POST["name"];
    //$sqlw = "UPDATE products SET  packages=packages+'$pck' WHERE desc LIKE '$desc'";
    $sqlw = "UPDATE products
           SET packages = CAST(packages AS SIGNED) + CAST('$pck' AS SIGNED)
            WHERE name LIKE '$name'";
    $sqlq = "UPDATE supply SET investatus='Approved' WHERE code LIKE '$code'";
    //$sqlq = "UPDATE supply SET status='Delivered' WHERE code LIKE '$code'";

    //$sqlq = "INSERT INTO tblactivity (`name`, `time`, `activity`) VALUES ('$name','$time','$activity')";
    if ($con->query($sqlq) === TRUE) {
        if ($con->query($sqlw) === TRUE) {
            echo "Record updated successfully";
        } else {
            echo "Error updating products: " . $con->error;
        }
    } else {
        echo "Error updating supply: " . $con->error;
    }
  }
  if($status=='supplydel'){
    $code = $_POST["code"];
    $pck = $_POST["pck"]; //6

    $name = $_POST["name"];

    
    $sqlq = "UPDATE supply SET status='Delivered' WHERE code LIKE '$code'";

    if ($con->query($sqlq) === TRUE) {
      
       //  { 
            echo "Record updated successfully";
       // }
       
    } else {
        echo "Error updating supply: " . $con->error;
    }
  }
    if($status=='supplyQuote'){
    //$code = $_POST["code"];
    $amount = $_POST["amount"];
  $desc = $_POST["desc"];

    $sqlq = "UPDATE supply SET amount='$amount' WHERE description LIKE '$desc'";

    //$sqlq = "INSERT INTO tblactivity (`name`, `time`, `activity`) VALUES ('$name','$time','$activity')";
  if ($con->query($sqlq) === TRUE) {
  echo "Record updated successfully";
   } else {
  echo "Error updating record: " . $connect->error;
    }
  }
  if($status=='supplyRejected'){
    $code = $_POST["code"];
   // $fprice = $_POST["fprice"];
  //$desc = $_POST["desc"];

    $sqlq = "UPDATE supply SET investatus='Rejected' WHERE code LIKE '$code'";

    //$sqlq = "INSERT INTO tblactivity (`name`, `time`, `activity`) VALUES ('$name','$time','$activity')";
  if ($con->query($sqlq) === TRUE) {
  echo "Record updated successfully";
   } else {
  echo "Error updating record: " . $connect->error;
    }
  }
   if($status=='financeAmount'){
    $code = $_POST["code"];
    $fprice = $_POST["fprice"];
  $desc = $_POST["desc"];
 $id = $_POST["id"];
    $sqlq = "UPDATE supply SET fprice='$fprice',code='$code' WHERE id LIKE '$id'";

    //$sqlq = "INSERT INTO tblactivity (`name`, `time`, `activity`) VALUES ('$name','$time','$activity')";
  if ($con->query($sqlq) === TRUE) {
  echo "Record updated successfully";
   } else {
  echo "Error updating record: " . $connect->error;
    }
  }

if($status=='selectq'){
    $imageurl = $_POST["imageurl"];
    $qty = $_POST["qty"];
 

    $sqlq = "UPDATE products SET qty='$qty' WHERE image LIKE '$imageurl'";

    //$sqlq = "INSERT INTO tblactivity (`name`, `time`, `activity`) VALUES ('$name','$time','$activity')";
  if ($con->query($sqlq) === TRUE) {
  echo "Record updated successfully";
   } else {
  echo "Error updating record: " . $connect->error;
    }
  }
if($status=='selectitems'){
    $imageurl = $_POST["imageurl"];
    $items = $_POST["items"];
 
    $sqly = "UPDATE products SET qty='1'";
    $sqlq = "UPDATE orders SET items='$items' WHERE code LIKE '$imageurl'";

    //$sqlq = "INSERT INTO tblactivity (`name`, `time`, `activity`) VALUES ('$name','$time','$activity')";
  if ($con->query($sqlq) === TRUE) {
    if ($con->query($sqly) === TRUE) {
  echo "Record updated successfully";}
   } else {
  echo "Error updating record: " . $connect->error;
    }
  }
if($status=='selectcleaners'){
    $code = $_POST["code"];
    $cleaners = $_POST["cleaners"];
 

    $sqlq = "UPDATE orders SET assignee='$cleaners' WHERE code LIKE '$code'";

    //$sqlq = "INSERT INTO tblactivity (`name`, `time`, `activity`) VALUES ('$name','$time','$activity')";
  if ($con->query($sqlq) === TRUE) {
  echo "success";
   } else {
  echo "Error updating record: " . $connect->error;
    }
  }
  //   if($status=='request'){
  //   $pname = $_POST["pname"];
  //   $pack = $_POST["pack"];
  //    $email = $_POST["email"];
  //  $desc = $_POST["desc"];
  //      $amount = $_POST["amount"];
  //   $fprice = $_POST["fprice"];
  //    $code = $_POST["code"];
  //    //6460277998158301  
  //   //  $pname="null";
  //   //  $pack="vv";
  //   //  $email="@mvn";
  //   //  $desc="bbb";
  //   //  $amount="bnvm";
  //   //  $fprice="0008";
  //   //  $code="hcbsc";


  //   //$sqlq = "UPDATE orders SET status='Complete' WHERE code LIKE '$code'";

  //   $sqlq = "INSERT INTO supply (`name`, `description`, `pck`,`fprice`,`amount`,`code`,`status`,`email`) VALUES ('$pname','$desc','$pack',
  //     '$fprice','$amount','$code','Pending','$email')";
  // if ($con->query($sqlq) === TRUE) {
  // echo "Record updated successfully";
  //  } else {
  // echo "Error updating record: " . $con->error;
  //   }
  // }
 
  if ($status == 'request') {
    $pname = $_POST["pname"];
    $pack = $_POST["pack"];
    $email = $_POST["email"];
    $desc = $_POST["desc"];
    $amount = $_POST["amount"];
    $fprice = $_POST["fprice"];
    $code = $_POST["code"];

    // Use prepared statements to avoid SQL injection
    $sqlq = "INSERT INTO supply (`name`, `description`, `pck`, `fprice`, `amount`, `code`, `status`, `email`)
             VALUES (?, ?, ?, ?, ?, ?, 'Pending', ?)";

    $stmt = $con->prepare($sqlq);
    $stmt->bind_param("sssssss", $pname, $desc, $pack, $fprice, $amount, $code, $email);

    if ($stmt->execute()) {
        echo "Record updated successfully";
    } else {
        echo "Error updating record: " . $stmt->error;
    }

    $stmt->close();
}
}
else{
echo "Connection Error";
}
?>