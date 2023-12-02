<?php

// $dsn = 'mysql:host=localhost;dbname=greencarpet';
// $username = 'root';
// $password = '';

$dsn = 'mysql:host=localhost;dbname=blupayin_greencarpet';
$username = 'blupayin_panafrica';
$password = 'AweSome2030!';
try {
    $db = new PDO($dsn, $username, $password);

$email = isset($_GET['email']) ? $_GET['email'] : '';
$email = filter_var($email, FILTER_SANITIZE_EMAIL);


 
    $query = "SELECT * FROM orders  where status='Approved' and assignee like '%$email%'";
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
                   'customerName' => $row['customerName'],
                    'items' => $row['items']
        ];
        array_push($images, $image);
    }

    header('Content-Type: application/json');
    echo json_encode($images);

} catch (PDOException $e) {
    echo "Error: " . $e->getMessage();
}
?>
