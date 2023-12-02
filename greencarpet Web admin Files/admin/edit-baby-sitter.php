<?php session_start();
error_reporting(0);
// Database Connection
include('includes/config.php');
//Validating Session
if(strlen($_SESSION['aid'])==0)
  { 
header('location:index.php');
}
else{
// Code for Update New Baby sitter
if(isset($_POST['update'])){
//Getting Post Values  
$fname=$_POST['fullname'];
$email=$_POST['emailid'];
$mobileno=$_POST['mobilenumber'];
$address=$_POST['address'];
$city=$_POST['city'];
$state=$_POST['state'];
$languagesknown=$_POST['languagesknown'];
$experience=$_POST['experience'];
$certificate=$_POST['certificate'];
$description=$_POST['description'];
$editid=intval($_GET['editid']);

$query=mysqli_query($con,"update tblbabysitter set Name='$fname',Email='$email',MobileNo='$mobileno',Address='$address',City='$city',State='$state',LanguagesKnown='$languagesknown',BabysitterExp='$experience',Certificate='$certificate',Description='$description' where id='$editid'");
if($query){
echo "<script>alert('Baby sitter details updated successfully.');</script>";
echo "<script type='text/javascript'> document.location = 'manage-baby-sitter.php'; </script>";
} else {
echo "<script>alert('Something went wrong. Please try again.');</script>";
}
}


  ?>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Metropolitan  | Update/Edit Care giver</title>

  <link rel="stylesheet" href="../plugins/fontawesome-free/css/all.min.css">

  <link rel="stylesheet" href="../plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
  <!-- Select2 -->
  <link rel="stylesheet" href="../plugins/select2/css/select2.min.css">
  <link rel="stylesheet" href="../plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css">
  <!-- Bootstrap4 Duallistbox -->
  <link rel="stylesheet" href="../plugins/bootstrap4-duallistbox/bootstrap-duallistbox.min.css">
  <!-- BS Stepper -->
  <link rel="stylesheet" href="../plugins/bs-stepper/css/bs-stepper.min.css">
  <!-- dropzonejs -->
  <link rel="stylesheet" href="../plugins/dropzone/min/dropzone.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="../dist/css/adminlte.min.css">
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
  <!-- Navbar -->
<?php include_once("includes/navbar.php");?>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
 <?php include_once("includes/sidebar.php");?>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->




    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Update/Edit care giver</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="dashboard.php">Dashboard</a></li>
              <li class="breadcrumb-item active">Update/Edit care giver</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>
<?php 
$editid=intval($_GET['editid']);
$query=mysqli_query($con,"select * from tblbabysitter where id='$editid'");
while($result=mysqli_fetch_array($query))
{
?>


    <!-- Main content -->
    <section class="content">   
      <div class="container-fluid">
    <h5 style="color:red"><?php echo $result['Name'];?>'s Profile</h5>       
        <div class="row">
          <!-- left column -->
          <div class="col-md-6">
            <!-- general form elements -->
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">Persoanl Info</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form name="" method="post" enctype="multipart/form-data">
                <div class="card-body">

<!--  Full Name--->
   <div class="form-group">
                    <label for="exampleInputFullname">Full Name</label>
                    <input type="text" class="form-control" id="fullname" name="fullname" value="<?php echo $result['Name'];?>" required>
                  </div>
<!--   Email---->
                  <div class="form-group">
                    <label for="exampleInputEmail1">Email address</label>
                    <input type="email" class="form-control" id="emailid" name="emailid" required value="<?php echo $result['Email'];?>">
                  </div>
<!--Number---->
                  <div class="form-group">
                    <label for="text">Mobile Number</label>
                    <input type="text" class="form-control" id="mobilenumber" name="mobilenumber" pattern="[0-9]{10}" title="10 numeric characters only" required value="<?php echo $result['MobileNo'];?>" maxlength="10">
                  </div>

<!---Address--->
                  <div class="form-group">
                    <label for="exampleInputaddress">Address</label>
                    <textarea class="form-control" id="officeaddress" name="officeaddress" required><?php echo $result['Address'];?></textarea>
                  </div>

<!--   City---->
                  <div class="form-group">
                    <label for="exampleInputCity">City</label>
                    <input type="text" class="form-control" id="city" name="city" required value="<?php echo $result['City'];?>">
                  </div>
<!--State---->
                  <div class="form-group">
                    <label for="exampleInputEmail1">State</label>
                    <input type="text" class="form-control" id="state" name="state" required value="<?php echo $result['State'];?>">
                  </div>


  <!--Languages Known---->
                  <div class="form-group">
                    <label for="text"> Languages known <span style="font-size:12px;">(Langueage should be Seprated by comma(,) Ex: English, kiswahili )</span></label>
                    <input type="text" class="form-control" id="languagesknown" name="languagesknown" required value="<?php echo $result['LanguagesKnown'];?>">
                  </div>
  <!--Profile Pic---->
  <div class="form-group">
                    <label for="exampleInputFile">Profile Pic </label>
               <img src="images/<?php echo $result['ProfilePic']?>" width="120">
               <a href="update-baby-sitter-pic.php?lid=<?php echo $result['id'];?>">Update Profile Pic</a>
                  </div>

      
                </div>
                <!-- /.card-body -->
          
            </div>
            <!-- /.card -->
          </div>
          <!--/.col (left) -->

<div class="col-md-6">
            <!-- Form Element sizes -->
            <div class="card card-success">
              <div class="card-header">
                <h3 class="card-title">Professional Info</h3>
              </div>
              <div class="card-body">
   <!--Experience --->             
                 <div class="form-group">
                    <label for="text">Experience (in years)</label>
                    <input type="text" class="form-control" id="experience" name="experience" pattern="[0-9]" title="2 numeric characters only" maxlength="2" required value="<?php echo $result['BabysitterExp'];?>">
                  </div>




  <!--Courts---->
                  <div class="form-group">
                    <label for="text"> Certificate <span style="font-size:12px;">(Should be Seprated by comma(,) Ex: High Court, Supreme Court )</span></label>
                    <input type="text" class="form-control" id="courts" name="certificate" required value="<?php echo $result['Certificate'];?>">
                  </div>

                   

  <!--Desciption ---->
                  <div class="form-group">
                    <label for="text"> Description (if Any) </label>
                    <textarea class="form-control" id="description" name="description" rows="5" ><?php echo $result['Description'];?></textarea>
                  </div>
        <!--Profile Reg Date---->
                  <div class="form-group">
                    <label for="text"> Profile Reg. Date</span></label>
                    <input type="text" class="form-control" id="regdate" name="regdate" readonly value="<?php echo $result['RegDate'];?>">
                  </div>
     


      <div class="card-footer">
                  <button type="submit" class="btn btn-primary" name="update" id="update">Update</button>
                </div>
              </form>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->
          </div>
        </div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </section>
<?php } ?>


    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
<?php include_once('includes/footer.php');?>

</div>
<!-- ./wrapper -->

<!-- jQuery -->
<script src="../plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="../plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- bs-custom-file-input -->
<script src="../plugins/bs-custom-file-input/bs-custom-file-input.min.js"></script>
<!-- AdminLTE App -->
<script src="../dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="../dist/js/demo.js"></script>
<!-- Page specific script -->
<script src="../plugins/select2/js/select2.full.min.js"></script>
<script>
$(function () {
  bsCustomFileInput.init();
});
  $(function () {
    //Initialize Select2 Elements
    $('.select2').select2()

    //Initialize Select2 Elements
    $('.select2bs4').select2({
      theme: 'bootstrap4'
    })
});
</script>
</body>
</html>
<?php } ?>
