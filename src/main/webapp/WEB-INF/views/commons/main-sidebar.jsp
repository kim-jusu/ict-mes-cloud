<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<c:url value="/" var="url" />
<script type="text/javascript">
	var url='${url}';
</script>
<body>
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="${url}resources/jusu/adminLTE/dist/img/user8-128x128.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>관리자</p>
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div><br>
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <li class="header">MAIN NAVIGATION</li>
        	
         <li>
          <a href="${url}main/working">
            <i class="fa fa-laptop"></i>
            <span>근무현황</span>
            <small class="label pull-right bg-green">live</small>
          </a>
        </li>
        
        <li class="treeview">
          <a href="#">
            <i class="fa fa-th"></i>
            <span>DD 관리</span>
            <i class="fa fa-angle-left pull-right"></i>
          </a>
          <ul class="treeview-menu">
            <li><a href="${url}dd/regist"><i class="fa fa-circle-o"></i> DD 등록</a></li>
            <li><a href="${url}dd/list"><i class="fa fa-circle-o"></i> DD 조회/삭제</a></li>
          </ul>
        </li>
        
        <li class="header">LABELS</li>
        <li><a href="#"><i class="fa fa-circle-o text-red"></i> <span>위험 600ppm</span></a></li>
        <li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>경고 500ppm</span></a></li>
        <li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>안전 400ppm</span></a></li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

</body>
</html>