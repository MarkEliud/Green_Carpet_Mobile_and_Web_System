<?php


// $dsn = 'mysql:host=localhost;dbname=greencarpet';
// $username = 'root';
// $password = '';

$dsn = 'mysql:host=localhost;dbname=blupayin_greencarpet';
$username = 'blupayin_panafrica';
$password = 'AweSome2030!';
  $category = $_GET['category'];
if($category=='all'){
    try {
    $db = new PDO($dsn, $username, $password);
//$sql = "SELECT fullname, address FROM tblcustomer WHERE email = '$email'";

    $query = "SELECT * FROM tblservices";
    $stmt = $db->query($query);

    $images = [];

    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
        $image = [
            'size' => $row['size'],
            'imageUrl' => $row['image'],
            'price' => $row['price'],
             'desc' => $row['descr'],
              'id' => $row['id'],
              'category' => $row['category']
        ];
        array_push($images, $image);
    }

    header('Content-Type: application/json');
    echo json_encode($images);

} catch (PDOException $e) {
    echo "Error: " . $e->getMessage();
}
}
else{
    try {
    $db = new PDO($dsn, $username, $password);
//$sql = "SELECT fullname, address FROM tblcustomer WHERE email = '$email'";

    $query = "SELECT id, size, image,price,descr,category FROM tblservices where category='$category'";
    $stmt = $db->query($query);

    $images = [];

    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
        $image = [
            'size' => $row['size'],
            'imageUrl' => $row['image'],
            'price' => $row['price'],
             'desc' => $row['descr'],
              'id' => $row['id'],
              'category' => $row['category']
        ];
        array_push($images, $image);
    }

    header('Content-Type: application/json');
    echo json_encode($images);

} catch (PDOException $e) {
    echo "Error: " . $e->getMessage();
}
}

?>
