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
			<li><a href='<c:url value="bevestigen.htm"/>'>Bevestigen</a></li>
		</ul>
	</nav>
	<div id="wrapper">
		<h1 id="pageTitle">Het Cultuurhuis:Registreren</h1>
		<form name="form" id="form" method="post" action="register.htm"></form>
		<table class="tables">
			<tr>
				<th class="tableHeader">Voornaam</th>
				<td class="tableContent"><input id="firstname" name="firstname"
					value='' form="form"></td>
			</tr>
			<tr>
				<th class="tableHeader">Familienaam</th>
				<td class="tableContent"><input id="lastname" name="lastname"
					value='' form="form"></td>
			</tr>
			<tr>
				<th class="tableHeader">Straat</th>
				<td class="tableContent"><input id="street" name="street"
					value='' form="form"></td>
			</tr>
			<tr>
				<th class="tableHeader">huisnr</th>
				<td class="tableContent"><input id="number" name="number"
					value='' form="form"></td>
			</tr>
			<tr>
				<th class="tableHeader">Postcode</th>
				<td class="tableContent"><input id="postal" name="postal"
					value='' form="form"></td>
			</tr>
			<tr>
				<th class="tableHeader">Gemeente</th>
				<td class="tableContent"><input id="city" name="city" value=''
					form="form"></td>
			</tr>
			<tr>
				<th class="tableHeader">Gebruikersnaam</th>
				<td class="tableContent"><input id="username" name="username"
					value='' form="form"></td>
			</tr>
			<tr>
				<th class="tableHeader">Passwoord:</th>
				<td class="tableContent"><input id="password1" name="password1"
					type="password" value='' form="form"></td>
			</tr>
			<tr>
				<th class="tableHeader">Herhaal Passwoord:</th>
				<td class="tableContent"><input id="password2" name="password2"
					type="password" value='' form="form"></td>
			</tr>
		</table>

		<button id="okayButton" type='submit' name='OK' form="form">OK</button>

		<ul>
			<c:forEach var='error' items='${errors}'>
				<li>${error}</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>