<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
<header class="main-header">
    <!-- Logo -->
    <a href="${url}main/working" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>DDNAS</b></span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>DDNAS</b></span>
    </a>

    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
          <li class="dropdown messages-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-envelope-o"></i>
              <span class="label label-success">2</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">2���� �޼���</li>
              <li>
                <!-- inner menu: contains the actual data -->
                <ul class="menu">
                  <li><!-- start message -->
                    <a href="#">
                      <div class="pull-left">
                        <img src="${url}resources/images/profiles/user21.jpg" class="img-circle" alt="User Image">
                      </div>
                      <h4>
                        ���缮
                        <small><i class="fa fa-clock-o"></i> 5 mins</small>
                      </h4>
                      <p>�ȳ��ϼ���.</p>
                    </a>
                  </li>
                  <!-- end message -->
                  <li>
                    <a href="#">
                      <div class="pull-left">
                        <img src="${url}resources/images/profiles/user22.jpg" class="img-circle" alt="User Image">
                      </div>
                      <h4>
                        �ڸ��
                        <small><i class="fa fa-clock-o"></i> 2 hours</small>
                      </h4>
                      <p>����������</p>
                    </a>
                  </li>
                </ul>
              </li>
              <li class="footer"><a href="#">All Messages</a></li>
            </ul>
          </li>
          <!-- Notifications: style can be found in dropdown.less -->
          <!-- <li class="dropdown notifications-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-bell-o"></i>
              <span class="label label-warning">2</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">2���� �˶�</li>
              <li>
                inner menu: contains the actual data
                <ul class="menu">
                  
                  <li>
                    <a href="#">
                      <i class="fa fa-warning text-yellow"></i> A���� CO�� 700PPM�̻�    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-users text-red"></i> 2���� �뵿�� �ٹ���
                    </a>
                  </li>
                 </ul>
              </li>
              <li class="footer"><a href="#">View all</a></li>
            </ul>
          </li> -->
          <!-- Tasks: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="${url}resources/jusu/adminLTE/dist/img/user8-128x128.jpg" class="user-image" alt="User Image">
              <span class="hidden-xs">������</span>
            </a>
          </li>
          
        </ul>
      </div>

    </nav>
  </header>
</body>
</html>