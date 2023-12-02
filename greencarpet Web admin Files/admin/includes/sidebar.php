    
      <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="dashboard.php" class="brand-link">

      <span class="brand-text font-weight-light"> Green Carpet Cleaning |Admin </span>
    </a>
    <div class="sidebar">
      <!-- Sidebar user panel (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="image">
          <img src="../dist/img/manager.png" class="img-circle elevation-2" alt="User Image">
        </div>
        <div class="info">
          <a href="#" class="d-block"><?php  echo $_SESSION['uname'];?></a>
        </div>
      </div>



      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
    <li class="nav-item">
            <a href="dashboard.php" class="nav-link">
                 <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
             Dashboard
              </p>
            </a>
          </li>
<li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-table"></i>
              <p>
               Customers
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview" style="display: none;">
              <li class="nav-item">
                <a href="all-enrollment.php" class="nav-link">  
                  <i class="far fa-circle nav-icon"></i>
                  <p>All Customers</p>
                </a>
              </li>   
           <!--    <li class="nav-item">
                <a href="new-enrollment.php" class="nav-link">  
                  <i class="far fa-circle nav-icon"></i>
                  <p>New Customers</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="accept-enrollment.php" class="nav-link">  
                  <i class="far fa-circle nav-icon"></i>
                  <p>Accept Customers</p>
                </a>
              </li> -->
             <!--  <li class="nav-item">
                <a href="onhold-enrollment.php" class="nav-link">  
                  <i class="far fa-circle nav-icon"></i>
                  <p>Onhold Enrollment</p>
                </a>
              </li> -->
             <!--  <li class="nav-item">
                <a href="reject-enrollment.php" class="nav-link">  
                  <i class="far fa-circle nav-icon"></i>
                  <p>Reject Customers</p>
                </a>
              </li> -->
            </ul>
          </li> 
          <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-users"></i>
              <p>
               Drivers
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview" style="display: none;">

<li class="nav-item"><!-- ../dist/img/manager.png -->
                <a href="driver.php" class="nav-link">  
                  <i class="far fa-circle nav-icon"></i>
                  <p>Drivers</p>
                </a>
              </li>
           <!--    <li class="nav-item">
                <a href="care-accept-enrollment.php" class="nav-link">  
                  <i class="far fa-circle nav-icon"></i>
                  <p>Accept Drivers</p>
                </a>
              </li>
             
              <li class="nav-item">
                <a href="care-reject-enrollment.php" class="nav-link">  
                  <i class="far fa-circle nav-icon"></i>
                  <p>Reject Drivers</p>
                </a>
              </li> -->
             </ul>
             </li> 
      <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-users"></i>
              <p>
               Finance
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview" style="display: none;">

             <li class="nav-item"><!-- ../dist/img/manager.png -->
                <a href="finance.php" class="nav-link">  
                  <i class="far fa-circle nav-icon"></i>
                  <p>Manage Finance</p>
                </a>
              </li>
           
             </ul>
             </li> 
              <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-users"></i>
              <p>
              Cleaners
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview" style="display: none;">

             <li class="nav-item"><!-- ../dist/img/manager.png -->
                <a href="cleaner.php" class="nav-link">  
                  <i class="far fa-circle nav-icon"></i>
                  <p>Manage Cleaners</p>
                </a>
              </li>
           
             </ul>
             </li> 
                  <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-users"></i>
              <p>
              Supplier
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview" style="display: none;">

             <li class="nav-item"><!-- ../dist/img/manager.png -->
                <a href="supplier.php" class="nav-link">  
                  <i class="far fa-circle nav-icon"></i>
                  <p>Manage Suppliers</p>
                </a>
              </li>
           
             </ul>
             </li> 
                       <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-users"></i>
              <p>
              Service Manager
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview" style="display: none;">

             <li class="nav-item"><!-- ../dist/img/manager.png -->
                <a href="service.php" class="nav-link">  
                  <i class="far fa-circle nav-icon"></i>
                  <p>View Service manager</p>
                </a>
              </li>
           
             </ul>
             </li> 


             <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-users"></i>
              <p>
              Inventory Manager
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview" style="display: none;">

             <li class="nav-item"><!-- ../dist/img/manager.png -->
                <a href="inventory.php" class="nav-link">  
                  <i class="far fa-circle nav-icon"></i>
                  <p>View Inventory manager</p>
                </a>
              </li>
           
             </ul>
             </li> 

             <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-users"></i>
              <p>
              Supervisor Manager
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview" style="display: none;">

             <li class="nav-item"><!-- ../dist/img/manager.png -->
                <a href="supervisor.php" class="nav-link">  
                  <i class="far fa-circle nav-icon"></i>
                  <p>View Supervisor</p>
                </a>
              </li>
           
             </ul>
             </li> 
             <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-users"></i>
              <p>
              Messages
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview" style="display: none;">

             <li class="nav-item"><!-- ../dist/img/manager.png -->
                <a href="messages.php" class="nav-link">  
                  <i class="far fa-circle nav-icon"></i>
                  <p>View All Messages</p>
                </a>
              </li>
           
             </ul>
             </li> 
<!--    <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-file"></i>
              <p>
               Services module
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview" style="display: none;">
              <li class="nav-item">
                <a href="add-services.php" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Add</p>
                </a>
              </li>
          
           <li class="nav-item">
                <a href="manage-services.php" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p> Manage</p>
                </a>
              </li>

            </ul>
          </li>  -->




   
            <!--   <li class="nav-item">
                <a href="add-baby-sitter.php" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Add</p>
                </a>
              </li>
          
           <li class="nav-item">
                <a href="manage-baby-sitter.php" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p> Manage</p>
                </a>
              </li>

                

            </ul>
          </li>    -->       
        <!-- <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-users"></i>
              <p>
               Enquiry
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview" style="display: none;">
              <li class="nav-item">
                <a href="unreadenq.php" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Unread Enquiry</p>
                </a>
              </li>
          
           <li class="nav-item">
                <a href="readenq.php" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p> Read Enquiry</p>
                </a>
              </li>

                

            </ul>
          </li> -->
<!-- <li class="nav-item">
            <a href="subscriber.php" class="nav-link">
                 <i class="nav-icon fas fa-users"></i>
              <p>
             Subscriber
              </p>
            </a>
          </li> -->

             <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-table"></i>
              <p>
              Bookings
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview" style="display: none;">
              <li class="nav-item">
                <a href="transactions.php" class="nav-link">  
                  <i class="far fa-calendar-alt nav-icon"></i>
                  <p>View Bookings</p>
                </a>
              </li>   

            
            </ul>
          </li> 
          <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-table"></i>
              <p>
               View Services
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview" style="display: none;">
              <li class="nav-item">
                <a href="manage-cservices.php" class="nav-link">  
                  <i class="far fa-calendar-alt nav-icon"></i>
                  <p>View Services</p>
                </a>
              </li>   

            
            </ul>
          </li> 
<!--Reports--->
   <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-table"></i>
              <p>
               Reports
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview" style="display: none;">
              <li class="nav-item">
                <a href="bwdates-report-ds.php" class="nav-link">  
                  <i class="far fa-calendar-alt nav-icon"></i>
                  <p>B/w Dates</p>
                </a>
              </li>   

              <li class="nav-item">
                <a href="search-report.php" class="nav-link">  
                  <i class="fas fa-search nav-icon"></i>
                  <p>Search Report</p>
                </a>
              </li>
            </ul>
          </li> 

<!--Reports--->
   <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-file"></i>
              <p>
               Pages
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview" style="display: none;">
              <li class="nav-item">
                <a href="aboutus.php" class="nav-link">  
                  <i class="far fa-file-alt nav-icon"></i>
                  <p>About us</p>
                </a>
              </li>   

              <li class="nav-item">
                <a href="contact-us.php" class="nav-link">  
                  <i class="fas fa-file nav-icon"></i>
                  <p>Contact us</p>
                </a>
              </li>
            </ul>
          </li>



<!--Profile--->
   <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-user-cog"></i> 
              <p>
               Account Settings
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview" style="display: none;">
              <li class="nav-item">
                <a href="profile.php" class="nav-link">
                  <i class="far fa-user nav-icon"></i>
                  <p>Profile</p>
                </a>
              </li>

 <li class="nav-item">
                <a href="change-password.php" class="nav-link">
                  <i class="fas fa-cog nav-icon"></i>
                  <p>Change Password</p>
                </a>
              </li>

               <li class="nav-item">
                <a href="logout.php" class="nav-link">
  <i class="fas fa-sign-out-alt nav-icon"></i>
                  <p>Logout</p>
                </a>
              </li>

            </ul>
          </li> 

        </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>