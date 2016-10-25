<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
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

	<h1 id="pageTitle">Het Cultuurhuis:reserveren</h1>
	<nav>
		<ul>
			<li><a href='<c:url value="/"/>'>Voorstellingen</a></li>
			<c:if test='${not empty mandje}'>
				<li><a href='<c:url value="mandje.htm"/>'>Mandje</a></li>
				<li><a href='<c:url value="bevestigen.htm"/>'>Bevestigen</a></li>
			</c:if>
		</ul>
	</nav>
	<div id="wrapper">
		<table class="tables">
			<tr>
				<th class="tableHeader">Voorstelling:<br></th>
				<th class="tableContent">${voorstelling.titel}<br></th>
			</tr>
			<tr>
				<td class="tableHeader">Uitvoerders:<br></td>
				<td class="tableContent">${voorstelling.uitvoerders}</td>
			</tr>
			<tr>
				<td class="tableHeader">Datum:<br></td>
				<td class="tableContent">${voorstelling.datum}</td>
			</tr>
			<tr>
				<td class="tableHeader">Prijs:</td>
				<td class="tableContent">€${voorstelling.prijs}</td>
			</tr>
			<tr>
				<td class="tableHeader">Vrije plaatsen:</td>
				<td class="tableContent">${voorstelling.vrijeplaatsen}</td>
			</tr>
			<c:if test='${voorstelling.vrijeplaatsen > 0}'>
				<tr>
					<td class="tableHeader">Plaatsen:</td>
					<td class="tableContent">${error}
						<form method='post' action="voorstelling.htm">
							<input name="plaatsen" value='${plaatsen}'></input> <input
								name="id" value="${voorstelling.id}" hidden="true" />
							<button type="submit" id="shoppingcart">Reserveren</button>
						</form>
					</td>
				</tr>
			</c:if>
		</table>
	</div>
</body>
</html>