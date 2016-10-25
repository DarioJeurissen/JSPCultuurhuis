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

	<h1 id="pageTitle">Het Cultuurhuis:voorstellingen</h1>
	<nav>
		<ul>
			<li><a href='<c:url value="/"/>'>Voorstellingen</a></li>
			<c:if test='${sessionScope.mandje != null}'>
			<li><a href='<c:url value="mandje.htm"/>'>Mandje</a></li>
			<li><a href='<c:url value="bevestigen.htm"/>'>Bevestigen</a></li>
			</c:if>
		</ul>
	</nav>
	<nav id="genres">
		<ul>
			<c:forEach var='entry' items='${genres}'>
				<c:if test='${id == entry.id}'>
					<li id="selected">${entry.naam}</li>
				</c:if>
				<c:if test='${id != entry.id}'>
					<li><a href="<c:url value='/index.htm?id=${entry.id}'/>">${entry.naam}</a></li>
				</c:if>

			</c:forEach>
		</ul>
	</nav>
	<div id="wrapper">
		<div id="genreDiv">
			<table class="tables">
			<tr>
				<th class="tableHeader">Datum</th>
				<th class="tableHeader">Titel</th>
				<th class="tableHeader">Uitvoerders</th>
				<th class="tableHeader">Prijs</th>
				<th class="tableHeader">Vrije Plaatsen</th>
				<th class="tableHeader">Reserveren</th>
			</tr>

			<c:forEach var='v' items='${voorstellingen}'>
				<tr>
					<td class="tableContent">${v.datum}</td>
					<td class="tableContent">${v.titel}</td>
					<td class="tableContent">${v.uitvoerders}</td>
					<td class="tableContent">€${v.prijs}</td>
					<td class="tableContent">${v.vrijeplaatsen}</td>
					<td class="tableContent">
						<c:if test='${v.vrijeplaatsen > 0}'>
							<a href="<c:url value='/voorstelling.htm?id=${v.id}'/>">Reserveren</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</div>
</body>
</html>