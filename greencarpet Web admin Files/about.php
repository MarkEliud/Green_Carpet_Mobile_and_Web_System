<?php
session_start();
error_reporting(0);
include('includes/config.php');
?>
<!DOCTYPE html>
<html lang="zxx">
   <head>
      <title>Baby Day Care Management System | Home Page</title>
      <!--meta tags -->
      
      <script>
         addEventListener("load", function () {
         	setTimeout(hideURLbar, 0);
         }, false);
         
         function hideURLbar() {
         	window.scrollTo(0, 1);
         }
      </script>
      <!--booststrap-->
      <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all">
      <!--//booststrap end-->
      <!-- font-awesome icons -->
      <link href="css/font-awesome.min.css" rel="stylesheet">
      <!-- //font-awesome icons -->
      <!--stylesheets-->
      <link href="css/style.css" rel='stylesheet' type='text/css' media="all">
      <!--//stylesheets-->
      <link href="//fonts.googleapis.com/css?family=Dosis:400,500,600,700" rel="stylesheet">
      <link href="//fonts.googleapis.com/css?family=Quicksand:400,500,700" rel="stylesheet">
   </head>
   <body>
      <div class="main-top" id="home">
         <!-- header -->
         <?php include_once("includes/navbar.php");?>
         <!-- //header -->
         
      <!-- about -->
      <section class="about py-lg-4 py-md-3 py-sm-3 py-3" id="about">
         <div class="container py-lg-5 py-md-5 py-sm-4 py-3">
            <div class="main">
               <input id="tab1" type="radio" name="tabs" checked>
               <label for="tab1" class="baby-care">About Us</label>
               <div class="content">
                  <div id="content1">
                     <div class="row">
                        <div class="col-lg-6 text-center image-about-text">
                           <img src="images/ab1.jpg" class="img-fluid" alt="">
                        </div>
                        <div class="col-lg-6 ">
                            <?php

$ret=mysqli_query($con,"select * from tblpage where PageType='aboutus' ");
$cnt=1;
while ($row=mysqli_fetch_array($ret)) {

?>
                           <div class="about-w3layouts-grid">
                              <h5>
                                <?php  echo $row['PageTitle'];?>
                              </h5>
                           </div>
                           <div class="list-job-grid mt-lg-5 mt-md-4 mt-3 ">
                              <p><?php  echo $row['PageDescription'];?> </p>
                           </div>
                         <?php } ?> 
                        </div>
                     </div>
                  </div>
                
                  
               </div>
            </div>
         </div>
      </section>
      <!--//about -->	  
  
      <!-- footer -->
     <?php include_once('includes/footer.php');?>
      <!--//footer -->
   </body>
</html>