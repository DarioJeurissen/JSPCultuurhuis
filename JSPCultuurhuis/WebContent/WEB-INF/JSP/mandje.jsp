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
			<li><a href='<c:url value="bevestigen.htm"/>'>Bevestigen</a></li>
		</ul>
	</nav>
	<div id="wrapper">
		<h1 id="pageTitle">Het Cultuurhuis:reservatiemandje</h1>
		<form name="form" id="my_form" method="post"></form>
		<table class="tables">
			<tr>
				<th class="tableHeader">Datum</th>
				<th class="tableHeader">Titel</th>
				<th class="tableHeader">Uitvoerders</th>
				<th class="tableHeader">Prijs</th>
				<th class="tableHeader">Plaatsen</th>
				<th class="tableHeader">
				<input id="mandjeButton" type='submit'
					name='submit' value='verwijderen' form="my_form">
				</th>
			</tr>

			<c:forEach var='voorstelling' items='${voorstellingen}'>
				<tr>
					<td class="tableContent">${voorstelling.datum}</td>
					<td class="tableContent">${voorstelling.titel}</td>
					<td class="tableContent">${voorstelling.uitvoerders}</td>
					<td class="tableContent">€${voorstelling.prijs}</td>
					<td class="tableContent">${mandje.getPlaatsenGereserveerd(voorstelling.id)}</td>
					<td class="tableContent"><input type="checkbox"
						name="voorstellingen" value="${voorstelling.id}" form="my_form"></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>