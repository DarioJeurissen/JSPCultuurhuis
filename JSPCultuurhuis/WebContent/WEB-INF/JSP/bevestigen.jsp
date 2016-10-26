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
		<form name="form" id="form" method="post" action="bevestigen.htm"></form>
		<h2>Stap 1: Wie ben je ?</h2>
		<table class="tables">
			<tr>
				<th class="tableHeader">Gebruikersnaam</th>
				<td class="tableContent"><input name="username" form="form"></td>
			</tr>
			<tr>
				<th class="tableHeader">Password</th>
				<td class="tableContent"><input name="password" type="password"
					form="form"></td>
			</tr>
		</table>
		<c:if test="${klant == null}">
			<input id="loginButton" type='submit' value="Login" name='login'
				form="form">
			<form action="register.htm" method="get">
				<button id="registerButton" type='submit'>Register</button>
			</form>
		</c:if>
		<c:if test="${klant != null}">
			<input id="loginButton" type='submit' value="Login" name='login'
				form="form" disabled>
			<form action="register.htm" method="get">
				<button id="registerButton" type='submit' disabled>Register</button>
			</form>
		</c:if>
		<c:if test="${klant != null}">
			<table class="tables">
				<tr>
					<th>Gebruikersnaam:</th>
					<th>Naam:</th>
					<th>Adres:</th>
				</tr>
				<tr>
					<td>${klant.gebruikersnaam}</td>
					<td>${klant.voornaam} ${klant.familienaam}</td>
					<td>${klant.straat} ${klant.nummer}, ${klant.postcode}
						${klant.gemeente}</td>
				</tr>
			</table>
		</c:if>

		<h2>Stap 2: Bevestigen</h2>
		<form id="confirmF" name="confirmForm" action="bevestigen.htm" method="post"></form>
			<input name="aKlant" value="${klant.gebruikersnaam}" hidden="true" form="confirmF"/>
			<c:if test="${klant == null}">
				<button type="submit" disabled form="confirmF">Bevestigen</button>
			</c:if>
			<c:if test="${klant != null}">
				<input type="submit" name="confirm" value="Bevestigen" form="confirmF">
			</c:if>
	</div>
</body>
</html>