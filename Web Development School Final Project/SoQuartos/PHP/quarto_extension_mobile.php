<!--/ Form Search Star /-->
  <div> 
    <div>
      <form>
        <div>
          <div class="col-md-6 mb-2">
            <div class="form-group">
              <label class="label" for="city">Distrito</label>
                         <?php 
                       
               include 'PHP/destrito2.php';

           ?>  
            </div>
          </div>

          <div class="col-md-6 mb-2">
            <div class="form-group">
              <label class="label" for="bedrooms">Tipo de cama</label>
                 <?php 

              include 'PHP/tipocama2.php';

           ?>
               
            </div>
          </div>

          <div class="col-md-6 mb-2">
            <div class="form-group">
              <label class="label" for="bathrooms">Casa de banho privada</label>
              <select class="form-control form-control-lg form-control-a sizebox" id="selectcasadebanho">
                <option value="1">Sim</option>
                <option value="0">Não</option>
              </select>
            </div>
          </div>
                 <div class="col-md-6 mb-2">
            <div class="form-group">
              <label class="label" for="bedrooms">Internet</label>
              <select class="form-control form-control-lg form-control-a sizebox" id="selectcinternet">
                <option value="1">Sim</option>
                <option value="0">Não</option>
              </select>
            </div>
          </div>

          <div class="col-md-12">
            <input id="bprocurar" type="button" onclick="procurarquarto2()" class="btn btn-b" value="Procurar">
          </div>
        </div>
      </form>
    </div>
  </div>
  <!--/ Form Search End /-->