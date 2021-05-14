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
              <li class="page-item <?php if($pageno <= 1){ echo 'disabled'; } ?>">
                <a class="page-link"    href="<?php echo"?op=1&valor=".$valor."&selectdestrito=" .  $selectdestrito  . "&selectquartos=" . $selecttipocama . "&selectcasadebanho=" . $selectcasadebanho . "&selectcinternet=" . $selectcinternet .   "&type=test&status=test&pageno=1"?>">Inicio</a>
              </li>
              <li class="page-item <?php if($pageno <= 1){ echo 'disabled'; } ?>">
                <a class="page-link <?php if($pageno <= 1){ echo 'disabled'; } ?>" href="<?php if($pageno <= 1){ echo '#'; } else { echo "?op=1&valor=".$valor."&selectdestrito=" .  $selectdestrito  ."&selectquartos=" . $selecttipocama . "&selectcasadebanho=" . $selectcasadebanho . "&selectcinternet=" . $selectcinternet .   "&type=test&status=test&pageno=".($pageno - 1); } ?>"><</a>
              </li>
              <li class="page-item <?php if($pageno >= $total_pages){ echo 'disabled'; } ?>">
                <a class="page-link" href="<?php if($pageno >= $total_pages){ echo '#'; } else { echo "?op=1&valor=".$valor."&selectdestrito=" .  $selectdestrito  ."&selectquartos=" . $selecttipocama . "&selectcasadebanho=" . $selectcasadebanho . "&selectcinternet=" . $selectcinternet .  "&type=test&status=test&pageno=".($pageno + 1); } ?>">></a>
              </li>
              <li class="page-item <?php if($pageno >= $total_pages){ echo 'disabled'; } ?>">
                <a class="page-link" href="<?php echo"?op=1&valor=".$valor."&selectdestrito=" .  $selectdestrito  ."&selectquartos=" . $selecttipocama . "&selectcasadebanho=" . $selectcasadebanho . "&selectcinternet=" . $selectcinternet . "&type=test&status=test&pageno=" . $total_pages . ";" ?>">Fim</a>
                  
            </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
  </section>