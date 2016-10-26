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
		<h1 id="pageTitle">Het Cultuurhuis:Klaar</h1>
		<c:if test="${not empty success}">
		<h2>Gelukte reservatie's</h2>
		<table class="tables">
			<tr>
				<th class="tableHeader">Voorstelling</th>
				<th class="tableHeader">Datum</th>
				<th class="tableHeader">Plaatsen</th>
			</tr>
			<c:forEach var='entry' items='${success}'>
				<tr>
					<td class="tableContent">${entry.key.titel}</td>
					<td class="tableContent">${entry.key.datum}</td>
					<td class="tableContent">${entry.value}</td>
				</tr>
			</c:forEach>
		</table>
		</c:if>
		<c:if test="${not empty errors}">
		<h2>Mislukte reservatie's</h2>
		<table class="tables">
			<tr>
				<th class="tableHeader">Voorstelling</th>
				<th class="tableHeader">Datum</th>
				<th class="tableHeader">Plaatsen</th>
			</tr>
			<c:forEach var='entry' items='${success}'>
				<tr>
					<td class="tableContent">${entry.key.titel}</td>
					<td class="tableContent">${entry.key.datum}</td>
					<td class="tableContent">${entry.value}</td>
				</tr>
			</c:forEach>
		</table>
		</c:if>
	</div>
</body>
</html>