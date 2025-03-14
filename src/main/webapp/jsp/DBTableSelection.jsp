<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<title>QA Data Verification</title>
<link rel="stylesheet" type="text/css" href="../test.css" />
<link rel='stylesheet' type='text/css' href='../mms.css' />
</head>

<body>
<br />

<form id="params" name="params" method="post" action="ColumnSelection">
<table align="center" border class="alerts" style='width: 50%'>
	<tr class="alertHd">
		<td colspan='10' style="font-size: 12">Table Selection</td>
	</tr>
	<tr class="alertHd">
		<td colspan='10'>
		    <input type="button" value="Back" onClick="javascript:history.go(-1);">
			<input type="submit" value="Next">
		</td>
	</tr>
	<tr class="alertHd">
		<td><b>N�</b></td>
		<td><b>Table Type</b></td>
		<td><b>Table Cat</b></td>
		<td><b>Table Scheme</b></td>
		<td> <b>Table Name</b></td>
		<td><b>Table Type</b></td>
		<td><b>Table Cat</b></td>
		<td><b>Table Scheme</b></td>
		<td> <b>Table Name</b></td>
		<td> <b>Compare</b></td>
	</tr>
	<c:set var="row" value="0" />
	<c:forEach var="pos" items="${MODEL.modelTables}">
		<tr class="alertBod">
			<c:set var="row" value="${row+1}" />
			<td class="rowNumber"><c:out value="${row}" escapeXml="true" /></td>
			<td class="ids"><c:out value="${pos.tableType}"
				escapeXml="true" /></td>
			<td><c:out value="${pos.tableCat}"
				escapeXml="true" /></td>
			<td><c:out value="${pos.tableScheme}"
				escapeXml="true" /></td>
			<td><c:out value="${pos.tableName}"
				escapeXml="true" /></td>
			<td class="ids"><c:out value="${pos.tableGrantor}"
				escapeXml="true" /></td>
			<td><c:out value="${pos.tableGrantee}"
				escapeXml="true" /></td>
			<td><c:out value="${pos.tablePriv}"
				escapeXml="true" /></td>
			<td><c:out value="${pos.tableIsGrantable}"
				escapeXml="true" /></td>
			<td><input type="radio" name="tables"
				value="${pos.tableName}"></td>
		</tr>
	</c:forEach>
	<tr class="alertHd">
		<td colspan='10'>
		    <input type="button" value="Back" onClick="javascript:history.go(-1);">
			<input type="submit" value="Next">
		</td>
	</tr>
</table>
</form>

<br />

</body>
</html>
