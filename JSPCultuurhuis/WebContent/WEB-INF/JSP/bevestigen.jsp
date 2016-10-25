<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang="nl">
<head>
<title>Cultuurhuis</title>
<link rel='icon' href='images/favicon.ico'>
<meta name='viewport' content='width=device-width,initial-scale=1'>
<link rel='stylesheet' href='styles/default.css'>
</head>
<body>
	<nav>
		<ul>
			<li><a href='<c:url value="/"/>'>Voorstellingen</a></li>
			<li><a href='<c:url value="mandje.htm"/>'>Mandje</a></li>
		</ul>
	</nav>
	<div id="wrapper">
		<h1 id="pageTitle">Het Cultuurhuis:bevestigen</h1>
		<form name="form" id="form" method="post"></form>
		<h2>Stap 1: Wie ben je ?</h2>
		<table class="tables">
			<tr>
				<th class="tableHeader">Gebruikersnaam</th>
				<td class="tableContent"><input name="username"
					form="form"></td>
			</tr>
			<tr>
				<th class="tableHeader">Password</th>
				<td class="tableContent"><input name="password" type="password"
					 form="form"></td>
			</tr>
		</table>

		<button id="loginButton" type='submit' value ="submit" name='login' 
			form="form">Login</button>
		<form action="register.htm" method="get">
			<button id="registerButton" type='submit'>Register</button>
		</form>

		<h2>Stap 2: Bevestigen</h2>
		<form>
			<button type="submit" disabled>Bevestigen</button>
		</form>
	</div>
</body>
</html>