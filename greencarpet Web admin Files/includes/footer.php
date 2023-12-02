<?php
include('includes/config.php');
session_start();
error_reporting(0);

if(isset($_POST['sub']))
  {
   
    $email=$_POST['email'];
 
     
    $query=mysqli_query($con, "insert into tblsubscriber(Email) value('$email')");
    if ($query) {
   echo "<script>alert('Your subscribe successfully!.');</script>";
echo "<script>window.location.href ='index.php'</script>";
  }
  else
    {
       echo '<script>alert("Something Went Wrong. Please try again")</script>';
    }

  
}
  ?>  
 <footer class="py-3">
    <!-- subscribe -->
      <section class="subceibe py-lg-4 py-md-3 py-sm-3 py-3" >

         <div class="container py-lg-5 py-md-4 py-sm-4 py-3">
            <h3 class="title text-center mb-lg-5 mb-md-4 mb-sm-4 mb-3" style="color: white;">Join Our Newsletter Now</h3>
            <form action="#" method="post">
               <div class="contact-mid row">
                 
                  <div class="col-lg-6 col-md-6 col-sm-6 form-group contact-forms">
                     <input type="email" class="form-control" placeholder="Email" required="" name="email">
                  </div>
                  <div class="col-lg-4 col-md-4 col-sm-4 form-group contact-forms">
                  <button type="submit" class="btn click-me" value="submit" name="sub">Subscribe</button>
               </div>
               </div>
               
            </form>
         </div>
      </section>
         <div class="copy-bottom-txt text-center py-3">
            <p> 
               © <?php echo date('Y');?> Baby Day Care Management System.
            </p>
         </div>

      <!--//subscribe -->
         <div class="social-icons mt-lg-3 mt-2 text-center">
            <ul>
               <li><a href="#"><span class="fa fa-facebook"></span></a></li>
               <li><a href="#"><span class="fa fa-twitter"></span></a></li>
               <li><a href="#"><span class="fa fa-rss"></span></a></li>
            </ul>

         </div>

         <!-- move icon -->
         <div class="text-center">
            <a href="#home" class="move-top text-center mt-lg-4 mt-3"></a>
         </div>
         <!--//move icon -->

      </footer>