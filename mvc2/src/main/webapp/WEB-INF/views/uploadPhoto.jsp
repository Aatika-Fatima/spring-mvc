 <div class="container">
    <div class="card card-login mx-auto mt-8">
      <div class="card-header">Upload Photo</div>
      <div class="card-body">
      
		<form method="post" action="/mvc/uploadFile" enctype="multipart/form-data">
		 <p style="color:green">${msg}</p>
          <div class="form-group">
           	<input class="form-control" type="file" name="file">
          </div>
         <input type="submit" class="btn btn-primary" value="Upload"/>
        </form>
   
      </div>
    </div>
  </div>