<?php
//$con=mysqli_connect('localhost','blupayin_panafrica','AweSome2030!','greencarpet');
//blupayin_greencarpet
$dsn = 'mysql:host=localhost;dbname=blupayin_greencarpet';
$username = 'blupayin_panafrica';
$password = 'AweSome2030!';

try {
    $db = new PDO($dsn, $username, $password);
    $email = isset($_GET['email']) ? $_GET['email'] : '';
$email = filter_var($email, FILTER_SANITIZE_EMAIL);
$query = "SELECT * FROM orders  where status='Completed' and assignee like '%$email%'";
   // $query = "SELECT * FROM orders where status='Completed'";
    $stmt = $db->query($query);

    $images = [];

    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
        $image = [
            'name' => $row['name'],
            'date' => $row['date'],
            'description' => $row['description'],
            'imageUrl' => $row['image'],
             'status' => $row['status'],
              'amount' => $row['amount'],
               'address' => $row['address'],
                'code' => $row['code'],
                 'fprice' => $row['fprice'],
                  'email' => $row['email'],
                   'customerName' => $row['customerName']
        ];
        array_push($images, $image);
    }

    header('Content-Type: application/json');
    echo json_encode($images);

} catch (PDOException $e) {
    echo "Error: " . $e->getMessage();
}
?>
