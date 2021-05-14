<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<body>

	<section id="display" class="property-grid grid">
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          <div class="grid-option">     
          </div>
        </div>
      <div class="row">
        <div class="col-sm-12">
          <nav class="pagination-a">
            <ul class="pagination justify-content-end">
              <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1">   
                </a>
              </li>
              <li class="page-item <?php if($pageno2 <= 1){ echo 'disabled'; } ?>">
                <a class="page-link" href="?type=test&status=test&pageno2=1">Inicio</a>
              </li>
              <li class="page-item <?php if($pageno2 <= 1){ echo 'disabled'; } ?>">
                <a class="page-link <?php if($pageno2 <= 1){ echo 'disabled'; } ?>" href="<?php if($pageno2 <= 1){ echo '#'; } else { echo "?type=test&status=test&pageno2=".($pageno2 - 1); } ?>"><</a>
              </li>
              <li class="page-item <?php if($pageno2 >= $total_pages){ echo 'disabled'; } ?>">
                <a class="page-link" href="<?php if($pageno2 >= $total_pages){ echo '#'; } else { echo "?type=test&status=test&pageno2=".($pageno2 + 1); } ?>">></a>
              </li>
              <li class="page-item <?php if($pageno2 >= $total_pages){ echo 'disabled'; } ?>">
                <a class="page-link" href="?type=test&status=test&pageno2=<?php echo $total_pages; ?>">Fim</a>
                  
            </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
  </section>

</body>
</html>