$hash = "5f4dcc3b5aa765d61d8327deb882cf99";
$hash_type = "md5";
$email = "Maher.guerfali@gmail.com";
$code = "0123456789";
$reponse = file_get_contents("https://md5decrypt.net/Api/api.php?hash=".$hash."&hash_type=".$hash_type."&email=".$email."&code=".$code);
echo $reponse;