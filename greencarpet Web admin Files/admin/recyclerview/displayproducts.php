<?php

// $dsn = 'mysql:host=localhost;dbname=greencarpet';
// $username = 'root';
// $password = '';
$dsn = 'mysql:host=localhost;dbname=blupayin_greencarpet';
$username = 'blupayin_panafrica';
$password = 'AweSome2030!';
try {
    $db = new PDO($dsn, $username, $password);

    $query = "SELECT * FROM products";
    $stmt = $db->query($query);

    $images = [];

    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
        $image = [
            'name' => $row['name'],
            'description' => $row['description'],
            'imageUrl' => $row['image'],
             'packages' => $row['packages'],
              'qty' => $row['qty'],
              'id' => $row['id']
        ];
        array_push($images, $image);
    }

    header('Content-Type: application/json');
    echo json_encode($images);

} catch (PDOException $e) {
    echo "Error: " . $e->getMessage();
}
?>
