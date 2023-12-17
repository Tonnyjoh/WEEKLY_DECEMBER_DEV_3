<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" />
<link rel="stylesheet" href="<c:url value="/inc/styles.css"/>" />
<title>Up</title>
</head>
<body>
	<span id="three">&#9776;</span>
	<div id="div_con">
		<div id="div_mess_bloc">
			<div id="firstBloc">
				<div id="logo">
					<P>
						<i class="fas fa-leaf"> </i>
					</P>
				</div>
				<div id="bloc_signin">
					<c:import url="/WEB-INF/connexion.jsp"></c:import>
				</div>
				<div id="bloc_signup">
					<button id="signin">Sign In</button>
					<p id="p_bloc_signup">
						Private storage?<br /> <a id="signup">Sign Up</a>
					</p>
				</div>

			</div>
			<div id="secondBloc">
				<i class="fas fa-arrow-left"></i>
				<c:import url="/WEB-INF/inscription.jsp"></c:import>
			</div>

		</div>

	</div>
	<div class="allpage" id="allpage">
		<div id="sidebar" class="sidebar">
			<c:choose>
				<c:when test="${ sessionScope.client == null}">
					<i id="user" class="fas fa-user"></i>
				</c:when>
				<c:otherwise>
					<a href="deconnexion"> <i id="logout"
						class="fas fa-sign-out-alt "></i></a>
				</c:otherwise>
			</c:choose>

			<h1 class="title">Menu</h1>
			<span id="close" class="closebtn" onclick="closeNav()">&times;</span>
		</div>

		<div id="upload" class="page">
			<h1>Upload</h1>
			<div>
				<c:import url="/WEB-INF/upload.jsp"></c:import>
			</div>
		</div>

		<div id="upmul" class="page">
			<h1>Multi Upload</h1>
			<div>
				<c:import url="/WEB-INF/uploadMul.jsp"></c:import>
			</div>
		</div>

		<div id="compress" class="page">
			<h1>Zipper</h1>
			<div>
				<c:import url="/WEB-INF/compress.jsp"></c:import>
			</div>
		</div>
	</div>

	<script src="<c:url value="/inc/script.js"/>"></script>

	<script src="<c:url value="/inc/dragdrop.js"/>"></script>

	<script src="<c:url value="/inc/toggle.js"/>"></script>
</body>
</html>
