<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>DDNAS</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

<c:url value="/" var="url" />
<script type="text/javascript">
	var url='${url}';
</script>

  <link rel="stylesheet" href="${url}resources/jusu/adminLTE/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <link rel="stylesheet" href="${url}resources/jusu/adminLTE/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="${url}resources/jusu/adminLTE/dist/css/skins/_all-skins.min.css">
  <link href="${url}resources/jusu/css/sweetalert.css" rel="stylesheet" type="text/css" >

</head>


<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

<jsp:include page="/WEB-INF/views/commons/header.jsp" />
<jsp:include page="/WEB-INF/views/commons/main-sidebar.jsp" />
  
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper"  style="min-height: 660px">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
       근무현황 
      </h1>
      <ol class="breadcrumb">
        <li><a href="${url}main/working"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">근무현황</li>
      </ol>
    </section>
    <!-- Main content -->
    <section class="content">
      <!-- Info boxes -->
      <div class="row">
			<div class="col-md-12">
              <!-- USERS LIST -->
              <div class="box box-danger">
                <div class="box-header with-border">
                <img id="ventilImg" style="width:2.5%;float:right;cursor:pointer;" onclick="ventilatorTable()" data-toggle='modal'  data-target='#myModal' src="${url}resources/images/utils/ventilator.jpg">
                <a id="ventilA" class="users-list-name" style="font-size:18px; float: right; cursor: pointer;" onclick="ventilatorTable()" data-toggle='modal'  data-target='#myModal' >환풍기 제어</a></div>

                <!-- /.box-header -->
                
                <div class="box-body no-padding">
                  <ul id="workingUl" class="users-list clearfix">
                               </ul>


				<!-- Modal -->
					<div style="margin-top: 200px; " class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					  <div class="modal-dialog" >
					    <div class="modal-content" style="z-index:3000;">
					      <div class="modal-header" id="modalHeader">
					      
					      </div>
					      <div class="modal-body" id="modalBody">
<!-- 					      <table id="viewDetail">
						  </table>
 -->					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					      </div>
					    </div>
					  </div>
					</div>                  

                </div>
                <!-- /.box-footer -->
              </div>
              <!--/.box -->
            </div>
            <!-- /.col -->
          </div>
          <!-- /.row -->
      </section>
      
    </div>
  <!-- /.content-wrapper -->

  <!-- /.content-wrapper -->
  <jsp:include page="/WEB-INF/views/commons/footer.jsp" />
  

  <!-- /.control-sidebar -->
    <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>

</div>
<!-- ./wrapper -->

<!-- jQuery 2.2.0 -->
<script src="${url}resources/jusu/adminLTE/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="${url}resources/jusu/adminLTE/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="${url}resources/jusu/adminLTE/dist/js/app.min.js"></script>
<!-- ChartJS 1.0.1 -->
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="${url}resources/jusu/js/websocket.js"></script>
<script src="${url}resources/jusu/js/working.js"></script>
<script src="${url}resources/jusu/js/sweetalert.min.js"></script>

</body>
</html>
