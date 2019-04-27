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
<SCRIPT LANGUAGE="JavaScript">
<!-- Begin
function checkAll(field)
{
for (i = 0; i < field.length; i++)
	field[i].checked = true ;
}

function uncheckAll(field)
{
for (i = 0; i < field.length; i++)
	field[i].checked = false ;
}
//  End -->
</script>
</head>

<body>
<br />

<form id="params" name="params" method="post" action="ValidateSelection">
<table align="center" border class="alerts" style='width: 50%'>
	<tr class="alertHd">
		<td colspan='10' style="width: 12">Column Selection</td>
	</tr>
	<tr class="alertHd">
		<td colspan='9'>
		    <input type="button" value="Back" onClick="javascript:history.go(-1);">
		    <input type="button" value="Check All" onClick="checkAll(document.params.list)">
			<input type="button" value="Uncheck All" onClick="uncheckAll(document.params.list)">
			<input type="submit" value="Next">
		</td>
	</tr>
	<tr class="alertHd">
		<td><b>N°</b></td>
		<td><b>Column Name</b></td>
		<td><b>Column Type</b></td>
		<td><b>Size</b></td>
		<td><b>Nullable</b></td>
		<td><b>Position</b></td>
		<!-- <td <b>Primary Key</b></td> -->
		<td> <b>Include</b></td>
		<td> <b>Group By</b></td>
		<td> <b>Calculate Diff</b></td>
	</tr>
	<c:set var="row" value="0" />
	<c:forEach var="pos" items="${COLUMNS}">
		<tr class="alertBod">
			<c:set var="row" value="${row+1}" />
			<td class="rowNumber"><c:out value="${row}" escapeXml="true" /></td>
			<td class="ids"><c:out value="${pos.columnName}"
				escapeXml="true" /></td>
			<td><c:out value="${pos.columnType}"
				escapeXml="true" /></td>
			<td><c:out value="${pos.size}"
				escapeXml="true" /></td>
			<td><c:out value="${pos.nullable}"
				escapeXml="true" /></td>
			<td><c:out value="${pos.position}"
				escapeXml="true" /></td>
			<!--<td><input type="checkbox" name="pks"
				value="${pos.columnName}"></td> -->
			<td><input type="checkbox" name="list"
				value="${pos.columnName}"></td>
			<td><input type="checkbox" name="groupby"
				value="${pos.columnName}"></td>
			<td><input type="checkbox" name="diff" value="${pos.columnName}"
				<c:if test="${pos.numeric==false}"> disabled </c:if> /></td>
		</tr>
	</c:forEach>
	<tr class="alertHd">
		<td colspan='9'>
		    <input type="button" value="Back" onClick="javascript:history.go(-1);">
		    <input type="button" value="Check All" onClick="checkAll(document.params.list)">
			<input type="button" value="Uncheck All" onClick="uncheckAll(document.params.list)">
			<input type="submit" value="Next">
		</td>
	</tr>
</table>
</form>

<br />

</body>
</html>
