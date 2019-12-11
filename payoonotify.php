<?php
$entityBody = file_get_contents('php://input');

$myfile = fopen("LogPayoo.txt", "a") or die("Unable to open file!");

fwrite($myfile, $entityBody . PHP_EOL);
fwrite($myfile, '*************************************' . PHP_EOL);
fclose($myfile);

$ch = curl_init();
curl_setopt($ch, CURLOPT_HEADER, 0);
curl_setopt($ch, CURLOPT_RETURNTRANSFER,1);
curl_setopt($ch, CURLOPT_URL, "http://35.240.147.45:9212/payoo/notify");
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, urlencode($entityBody));
echo $content=curl_exec($ch);

?>