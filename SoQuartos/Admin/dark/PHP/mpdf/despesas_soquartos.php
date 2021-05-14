<?php 


require_once __DIR__ . '/vendor/autoload.php';

$mpdf = new \Mpdf\Mpdf();

include '../config.php';

include 'config.php';




$html = '<html>

<div id="fora">

<head>
<script src="script.js"></script>

<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>

<h1 id="cabecalho"> <img src="../../../../upload/logo.png" height="30" >  </h1>




<h5> Morada: ' . $morada . '</h5>
<h5> Senhorio:<br> ' . $nomesenhorio . '<br>Contribuinte: ' . $contribuinte_senhorio . '</h5>
<h5> Inquilino:<br> ' . $nomeinq . '<br>Contribuinte: ' . $contribuinte_inquilino . '</h5>







<br>
<br>
<br>






<br>

<div id="tabela">'.$loop.'



</div>
<br>
<br>
<footer>
<h5 id="data">Data de emissão: '.date(d."/".m."/".y).' </h5>
<br>
<br>
</footer>
<hr style="border-top: dotted 1px;" />
</body>

</div>

</html>';

$mpdf->WriteHTML($html);

$mpdf->Output();


 ?>