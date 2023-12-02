<?php
//time zone
date_default_timezone_set('Asia/Kolkata');
//database connectionbdcmsdb
$con=mysqli_connect('localhost','blupayin_panafrica','AweSome2030!','blupayin_greencarpet');
//$con=mysqli_connect("localhost","root","","greencarpet");
if(mysqli_connect_errno()){
echo "Connection Fail".mysqli_connect_error();
}

?>
