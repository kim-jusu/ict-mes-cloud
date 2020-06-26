<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap Login Form Template</title>


<c:url value="/" var="url" />
<script type="text/javascript">
	var url='${url}';
</script>

<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link rel="stylesheet"
	href="${url}resources/jusu/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${url}resources/jusu/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${url}resources/jusu/css/form-elements.css">
<link rel="stylesheet" href="${url}resources/jusu/css/style.css">

</head>
 
<body>

	<!-- Top content -->
	<div class="top-content">

		<div class="inner-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">
						<h1>
							<strong>DDNAS</strong> Login
						</h1>
						<div class="description">
							<p>
								모든 기능은 회원만 사용 가능합니다. 
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
						<div class="form-top">
							<div class="form-top-left">
								<h3>Login to DDNAS</h3>
								<p>E-mail과 Password를 입력하세요.</p>
							</div>
							<div class="form-top-right">
								<i class="fa fa-key"></i>
							</div>
						</div>
						<div class="form-bottom">
							<form role="form" action="${url}user/login" method="post" class="login-form" id="login-form">
								<div class="form-group">
									<label class="sr-only" for="userId">UserID</label> <input
										type="text" name="userId" placeholder="E-mail..."
										class="form-username form-control" id="userId">
								</div>
								<div class="form-group">
									<label class="sr-only" for="userPw">Password</label> <input
										type="password" name="userPw" placeholder="Password..."
										class="form-password form-control" id="userPw">
								</div>
								<button type="button" class="btn" id="submitBtn">Login</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>


	<!-- Javascript -->
	<script src="${url}resources/jusu/js/jquery-1.11.1.min.js"></script>
	<script src="${url}resources/jusu/bootstrap/js/bootstrap.min.js"></script>
	<script src="${url}resources/jusu/js/jquery.backstretch.min.js"></script>
	<script src="${url}resources/jusu/js/scripts.js"></script>
	<script src="${url}resources/jusu/js/login.js"></script>

</body>

</html>